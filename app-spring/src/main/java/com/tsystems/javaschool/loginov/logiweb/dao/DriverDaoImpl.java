package com.tsystems.javaschool.loginov.logiweb.dao;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsRoute;
import com.tsystems.javaschool.loginov.logiweb.exceptions.DuplicateEntryException;
import com.tsystems.javaschool.loginov.logiweb.exceptions.PlateNumberNotFoundException;
import com.tsystems.javaschool.loginov.logiweb.models.*;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Hibernate specific DAO implementation for drivers.
 */
@Repository
public class DriverDaoImpl implements DriverDao {
    private static final Logger LOG = Logger.getLogger(DriverDaoImpl.class);
    private static final String ORDER_BY_ID_QUERY = "from Order where id = :orderID";
    private static final String ORDER_ID = "orderID";

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Driver addDriver(Driver driver) throws PlateNumberNotFoundException, DuplicateEntryException {
        Session session = this.sessionFactory.getCurrentSession();

        // Email database check for uniqueness
        Query driverCheckQuery = session.createQuery("from Driver where email = :email");
        driverCheckQuery.setString("email", driver.getEmail());
        Driver checkedDriver = (Driver) driverCheckQuery.uniqueResult();

        if (checkedDriver != null) {
            throw new DuplicateEntryException("Email is unique and this one is already present in the database",
                    "Duplicate entry");
        }

        // Password encryption using MD5
        String encryptedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(driver.getPassword().getBytes());
            byte[] bytes = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();

            for (byte aByte : bytes) {
                stringBuilder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            encryptedPassword = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            LOG.error("NoSuchAlgorithmException", e);
        }

        // DB save
        driver.setPassword(encryptedPassword);

        Query locationQuery = session.createQuery("from Location where city = :city");
        locationQuery.setString("city", driver.getLocation().getCity());
        Location dbLocation = (Location) locationQuery.uniqueResult();
        driver.setLocation(dbLocation);

        Query truckQuery = session.createQuery("from Truck where plate_number = :plate_number");
        truckQuery.setString("plate_number", driver.getTruck().getPlate_number());
        Truck dbTruck = (Truck) truckQuery.uniqueResult();

        // plate number can be empty, we don't need to throw exception in that case
        if (!driver.getTruck().getPlate_number().isEmpty() && dbTruck == null) {
            throw new PlateNumberNotFoundException("Entered plate number is not found in the database",
                    "Plate number not found");
        }
        driver.setTruck(dbTruck);

        int savedDriverID = (int) session.save(driver);

        Query driverQuery = session.createQuery("from Driver where id = :savedDriverID");
        driverQuery.setInteger("savedDriverID", savedDriverID);
        Driver savedDriver = (Driver) driverQuery.uniqueResult();
        LOG.info("Driver saved successfully, Driver details=" + savedDriver);

        return savedDriver;
    }

