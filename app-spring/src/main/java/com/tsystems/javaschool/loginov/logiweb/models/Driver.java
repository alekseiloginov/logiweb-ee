package com.tsystems.javaschool.loginov.logiweb.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple java bean that will hold driver information.
 */
@XmlRootElement
@Entity
@Table(name = "drivers", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "email"})})
public class Driver extends AbstractUser {

    @Column(name = "worked_hours", nullable = false)
    private int workedHours;

    @Column(name = "status", nullable = false, length = 255)
    private String status;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "truck_id")
    private Truck truck;

    public Driver() {
    }

    public Driver(int id) {
        super(id);
    }

    public Driver(String name, String surname, String email, String password, int workedHours, String status) {
        super(name, surname, email, password);
        this.workedHours = workedHours;
        this.status = status;
    }

    public Driver(int id, String name, String surname, String email, String password, int workedHours, String status) {
        super(id, name, surname, email, password);
        this.workedHours = workedHours;
        this.status = status;
    }

    public int getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}
