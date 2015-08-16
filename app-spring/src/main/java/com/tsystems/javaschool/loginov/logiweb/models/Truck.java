package com.tsystems.javaschool.loginov.logiweb.models;

import javax.persistence.*;

/**
 * Simple java bean that will hold truck information.
 */
@Entity
@Table(name = "trucks", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "plate_number"})})
public class Truck extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "plate_number", nullable = false, length = 7)
    private String plateNumber;

    @Column(name = "driver_number", nullable = false)
    private int driverNumber;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "drivable", nullable = false)
    private int drivable;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    public Truck() {

    }

    public Truck(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Truck(String plateNumber, int driverNumber, int capacity, int drivable, Location location) {
        this.plateNumber = plateNumber;
        this.driverNumber = driverNumber;
        this.capacity = capacity;
        this.drivable = drivable;
        this.location = location;
    }

    public Truck(int id, String plateNumber, int driverNumber, int capacity, int drivable, Location location) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.driverNumber = driverNumber;
        this.capacity = capacity;
        this.drivable = drivable;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(int driverNumber) {
        this.driverNumber = driverNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getDrivable() {
        return drivable;
    }

    public void setDrivable(int drivable) {
        this.drivable = drivable;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
