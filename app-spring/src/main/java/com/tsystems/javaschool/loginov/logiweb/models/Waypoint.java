package com.tsystems.javaschool.loginov.logiweb.models;

import javax.persistence.*;

/**
 * Simple java bean that will hold waypoint information.
 */
@Entity
@Table(name = "waypoints", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Waypoint extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "operation", nullable = false, length = 255)
    private String operation;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "freight_id", nullable = false)
    private Freight freight;

    public Waypoint() {

    }

    public Waypoint(String operation, Location location, Freight freight) {
        this.operation = operation;
        this.location = location;
        this.freight = freight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Freight getFreight() {
        return freight;
    }

    public void setFreight(Freight freight) {
        this.freight = freight;
    }
}
