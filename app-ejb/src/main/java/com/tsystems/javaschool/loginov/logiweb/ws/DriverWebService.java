package com.tsystems.javaschool.loginov.logiweb.ws;

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
    String setDriverStatus(@WebParam(name = "driverId") Integer driverId,
                           @WebParam(name = "driverStatus") String driverStatus);

    /**
     * Takes a driver id and gets his/her status using SOAP webservice.
     */
    @WebMethod
    String getDriverStatus(@WebParam(name = "driverId") Integer driverId);
}
