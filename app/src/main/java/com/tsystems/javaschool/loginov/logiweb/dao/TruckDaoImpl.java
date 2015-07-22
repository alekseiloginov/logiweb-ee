package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Truck;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate specific DAO implementation for trucks.
 */
@Repository
public class TruckDaoImpl implements TruckDao {
    private static Logger logger = Logger.getLogger(TruckDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void addTruck(Truck truck) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(truck);
        logger.info("Truck saved successfully, Truck details=" + truck);
    }

    public void updateTruck(Truck truck) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(truck);
        logger.info("Truck updated successfully, Truck details=" + truck);
    }

    @SuppressWarnings("unchecked")
    public List<Truck> listTrucks() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Truck> truckList = session.createQuery("from Truck").list();
        for (Truck truck : truckList) {
            logger.info("Truck list::" + truck);
        }
        return truckList;
    }

    public Truck getTruckById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Truck truck = (Truck) session.load(Truck.class, new Integer(id));
        logger.info("Truck loaded successfully, Truck details=" + truck);
        return truck;
    }

    public void removeTruck(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Truck truck = (Truck) session.load(Truck.class, new Integer(id));
        if (truck != null){
            session.delete(truck);
        }
        logger.info("Truck deleted successfully, Truck details=" + truck);
    }
}
