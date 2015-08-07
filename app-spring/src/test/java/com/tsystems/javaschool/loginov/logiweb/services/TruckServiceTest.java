package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.exceptions.DuplicateEntryException;
import com.tsystems.javaschool.loginov.logiweb.exceptions.PlateNumberIncorrectException;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
import com.tsystems.javaschool.loginov.logiweb.models.Truck;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TruckService unit tests.
 */
public class TruckServiceTest {

    TruckService truckService;
    Location location;
    Truck truck;

    @Before
    public void setUp() throws Exception {
        truckService = new TruckService();
        location = new Location("Moscow");
        truck = new Truck("AB12345", 3, 3, 1, location);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = PlateNumberIncorrectException.class)
    public void plateNumberIncorrectExceptionTest() throws PlateNumberIncorrectException, DuplicateEntryException {
        truck.setPlate_number("ABC1234");
        truckService.addTruck(truck);
    }

    @Test(expected = DuplicateEntryException.class)
    public void duplicateEntryExceptionTest() throws PlateNumberIncorrectException, DuplicateEntryException {
        // mock this call to the db
        truckService.addTruck(truck);
    }

    @Test
    public void testAddTruck() throws Exception {

    }

    @Test
    public void testUpdateTruck() throws Exception {

    }

    @Test
    public void testListTrucks() throws Exception {

    }

    @Test
    public void testGetTruckById() throws Exception {

    }

    @Test
    public void testRemoveTruck() throws Exception {

    }

    @Test
    public void testGetTruckOptions() throws Exception {

    }
}