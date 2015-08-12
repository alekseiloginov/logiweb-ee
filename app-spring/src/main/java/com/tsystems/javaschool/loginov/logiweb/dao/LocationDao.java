package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Location;

import java.util.List;

/**
 * DAO interface to declare the methods that we will use in our project to work with the location data.
 */
public interface LocationDao {

    Location addLocation(Location location);

    Location updateLocation(Location location);

    List<Location> listLocations();

    Location getLocationById(int id);

    Location getLocationByCity(String city);

    void removeLocation(int id);
}
