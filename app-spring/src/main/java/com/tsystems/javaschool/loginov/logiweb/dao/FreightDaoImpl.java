package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Freight;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
import com.tsystems.javaschool.loginov.logiweb.models.Order;
import com.tsystems.javaschool.loginov.logiweb.models.Waypoint;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Hibernate specific DAO implementation for freights.
 */
@Repository
public class FreightDaoImpl implements FreightDao {
    private static final Logger LOG = Logger.getLogger(FreightDaoImpl.class);
    private static final String LOADING = "loading";

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Freight addFreight(Freight freight) {
        Session session = this.sessionFactory.getCurrentSession();

        int savedFreightID = (int) session.save(freight);

        Query freightQuery = session.createQuery("from Freight where id = :savedFreightID");
        freightQuery.setInteger("savedFreightID", savedFreightID);
        Freight savedFreight = (Freight) freightQuery.uniqueResult();
        LOG.info("Freight saved successfully, Freight details=" + savedFreight);

        // Now we need to save waypoints associated with the freight's loading and unloading

        // get a location object of the freight LOADING
        Query locationQuery = session.createQuery("from Location where city = :location");
        locationQuery.setString("location", freight.getLoading());
        Location dbLoadingLocation = (Location) locationQuery.uniqueResult();

        // get a location object of the freight UNLOADING
        locationQuery.setString("location", freight.getUnloading());
        Location dbUnloadingLocation = (Location) locationQuery.uniqueResult();

        // save associated waypoints
        Waypoint waypoint = new Waypoint(LOADING, dbLoadingLocation, savedFreight);
        session.save(waypoint);

        waypoint = new Waypoint("unloading", dbUnloadingLocation, savedFreight);
        session.save(waypoint);

        // add loading and unloading cities to freight object to ease JTable fields parsing
        savedFreight.setLoading(freight.getLoading());
        savedFreight.setUnloading(freight.getUnloading());

        return savedFreight;
    }

    @Override
    public Freight updateFreight(Freight freight) {
        Session session = this.sessionFactory.getCurrentSession();

        Query freightQuery = session.createQuery("from Freight where id = :id");
        freightQuery.setInteger("id", freight.getId());
        Freight freightToUpdate = (Freight) freightQuery.uniqueResult();

        freightToUpdate.setName(freight.getName());
        freightToUpdate.setWeight(freight.getWeight());
        freightToUpdate.setLoading(freight.getLoading());
        freightToUpdate.setUnloading(freight.getUnloading());
        freightToUpdate.setStatus(freight.getStatus());

        session.update(freightToUpdate);

        Freight updatedFreight = (Freight) freightQuery.uniqueResult();
        LOG.info("Freight updated successfully, Freight details=" + updatedFreight);

        return updatedFreight;
    }

    @Override
    public void updateFreightStatus(Freight freight) {
        Session session = this.sessionFactory.getCurrentSession();
        int freightId = freight.getId();
        String newFreightStatus = freight.getStatus();

        Freight dbFreight = (Freight) session.createCriteria(Freight.class)
                .add(Restrictions.eq("id", freightId))
                .uniqueResult();

        dbFreight.setStatus(newFreightStatus);

        session.update(dbFreight);
        LOG.info("Freight status updated successfully, Freight details=" + dbFreight);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Freight> listFreights() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Freight> freightList = session.createQuery("from Freight").list();

        for (Freight freight : freightList) {
            int freightID = freight.getId();

            // get a waypoint object of the freight loading
            Query waypointQuery =
                    session.createQuery("from Waypoint where freight_id = :freight_id and operation = :operation");
            waypointQuery.setInteger("freight_id", freightID);
            waypointQuery.setString("operation", LOADING);
            Waypoint dbLoadingWaypoint = (Waypoint) waypointQuery.uniqueResult();

            // get a waypoint object of the freight unloading
            waypointQuery.setString("operation", "unloading");
            Waypoint dbLUnloadingWaypoint = (Waypoint) waypointQuery.uniqueResult();

            String loadingLocation = dbLoadingWaypoint.getLocation().getCity();
            String unloadingLocation = dbLUnloadingWaypoint.getLocation().getCity();

            freight.setLoading(loadingLocation);
            freight.setUnloading(unloadingLocation);

            LOG.info("Freight list::" + freight);
        }
        return freightList;
    }

    @Override
    public Freight getFreightById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Freight freight = (Freight) session.get(Freight.class, id);
        LOG.info("Freight by id loaded, Freight details=" + freight);
        return freight;
    }

    @Override
    public void removeFreight(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Freight freight = (Freight) session.get(Freight.class, id);
        if (freight != null) {
            session.delete(freight);
            LOG.info("Freight deleted successfully, Freight details=" + freight);
        }
    }

    /**
     * Fetches all valid freight options from the database and returns them as a JSON string suitable for JTable.
     */
    @Override
    public String getFreightOptions(int orderID, String city) {
        Session session = sessionFactory.getCurrentSession();

        // get a location object of the chosen city
        Query locationQuery = session.createQuery("from Location where city = :city");
        locationQuery.setString("city", city);
        Location dbLocation = (Location) locationQuery.uniqueResult();

        int locationID = dbLocation.getId();

        // get all waypoints connected with this city
        Query waypointQuery = session.createQuery("from Waypoint where location_id = :locationID");
        waypointQuery.setInteger("locationID", locationID);
        List cityWaypointList = waypointQuery.list();

        // fetch all orders from the database to check them for the chosen waypoints
        List orderList = session.createQuery("from Order").list();

        // Fetch the order with the given orderID from the database
        Query orderQuery = session.createQuery("from Order where id = :orderID");
        orderQuery.setInteger("orderID", orderID);
        Order chosenOrder = (Order) orderQuery.uniqueResult();

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

        // check that if operation is loading and freight status is 'prepared' - there's enough space for this freight
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

        // Creating a JSON string
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
