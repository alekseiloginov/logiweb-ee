package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.dao.DriverDao;
import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void addDriver(Driver driver) {
        this.driverDao.addDriver(driver);
    }

    @Transactional
    public void updateDriver(Driver driver) {
        this.driverDao.updateDriver(driver);
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
}
