package com.tsystems.javaschool.loginov.logiweb.ws;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Service Endpoint Interface (SEI).
 */
@WebService
public interface DriverWebService {

    /**
     * Takes an id and a status of a driver and responses with the driver status using SOAP webservice.
     */
    @WebMethod
    String setDriverStatus(@WebParam(name="driverId") int driverId,
                           @WebParam(name="driverStatus") String driverStatus);

    /**
     * Takes a driver id and gets his/her status using SOAP webservice.
     */
    @WebMethod
    String getDriverStatus(@WebParam(name = "driverId") int driverId);

    /**
     * Takes a driver credentials and processes authentication using SOAP webservice.
     */
    @WebMethod
    Driver authenticateDriver(@WebParam(name = "driverId") int driverId,
                              @WebParam(name = "driverPassword") String driverPassword);
}
