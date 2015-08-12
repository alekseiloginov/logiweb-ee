package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.tsystems.javaschool.loginov.logiweb.models.Location;
import com.tsystems.javaschool.loginov.logiweb.services.LocationService;
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

/**
 * Spring MVC Controller implementation to work with the location data.
 */
@Controller
public class LocationController {
    private static final Logger LOG = Logger.getLogger(LocationController.class);
    private static final String JTABLE_ERROR_MESSAGE = "jTableError";
    private static final String DATUM = "datum";
    private static final String DATA = "data";
    private Map<String, Object> resultMap;

    @Autowired
    private LocationService locationService;

    @Autowired
    private GsonParser gsonParser;

    /**
     * Redirects user to the locations page.
     */
    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public String getLocationPage() {
        return "secure/manager/locations";
    }

    /**
     * Fetches a list of all locations using the LocationService and puts it to the result map.
     */
    @RequestMapping(value = "/LocationList.do", method = RequestMethod.POST)
    public void getAllLocations(HttpServletResponse resp) throws IOException {
        List locationList = locationService.listLocations();
        resultMap = new HashMap<>();
        resultMap.put(DATA, locationList);
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Adds a location to the database using the LocationService and puts the saved location back to the result map.
     */
    @RequestMapping(value = "/LocationSave.do", method = RequestMethod.POST)
    public void saveLocation(@RequestParam(value = "city") String city, HttpServletResponse resp) throws IOException {
        resultMap = new HashMap<>();
        try {
            Location savedLocation = locationService.addLocation(new Location(city));
            resultMap.put(DATUM, savedLocation);

        } catch (ConstraintViolationException e) {
            LOG.error("Problem with Location saving", e);
            resultMap.put(JTABLE_ERROR_MESSAGE, e.getCause().getMessage());
        }
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Updates a location in the database using the LocationService and puts the updated location back to the result map.
     */
    @RequestMapping(value = "/LocationUpdate.do", method = RequestMethod.POST)
    public void updateLocation(@RequestParam(value = "id") int id,
                               @RequestParam(value = "city") String city, HttpServletResponse resp) throws IOException {
        resultMap = new HashMap<>();
        try {
            Location updatedLocation = locationService.updateLocation(new Location(id, city));
            resultMap.put(DATUM, updatedLocation);

        } catch (DataIntegrityViolationException e) {
            LOG.error("Problem with Location updating", e);
            resultMap.put(JTABLE_ERROR_MESSAGE, e.getRootCause().getMessage());
        }
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Deletes a location from the database using the LocationService and puts "OK" back to the result map.
     */
    @RequestMapping(value = "/LocationDelete.do", method = RequestMethod.POST)
    public void deleteLocation(@RequestParam(value = "id") int id, HttpServletResponse resp) throws IOException {
        locationService.removeLocation(id);
        resultMap = new HashMap<>();
        resultMap.put("OK", "OK");
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Fetches all valid location options using the LocationService and puts them as a JSON string to the response map.
     */
    @RequestMapping(value = "/LocationOptions.do", method = RequestMethod.POST)
    public void getAllLocationOptions(HttpServletResponse resp) throws IOException {
        String locationOptionJSONList = locationService.getLocationOptions();
        resultMap = new HashMap<>();
        resultMap.put("options", locationOptionJSONList);
        gsonParser.parse(resultMap, resp);
    }
}
