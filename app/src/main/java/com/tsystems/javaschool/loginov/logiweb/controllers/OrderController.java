package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.tsystems.javaschool.loginov.logiweb.services.OrderService;
import com.tsystems.javaschool.loginov.logiweb.utils.GsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spring MVC Controller to work with the order data.
 */
@Controller
public class OrderController {

    private OrderService orderService;

    @Autowired(required=true)
    @Qualifier(value="orderService")
    public void setOrderService(OrderService orderService){
        this.orderService = orderService;
    }

//    private ListService listService;
//    private SaveService saveService;
//    private UpdateService updateService;
//    private DeleteService deleteService;
//
//    public OrderController() {
//        listService = ListService.getInstance();
//        saveService = SaveService.getInstance();
//        updateService = UpdateService.getInstance();
//        deleteService = DeleteService.getInstance();
//    }

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
     * Fetches a list of all orders using the OrderService and puts it to the response map.
     */
    @RequestMapping(value = "/OrderList.do", method = RequestMethod.POST)
    public void getAllOrders(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SecurityContextHolderAwareRequestWrapper wrapper = new SecurityContextHolderAwareRequestWrapper(req, "");
        Map<String, Object> resultMap = new HashMap<>();

//        if (wrapper.isUserInRole("ROLE_MANAGER")) {
            List orderList = orderService.listOrders();
            resultMap.put("data", orderList);

//        } else if (wrapper.isUserInRole("ROLE_DRIVER")) {
//            Order order = orderService.getOrderById(truckID);
//            resultMap.put("data", order);
//        }

        new GsonParser().parse(resultMap, resp);
    }

//    /**
//     * Fetches a list of all orders using the ListService and puts it to the response map.
//     */
//    @RequestInfo(value = "OrderList.do", method = "POST")
//    public Map<String, Object> getAllOrders(Map requestParameters) {
//        String role = ((String[]) requestParameters.get("role"))[0];
//        List orderList = null;
//
//        Map<String, Object> response = new HashMap<>();
//
//        if (role.equals("manager")) {
//            String sorting = null;
//            if (requestParameters.containsKey("jtSorting")) {
//                sorting = ((String[]) requestParameters.get("jtSorting"))[0];
//            }
//            orderList = listService.getAllItems("Order", sorting);
//
//        } else if (role.equals("driver")) {
//            int truckID = Integer.parseInt(((String[]) requestParameters.get("truckID"))[0]);
//            orderList = listService.getAllDriverOrders(truckID);
//        }
//
//        response.put("data", orderList);
//        return response;
//    }
//
//    /**
//     * Adds an order to the database using the SaveService and puts the saved object back to the response map.
//     */
//    @RequestInfo(value = "OrderSave.do", method = "POST")
//    public Map<String, Object> saveOrder(Map requestParameters) {
//        int completed = Integer.parseInt(((String[]) requestParameters.get("completed"))[0]);
//        String plate_number = ((String[]) requestParameters.get("truck"))[0];
//
//        Map<String, Object> response = new HashMap<>();
//
//        Object savedOrder = saveService.saveOrder(plate_number, completed);
//
//        response.put("datum", savedOrder);
//        return response;
//    }
//
//    /**
//     * Updates an order in the database using the UpdateService and puts the updated order back to the response map.
//     */
//    @RequestInfo(value = "OrderUpdate.do", method = "POST")
//    public Map<String, Object> updateOrder(Map requestParameters) {
//        int id = Integer.parseInt(((String[]) requestParameters.get("id"))[0]);
//        int completed = Integer.parseInt(((String[]) requestParameters.get("completed"))[0]);
//        String plate_number = ((String[]) requestParameters.get("truck"))[0];
//
//        Map<String, Object> response = new HashMap<>();
//
//        Object updatedOrder = updateService.updateOrder(id, plate_number, completed);
//
//        response.put("datum", updatedOrder);
//        return response;
//    }
//
//    /**
//     * Deletes an order from the database using the DeleteService and puts "OK" back to the response map.
//     */
//    @RequestInfo(value = "OrderDelete.do", method = "POST")
//    public Map<String, Object> deleteOrder(Map requestParameters) {
//        int id = Integer.parseInt(((String[]) requestParameters.get("id"))[0]);
//        Map<String, Object> response = new HashMap<>();
//
//        deleteService.deleteItem("Order", id);
//
//        response.put("OK", "OK");
//        return response;
//    }
}
