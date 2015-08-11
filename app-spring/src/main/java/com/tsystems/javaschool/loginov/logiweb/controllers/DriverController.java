package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.tsystems.javaschool.loginov.logiweb.exceptions.PlateNumberNotFoundException;
import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
import com.tsystems.javaschool.loginov.logiweb.models.Truck;
import com.tsystems.javaschool.loginov.logiweb.services.DriverService;
import com.tsystems.javaschool.loginov.logiweb.utils.GsonParser;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Spring MVC Controller to work with the driver data.
 */
@Controller
public class DriverController {
    private static final Logger LOG = Logger.getLogger(DriverController.class);
    private static final String JTABLE_ERROR_MESSAGE = "jTableError";
    private static final String DATUM = "datum";
    private static final String DATA = "data";

    @Autowired
    private DriverService driverService;

    @Autowired
    private GsonParser gsonParser;

    /**
     * Redirects user to the driver page.
     */
    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    public String getDriverPage() {
        return "secure/manager/drivers";
    }

    /**
     * Fetches a list of all drivers using the DriverService and puts it to the result map.
     */
    @RequestMapping(value = "/DriverList.do", method = RequestMethod.POST)
    public void getAllDrivers(HttpServletResponse resp) throws IOException {
        List driverList = driverService.listDrivers();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(DATA, driverList);
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Adds a driver to the database using the DriverService and puts the saved driver back to the result map.
     */
    @RequestMapping(value = "/DriverSave.do", method = RequestMethod.POST)
    public void saveDriver(@RequestParam(value = "name") String name,
                           @RequestParam(value = "surname") String surname,
                           @RequestParam(value = "email") String email,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "worked_hours") int worked_hours,
                           @RequestParam(value = "status") String status,
                           @RequestParam(value = "location") String city,
                           @RequestParam(value = "truck") String plate_number,
                           HttpServletResponse resp) throws IOException {

        Map<String, Object> resultMap = new HashMap<>();

        try {
            Driver savedDriver =
                    driverService.addDriver(new Driver(name, surname, email, password, worked_hours, status,
                                            new Location(city), new Truck(plate_number)));
            resultMap.put(DATUM, savedDriver);

        } catch (ConstraintViolationException e) {
            LOG.error("Problem with Driver saving", e);
            resultMap.put(JTABLE_ERROR_MESSAGE, e.getCause().getMessage());
        } catch (PlateNumberNotFoundException e) {
            LOG.error("Plate number not found: " + plate_number, e);
            resultMap.put(JTABLE_ERROR_MESSAGE, "No truck with the entered plate number, add it first.");
        }

        gsonParser.parse(resultMap, resp);
    }

    /**
     * Updates a driver in the database using the DriverService and puts the updated driver back to the result map.
     */
    @RequestMapping(value = "/DriverUpdate.do", method = RequestMethod.POST)
    public void updateDriver(@RequestParam(value = "id") int id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "surname") String surname,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "worked_hours") int worked_hours,
                             @RequestParam(value = "status") String status,
                             @RequestParam(value = "location") String city,
                             @RequestParam(value = "truck") String plate_number,
                             HttpServletResponse resp) throws IOException {

        Map<String, Object> resultMap = new HashMap<>();

        try {
            Driver updatedDriver = driverService.updateDriver(new Driver(id, name, surname, email, password,
                                    worked_hours, status, new Location(city), new Truck(plate_number)));
            resultMap.put(DATUM, updatedDriver);

        } catch (DataIntegrityViolationException e) {
            LOG.error("Problem with Driver updating", e);
            resultMap.put(JTABLE_ERROR_MESSAGE, e.getRootCause().getMessage());
        } catch (PlateNumberNotFoundException e) {
            LOG.error("Plate number not found: " + plate_number, e);
            resultMap.put(JTABLE_ERROR_MESSAGE, "No truck with the entered plate number, add it first.");
        }

        gsonParser.parse(resultMap, resp);
    }

    /**
     * Deletes a driver from the database using the DriverService and puts "OK" back to the result map.
     */
    @RequestMapping(value = "/DriverDelete.do", method = RequestMethod.POST)
    public void deleteDriver(@RequestParam(value = "id") int id, HttpServletResponse resp) throws IOException {
        Map<String, Object> resultMap = new HashMap<>();
        driverService.removeDriver(id);
        resultMap.put("OK", "OK");
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Fetches a list of drivers for the required order's truck using the DriverService and puts it to the result map.
     */
    @RequestMapping(value = "/OrderTruckDriverList.do", method = RequestMethod.POST)
    public void getAllOrderTruckDrivers(@RequestParam(value = "orderID") int orderID, HttpServletResponse resp) throws IOException {
        Map<String, Object> resultMap = new HashMap<>();
        Set<Driver> driverSet = driverService.getAllOrderDrivers(orderID);
        resultMap.put(DATA, driverSet);
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Adds a driver to the required order using the DriverService and puts the saved driver back to the result map.
     */
    @RequestMapping(value = "/OrderTruckDriverSave.do", method = RequestMethod.POST)
    public void saveOrderTruckDriver(@RequestParam(value = "orderID") int orderID,
                                     @RequestParam(value = "email") String driverEmail,
                                     HttpServletResponse resp) throws IOException {

        Map<String, Object> resultMap = new HashMap<>();
        Driver savedOrderDriver = driverService.saveOrderDriver(orderID, driverEmail);
        resultMap.put(DATUM, savedOrderDriver);
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Fetches a list of valid driver options using the DriverService and puts a returned JSON string to the result map.
     */
    @RequestMapping(value = "/DriverOptions.do", method = RequestMethod.POST)
    public void getAllDriverOptions(@RequestParam(value = "orderID") int orderID, HttpServletResponse resp) throws IOException {
        Map<String, Object> resultMap = new HashMap<>();
        String driverOptionJSONList = driverService.getDriverOptions(orderID);
        resultMap.put("options", driverOptionJSONList);
        gsonParser.parse(resultMap, resp);
    }
}