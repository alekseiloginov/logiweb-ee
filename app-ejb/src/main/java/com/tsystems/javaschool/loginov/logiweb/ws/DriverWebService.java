package com.tsystems.javaschool.loginov.logiweb.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Service Endpoint Interface (SEI).
 */
@WebService
public interface DriverWebService {

    @WebMethod
    public String setDriverStatus(@WebParam(name = "driverId") Integer driverId,
                                  @WebParam(name = "driverStatus") String driverStatus);
}
