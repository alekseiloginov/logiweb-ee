package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.dao.LocationDao;
import com.tsystems.javaschool.loginov.logiweb.dao.OrderDao;
import com.tsystems.javaschool.loginov.logiweb.dao.TruckDao;
import com.tsystems.javaschool.loginov.logiweb.exceptions.PlateNumberIncorrectException;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
import com.tsystems.javaschool.loginov.logiweb.models.Order;
import com.tsystems.javaschool.loginov.logiweb.models.Truck;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service class that uses Hibernate DAO classes to work with Truck objects.
 */
@Service
public class TruckService {
    private TruckDao truckDao;
    private LocationDao locationDao;
    private OrderDao orderDao;
    private String plateNumberPattern = "^[a-zA-Z]{2}[0-9]{5}$";

    public void setTruckDao(TruckDao truckDao) {
        this.truckDao = truckDao;
    }

    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Transactional
    public Truck addTruck(Truck truck) throws PlateNumberIncorrectException {

        if (!truck.getPlateNumber().matches(plateNumberPattern)) {
            throw new PlateNumberIncorrectException("Plate number should contain 2 letters and 5 digits");
        }

        Location location = locationDao.getLocationByCity(truck.getLocation().getCity());
        truck.setLocation(location);

        return this.truckDao.addTruck(truck);
    }

    @Transactional
    public Truck updateTruck(Truck truck) throws PlateNumberIncorrectException {

        if (!truck.getPlateNumber().matches("^[a-zA-Z]{2}[0-9]{5}$")) {
            throw new PlateNumberIncorrectException("Plate number should contain 2 letters and 5 digits");
        }

        Truck truckToUpdate = truckDao.getTruckById(truck.getId());
        truckToUpdate.setPlateNumber(truck.getPlateNumber());
        truckToUpdate.setDriverNumber(truck.getDriverNumber());
        truckToUpdate.setCapacity(truck.getCapacity());
        truckToUpdate.setDrivable(truck.getDrivable());

        Location location = locationDao.getLocationByCity(truck.getLocation().getCity());
        truckToUpdate.setLocation(location);

        return this.truckDao.updateTruck(truckToUpdate);
    }

    @Transactional
    public List<Truck> listTrucks() {
        return this.truckDao.listTrucks();
    }

    @Transactional
    public Truck getTruckById(int id) {
        return this.truckDao.getTruckById(id);
    }

    @Transactional
    public void removeTruck(int id) {
        this.truckDao.removeTruck(id);
    }

    @Transactional
    public String getTruckOptions() {

        // get all drivable trucks
        List drivableTruckList = truckDao.getDrivableTrucks();
        // get all orders to check for occupied trucks
        List orderList = orderDao.listOrders();

        // check all drivable trucks if they are occupied with any other order
        Set<Truck> validTruckSet = new HashSet<>();
        Truck truck;
        Order order;

        for (Object truckObject : drivableTruckList) {
            truck = (Truck) truckObject;
            validTruckSet.add(truck);

            for (Object orderObject : orderList) {
                order = (Order) orderObject;

                if (order.getTruck().equals(truck)) {
                    validTruckSet.remove(truck);
                    break;
                }
            }
        }

        // Creating a JSON string
        int optionCount = 0;
        String truckOptionJSONList = "[";

        if (validTruckSet.isEmpty()) {
            truckOptionJSONList += "]";

        } else {

            for (Truck validTruck : validTruckSet) {
                truckOptionJSONList += "{\"DisplayText\":\"";
                truckOptionJSONList += validTruck.getPlateNumber();
                truckOptionJSONList += "\",\"Value\":\"";
                truckOptionJSONList += validTruck.getPlateNumber();
                ++optionCount;

                if (optionCount < validTruckSet.size()) {
                    truckOptionJSONList += "\"},";
                } else {
                    truckOptionJSONList += "\"}]";
                }
            }
        }

        return truckOptionJSONList;
    }
}
