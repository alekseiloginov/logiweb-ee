package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.exceptions.DuplicateEntryException;
import com.tsystems.javaschool.loginov.logiweb.exceptions.PlateNumberNotFoundException;
import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
import com.tsystems.javaschool.loginov.logiweb.models.Truck;
import org.junit.Before;
import org.junit.Test;

/**
 * TruckDaoImpl unit tests.
 */
public class DriverDaoImplTest {
    private DriverDao driverDao;
    private Location location;
    private Truck truck;
    private Driver driver;

    @Before
    public void setUp() throws Exception {
        driverDao = new DriverDaoImpl();
        location = new Location("Moscow");
        truck = new Truck("AB12345", 3, 3, 1, location);
        driver = new Driver("Vasya", "Pupkin", "abcd@abc.com", "1234", 40, "free", location, truck);
    }

    @Test(expected = PlateNumberNotFoundException.class)
    public void addDriverPlateNumberNotFoundExceptionTest() throws PlateNumberNotFoundException, DuplicateEntryException {
        truck.setPlate_number("YZ12345");
        driver.setTruck(truck);
        driverDao.addDriver(driver);
    }

    @Test(expected = DuplicateEntryException.class)
    public void addDriverDuplicateEntryExceptionTest() throws PlateNumberNotFoundException, DuplicateEntryException {
        driver.setPassword("abc@abc.com");
        driverDao.addDriver(driver);
    }

    @Test(expected = PlateNumberNotFoundException.class)
    public void updateDriverPlateNumberNotFoundExceptionTest() throws PlateNumberNotFoundException, DuplicateEntryException {
        truck.setPlate_number("YZ12345");
        driver.setTruck(truck);
        driverDao.updateDriver(driver);
    }

    @Test(expected = DuplicateEntryException.class)
    public void updateDriverDuplicateEntryExceptionTest() throws PlateNumberNotFoundException, DuplicateEntryException {
        driver.setPassword("abc@abc.com");
        driverDao.updateDriver(driver);
    }
}