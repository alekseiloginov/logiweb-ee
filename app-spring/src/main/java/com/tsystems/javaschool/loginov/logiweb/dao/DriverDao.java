package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;

import java.util.List;

/**
 * DAO interface to declare the methods that we will use in our project to work with the driver data.
 */
public interface DriverDao {

    Driver addDriver(Driver driver);

    Driver updateDriver(Driver driver);

    void updateDriverStatusAndWorkedHours(Driver driver);

    List<Driver> listDrivers();

    Driver getDriverById(int id);

    Driver getDriverByEmail(String email);

    void removeDriver(int id);

    List getFreeDriversInCity(int locationID, String freeDriverStatus);
}
