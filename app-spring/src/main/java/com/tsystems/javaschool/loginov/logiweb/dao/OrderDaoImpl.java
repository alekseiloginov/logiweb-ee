package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.*;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Hibernate specific DAO implementation for orders.
 */
@Repository
public class OrderDaoImpl implements OrderDao {
    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order addOrder(Order order) {
        Session session = this.sessionFactory.getCurrentSession();

        Query truckQuery = session.createQuery("from Truck where plate_number = :plate_number");
        truckQuery.setString("plate_number", order.getTruck().getPlate_number());
        Truck dbTruck = (Truck) truckQuery.uniqueResult();
        order.setTruck(dbTruck);

        int savedOrderID = (int) session.save(order);

        Query orderQuery = session.createQuery("from Order where id = :savedOrderID");
        orderQuery.setInteger("savedOrderID", savedOrderID);
        Order savedOrder = (Order) orderQuery.uniqueResult();
        LOG.info("Order saved successfully, Order details=" + savedOrder);

        return savedOrder;
    }

    @Override
    public Order updateOrder(Order order) {
        Session session = this.sessionFactory.getCurrentSession();

        Query orderQuery = session.createQuery("from Order where id = :id");
        orderQuery.setInteger("id", order.getId());
        Order orderToUpdate = (Order) orderQuery.uniqueResult();
        orderToUpdate.setCompleted(order.getCompleted());

        Query truckQuery = session.createQuery("from Truck where plate_number = :plate_number");
        truckQuery.setString("plate_number", order.getTruck().getPlate_number());
        Truck dbTruck = (Truck) truckQuery.uniqueResult();
        orderToUpdate.setTruck(dbTruck);

        session.update(orderToUpdate);

        Order updatedOrder = (Order) orderQuery.uniqueResult();
        LOG.info("Order updated successfully, Order details=" + updatedOrder);

        return updatedOrder;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> listOrders() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Order> orderList = session.createQuery("from Order").list();
        for (Order order : orderList) {
            LOG.info("Order list::" + order);
        }
        return orderList;
    }

    @Override
    public Order getOrderById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Order order = (Order) session.get(Order.class, id);
        LOG.info("Order by id loaded successfully, Order details=" + order);
        return order;
    }

    @Override
    public Order getOrderByDriver(Driver driver) {
        Order order = null;
        Session session = this.sessionFactory.getCurrentSession();

        int driverId = driver.getId();

        List orderIdList = session.createSQLQuery("SELECT order_id FROM order_drivers WHERE driver_id="+driverId).list();

        if (!orderIdList.isEmpty()) {
            int orderId = (int) orderIdList.get(0);
            order = (Order) session.get(Order.class, orderId);
            LOG.info("Order by driver loaded successfully, Order details=" + order);
        } else {
            LOG.info("No freights found for the Drivers=" + driver);
        }
        return order;
    }

    @Override
    public void removeOrder(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Order order = (Order) session.load(Order.class, new Integer(id));
        if (order != null){
            session.delete(order);
        }
        LOG.info("Order deleted successfully, Order details=" + order);
    }

    /**
     * Gets all waypoints for the provided order ID from the database and returns them as a set of waypoints.
     */
    @Override
    public Set<Waypoint> getAllOrderWaypoints(Integer orderID) {
        Session session = sessionFactory.getCurrentSession();
        Query orderQuery = session.createQuery("from Order where id = :orderID");
        orderQuery.setInteger("orderID", orderID);
        Order order = (Order) orderQuery.uniqueResult();
        return order.getWaypoints();
    }

    /**
     * Saves a waypoint to the database and returns saved object.
     */
    @Override
    public Waypoint saveOrderWaypoint(int orderID, String waypointCity, String waypointFreightName) {
        Session session = sessionFactory.getCurrentSession();

        Query orderQuery = session.createQuery("from Order where id = :orderID");
        orderQuery.setInteger("orderID", orderID);
        Order order = (Order) orderQuery.uniqueResult();

        // get a location object of the chosen city
        Query locationQuery = session.createQuery("from Location where city = :waypointCity");
        locationQuery.setString("waypointCity", waypointCity);
        Location dbLocation = (Location) locationQuery.uniqueResult();
        int locationID = dbLocation.getId();

        // get a freight object of the chosen freight name
        Query freightQuery = session.createQuery("from Freight where name = :waypointFreightName");
        freightQuery.setString("waypointFreightName", waypointFreightName);
        Freight dbFreight = (Freight) freightQuery.uniqueResult();
        int freightID = dbFreight.getId();

        Query waypointQuery = session.createQuery("from Waypoint where location_id = :locationID and freight_id = :FreightID");
        waypointQuery.setInteger("locationID", locationID);
        waypointQuery.setInteger("FreightID", freightID);
        Waypoint waypoint = (Waypoint) waypointQuery.list().get(0);  // may be two similar freight in two similar cities!

        // assign an waypoint to the order and update the order in the database
        Set<Waypoint> waypointSet = order.getWaypoints();
        waypointSet.add(waypoint);
        order.setWaypoints(waypointSet);
        session.update(order);

        return waypoint;
    }
}
