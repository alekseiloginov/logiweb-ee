package com.tsystems.javaschool.loginov.logiweb.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Simple java bean that will hold order information.
 */
@Entity
@Table(name = "orders", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Order extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "completed", nullable = false)
    private int completed;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "order_drivers", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id"))
    private Set<Driver> drivers;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "order_waypoints", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "waypoint_id"))
    private Set<Waypoint> waypoints;

    public Order() {

    }

    public Order(int completed, Truck truck) {
        this.completed = completed;
        this.truck = truck;
    }

    public Order(int id, int completed, Truck truck) {
        this.id = id;
        this.completed = completed;
        this.truck = truck;
    }

    public Order(int completed, Truck truck, Set<Driver> drivers, Set<Waypoint> waypoints) {
        this.completed = completed;
        this.truck = truck;
        this.drivers = drivers;
        this.waypoints = waypoints;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    public Set<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(Set<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }
}
