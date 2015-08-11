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
    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order addOrder(Order order) {
        Session session = this.sessionFactory.getCurrentSession();
        int savedOrderID = (int) session.save(order);
        LOG.info("Order saved successfully, Order details=" + order);
        return getOrderById(savedOrderID);
    }

    @Override
    public Order updateOrder(Order order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(order);
        LOG.info("Order updated successfully, Order details=" + order);
        return getOrderById(order.getId());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> listOrders() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Order").list();
    }

    @Override
    public Order getOrderById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Order) session.get(Order.class, id);
    }

    @Override
    public Order getOrderByDriver(Driver driver) {
        Session session = this.sessionFactory.getCurrentSession();
        List orderIdList =
                session.createSQLQuery("SELECT order_id FROM order_drivers WHERE driver_id="+driver.getId()).list();
        return (Order) session.get(Order.class, (int) orderIdList.get(0));
    }

    @Override
    public void removeOrder(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Order order = getOrderById(id);
        if (order != null){
            session.delete(order);
            LOG.info("Order deleted successfully, Order details=" + order);
        }
    }
}
