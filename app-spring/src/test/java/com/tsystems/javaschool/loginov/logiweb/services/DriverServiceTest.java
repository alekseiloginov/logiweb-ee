package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.dao.DriverDao;
import com.tsystems.javaschool.loginov.logiweb.dao.LocationDao;
import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
import com.tsystems.javaschool.loginov.logiweb.models.Truck;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    @Mock
    private LocationDao locationDao;

    @Mock
    SessionFactory sessionFactory;

    @Mock
    Session session;

    @Mock
    Query locationQuery;

    @Before
    public void setUp() throws Exception {
        driverService = new DriverService();
        location = new Location("Moscow");
        truck = new Truck("AB12345", 3, 3, 1, location);
        driver = new Driver("Vasya", "Pupkin", "abcd@abc.com", "1234", 40, "free");
        driver.setLocation(location);
        driver.setTruck(truck);
    }

    @After
    public void tearDown() throws Exception {

    }

//    @Test(expected = PlateNumberNotFoundException.class)
//    public void addDriverPlateNumberNotFoundExceptionTest() throws Exception {
//        truck.setPlateNumber("YZ12345");
//        driver.setTruck(truck);
//        driverService.addDriver(driver);
//    }
//
//    @Test(expected = ConstraintViolationException.class)
//    public void addDriverConstraintViolationExceptionTest() throws Exception {
//        driver.setPassword("abc@abc.com");
//        driverService.addDriver(driver);
//    }
//
//    @Test(expected = PlateNumberNotFoundException.class)
//    public void updateDriverPlateNumberNotFoundExceptionTest() throws Exception {
//        truck.setPlateNumber("YZ12345");
//        driver.setTruck(truck);
//        driverService.updateDriver(driver);
//    }
//
//    @Test(expected = DataIntegrityViolationException.class)
//    public void updateDriverDataIntegrityViolationExceptionTest() throws Exception {
//        driver.setPassword("abc@abc.com");
//        driverService.updateDriver(driver);
//    }

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