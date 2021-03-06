package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.dao.TruckDao;
import com.tsystems.javaschool.loginov.logiweb.exceptions.PlateNumberIncorrectException;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
import com.tsystems.javaschool.loginov.logiweb.models.Truck;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * TruckService unit tests.
 */
public class TruckServiceTest {
    private TruckService truckService;
    private Location location;
    private Truck truck;

    @Mock
    private TruckDao truckDao;

    @Before
    public void setUp() throws Exception {
        truckService = new TruckService();
        location = new Location("Moscow");
        truck = new Truck("AB12345", 3, 3, 1, location);
    }

    @Test(expected = PlateNumberIncorrectException.class)
    public void addTruckPlateNumberIncorrectExceptionTest() throws Exception {
        truck.setPlateNumber("ABC1234");
        truckService.addTruck(truck);
    }

//    @Test(expected = DataIntegrityViolationException.class)
//    public void addTruckDataIntegrityViolationExceptionTest() throws Exception {
//        // mock this call to the db
//        truckService.addTruck(truck);
//    }

    @Test(expected = PlateNumberIncorrectException.class)
    public void updateTruckPlateNumberIncorrectExceptionTest() throws Exception {
        truck.setPlateNumber("ABC1234");
        truckService.addTruck(truck);
    }

//    @Test(expected = DataIntegrityViolationException.class)
//    public void updateTruckDataIntegrityViolationExceptionTest() throws Exception {
//        // mock this call to the db
//        truckService.addTruck(truck);
//    }

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