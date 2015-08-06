package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.dao.TruckDao;
import com.tsystems.javaschool.loginov.logiweb.exceptions.DuplicateEntryException;
import com.tsystems.javaschool.loginov.logiweb.exceptions.PlateNumberIncorrectException;
import com.tsystems.javaschool.loginov.logiweb.models.Truck;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class that uses Hibernate DAO classes to work with Truck objects.
 */
@Service
public class TruckService {

    private TruckDao truckDao;

    public void setTruckDao(TruckDao truckDao) {
        this.truckDao = truckDao;
    }

    @Transactional
    public Truck addTruck(Truck truck) throws PlateNumberIncorrectException, DuplicateEntryException {
        return this.truckDao.addTruck(truck);
    }

    @Transactional
    public Truck updateTruck(Truck truck) throws PlateNumberIncorrectException, DuplicateEntryException {
        return this.truckDao.updateTruck(truck);
    }

    @Transactional
    public List<Truck> listTrucks() {
        return this.truckDao.listTrucks();
    }

    @Transactional
    public Truck getTruckById(int id) {
        return this.truckDao.getTruckById(id);
    }

    @Transactional
    public void removeTruck(int id) {
        this.truckDao.removeTruck(id);
    }

    @Transactional
    public String getTruckOptions() {
        return this.truckDao.getTruckOptions();
    }
}
