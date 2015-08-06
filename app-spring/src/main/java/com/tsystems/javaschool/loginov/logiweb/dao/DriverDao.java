package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.exceptions.DuplicateEntryException;
import com.tsystems.javaschool.loginov.logiweb.exceptions.PlateNumberNotFoundException;
import com.tsystems.javaschool.loginov.logiweb.models.Driver;

import java.util.List;
import java.util.Set;

/**
 * DAO interface to declare the methods that we will use in our project to work with the driver data.
 */
public interface DriverDao {

    Driver addDriver(Driver driver) throws PlateNumberNotFoundException, DuplicateEntryException;

    Driver updateDriver(Driver driver) throws PlateNumberNotFoundException, DuplicateEntryException;

    void updateDriverStatusAndWorkedHours(Driver driver);

    List<Driver> listDrivers();

    Driver getDriverById(int id);

    void removeDriver(int id);

    Set<Driver> getAllOrderDrivers(int orderID);

    Driver saveOrderDriver(int orderID, String driverEmail);

    String getDriverOptions(int orderID);
}
