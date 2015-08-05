package com.tsystems.javaschool.loginov.logiweb.rs;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * CXF RESTful webservice.
 */
public interface FreightRestService {

    /**
     * Takes a freight id and freight status and responses with the freight status using RESTful webservice.
     */
    @POST
    @Path("/freights/{freightId}/statuses/{freightStatus}")
//    @Produces({"application/json"})
    @Produces({"application/xml"})
    @Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
    Response setFreightStatus(@PathParam("freightId") Integer freightId,
                             @PathParam("freightStatus") String freightStatus);

    /**
     * Takes a driver id and responses with the driver's freight list using RESTful webservice.
     */
    @POST
    @Path("/drivers/{driverId}/freights/list")
    @Produces({"application/json"})
    @Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
    Response getFreightList(@PathParam("driverId") Integer driverId);
}
