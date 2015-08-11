package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.DriverStatusChange;

/**
 * DAO interface to declare the methods that we will use in our project to work with the driver status change data.
 */
public interface DriverStatusChangeDao {

    void saveDriverStatus(DriverStatusChange driverStatusChange);

    void updateDriverStatus(DriverStatusChange driverStatusChange);

    DriverStatusChange getDriverStatusChange(Driver driver, String driverStatus);
}
