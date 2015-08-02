package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Freight;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

    public void updateFreightStatus(Freight freight) {
        Session session = this.sessionFactory.getCurrentSession();
        int freightId = freight.getId();
        String newFreightStatus = freight.getStatus();

        Freight dbFreight = (Freight) session.createCriteria(Freight.class)
                .add(Restrictions.eq("id", freightId))
                .uniqueResult();

        dbFreight.setStatus(newFreightStatus);

        session.update(dbFreight);
        logger.info("Freight status updated successfully, Freight details=" + dbFreight);
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
        Freight freight = (Freight) session.get(Freight.class, id);
        logger.info("Freight by id loaded, Freight details=" + freight);
        return freight;
    }

    public void removeFreight(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Freight freight = (Freight) session.get(Freight.class, id);
        if (freight != null) {
            session.delete(freight);
            logger.info("Freight deleted successfully, Freight details=" + freight);
        }
    }
}
