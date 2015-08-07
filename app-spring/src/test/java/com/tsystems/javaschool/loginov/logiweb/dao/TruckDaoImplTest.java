package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.exceptions.DuplicateEntryException;
import com.tsystems.javaschool.loginov.logiweb.exceptions.PlateNumberIncorrectException;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
import com.tsystems.javaschool.loginov.logiweb.models.Truck;
import org.junit.Before;
import org.junit.Test;

/**
 * TruckDaoImpl unit tests.
 */
public class TruckDaoImplTest {
    private TruckDao truckDao;
    private Location location;
    private Truck truck;

    @Before
    public void setUp() throws Exception {
        truckDao = new TruckDaoImpl();
        location = new Location("Moscow");
        truck = new Truck("AB12345", 3, 3, 1, location);
    }

    @Test(expected = PlateNumberIncorrectException.class)
    public void addTruckPlateNumberIncorrectExceptionTest() throws PlateNumberIncorrectException, DuplicateEntryException {
        truck.setPlate_number("ABC1234");
        truckDao.addTruck(truck);
    }

    @Test(expected = DuplicateEntryException.class)
    public void addTruckDuplicateEntryExceptionTest() throws PlateNumberIncorrectException, DuplicateEntryException {
        // mock this call to the db
        truckDao.addTruck(truck);
    }

    @Test(expected = PlateNumberIncorrectException.class)
    public void updateTruckPlateNumberIncorrectExceptionTest() throws PlateNumberIncorrectException, DuplicateEntryException {
        truck.setPlate_number("ABC1234");
        truckDao.updateTruck(truck);
    }

    @Test(expected = DuplicateEntryException.class)
    public void updateTruckDuplicateEntryExceptionTest() throws PlateNumberIncorrectException, DuplicateEntryException {
        // mock this call to the db
        truckDao.updateTruck(truck);
    }
}