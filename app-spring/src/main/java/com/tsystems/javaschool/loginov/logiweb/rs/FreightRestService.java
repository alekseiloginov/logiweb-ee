package com.tsystems.javaschool.loginov.logiweb.rs;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * CXF RESTful webservice.
 */
public interface FreightRestService {

    @POST
    @Path("/freight/{freightId}/status/{freightStatus}")
    @Produces({"application/xml","application/json"})
    @Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
    Response setFreightStatus(@PathParam("freightId") Integer freightId,
                             @PathParam("freightStatus") String freightStatus);
}
