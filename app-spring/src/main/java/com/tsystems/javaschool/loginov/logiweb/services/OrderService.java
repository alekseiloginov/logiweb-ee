package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.dao.*;
import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.Order;
import com.tsystems.javaschool.loginov.logiweb.models.User;
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
    private TruckDao truckDao;
    private LocationDao locationDao;
    private FreightDao freightDao;
    private WaypointDao waypointDao;
    private UserDao userDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setTruckDao(TruckDao truckDao) {
        this.truckDao = truckDao;
    }

    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public void setFreightDao(FreightDao freightDao) {
        this.freightDao = freightDao;
    }

    public void setWaypointDao(WaypointDao waypointDao) {
        this.waypointDao = waypointDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public Order addOrder(Order order) {
        order.setTruck(truckDao.getTruckByPlateNumber(order.getTruck().getPlate_number()));
        return this.orderDao.addOrder(order);
    }

    @Transactional
    public Order updateOrder(Order order) {
        Order orderToUpdate = orderDao.getOrderById(order.getId());
        orderToUpdate.setCompleted(order.getCompleted());
        orderToUpdate.setTruck(truckDao.getTruckByPlateNumber(order.getTruck().getPlate_number()));
        return this.orderDao.updateOrder(orderToUpdate);
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
    public Order getOrderByDriverUsername(String driverUsername) {
        User user = userDao.getUserByUsername(driverUsername);
        return this.orderDao.getOrderByDriver(new Driver(user.getId()));
    }

    @Transactional
    public void removeOrder(int id) {
        this.orderDao.removeOrder(id);
    }

    @Transactional
    public Set<Waypoint> getAllOrderWaypoints(Integer orderID) {
        Order order = orderDao.getOrderById(orderID);
        return order.getWaypoints();
    }

    @Transactional
    public Waypoint saveOrderWaypoint(int orderID, String waypointCity, String waypointFreightName) {
        Order order = orderDao.getOrderById(orderID);
        // get a freight ID of the chosen freight name
        int freightID = freightDao.getFreightByName(waypointFreightName).getId();
        // get a location ID of the chosen city
        int locationID = locationDao.getLocationByCity(waypointCity).getId();

        Waypoint waypoint = waypointDao.getWaypointByFreightIdAndLocationId(freightID, locationID);

        // assign a waypoint to the order and update the order in the database
        order.getWaypoints().add(waypoint);
        orderDao.updateOrder(order);

        return waypoint;
    }
}
