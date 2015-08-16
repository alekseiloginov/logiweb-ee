package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.dao.*;
import com.tsystems.javaschool.loginov.logiweb.exceptions.PlateNumberNotFoundException;
import com.tsystems.javaschool.loginov.logiweb.models.*;
import com.tsystems.javaschool.loginov.logiweb.utils.Gmaps;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Service class that uses Hibernate DAO classes to work with Driver objects.
 */
@Service
public class DriverService {
    private DriverDao driverDao;
    private LocationDao locationDao;
    private TruckDao truckDao;
    private OrderDao orderDao;
    private DriverStatusChangeDao driverStatusChangeDao;

    public void setDriverDao(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public void setTruckDao(TruckDao truckDao) {
        this.truckDao = truckDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setDriverStatusChangeDao(DriverStatusChangeDao driverStatusChangeDao) {
        this.driverStatusChangeDao = driverStatusChangeDao;
    }

    @Transactional
    public Driver addDriver(Driver driver) throws PlateNumberNotFoundException {

        // Password encryption using BCrypt
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(driver.getPassword());

        driver.setPassword(encryptedPassword);

        Location location = locationDao.getLocationByCity(driver.getLocation().getCity());
        driver.setLocation(location);

        Truck truck = truckDao.getTruckByPlateNumber(driver.getTruck().getPlateNumber());

        // plate number can be empty, we don't need to throw exception in that case
        if (!driver.getTruck().getPlateNumber().isEmpty() && truck == null) {
            throw new PlateNumberNotFoundException("Entered plate number is not found in the database");
        }
        driver.setTruck(truck);

        return this.driverDao.addDriver(driver);
    }

    @Transactional
    public Driver updateDriver(Driver driver) throws PlateNumberNotFoundException {

        // Password encryption using BCrypt
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(driver.getPassword());

        Driver driverToUpdate = driverDao.getDriverById(driver.getId());
        driverToUpdate.setName(driver.getName());
        driverToUpdate.setSurname(driver.getSurname());
        driverToUpdate.setEmail(driver.getEmail());
        driverToUpdate.setPassword(encryptedPassword);
        driverToUpdate.setWorkedHours(driver.getWorkedHours());
        driverToUpdate.setStatus(driver.getStatus());

        Location location = locationDao.getLocationByCity(driver.getLocation().getCity());
        driverToUpdate.setLocation(location);

        Truck truck = truckDao.getTruckByPlateNumber(driver.getTruck().getPlateNumber());

        // plate number can be empty, we don't need to throw exception in that case
        if (!driver.getTruck().getPlateNumber().isEmpty() && truck == null) {
            throw new PlateNumberNotFoundException("Entered plate number is not found in the database");
        }
        driverToUpdate.setTruck(truck);

        return this.driverDao.updateDriver(driverToUpdate);
    }

    @Transactional
    public void updateDriverStatusAndWorkedHours(Driver driver) {
        String newDriverStatus = driver.getStatus();
        Driver dbDriver = driverDao.getDriverById(driver.getId());
        String lastDriverStatus = dbDriver.getStatus();

        // if statuses are the same or last one is FREE, don't update driver's worked hours
        if (!newDriverStatus.equalsIgnoreCase(lastDriverStatus) && !"free".equalsIgnoreCase(lastDriverStatus)) {

            DriverStatusChange lastDriverStatusChange =
                    driverStatusChangeDao.getDriverStatusChange(dbDriver, lastDriverStatus);

            long statusLastModifiedTimeInMillis = lastDriverStatusChange.getLastModifiedTime().getTime();
            long newWorkedHoursInMillis = (new Date()).getTime() - statusLastModifiedTimeInMillis;
            int newWorkedHours = (int) TimeUnit.MILLISECONDS.toHours(newWorkedHoursInMillis);
            dbDriver.setWorkedHours(dbDriver.getWorkedHours() + newWorkedHours);
        }

        dbDriver.setStatus(newDriverStatus);

        this.driverDao.updateDriverStatusAndWorkedHours(dbDriver);
    }

    @Transactional
    public List<Driver> listDrivers() {
        return this.driverDao.listDrivers();
    }

    @Transactional
    public Driver getDriverById(int id) {
        return this.driverDao.getDriverById(id);
    }

    @Transactional
    public void removeDriver(int id) {
        this.driverDao.removeDriver(id);
    }

    @Transactional
    public Set<Driver> getAllOrderDrivers(int orderID) {
        Order order = orderDao.getOrderById(orderID);
        return order.getDrivers();
    }

    @Transactional
    public Driver saveOrderDriver(int orderID, String driverEmail) {
        Order order = orderDao.getOrderById(orderID);
        Driver driver = driverDao.getDriverByEmail(driverEmail);

        // assign an order truck to the driver and update in the database
        driver.setTruck(order.getTruck());
        driverDao.updateDriver(driver);

        // assign an driver to the order and update in the database
        order.getDrivers().add(driver);
        orderDao.updateOrder(order);

        return driver;
    }

    @Transactional
    public String getDriverOptions(int orderID) {

        Order order = orderDao.getOrderById(orderID);

        String mainCity = order.getTruck().getLocation().getCity();
        int orderTruckDriverNumber = order.getTruck().getDriverNumber();
        int orderOccupiedTruckDriverNumber = order.getDrivers().size();
        int orderTruckDriverLeft = orderTruckDriverNumber - orderOccupiedTruckDriverNumber;
        String freeDriverStatus = "free";
        List driversInCity = null;

        // if there is a space in the truck's shift for another driver
        if (orderTruckDriverLeft > 0) {

            // get a location ID of the truck's city
            int locationID = locationDao.getLocationByCity(mainCity).getId();

            // get all FREE drivers from the same city
            driversInCity = driverDao.getFreeDriversInCity(locationID, freeDriverStatus);
        }

        Set<Driver> validDriverSet = new HashSet<>();
        if (driversInCity != null && order.getWaypoints() != null) {
            validDriverSet = getValidDriverSet(order, mainCity, driversInCity, validDriverSet);
        }

        return createJson(validDriverSet);
    }

    /**
     * Gets all valid drivers fot the order.
     *
     * @param order
     * @param mainCity
     * @param driversInCity
     * @param validDriverSet
     * @return
     */
    private Set<Driver> getValidDriverSet(Order order, String mainCity, List driversInCity, Set<Driver> validDriverSet) {
        Driver driverInCity;

        // get all cities associated with this order and put them to the city set (excluding the origin city)
        Set<Waypoint> orderWaypointSet = order.getWaypoints();
        Set<String> citySet = new HashSet<>();

        for (Waypoint orderWaypoint : orderWaypointSet) {

            if (!orderWaypoint.getLocation().getCity().equals(mainCity)) {
                citySet.add( orderWaypoint.getLocation().getCity() );
            }
        }

        long durationInSeconds = (new Gmaps()).calculateTripDurationInSeconds(citySet, mainCity);

        long orderDurationInHoursLong = Math.round(durationInSeconds / 60.0 / 60.0);
        int orderDurationInHoursInThisMonthInt = (int) orderDurationInHoursLong;

        // Check month changes during the order, start - this moment (if it start later - it'll pass too)

        // Start of the order DATE:TIME
        Date orderStartDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(orderStartDate);
        int orderStartMonth = cal.get(Calendar.MONTH);
        long orderStartLong = orderStartDate.getTime();

        // Start of a new month DATE:TIME
        Date date = new Date();
        cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, orderStartMonth + 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date startOfTheNextMonthDate = cal.getTime();
        long startOfTheNextMonthLong = startOfTheNextMonthDate.getTime();
        long diffInMilliesLong = startOfTheNextMonthLong - orderStartLong;
        long diffInHoursLong = TimeUnit.HOURS.convert(diffInMilliesLong, TimeUnit.MILLISECONDS);

        if (diffInHoursLong < orderDurationInHoursLong) {
            orderDurationInHoursInThisMonthInt = (int) diffInHoursLong;
        }

        for (Object driverObject : driversInCity) {
            driverInCity = (Driver) driverObject;

            // check if the driver is FREE + driver's truck is NULL + driver's worked hours in this month <= 176
            if (driverInCity.getTruck() == null &&
                    (driverInCity.getWorkedHours() + orderDurationInHoursInThisMonthInt) <= 176) {

                validDriverSet.add(driverInCity);
            }
        }

        return validDriverSet;
    }

    /**
     * Creates a JSON string from the valid driver set.
     *
     * @param validDriverSet
     * @return driverOptionJSONList
     */
    private String createJson(Set<Driver> validDriverSet) {
        int optionCount = 0;
        String driverOptionJSONList = "[";

        if (validDriverSet.isEmpty()) {
            driverOptionJSONList += "]";

        } else {

            for (Driver validDriver : validDriverSet) {
                driverOptionJSONList += "{\"DisplayText\":\"";
                driverOptionJSONList += validDriver.getEmail();
                driverOptionJSONList += "\",\"Value\":\"";
                driverOptionJSONList += validDriver.getEmail();
                ++optionCount;

                if (optionCount < validDriverSet.size()) {
                    driverOptionJSONList += "\"},";
                } else {
                    driverOptionJSONList += "\"}]";
                }
            }
        }
        return driverOptionJSONList;
    }
}
