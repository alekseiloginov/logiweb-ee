package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.tsystems.javaschool.loginov.logiweb.services.LocationService;
import com.tsystems.javaschool.loginov.logiweb.utils.GsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring MVC Controller implementation to work with the location data.
 */
@Controller
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private GsonParser gsonParser;

    /**
     * Fetches all valid location options using the LocationService and puts them as a JSON string to the response map.
     */
    @RequestMapping(value = "/LocationOptions.do", method = RequestMethod.POST)
    public void getAllLocationOptions(HttpServletResponse resp) throws IOException {
        String locationOptionJSONList = locationService.getLocationOptions();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("options", locationOptionJSONList);
        gsonParser.parse(resultMap, resp);
    }
}
