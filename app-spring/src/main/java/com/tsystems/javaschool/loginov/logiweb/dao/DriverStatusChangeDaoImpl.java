package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.DriverStatusChange;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Hibernate specific DAO implementation for driver status changes.
 */
@Repository
public class DriverStatusChangeDaoImpl implements DriverStatusChangeDao {
    private static Logger logger = Logger.getLogger(DriverStatusChangeDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdateDriverStatus(DriverStatusChange driverStatusChange) {
        Session session = this.sessionFactory.getCurrentSession();
        int driverId = driverStatusChange.getDriver().getId();
        String driverStatus = driverStatusChange.getStatus();

        Driver dbDriver =
                (Driver) session.createCriteria(Driver.class).add(Restrictions.eq("id", driverId)).uniqueResult();

        DriverStatusChange dbDriverStatusChange = (DriverStatusChange) session.createCriteria(DriverStatusChange.class)
                .add(Restrictions.eq("driver", dbDriver))
                .add(Restrictions.eq("status", driverStatus))
                .uniqueResult();

        if (dbDriverStatusChange != null) {
            dbDriverStatusChange.setLast_modified_time(new Date());
            session.update(dbDriverStatusChange);
            logger.info("DriverStatusChange updated successfully, DriverStatusChange details=" + driverStatusChange);

        } else {
            driverStatusChange.setDriver(dbDriver);
            session.save(driverStatusChange);
            logger.info("DriverStatusChange saved successfully, DriverStatusChange details=" + driverStatusChange);
        }
    }
}
