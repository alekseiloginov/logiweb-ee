package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.exceptions.DuplicateEntryException;
import com.tsystems.javaschool.loginov.logiweb.models.Location;

import java.util.List;

/**
 * DAO interface to declare the methods that we will use in our project to work with the location data.
 */
public interface LocationDao {

    Location addLocation(Location location) throws DuplicateEntryException;

    void updateLocation(Location location);

    List<Location> listLocations();

    Location getLocationById(int id);

    void removeLocation(int id);

    /**
     * Fetches all valid location options from the database and returns them as a JSON string suitable for JTable.
     */
    String getLocationOptions();
}
