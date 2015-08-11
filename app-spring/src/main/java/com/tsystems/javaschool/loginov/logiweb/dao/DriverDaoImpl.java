package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate specific DAO implementation for drivers.
 */
@Repository
public class DriverDaoImpl implements DriverDao {
    private static final Logger LOG = Logger.getLogger(DriverDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Driver addDriver(Driver driver) {
        Session session = this.sessionFactory.getCurrentSession();
        int savedDriverID = (int) session.save(driver);
        LOG.info("Driver saved successfully, Driver details=" + driver);
        return getDriverById(savedDriverID);
    }

    @Override
    public Driver updateDriver(Driver driver) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(driver);
        LOG.info("Driver updated successfully, Driver details=" + driver);
        return getDriverById(driver.getId());
    }

    @Override
    public void updateDriverStatusAndWorkedHours(Driver driver) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(driver);
        LOG.info("Driver status updated successfully, Driver details=" + driver);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Driver> listDrivers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Driver> driverList = session.createQuery("from Driver").list();
        for (Driver driver : driverList) {
            LOG.info("Driver list::" + driver);
        }
        return driverList;
    }

    @Override
    public Driver getDriverById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Driver) session.get(Driver.class, id);
    }

    @Override
    public Driver getDriverByEmail(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Driver) session.createCriteria(Driver.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public void removeDriver(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Driver driver = getDriverById(id);
        if (driver != null){
            session.delete(driver);
            LOG.info("Driver deleted successfully, Driver details=" + driver);
        }
    }

    @Override
    public List getFreeDriversInCity(int locationID, String freeDriverStatus) {
        Session session = sessionFactory.getCurrentSession();
        Query driverQuery = session.createQuery("from Driver where location_id = :locationID and status =:status");
        driverQuery.setInteger("locationID", locationID);
        driverQuery.setString("status", freeDriverStatus);
        return driverQuery.list();
    }
}
