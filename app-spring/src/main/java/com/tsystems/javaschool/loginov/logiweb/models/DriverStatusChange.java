package com.tsystems.javaschool.loginov.logiweb.models;

import javax.persistence.*;

/**
 * Simple java bean that will hold driver status change information.
 */
@Entity
@Table(name = "driver_status_changes", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class DriverStatusChange extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "status", nullable = false, length = 255)
    private String status;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    public DriverStatusChange() {

    }

    public DriverStatusChange(String status, Driver driver) {
        this.status = status;
        this.driver = driver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
