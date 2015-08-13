package com.tsystems.javaschool.loginov.logiweb.ws;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.DriverStatusChange;
import com.tsystems.javaschool.loginov.logiweb.services.DriverService;
import com.tsystems.javaschool.loginov.logiweb.services.DriverStatusChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.jws.WebService;

/**
 * SOAP webservice implementation to manage driver status changes from the client app.
 */
@WebService(endpointInterface="com.tsystems.javaschool.loginov.logiweb.ws.DriverWebService", serviceName="driverWebService")
public class DriverWebServiceImpl implements DriverWebService {

    @Autowired
    private DriverStatusChangeService driverStatusChangeService;

    @Autowired
    private DriverService driverService;

    /**
     * Takes an id and a status of a driver and responses with the driver status using SOAP webservice.
     */
    @Override
    public String setDriverStatus(int driverId, String driverStatus) {

        Driver driver = new Driver();
        driver.setId(driverId);
        driver.setStatus(driverStatus);
        DriverStatusChange driverStatusChange = new DriverStatusChange(driverStatus, driver);

        driverStatusChangeService.saveOrUpdateDriverStatus(driverStatusChange);

        driverService.updateDriverStatusAndWorkedHours(driver);

        return "Driver status successfully saved!";
    }

    /**
     * Takes a driver id and gets his/her status using SOAP webservice.
     */
    @Override
    public String getDriverStatus(int driverId) {
        return driverService.getDriverById(driverId).getStatus();
    }

    /**
     * Takes a driver credentials and processes authentication using SOAP webservice.
     */
    @Override
    public Driver authenticateDriver(int driverId, String driverPassword) {
        Driver driver = driverService.getDriverById(driverId);

        // check raw password from form with the encrypted one in the database using BCrypt
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (driver != null && encoder.matches(driverPassword, driver.getPassword())) {
            return driver;
        }

        return null;
    }
}
