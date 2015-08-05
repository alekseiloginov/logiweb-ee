package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.Order;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate specific DAO implementation for orders.
 */
@Repository
public class OrderDaoImpl implements OrderDao {
    private static Logger logger = Logger.getLogger(OrderDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void addOrder(Order order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(order);
        logger.info("Order saved successfully, Order details=" + order);
    }

    public void updateOrder(Order order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(order);
        logger.info("Order updated successfully, Order details=" + order);
    }

    @SuppressWarnings("unchecked")
    public List<Order> listOrders() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Order> orderList = session.createQuery("from Order").list();
        for (Order order : orderList) {
            logger.info("Order list::" + order);
        }
        return orderList;
    }

    public Order getOrderById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Order order = (Order) session.get(Order.class, id);
        logger.info("Order by id loaded successfully, Order details=" + order);
        return order;
    }

    public Order getOrderByDriver(Driver driver) {
        Order order = null;
        Session session = this.sessionFactory.getCurrentSession();

        int driverId = driver.getId();

        List orderIdList = session.createSQLQuery("SELECT order_id FROM order_drivers WHERE driver_id="+driverId).list();

        if (!orderIdList.isEmpty()) {
            int orderId = (int) orderIdList.get(0);
            order = (Order) session.get(Order.class, orderId);
            logger.info("Order by driver loaded successfully, Order details=" + order);
        } else {
            logger.info("No freights found for the Drivers=" + driver);
        }
        return order;
    }

    public void removeOrder(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Order order = (Order) session.load(Order.class, new Integer(id));
        if (order != null){
            session.delete(order);
        }
        logger.info("Order deleted successfully, Order details=" + order);
    }
}
