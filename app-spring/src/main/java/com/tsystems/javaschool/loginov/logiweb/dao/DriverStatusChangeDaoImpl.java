package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.DriverStatusChange;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Hibernate specific DAO implementation for driver status changes.
 */
@Repository
public class DriverStatusChangeDaoImpl implements DriverStatusChangeDao {
    private static final Logger LOG = Logger.getLogger(DriverStatusChangeDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveDriverStatus(DriverStatusChange driverStatusChange) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(driverStatusChange);
        LOG.info("DriverStatusChange saved successfully, DriverStatusChange details=" + driverStatusChange);
    }

    @Override
    public void updateDriverStatus(DriverStatusChange driverStatusChange) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(driverStatusChange);
        LOG.info("DriverStatusChange updated successfully, DriverStatusChange details=" + driverStatusChange);
    }

    @Override
    public DriverStatusChange getDriverStatusChange(Driver driver, String driverStatus) {
        Session session = this.sessionFactory.getCurrentSession();
        return (DriverStatusChange) session.createCriteria(DriverStatusChange.class)
                .add(Restrictions.eq("driver", driver))
                .add(Restrictions.eq("status", driverStatus))
                .uniqueResult();
    }
}
