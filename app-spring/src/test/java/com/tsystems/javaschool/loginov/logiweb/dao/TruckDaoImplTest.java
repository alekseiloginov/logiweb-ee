package com.tsystems.javaschool.loginov.logiweb.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.tsystems.javaschool.loginov.logiweb.exceptions.DuplicateEntryException;
import com.tsystems.javaschool.loginov.logiweb.exceptions.PlateNumberIncorrectException;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
import com.tsystems.javaschool.loginov.logiweb.models.Truck;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * TruckDaoImpl unit tests.
 */
public class TruckDaoImplTest {
    private TruckDao truckDao;
    private Location location;
    private Truck truck;

    @Mock
    private SessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        truckDao = new TruckDaoImpl();
        location = new Location("Moscow");
        truck = new Truck("AB12345", 3, 3, 1, location);
    }

    @Test(expected = PlateNumberIncorrectException.class)
    public void addTruckPlateNumberIncorrectExceptionTest() {
        truck.setPlate_number("ABC1234");
        truckDao.addTruck(truck);
    }

    @Test(expected = DuplicateEntryException.class)
    public void addTruckDuplicateEntryExceptionTest() {
        // mock this call to the db
        truckDao.addTruck(truck);
    }

    @Test(expected = PlateNumberIncorrectException.class)
    public void updateTruckPlateNumberIncorrectExceptionTest() throws MySQLIntegrityConstraintViolationException {
        truck.setPlate_number("ABC1234");
        truckDao.updateTruck(truck);
    }

    @Test(expected = DuplicateEntryException.class)
    public void updateTruckDuplicateEntryExceptionTest() throws MySQLIntegrityConstraintViolationException {
        // mock this call to the db
        truckDao.updateTruck(truck);
    }
}