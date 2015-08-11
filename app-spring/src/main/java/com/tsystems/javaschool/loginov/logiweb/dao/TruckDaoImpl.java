package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Truck;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate specific DAO implementation for trucks.
 */
@Repository
public class TruckDaoImpl implements TruckDao {
    private static final Logger LOG = Logger.getLogger(TruckDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Truck addTruck(Truck truck) {
        Session session = this.sessionFactory.getCurrentSession();
        int savedTruckID = (int) session.save(truck);
        LOG.info("Truck saved successfully, Truck details=" + truck);
        return getTruckById(savedTruckID);
    }

    @Override
    public Truck updateTruck(Truck truck) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(truck);
        LOG.info("Truck updated successfully, Truck details=" + truck);
        return getTruckById(truck.getId());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Truck> listTrucks() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Truck").list();
    }

    @Override
    public Truck getTruckById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Truck) session.get(Truck.class, id);
    }

    @Override
    public Truck getTruckByPlateNumber(String plateNumber) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Truck) session.createCriteria(Truck.class)
                .add(Restrictions.eq("plate_number", plateNumber))
                .uniqueResult();
    }

    @Override
    public void removeTruck(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Truck truck = getTruckById(id);
        if (truck != null) {
            session.delete(truck);
            LOG.info("Truck deleted successfully, Truck details=" + truck);
        }
    }

    @Override
    public List getDrivableTrucks() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Truck where drivable = 1").list();
    }
}
