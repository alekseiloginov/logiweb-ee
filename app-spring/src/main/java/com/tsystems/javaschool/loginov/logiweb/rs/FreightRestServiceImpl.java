package com.tsystems.javaschool.loginov.logiweb.rs;

import com.tsystems.javaschool.loginov.logiweb.models.Freight;
import org.springframework.stereotype.Component;

/**
 * CXF RESTful webservice implementation to manage freight status changes from the client app.
 */
@Component
public class FreightRestServiceImpl implements FreightRestService {

//    private FreightService freightService;

//    @Autowired(required=true)
//    @Qualifier(value="freightService")
//    public void setFreightService(FreightService freightService){
//        this.freightService = freightService;
//    }

    /**
     * Takes a freight id and freight status and responses with the freight status.
     */
    public Freight setFreightStatus(Integer freightId, String freightStatus) {

//        if (freightService.getFreightById(freightId) != null) {

            Freight freight = new Freight();
            freight.setId(freightId);
            freight.setStatus(freightStatus);

//            freightService.updateFreight(freight);

            return freight;

//        } else {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }
    }
}
