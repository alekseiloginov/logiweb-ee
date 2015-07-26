package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.DriverStatusChange;
import com.tsystems.javaschool.loginov.logiweb.models.Freight;
import com.tsystems.javaschool.loginov.logiweb.services.DriverStatusChangeService;
import com.tsystems.javaschool.loginov.logiweb.services.FreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring MVC Controller to manage REST queries from the second app.
 */
@Controller
@RequestMapping(value = "/rest")
public class RestController {

    private DriverStatusChangeService driverStatusChangeService;
    private FreightService freightService;

    @Autowired(required=true)
    @Qualifier(value="driverStatusChangeService")
    public void setDriverStatusChangeService(DriverStatusChangeService driverStatusChangeService){
        this.driverStatusChangeService = driverStatusChangeService;
    }

    @Autowired(required=true)
    @Qualifier(value="freightService")
    public void setFreightService(FreightService freightService){
        this.freightService = freightService;
    }


    /**
     * Takes an id and a status of a driver and responses with the driver status.
     */
    @RequestMapping(value = "/driver/{id}/status/{status}")
    public void setStatusFree(HttpServletResponse resp,
                              @PathVariable("id") int id,
                              @PathVariable("status") String status) throws IOException {

        Driver driver = new Driver();
        driver.setId(id);
        DriverStatusChange driverStatusChange = new DriverStatusChange(status, driver);

        driverStatusChangeService.saveOrUpdateDriverStatus(driverStatusChange);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(status);
    }

    /**
     * Takes a driver id and freight status and responses with the freight status.
     */
    @RequestMapping(value = "/freight/{id}/status/{status}")
    public void setStatusShipped(HttpServletResponse resp,
                                 @PathVariable("id") int id,
                                 @PathVariable("status") String status) throws IOException {
        Freight freight = new Freight();
        freight.setId(id);
        freight.setStatus(status);

        freightService.updateFreight(freight);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(status);
    }
}
