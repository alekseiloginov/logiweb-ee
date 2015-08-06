package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.Order;
import com.tsystems.javaschool.loginov.logiweb.models.Waypoint;

import java.util.List;
import java.util.Set;

/**
 * DAO interface to declare the methods that we will use in our project to work with the order data.
 */
public interface OrderDao {

    Order addOrder(Order order);

    Order updateOrder(Order order);

    List<Order> listOrders();

    Order getOrderById(int id);

    Order getOrderByDriver(Driver driver);

    void removeOrder(int id);

    Set<Waypoint> getAllOrderWaypoints(Integer orderID);

    Waypoint saveOrderWaypoint(int orderID, String waypointCity, String waypointFreightName);
}
