package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Waypoint;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate specific DAO implementation for waypoints.
 */
@Repository
public class WaypointDaoImpl implements WaypointDao {
    private static final Logger LOG = Logger.getLogger(WaypointDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addWaypoint(Waypoint waypoint) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(waypoint);
        LOG.info("Waypoint saved successfully, Waypoint details=" + waypoint);
    }

    @Override
    public Waypoint getWaypointByFreightIdAndOperation(int freightId, String operation) {
        Session session = this.sessionFactory.getCurrentSession();
        Query waypointQuery =
                session.createQuery("from Waypoint where freight_id = :freight_id and operation = :operation");
        waypointQuery.setInteger("freight_id", freightId);
        waypointQuery.setString("operation", operation);
        return (Waypoint) waypointQuery.uniqueResult();
    }

    @Override
    public Waypoint getWaypointByFreightIdAndLocationId(int freightId, int locationId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query waypointQuery =
                session.createQuery("from Waypoint where freight_id = :freightId and location_id = :locationId");
        waypointQuery.setInteger("freightId", freightId);
        waypointQuery.setInteger("locationId", locationId);
        return (Waypoint) waypointQuery.list().get(0);  // may be two similar freight in two similar cities!
    }

    @Override
    public List getWaypointListByLocationId(int locationId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query waypointQuery = session.createQuery("from Waypoint where location_id = :locationId");
        waypointQuery.setInteger("locationId", locationId);
        return waypointQuery.list();
    }
}
