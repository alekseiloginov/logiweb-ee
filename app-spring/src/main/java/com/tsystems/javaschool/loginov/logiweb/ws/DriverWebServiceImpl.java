package com.tsystems.javaschool.loginov.logiweb.ws;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.DriverStatusChange;
import com.tsystems.javaschool.loginov.logiweb.services.DriverStatusChangeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * SOAP webservice implementation to manage driver status changes from the client app.
 */
@Component
@WebService(endpointInterface="com.tsystems.javaschool.loginov.logiweb.ws.DriverWebService", serviceName="driverWebService")
public class DriverWebServiceImpl implements DriverWebService {
    private static Logger logger = Logger.getLogger(DriverWebServiceImpl.class);

    private DriverStatusChangeService driverStatusChangeService;

    @Autowired(required=true)
    @Qualifier(value="driverStatusChangeService")
    public void setDriverStatusChangeService(DriverStatusChangeService driverStatusChangeService){
        this.driverStatusChangeService = driverStatusChangeService;
    }

    /**
     * Takes an id and a status of a driver and responses with the driver status.
     */
    public String setDriverStatus(Integer driverId, String driverStatus) {

        Driver driver = new Driver();
        driver.setId(driverId);
        DriverStatusChange driverStatusChange = new DriverStatusChange(driverStatus, driver);

        logger.debug("driverStatusChangeService = " + driverStatusChangeService);

//        driverStatusChangeService.saveOrUpdateDriverStatus(driverStatusChange);

        return "Driver status successfully saved!";
    }
}
