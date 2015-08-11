package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.exceptions.DuplicateEntryException;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
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
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Location addLocation(Location location) throws DuplicateEntryException {
        return null;
    }

    @Override
    public void updateLocation(Location location) {

    }

    @Override
    public List<Location> listLocations() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Location").list();
    }

    @Override
    public Location getLocationById(int id) {
        return null;
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

    }
}
