package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate specific DAO implementation for drivers.
 */
@Repository
public class DriverDaoImpl implements DriverDao {
    private static Logger logger = Logger.getLogger(DriverDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void addDriver(Driver driver) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(driver);
        logger.info("Driver saved successfully, Driver details=" + driver);
    }

    public void updateDriver(Driver driver) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(driver);
        logger.info("Driver updated successfully, Driver details=" + driver);
    }

    @SuppressWarnings("unchecked")
    public List<Driver> listDrivers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Driver> driverList = session.createQuery("from Driver").list();
        for (Driver driver : driverList) {
            logger.info("Driver list::" + driver);
        }
        return driverList;
    }

    public Driver getDriverById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Driver driver = (Driver) session.load(Driver.class, new Integer(id));
        logger.info("Driver loaded successfully, Driver details=" + driver);
        return driver;
    }

    public void removeDriver(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Driver driver = (Driver) session.load(Driver.class, new Integer(id));
        if (driver != null){
            session.delete(driver);
        }
        logger.info("Driver deleted successfully, Driver details=" + driver);
    }
}
