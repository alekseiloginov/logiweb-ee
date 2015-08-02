package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;

import java.util.List;

/**
 * DAO interface to declare the methods that we will use in our project to work with the driver data.
 */
public interface DriverDao {

    void addDriver(Driver driver);

    void updateDriver(Driver driver);

    void updateDriverStatusAndWorkedHours(Driver driver);

    List<Driver> listDrivers();

    Driver getDriverById(int id);

    void removeDriver(int id);
}
