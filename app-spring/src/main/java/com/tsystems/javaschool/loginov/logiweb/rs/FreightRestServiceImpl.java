package com.tsystems.javaschool.loginov.logiweb.rs;

import com.tsystems.javaschool.loginov.logiweb.models.Freight;
import com.tsystems.javaschool.loginov.logiweb.services.FreightService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;

/**
 * CXF RESTful webservice implementation to manage freight status changes from the client app.
 */
public class FreightRestServiceImpl implements FreightRestService {

    @Autowired
    private FreightService freightService;

//    @Autowired(required=true)
//    @Qualifier(value="freightService")
//    public void setFreightService(FreightService freightService){
//        this.freightService = freightService;
//    }

    /**
     * Takes a freight id and freight status and responses with the freight status.
     */
    public Response setFreightStatus(Integer freightId, String freightStatus) {

        if (freightService.getFreightById(freightId) != null) {

            Freight freight = new Freight();
            freight.setId(freightId);
            freight.setStatus(freightStatus);

            freightService.updateFreightStatus(freight);

            return Response.ok(freight).build();

        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
