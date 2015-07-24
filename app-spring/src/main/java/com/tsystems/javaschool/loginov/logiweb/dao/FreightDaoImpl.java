package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Freight;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate specific DAO implementation for freights.
 */
@Repository
public class FreightDaoImpl implements FreightDao {
    private static Logger logger = Logger.getLogger(FreightDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void addFreight(Freight freight) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(freight);
        logger.info("Freight saved successfully, Freight details=" + freight);
    }

    public void updateFreight(Freight freight) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(freight);
        logger.info("Freight updated successfully, Freight details=" + freight);
    }

    @SuppressWarnings("unchecked")
    public List<Freight> listFreights() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Freight> freightList = session.createQuery("from Freight").list();
        for (Freight freight : freightList) {
            logger.info("Freight list::" + freight);
        }
        return freightList;
    }

    public Freight getFreightById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Freight freight = (Freight) session.load(Freight.class, new Integer(id));
        logger.info("Freight loaded successfully, Freight details=" + freight);
        return freight;
    }

    public void removeFreight(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Freight freight = (Freight) session.load(Freight.class, new Integer(id));
        if (freight != null){
            session.delete(freight);
        }
        logger.info("Freight deleted successfully, Freight details=" + freight);
    }
}