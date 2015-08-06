package com.tsystems.javaschool.loginov.logiweb.rs;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.Freight;
import com.tsystems.javaschool.loginov.logiweb.models.Order;
import com.tsystems.javaschool.loginov.logiweb.models.Waypoint;
import com.tsystems.javaschool.loginov.logiweb.services.DriverService;
import com.tsystems.javaschool.loginov.logiweb.services.FreightService;
import com.tsystems.javaschool.loginov.logiweb.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;

/**
 * CXF RESTful webservice implementation to manage freight status changes from the client app.
 */
public class FreightRestServiceImpl implements FreightRestService {

    @Autowired
    private FreightService freightService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private OrderService orderService;

    /**
     * Takes a freight id and freight status and responses with the freight status using RESTful webservice.
     */
    @Override
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

    /**
     * Takes a driver id and responses with the driver's freight list using RESTful webservice.
     */
    @Override
    public Response getFreightList(Integer driverId) {
        Driver driver = driverService.getDriverById(driverId);
        Order order = orderService.getOrderByDriver(driver);

        if (order != null) {
            Set<Waypoint> waypoints = order.getWaypoints();
            Set<Freight> freights = new HashSet<>();

            for (Waypoint waypoint : waypoints) {

                // freight should not be delivered
                if (!"delivered".equalsIgnoreCase(waypoint.getFreight().getStatus())) {
                    freights.add(waypoint.getFreight());
                }
            }
            return Response.ok(freights).build();
        }
        return Response.ok().build();
    }
}
