package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.tsystems.javaschool.loginov.logiweb.models.Freight;
import com.tsystems.javaschool.loginov.logiweb.services.FreightService;
import com.tsystems.javaschool.loginov.logiweb.utils.GsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spring MVC Controller to work with the freight data.
 */
@Controller
public class FreightController {

    @Autowired
    private FreightService freightService;

    @Autowired
    private GsonParser gsonParser;

    /**
     * Redirects user to the freights page.
     */
    @RequestMapping(value = "/freights", method = RequestMethod.GET)
    public String getDriverPage() {
        return "secure/manager/freights";
    }

    /**
     * Fetches a list of all freights using the FreightService and puts it to the result map.
     */
    @RequestMapping(value = "/FreightList.do", method = RequestMethod.POST)
    public void getAllFreights(HttpServletResponse resp) throws IOException {
        List freightList = freightService.listFreights();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", freightList);
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Adds a freight to the database using the FreightService and puts the saved freight back to the result map.
     */
    @RequestMapping(value = "/FreightSave.do", method = RequestMethod.POST)
    public void saveFreight(@RequestParam(value = "name") String name,
                            @RequestParam(value = "weight") int weight,
                            @RequestParam(value = "status") String status,
                            @RequestParam(value = "loading") String loadingLocation,
                            @RequestParam(value = "unloading") String unloadingLocation,
                            HttpServletResponse resp) throws IOException {

        Map<String, Object> resultMap = new HashMap<>();
        Freight savedFreight = freightService.addFreight(new Freight(name, weight, status, loadingLocation, unloadingLocation));
        resultMap.put("datum", savedFreight);
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Updates a freight in the database using the FreightService and puts the updated freight back to the result map.
     */
    @RequestMapping(value = "/FreightUpdate.do", method = RequestMethod.POST)
    public void updateFreight(@RequestParam(value = "id") int id,
                              @RequestParam(value = "name") String name,
                              @RequestParam(value = "weight") int weight,
                              @RequestParam(value = "status") String status,
                              @RequestParam(value = "loading") String loadingLocation,
                              @RequestParam(value = "unloading") String unloadingLocation,
                              HttpServletResponse resp) throws IOException {

        Map<String, Object> resultMap = new HashMap<>();
        Freight updatedFreight =
                freightService.updateFreight(new Freight(id, name, weight, status, loadingLocation, unloadingLocation));
        resultMap.put("datum", updatedFreight);
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Deletes a freight from the database using the FreightService and puts "OK" back to the result map.
     */
    @RequestMapping(value = "/FreightDelete.do", method = RequestMethod.POST)
    public void deleteFreight(@RequestParam(value = "id") int id, HttpServletResponse resp) throws IOException {
        Map<String, Object> resultMap = new HashMap<>();
        freightService.removeFreight(id);
        resultMap.put("OK", "OK");
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Fetches all valid freight options using the FreightService and puts a returned JSON string to the result map.
     */
    @RequestMapping(value = "/FreightOptions.do", method = RequestMethod.POST)
    public void getAllFreightOptions(@RequestParam(value = "orderID") int orderID,
                                     @RequestParam(value = "city") String city,
                                     HttpServletResponse resp) throws IOException {

        Map<String, Object> resultMap = new HashMap<>();
        String freightOptionJSONList = freightService.getFreightOptions(orderID, city);
        resultMap.put("options", freightOptionJSONList);
        gsonParser.parse(resultMap, resp);
    }
}