package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.dao.DriverStatusChangeDao;
import com.tsystems.javaschool.loginov.logiweb.models.DriverStatusChange;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class that uses Hibernate DAO classes to work with DriverStatusChange objects.
 */
@Service
public class DriverStatusChangeService {

    private DriverStatusChangeDao driverStatusChangeDao;

    public void setDriverStatusChangeDao(DriverStatusChangeDao driverStatusChangeDao) {
        this.driverStatusChangeDao = driverStatusChangeDao;
    }

    @Transactional
    public void saveOrUpdateDriverStatus(DriverStatusChange driverStatusChange) {
        this.driverStatusChangeDao.saveOrUpdateDriverStatus(driverStatusChange);
    }
}