    @Override
    public Driver updateDriver(Driver driver) throws PlateNumberNotFoundException, DuplicateEntryException {
        Session session = this.sessionFactory.getCurrentSession();

        // Password encryption using MD5
        String encryptedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(driver.getPassword().getBytes());
            byte[] bytes = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();

            for (byte aByte : bytes) {
                stringBuilder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            encryptedPassword = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            LOG.error("NoSuchAlgorithmException", e);
        }

        // DB update
        Query driverQuery = session.createQuery("from Driver where id = :id");
        driverQuery.setInteger("id", driver.getId());
        Driver driverToUpdate = (Driver) driverQuery.uniqueResult();

        driverToUpdate.setName(driver.getName());
        driverToUpdate.setSurname(driver.getSurname());

        // if user changes driver's email, check if other drivers don't have it
        if (!driverToUpdate.getEmail().equals(driver.getEmail())) {
            Query driverEmailCheckQuery = session.createQuery("from Driver where email != :old_email");
            driverEmailCheckQuery.setString("old_email", driverToUpdate.getEmail());
            List otherDrivers = driverEmailCheckQuery.list();

            if (otherDrivers != null) {
                for (Object otherDriverObject : otherDrivers) {
                    Driver otherDriver = (Driver) otherDriverObject;

                    if (otherDriver.getEmail().equals(driver.getEmail())) {
                        throw new DuplicateEntryException("Email is unique and this one is already present in database",
                                "Duplicate entry");
                    }
                }
            }
        }

        driverToUpdate.setEmail(driver.getEmail());
        driverToUpdate.setPassword(encryptedPassword);
        driverToUpdate.setWorked_hours(driver.getWorked_hours());
        driverToUpdate.setStatus(driver.getStatus());

        Query locationQuery = session.createQuery("from Location where city = :city");
        locationQuery.setString("city", driver.getLocation().getCity());
        Location dbLocation = (Location) locationQuery.uniqueResult();
        driverToUpdate.setLocation(dbLocation);

        Query truckQuery = session.createQuery("from Truck where plate_number = :plate_number");
        truckQuery.setString("plate_number", driver.getTruck().getPlate_number());
        Truck dbTruck = (Truck) truckQuery.uniqueResult();

        // plate number can be empty, we don't need to throw exception in that case
        if (!driver.getTruck().getPlate_number().isEmpty() && dbTruck == null) {
            throw new PlateNumberNotFoundException("Entered plate number is not found in the database",
                    "Plate number not found");
        }
        driverToUpdate.setTruck(dbTruck);

        session.update(driverToUpdate);

        Driver updatedDriver = (Driver) driverQuery.uniqueResult();
        LOG.info("Driver updated successfully, Driver details=" + updatedDriver);

        return updatedDriver;
    }

    @Override
    public void updateDriverStatusAndWorkedHours(Driver driver) {
        Session session = this.sessionFactory.getCurrentSession();
        int driverId = driver.getId();
        String newDriverStatus = driver.getStatus();

        Driver dbDriver = (Driver) session.createCriteria(Driver.class)
                .add(Restrictions.eq("id", driverId))
                .uniqueResult();

        // update driver's worked hours
        String lastDriverStatus = dbDriver.getStatus();

        // if statuses are the same or last one is FREE, don't update driver's worked hours
        if (!newDriverStatus.equalsIgnoreCase(lastDriverStatus) && !"free".equalsIgnoreCase(lastDriverStatus)) {

            DriverStatusChange lastDriverStatusChange =
                    (DriverStatusChange) session.createCriteria(DriverStatusChange.class)
                            .add(Restrictions.eq("driver", dbDriver))
                            .add(Restrictions.eq("status", lastDriverStatus))
                            .uniqueResult();

            long statusLastModifiedTimeInMillis = lastDriverStatusChange.getLast_modified_time().getTime();

            long newWorkedHoursInMillis = (new Date()).getTime() - statusLastModifiedTimeInMillis;

            int newWorkedHours = (int) TimeUnit.MILLISECONDS.toHours(newWorkedHoursInMillis);

            dbDriver.setWorked_hours(dbDriver.getWorked_hours() + newWorkedHours);
        }



        // update driver status
        dbDriver.setStatus(newDriverStatus);

        session.update(dbDriver);
        LOG.info("Driver status updated successfully, Driver details=" + dbDriver);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Driver> listDrivers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Driver> driverList = session.createQuery("from Driver").list();
        for (Driver driver : driverList) {
            LOG.info("Driver list::" + driver);
        }
        return driverList;
    }

