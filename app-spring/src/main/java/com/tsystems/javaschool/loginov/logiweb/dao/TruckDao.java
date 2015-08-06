package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.exceptions.DuplicateEntryException;
import com.tsystems.javaschool.loginov.logiweb.exceptions.PlateNumberIncorrectException;
import com.tsystems.javaschool.loginov.logiweb.models.Truck;

import java.util.List;

/**
 * DAO interface to declare the methods that we will use in our project to work with the truck data.
 */
public interface TruckDao {

    Truck addTruck(Truck truck) throws PlateNumberIncorrectException, DuplicateEntryException;

    Truck updateTruck(Truck truck) throws PlateNumberIncorrectException, DuplicateEntryException;

    List<Truck> listTrucks();

    Truck getTruckById(int id);

    void removeTruck(int id);

    String getTruckOptions();
}
