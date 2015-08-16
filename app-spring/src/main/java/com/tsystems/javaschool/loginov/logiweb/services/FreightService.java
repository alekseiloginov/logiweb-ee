package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.dao.FreightDao;
import com.tsystems.javaschool.loginov.logiweb.dao.LocationDao;
import com.tsystems.javaschool.loginov.logiweb.dao.OrderDao;
import com.tsystems.javaschool.loginov.logiweb.dao.WaypointDao;
import com.tsystems.javaschool.loginov.logiweb.models.Freight;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
import com.tsystems.javaschool.loginov.logiweb.models.Order;
import com.tsystems.javaschool.loginov.logiweb.models.Waypoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service class that uses Hibernate DAO classes to work with Freight objects.
 */
@Service
public class FreightService {
    private FreightDao freightDao;
    private LocationDao locationDao;
    private WaypointDao waypointDao;
    private OrderDao orderDao;
    private static final String LOADING = "loading";
    private static final String UNLOADING = "unloading";

    public void setFreightDao(FreightDao freightDao) {
        this.freightDao = freightDao;
    }

    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public void setWaypointDao(WaypointDao waypointDao) {
        this.waypointDao = waypointDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Transactional
    public Freight addFreight(Freight freight) {

        Freight savedFreight = this.freightDao.addFreight(freight);

        // Now we need to save waypoints associated with the freight's loading and unloading

        // get a location object of the freight LOADING
        Location dbLoadingLocation = locationDao.getLocationByCity(freight.getLoading());

        // get a location object of the freight UNLOADING
        Location dbUnloadingLocation = locationDao.getLocationByCity(freight.getUnloading());

        // save associated waypoints
        Waypoint waypoint = new Waypoint(LOADING, dbLoadingLocation, savedFreight);
        waypointDao.addWaypoint(waypoint);

        waypoint = new Waypoint(UNLOADING, dbUnloadingLocation, savedFreight);
        waypointDao.addWaypoint(waypoint);

        // add loading and unloading cities to freight object to ease JTable fields parsing
        savedFreight.setLoading(freight.getLoading());
        savedFreight.setUnloading(freight.getUnloading());

        return savedFreight;
    }

    @Transactional
    public Freight updateFreight(Freight freight) {
        Freight freightToUpdate = freightDao.getFreightById(freight.getId());

        freightToUpdate.setName(freight.getName());
        freightToUpdate.setWeight(freight.getWeight());
        freightToUpdate.setLoading(freight.getLoading());
        freightToUpdate.setUnloading(freight.getUnloading());
        freightToUpdate.setStatus(freight.getStatus());

        return this.freightDao.updateFreight(freightToUpdate);
    }

    @Transactional
    public void updateFreightStatus(Freight freight) {
        Freight dbFreight = freightDao.getFreightById(freight.getId());
        dbFreight.setStatus(freight.getStatus());
        this.freightDao.updateFreightStatus(dbFreight);
    }

    @Transactional
    public List<Freight> listFreights() {
        List<Freight> freightList = this.freightDao.listFreights();

        for (Freight freight : freightList) {
            // get a waypoint object of the freight LOADING
            Waypoint dbLoadingWaypoint = waypointDao.getWaypointByFreightIdAndOperation(freight.getId(), LOADING);
            // get a waypoint object of the freight UNLOADING
            Waypoint dbLUnloadingWaypoint = waypointDao.getWaypointByFreightIdAndOperation(freight.getId(), UNLOADING);

            freight.setLoading(dbLoadingWaypoint.getLocation().getCity());
            freight.setUnloading(dbLUnloadingWaypoint.getLocation().getCity());
        }
        return freightList;
    }

    @Transactional
    public Freight getFreightById(int id) {
        return this.freightDao.getFreightById(id);
    }

    @Transactional
    public void removeFreight(int id) {
        this.freightDao.removeFreight(id);
    }

    @Transactional
    public String getFreightOptions(int orderId, String city) {
        // get a location ID of the chosen city
        int locationId = locationDao.getLocationByCity(city).getId();
        // get all waypoints connected with this city
        List cityWaypointList = waypointDao.getWaypointListByLocationId(locationId);
        // fetch all orders from the database to check them for the chosen waypoints
        List orderList = orderDao.listOrders();
        // Fetch the order with the given orderID from the database
        Order chosenOrder = orderDao.getOrderById(orderId);

        // add all city waypoints to the validCityWaypointSet
        Set<Waypoint> validCityWaypointSet = Collections.newSetFromMap(new ConcurrentHashMap<Waypoint, Boolean>());
        Waypoint cityWaypointOption;
        Order order;

        for (Object cityWaypointObject : cityWaypointList) {
            cityWaypointOption = (Waypoint) cityWaypointObject;
            validCityWaypointSet.add(cityWaypointOption);

            // if other orders already contain this cityWaypointOption, remove it from the validCityWaypointSet
            for (Object orderObject : orderList) {
                order = (Order) orderObject;

                if (order.getWaypoints().contains(cityWaypointOption)) {
                    validCityWaypointSet.remove(cityWaypointOption);
                    break;
                }
            }
        }

        // get sum of all freight weights (loaded in the same city) that are already assign to the order's truck
        Set<Waypoint> chosenOrderWaypointSet = chosenOrder.getWaypoints();
        int orderAssignedFreightsWeight = 0;

        for (Waypoint chosenOrderWaypoint : chosenOrderWaypointSet) {
            if (chosenOrderWaypoint.getLocation().getCity().equals(city)
                    && LOADING.equals(chosenOrderWaypoint.getOperation())) {
                orderAssignedFreightsWeight += chosenOrderWaypoint.getFreight().getWeight();
            }
        }

        return createJson(checkFreeSpace(chosenOrder, orderAssignedFreightsWeight, validCityWaypointSet));
    }

    /**
     * Checks that if operation is loading and freight status is 'prepared' - there's enough space for this freight.
     *
     * @return validCityWaypointSet
     */
    private Set<Waypoint> checkFreeSpace(Order chosenOrder, int orderAssignedFreightsWeight, Set<Waypoint> validCityWaypointSet) {
        int orderTruckCapacityInTones = chosenOrder.getTruck().getCapacity();
        int freeFreightWeight = (orderTruckCapacityInTones * 1000) - orderAssignedFreightsWeight;  // convert to kilos
        int freightWeight;

        for (Waypoint validCityWaypointOption : validCityWaypointSet) {
            freightWeight = validCityWaypointOption.getFreight().getWeight();

            if (!"prepared".equals(validCityWaypointOption.getFreight().getStatus()) ||
                    LOADING.equals(validCityWaypointOption.getOperation()) &&
                            "prepared".equals(validCityWaypointOption.getFreight().getStatus()) &&
                            freeFreightWeight < freightWeight) {

                validCityWaypointSet.remove(validCityWaypointOption);
            }
        }
        return validCityWaypointSet;
    }

    /**
     * Creates a JSON string from the valid city waypoint set.
     *
     * @param validCityWaypointSet
     * @return freightOptionJSONList
     */
    private String createJson(Set<Waypoint> validCityWaypointSet) {
        int optionCount = 0;
        String freightOptionJSONList = "[";

        if (validCityWaypointSet.isEmpty()) {
            freightOptionJSONList += "]";

        } else {
            for (Waypoint validCityWaypoint : validCityWaypointSet) {
                freightOptionJSONList += "{\"DisplayText\":\"";
                freightOptionJSONList += validCityWaypoint.getFreight().getName();
                freightOptionJSONList += "\",\"Value\":\"";
                freightOptionJSONList += validCityWaypoint.getFreight().getName();
                ++optionCount;

                if (optionCount < validCityWaypointSet.size()) {
                    freightOptionJSONList += "\"},";
                } else {
                    freightOptionJSONList += "\"}]";
                }
            }
        }

        return freightOptionJSONList;
    }
}
