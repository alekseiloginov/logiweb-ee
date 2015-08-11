package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Truck;

import java.util.List;

/**
 * DAO interface to declare the methods that we will use in our project to work with the truck data.
 */
public interface TruckDao {

    Truck addTruck(Truck truck);

    Truck updateTruck(Truck truck);

    List<Truck> listTrucks();

    Truck getTruckById(int id);

    Truck getTruckByPlateNumber(String plateNumber);

    void removeTruck(int id);

    List getDrivableTrucks();
}
