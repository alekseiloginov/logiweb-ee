package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.dao.OrderDao;
import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.Order;
import com.tsystems.javaschool.loginov.logiweb.models.Waypoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Service class that uses Hibernate DAO classes to work with Order objects.
 */
@Service
public class OrderService {

    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Transactional
    public Order addOrder(Order order) {
        return this.orderDao.addOrder(order);
    }

    @Transactional
    public Order updateOrder(Order order) {
        return this.orderDao.updateOrder(order);
    }

    @Transactional
    public List<Order> listOrders() {
        return this.orderDao.listOrders();
    }

    @Transactional
    public Order getOrderById(int id) {
        return this.orderDao.getOrderById(id);
    }

    @Transactional
    public Order getOrderByDriver(Driver driver) {
        return this.orderDao.getOrderByDriver(driver);
    }

    @Transactional
    public void removeOrder(int id) {
        this.orderDao.removeOrder(id);
    }

    @Transactional
    public Set<Waypoint> getAllOrderWaypoints(Integer orderID) {
        return this.orderDao.getAllOrderWaypoints(orderID);
    }

    @Transactional
    public Waypoint saveOrderWaypoint(int orderID, String waypointCity, String waypointFreightName) {
        return this.orderDao.saveOrderWaypoint(orderID, waypointCity, waypointFreightName);
    }
}
