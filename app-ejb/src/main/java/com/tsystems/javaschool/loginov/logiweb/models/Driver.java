package com.tsystems.javaschool.loginov.logiweb.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple java bean that will hold driver information.
 */
@XmlRootElement
@Entity
@Table(name = "drivers", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "email"})})
public class Driver extends AbstractModel {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "surname", nullable = false, length = 255)
    private String surname;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
