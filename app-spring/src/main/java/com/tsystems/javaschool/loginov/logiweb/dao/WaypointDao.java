package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Waypoint;

import java.util.List;

/**
 * DAO interface to declare the methods that we will use in our project to work with the waypoint data.
 */
public interface WaypointDao {

    void addWaypoint(Waypoint waypoint);

    Waypoint getWaypointByFreightIdAndOperation(int freightId, String operation);

    Waypoint getWaypointByFreightIdAndLocationId(int freightId, int locationId);

    List getWaypointListByLocationId(int locationId);
}
