package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.exceptions.DuplicateEntryException;
import com.tsystems.javaschool.loginov.logiweb.exceptions.PlateNumberIncorrectException;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
import com.tsystems.javaschool.loginov.logiweb.models.Order;
import com.tsystems.javaschool.loginov.logiweb.models.Truck;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Hibernate specific DAO implementation for trucks.
 */
@Repository
public class TruckDaoImpl implements TruckDao {
    private static final Logger LOG = Logger.getLogger(TruckDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Truck addTruck(Truck truck) throws PlateNumberIncorrectException, DuplicateEntryException {

        if (!truck.getPlate_number().matches("^[a-zA-Z]{2}[0-9]{5}$")) {
            throw new PlateNumberIncorrectException("Plate number should contain 2 letters and 5 digits",
                    "Plate number incorrect");
        }

        Session session = this.sessionFactory.getCurrentSession();

        // Plate number database check for uniqueness
        Query truckCheckQuery = session.createQuery("from Truck where plate_number = :plate_number");
        truckCheckQuery.setString("plate_number", truck.getPlate_number());
        Truck checkedTruck = (Truck) truckCheckQuery.uniqueResult();

        if (checkedTruck != null) {
            throw new DuplicateEntryException("Plate number is unique and this one is already present in the database",
                    "Duplicate entry");
        }

        Query locationQuery = session.createQuery("from Location where city = :city");
        locationQuery.setString("city", truck.getLocation().getCity());
        Location dbLocation = (Location) locationQuery.uniqueResult();
        truck.setLocation(dbLocation);

        int savedTruckID = (int) session.save(truck);

        Query truckQuery = session.createQuery("from Truck where id = :savedTruckID");
        truckQuery.setInteger("savedTruckID", savedTruckID);
        Truck savedTruck = (Truck) truckQuery.uniqueResult();
        LOG.info("Truck saved successfully, Truck details=" + savedTruck);

        return savedTruck;
    }

    @Override
    public Truck updateTruck(Truck truck) throws PlateNumberIncorrectException, DuplicateEntryException {

        if (!truck.getPlate_number().matches("^[a-zA-Z]{2}[0-9]{5}$")) {
            throw new PlateNumberIncorrectException("Plate number should contain 2 letters and 5 digits",
                    "Plate number incorrect");
        }

        Session session = sessionFactory.getCurrentSession();

        Query truckQuery = session.createQuery("from Truck where id = :id");
        truckQuery.setInteger("id", truck.getId());
        Truck truckToUpdate = (Truck) truckQuery.uniqueResult();

        // if user changes truck's plate number, check if other trucks don't already have it
        if (!truckToUpdate.getPlate_number().equals(truck.getPlate_number())) {

            Query plateNumberCheckQuery = session.createQuery("from Truck where plate_number != :old_plate_number");
            plateNumberCheckQuery.setString("old_plate_number", truckToUpdate.getPlate_number());
            List otherTrucks = plateNumberCheckQuery.list();

            if (otherTrucks != null) {
                for (Object otherTruckObject : otherTrucks) {
                    Truck otherTruck = (Truck) otherTruckObject;

                    if (otherTruck.getPlate_number().equals(truck.getPlate_number())) {
                        throw new DuplicateEntryException("Plate number is unique and this one is already in database",
                                "Duplicate entry");
                    }
                }
            }
        }

        Query locationQuery = session.createQuery("from Location where city = :city");
        locationQuery.setString("city", truck.getLocation().getCity());
        Location dbLocation = (Location) locationQuery.uniqueResult();

        truckToUpdate.setPlate_number(truck.getPlate_number());
        truckToUpdate.setDriver_number(truck.getDriver_number());
        truckToUpdate.setCapacity(truck.getCapacity());
        truckToUpdate.setDrivable(truck.getDrivable());
        truckToUpdate.setLocation(dbLocation);

        session.update(truckToUpdate);

        // multiple times usage of the created query
        Truck updatedTruck = (Truck) truckQuery.uniqueResult();
        LOG.info("Truck updated successfully, Truck details=" + updatedTruck);

        return updatedTruck;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Truck> listTrucks() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Truck> truckList = session.createQuery("from Truck").list();
        for (Truck truck : truckList) {
            LOG.info("Truck list::" + truck);
        }
        return truckList;
    }

    @Override
    public Truck getTruckById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Truck truck = (Truck) session.get(Truck.class, id);
        LOG.info("Truck loaded successfully, Truck details=" + truck);
        return truck;
    }

    @Override
    public void removeTruck(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Truck truck = (Truck) session.get(Truck.class, id);
        if (truck != null) {
            session.delete(truck);
        }
        LOG.info("Truck deleted successfully, Truck details=" + truck);
    }

    @Override
    public String getTruckOptions() {
        Session session = sessionFactory.getCurrentSession();

        // get all drivable trucks
        List drivableTruckList = session.createQuery("from Truck where drivable = 1").list();
        // get all orders to check for occupied trucks
        List orderList = session.createQuery("from Order").list();

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
                truckOptionJSONList += validTruck.getPlate_number();
                truckOptionJSONList += "\",\"Value\":\"";
                truckOptionJSONList += validTruck.getPlate_number();
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
