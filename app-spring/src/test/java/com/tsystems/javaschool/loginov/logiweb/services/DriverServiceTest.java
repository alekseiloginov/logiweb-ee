package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.dao.DriverDao;
import com.tsystems.javaschool.loginov.logiweb.exceptions.DuplicateEntryException;
import com.tsystems.javaschool.loginov.logiweb.exceptions.PlateNumberNotFoundException;
import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
import com.tsystems.javaschool.loginov.logiweb.models.Truck;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * DriverService unit tests.
 */
public class DriverServiceTest {
    private DriverService driverService;
    private Location location;
    private Truck truck;
    private Driver driver;

    @Mock
    private DriverDao driverDao;

    @Before
    public void setUp() throws Exception {
        driverService = new DriverService();
        location = new Location("Moscow");
        truck = new Truck("AB12345", 3, 3, 1, location);
        driver = new Driver("Vasya", "Pupkin", "abcd@abc.com", "1234", 40, "free", location, truck);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = PlateNumberNotFoundException.class)
    public void addDriverPlateNumberNotFoundExceptionTest() throws Exception {
        truck.setPlate_number("YZ12345");
        driver.setTruck(truck);
        driverService.addDriver(driver);
    }

    @Test(expected = DuplicateEntryException.class)
    public void addDriverDuplicateEntryExceptionTest() throws Exception {
        driver.setPassword("abc@abc.com");
        driverService.addDriver(driver);
    }

    @Test(expected = PlateNumberNotFoundException.class)
    public void updateDriverPlateNumberNotFoundExceptionTest() throws Exception {
        truck.setPlate_number("YZ12345");
        driver.setTruck(truck);
        driverService.updateDriver(driver);
    }

    @Test(expected = DuplicateEntryException.class)
    public void updateDriverDuplicateEntryExceptionTest() throws Exception {
        driver.setPassword("abc@abc.com");
        driverService.updateDriver(driver);
    }

    @Test
    public void testAddDriver() throws Exception {

    }

    @Test
    public void testUpdateDriver() throws Exception {

    }

    @Test
    public void testUpdateDriverStatusAndWorkedHours() throws Exception {

    }

    @Test
    public void testListDrivers() throws Exception {

    }

    @Test
    public void testGetDriverById() throws Exception {

    }

    @Test
    public void testRemoveDriver() throws Exception {

    }

    @Test
    public void testGetAllOrderDrivers() throws Exception {

    }

    @Test
    public void testSaveOrderDriver() throws Exception {

    }

    @Test
    public void testGetDriverOptions() throws Exception {

    }
}