package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.dao.DriverDao;
import com.tsystems.javaschool.loginov.logiweb.dao.DriverStatusChangeDao;
import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.DriverStatusChange;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Service class that uses Hibernate DAO classes to work with DriverStatusChange objects.
 */
@Service
public class DriverStatusChangeService {
    private DriverStatusChangeDao driverStatusChangeDao;
    private DriverDao driverDao;

    public void setDriverStatusChangeDao(DriverStatusChangeDao driverStatusChangeDao) {
        this.driverStatusChangeDao = driverStatusChangeDao;
    }

    public void setDriverDao(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    @Transactional
    public void saveOrUpdateDriverStatus(DriverStatusChange driverStatusChange) {
        String driverStatus = driverStatusChange.getStatus();
        Driver driver = driverDao.getDriverById(driverStatusChange.getDriver().getId());
        DriverStatusChange dbDriverStatusChange = driverStatusChangeDao.getDriverStatusChange(driver, driverStatus);

        if (dbDriverStatusChange != null) {
            dbDriverStatusChange.setLast_modified_time(new Date());
            driverStatusChangeDao.updateDriverStatus(dbDriverStatusChange);

        } else {
            driverStatusChange.setDriver(driver);
            driverStatusChangeDao.saveDriverStatus(driverStatusChange);
        }
    }
}
