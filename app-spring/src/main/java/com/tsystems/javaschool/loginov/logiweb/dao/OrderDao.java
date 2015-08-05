package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.Order;

import java.util.List;

/**
 * DAO interface to declare the methods that we will use in our project to work with the order data.
 */
public interface OrderDao {

    void addOrder(Order order);

    void updateOrder(Order order);

    List<Order> listOrders();

    Order getOrderById(int id);

    Order getOrderByDriver(Driver driver);

    void removeOrder(int id);
}
