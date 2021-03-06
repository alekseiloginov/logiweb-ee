package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.google.gson.Gson;
import com.tsystems.javaschool.loginov.logiweb.models.Order;
import com.tsystems.javaschool.loginov.logiweb.models.Truck;
import com.tsystems.javaschool.loginov.logiweb.models.Waypoint;
import com.tsystems.javaschool.loginov.logiweb.services.OrderService;
import com.tsystems.javaschool.loginov.logiweb.utils.GsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Spring MVC Controller to work with the order data.
 */
@Controller
public class OrderController {
    private static final String DATUM = "datum";
    private static final String DATA = "data";

    @Autowired
    private OrderService orderService;

    @Autowired
    private GsonParser gsonParser;

    /**
     * Redirects user to the order page.
     */
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelAndView getOrderPage(HttpServletRequest request, ModelAndView model) {

        SecurityContextHolderAwareRequestWrapper wrapper = new SecurityContextHolderAwareRequestWrapper(request, "");

        if (wrapper.isUserInRole("ROLE_MANAGER")) {
            model.setViewName("secure/manager/orders");

        } else if (wrapper.isUserInRole("ROLE_DRIVER")) {
            model.setViewName("secure/driver/orders");
        }

        return model;
    }

    /**
     * Fetches a list of all orders using the OrderService and puts it to the result map.
     */
    @RequestMapping(value = "/OrderList.do", method = RequestMethod.POST)
    public void getAllOrders(HttpServletRequest req, HttpServletResponse resp, Principal principal) throws IOException {

        SecurityContextHolderAwareRequestWrapper wrapper = new SecurityContextHolderAwareRequestWrapper(req, "");
        Map<String, Object> resultMap = new HashMap<>();

        if (wrapper.isUserInRole("ROLE_MANAGER")) {
            List orderList = orderService.listOrders();
            resultMap.put(DATA, orderList);

        } else if (wrapper.isUserInRole("ROLE_DRIVER")) {
            Order order = orderService.getOrderByDriverUsername(principal.getName());
            resultMap.put(DATA, order);
        }

        gsonParser.parse(resultMap, resp);
    }

    /**
     * Adds an order to the database using the OrderService and puts the saved object back to the result map.
     */
    @RequestMapping(value = "/OrderSave.do", method = RequestMethod.POST)
    public void saveOrder(@RequestParam(value = "completed") int completed,
                          @RequestParam(value = "truck") String plateNumber,
                          HttpServletResponse resp) throws IOException {

        Map<String, Object> resultMap = new HashMap<>();
        Order savedOrder = orderService.addOrder(new Order(completed, new Truck(plateNumber)));
        resultMap.put(DATUM, savedOrder);
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Updates an order in the database using the OrderService and puts the updated order back to the result map.
     */
    @RequestMapping(value = "/OrderUpdate.do", method = RequestMethod.POST)
    public void updateOrder(@RequestParam(value = "id") int id,
                            @RequestParam(value = "completed") int completed,
                            @RequestParam(value = "truck") String plateNumber,
                            HttpServletResponse resp) throws IOException {

        Map<String, Object> resultMap = new HashMap<>();
        Order updatedOrder = orderService.updateOrder(new Order(id, completed, new Truck(plateNumber)));
        resultMap.put(DATUM, updatedOrder);
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Deletes an order from the database using the OrderService and puts "OK" back to the result map.
     */
    @RequestMapping(value = "/OrderDelete.do", method = RequestMethod.POST)
    public void deleteOrder(@RequestParam(value = "id") int id, HttpServletResponse resp) throws IOException {
        Map<String, Object> resultMap = new HashMap<>();
        orderService.removeOrder(id);
        resultMap.put("OK", "OK");
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Fetches a list of waypoints for the required order using the OrderService and puts it to the result map.
     */
    @RequestMapping(value = "/OrderWaypointList.do", method = RequestMethod.POST)
    public void getAllOrderWaypoints(@RequestParam(value = "orderID", required = false) Integer orderID,
                                     HttpServletResponse resp) throws IOException {

        Map<String, Object> resultMap = new HashMap<>();
        Set<Waypoint> waypointSet = orderService.getAllOrderWaypoints(orderID);
        resultMap.put(DATA, waypointSet);
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Adds a waypoint to the required order using the OrderService and puts the saved waypoint back to the result map.
     */
    @RequestMapping(value = "/OrderWaypointSave.do", method = RequestMethod.POST)
    public void saveOrderWaypoint(@RequestParam(value = "orderID") int orderID,
                                  @RequestParam(value = "location") String waypointCity,
                                  @RequestParam(value = "freight") String waypointFreightName,
                                  HttpServletResponse resp) throws IOException {

        Map<String, Object> resultMap = new HashMap<>();
        Waypoint savedOrderWaypoint = orderService.saveOrderWaypoint(orderID, waypointCity, waypointFreightName);
        resultMap.put(DATUM, savedOrderWaypoint);
        gsonParser.parse(resultMap, resp);
    }

    /**
     * Redirects to the order's map page.
     */
    @RequestMapping(value = "/orders/{orderId}/map", method = RequestMethod.GET)
    public ModelAndView showOrderOnMap(@PathVariable("orderId") int orderId, ModelAndView model) {

        Order order = orderService.getOrderById(orderId);

        if (order.getTruck().getLocation().getCity() != null) {

            String mainCity = order.getTruck().getLocation().getCity();

            Set<Waypoint> waypointSet = order.getWaypoints();

            Set<Waypoint> waypointConcurrentSet = Collections.newSetFromMap(new ConcurrentHashMap<Waypoint, Boolean>());

            waypointConcurrentSet.addAll(waypointSet);

            Set<String> citySet = new HashSet<>();

            // remove main (start/end) waypoint
            for (Waypoint waypoint : waypointConcurrentSet) {
                if (mainCity.equals(waypoint.getLocation().getCity())) {
                    waypointSet.remove(waypoint);
                    continue;
                }

                citySet.add(waypoint.getLocation().getCity());
            }

            Gson gson = new Gson();

            String waypoints = gson.toJson(citySet);

            model.addObject("mainCity", mainCity);

            model.addObject("waypoints", waypoints);
        }

        model.setViewName("gmaps");

        return model;
    }
}
