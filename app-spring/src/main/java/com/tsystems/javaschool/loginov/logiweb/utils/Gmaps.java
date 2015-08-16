package com.tsystems.javaschool.loginov.logiweb.utils;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsRoute;
import org.apache.log4j.Logger;

import java.util.Set;

/**
 * Utility class to calculate a journey duration using Google Maps API implementation.
 */
public class Gmaps {
    private static final Logger LOG = Logger.getLogger(Gmaps.class);
    private String waypointCities = "optimize:true";

    public long calculateTripDurationInSeconds(Set<String> citySet, String mainCity) {

        // create a string of collected cities to use in Google Maps API
        for (String city : citySet) {
            waypointCities += "|" + city;
        }

        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyAPisnyi8SzMCy1NAZd7XMA6YBWYlFF9w4");
        DirectionsRoute[] routes = new DirectionsRoute[0];

        try {
            routes = DirectionsApi.newRequest(context)
                    .origin(mainCity)                 // origin - city where the order's truck is located
                    .destination(mainCity)
                    .optimizeWaypoints(true)
                    .waypoints(waypointCities)
                    .await();
        } catch (Exception e) {
            LOG.error("Problem with the routes request using Google Maps API.", e);
        }

        DirectionsLeg[] legs = routes[0].legs;
        long durationInSeconds = 0;

        for (DirectionsLeg leg : legs) {
            durationInSeconds += leg.duration.inSeconds;
        }

        return durationInSeconds;
    }
}
