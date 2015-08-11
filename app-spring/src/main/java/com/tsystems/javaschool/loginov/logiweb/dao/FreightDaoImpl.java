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
    private static final Logger LOG = Logger.getLogger(FreightDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Freight addFreight(Freight freight) {
        Session session = this.sessionFactory.getCurrentSession();
        int savedFreightID = (int) session.save(freight);
        LOG.info("Freight saved successfully, Freight details=" + freight);
        return getFreightById(savedFreightID);
    }

    @Override
    public Freight updateFreight(Freight freight) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(freight);
        LOG.info("Freight updated successfully, Freight details=" + freight);
        return getFreightById(freight.getId());
    }

    @Override
    public void updateFreightStatus(Freight freight) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(freight);
        LOG.info("Freight status updated successfully, Freight details=" + freight);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Freight> listFreights() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Freight").list();
    }

    @Override
    public Freight getFreightById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Freight) session.get(Freight.class, id);
    }

    @Override
    public Freight getFreightByName(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Freight) session.createCriteria(Freight.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
    }

    @Override
    public void removeFreight(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Freight freight = getFreightById(id);
        if (freight != null) {
            session.delete(freight);
            LOG.info("Freight deleted successfully, Freight details=" + freight);
        }
    }
}
