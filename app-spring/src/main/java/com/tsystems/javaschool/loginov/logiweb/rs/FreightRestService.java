package com.tsystems.javaschool.loginov.logiweb.rs;

import com.tsystems.javaschool.loginov.logiweb.models.Freight;

import javax.ws.rs.*;

/**
 * CXF RESTful webservice.
 */
public interface FreightRestService {

    @POST
    @Path("/freight/{freightId}/status/{freightStatus}")
    @Produces({"application/xml","application/json"})
    @Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
    public Freight setFreightStatus(@PathParam("freightId") Integer freightId,
                                     @PathParam("freightStatus") String freightStatus);
}
