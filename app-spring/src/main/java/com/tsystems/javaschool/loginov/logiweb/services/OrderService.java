package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.dao.OrderDao;
import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void addOrder(Order order) {
        this.orderDao.addOrder(order);
    }

    @Transactional
    public void updateOrder(Order order) {
        this.orderDao.updateOrder(order);
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
}
