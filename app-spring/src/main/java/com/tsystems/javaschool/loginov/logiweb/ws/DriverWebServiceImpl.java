package com.tsystems.javaschool.loginov.logiweb.ws;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.DriverStatusChange;
import com.tsystems.javaschool.loginov.logiweb.services.DriverService;
import com.tsystems.javaschool.loginov.logiweb.services.DriverStatusChangeService;
import org.springframework.beans.factory.annotation.Autowired;

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

//    @Autowired(required=true)
//    @Qualifier(value="driverStatusChangeService")
//    public void setDriverStatusChangeService(DriverStatusChangeService driverStatusChangeService){
//        this.driverStatusChangeService = driverStatusChangeService;
//    }

    /**
     * Takes an id and a status of a driver and responses with the driver status.
     */
    public String setDriverStatus(Integer driverId, String driverStatus) {

        Driver driver = new Driver();
        driver.setId(driverId);
        driver.setStatus(driverStatus);
        DriverStatusChange driverStatusChange = new DriverStatusChange(driverStatus, driver);

        driverStatusChangeService.saveOrUpdateDriverStatus(driverStatusChange);

        driverService.updateDriverStatusAndWorkedHours(driver);

        return "Driver status successfully saved!";
    }
}
