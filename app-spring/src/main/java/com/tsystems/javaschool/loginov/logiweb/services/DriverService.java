package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.dao.DriverDao;
import com.tsystems.javaschool.loginov.logiweb.exceptions.DuplicateEntryException;
import com.tsystems.javaschool.loginov.logiweb.exceptions.PlateNumberNotFoundException;
import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Service class that uses Hibernate DAO classes to work with Driver objects.
 */
@Service
public class DriverService {

    private DriverDao driverDao;

    public void setDriverDao(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    @Transactional
    public Driver addDriver(Driver driver) throws PlateNumberNotFoundException, DuplicateEntryException {
        return this.driverDao.addDriver(driver);
    }

    @Transactional
    public Driver updateDriver(Driver driver) throws PlateNumberNotFoundException, DuplicateEntryException {
        return this.driverDao.updateDriver(driver);
    }

    @Transactional
    public void updateDriverStatusAndWorkedHours(Driver driver) {
        this.driverDao.updateDriverStatusAndWorkedHours(driver);
    }

    @Transactional
    public List<Driver> listDrivers() {
        return this.driverDao.listDrivers();
    }

    @Transactional
    public Driver getDriverById(int id) {
        return this.driverDao.getDriverById(id);
    }

    @Transactional
    public void removeDriver(int id) {
        this.driverDao.removeDriver(id);
    }

    @Transactional
    public Set<Driver> getAllOrderDrivers(int orderID) {
        return this.driverDao.getAllOrderDrivers(orderID);
    }

    @Transactional
    public Driver saveOrderDriver(int orderID, String driverEmail) {
        return this.driverDao.saveOrderDriver(orderID, driverEmail);
    }

    @Transactional
    public String getDriverOptions(int orderID) {
        return this.driverDao.getDriverOptions(orderID);
    }
}
