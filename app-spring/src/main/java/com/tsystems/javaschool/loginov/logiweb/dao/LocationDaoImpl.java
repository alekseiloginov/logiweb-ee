package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.exceptions.DuplicateEntryException;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate specific DAO implementation for locations.
 */
@Repository
public class LocationDaoImpl implements LocationDao {
    private static Logger logger = Logger.getLogger(LocationDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Location addLocation(Location location) throws DuplicateEntryException {
        return null;
    }

    public void updateLocation(Location location) {

    }

    public List<Location> listLocations() {
        return null;
    }

    public Location getLocationById(int id) {
        return null;
    }

    public void removeLocation(int id) {

    }

    /**
     * Fetches all valid location options from the database and returns them as a JSON string suitable for JTable.
     */
    public String getLocationOptions() {
        Session session = this.sessionFactory.getCurrentSession();

        List locationList = session.createQuery("from Location").list();

        // Creating a JSON string
        int optionCount = 0;
        String locationOptionJSONList = "[";

        if (locationList.size() == 0) {
            locationOptionJSONList += "]";

        } else {

            for (Object location : locationList) {
                locationOptionJSONList += "{\"DisplayText\":\"";
                locationOptionJSONList += ((Location) location).getCity();
                locationOptionJSONList += "\",\"Value\":\"";
                locationOptionJSONList += ((Location) location).getCity();
                ++optionCount;

                if (optionCount < locationList.size()) {
                    locationOptionJSONList += "\"},";
                } else {
                    locationOptionJSONList += "\"}]";
                }
            }
        }
        return locationOptionJSONList;
    }
}