    @Override
    public Driver getDriverById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Driver driver = (Driver) session.load(Driver.class, new Integer(id));
        LOG.info("Driver loaded successfully, Driver details=" + driver);
        return driver;
    }

    @Override
    public void removeDriver(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Driver driver = (Driver) session.load(Driver.class, new Integer(id));
        if (driver != null){
            session.delete(driver);
        }
        LOG.info("Driver deleted successfully, Driver details=" + driver);
    }

    /**
     * Gets all drivers for the provided order ID from the database and returns them as a set of drivers.
     */
    @Override
    public Set<Driver> getAllOrderDrivers(int orderID) {
        Session session = sessionFactory.getCurrentSession();
        Query orderQuery = session.createQuery(ORDER_BY_ID_QUERY);
        orderQuery.setInteger(ORDER_ID, orderID);
        Order order = (Order) orderQuery.uniqueResult();

        return order.getDrivers();
    }

    /**
     * Saves a driver to the database and returns saved object.
     */
    @Override
    public Driver saveOrderDriver(int orderID, String driverEmail) {
        Session session = sessionFactory.getCurrentSession();

        Query orderQuery = session.createQuery(ORDER_BY_ID_QUERY);
        orderQuery.setInteger(ORDER_ID, orderID);
        Order order = (Order) orderQuery.uniqueResult();

        Query driverQuery = session.createQuery("from Driver where email = :driverEmail");
        driverQuery.setString("driverEmail", driverEmail);
        Driver driver = (Driver) driverQuery.uniqueResult();

        // assign an order truck to the driver and update in the database
        driver.setTruck(order.getTruck());
        session.update(driver);

        // assign an driver to the order and update in the database
        Set<Driver> driverSet = order.getDrivers();
        driverSet.add(driver);
        order.setDrivers(driverSet);
        session.update(order);

        return driver;
    }

    /**
     * Fetches all valid driver options from the database and returns them as a JSON string suitable for JTable.
     */
    @Override
    public String getDriverOptions(int orderID) {
        Session session = sessionFactory.getCurrentSession();

        // Fetch the order with the given orderID from the database
        Query orderQuery = session.createQuery(ORDER_BY_ID_QUERY);
        orderQuery.setInteger(ORDER_ID, orderID);
        Order order = (Order) orderQuery.uniqueResult();

        String orderTruckCity = order.getTruck().getLocation().getCity();
        int orderTruckDriverNumber = order.getTruck().getDriver_number();
        int orderOccupiedTruckDriverNumber = order.getDrivers().size();
        int orderTruckDriverLeft = orderTruckDriverNumber - orderOccupiedTruckDriverNumber;
        String freeDriverStatus = "free";
        List driversInCity = null;

        // if there is a space in the truck's shift for another driver
        if (orderTruckDriverLeft > 0) {

            // get a location object of the truck's city
            Query locationQuery = session.createQuery("from Location where city = :orderTruckCity");
            locationQuery.setString("orderTruckCity", orderTruckCity);
            Location dbLocation = (Location) locationQuery.uniqueResult();

            int locationID = dbLocation.getId();

            // get all FREE drivers from the same city
            Query driverQuery = session.createQuery("from Driver where location_id = :locationID and status =:status");
            driverQuery.setInteger("locationID", locationID);
            driverQuery.setString("status", freeDriverStatus);
            driversInCity = driverQuery.list();
        }

        Set<Driver> validDriverSet = new HashSet<>();
        Driver driverInCity;

        if (driversInCity != null && order.getWaypoints() != null) {

            // get all cities associated with this order and put them to the city set (excluding the origin city)
            Set<Waypoint> orderWaypointSet = order.getWaypoints();
            Set<String> citySet = new HashSet<>();

            for (Waypoint orderWaypoint : orderWaypointSet) {

                if (!orderWaypoint.getLocation().getCity().equals(orderTruckCity)) {
                    citySet.add( orderWaypoint.getLocation().getCity() );
                }
            }

            // create a string of collected cities to use in Google Maps API
            String waypointCities = "optimize:true";

            for (String city : citySet) {
                waypointCities += "|" + city;
            }

            // Google Maps API implementation

            GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyAPisnyi8SzMCy1NAZd7XMA6YBWYlFF9w4");
            DirectionsRoute[] routes = new DirectionsRoute[0];

            try {
                routes = DirectionsApi.newRequest(context)
                        .origin(orderTruckCity)                 // origin - city where the order's truck is located
                        .destination(orderTruckCity)
                        .optimizeWaypoints(true)
                        .waypoints(waypointCities)
                        .await();
            } catch (Exception e) {
                LOG.error("Problem with the routes request using Google Maps API.", e);
            }

            DirectionsLeg[] legs = routes[0].legs;
            long durationInSeconds = 0;

            for (DirectionsLeg leg : legs) {
                durationInSeconds += leg.duration.inSeconds;
            }

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
                        (driverInCity.getWorked_hours() + orderDurationInHoursInThisMonthInt) <= 176) {

                    validDriverSet.add(driverInCity);
                }
            }
        }

        // Creating a JSON string
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
