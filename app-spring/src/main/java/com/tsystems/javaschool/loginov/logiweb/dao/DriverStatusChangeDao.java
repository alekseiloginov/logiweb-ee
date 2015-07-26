package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.DriverStatusChange;

/**
 * DAO interface to declare the methods that we will use in our project to work with the driver status change data.
 */
public interface DriverStatusChangeDao {

    void saveOrUpdateDriverStatus(DriverStatusChange driverStatusChange);
}
