package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Location;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate specific DAO implementation for locations.
 */
@Repository
public class LocationDaoImpl implements LocationDao {
    private static final Logger LOG = Logger.getLogger(LocationDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Location addLocation(Location location) {
        Session session = this.sessionFactory.getCurrentSession();
        int savedLocationID = (int) session.save(location);
        LOG.info("Location saved successfully, Location details=" + location);
        return getLocationById(savedLocationID);
    }

    @Override
    public Location updateLocation(Location location) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(location);
        LOG.info("Location updated successfully, Location details=" + location);
        return getLocationById(location.getId());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Location> listLocations() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Location").list();
    }

    @Override
    public Location getLocationById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Location) session.get(Location.class, id);
    }

    @Override
    public Location getLocationByCity(String city) {
        Session session = this.sessionFactory.getCurrentSession();
        Query locationQuery = session.createQuery("from Location where city = :city");
        locationQuery.setString("city", city);
        return (Location) locationQuery.uniqueResult();
    }

    @Override
    public void removeLocation(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Location location = getLocationById(id);
        if (location != null) {
            session.delete(location);
            LOG.info("Location deleted successfully, Location details=" + location);
        }
    }
}
