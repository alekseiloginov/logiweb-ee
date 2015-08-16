package com.tsystems.javaschool.loginov.logiweb.models;

import javax.persistence.*;

/**
 * Simple java bean that will hold location information.
 */
@Entity
@Table(name = "locations", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "city"})})
public class Location extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "city", nullable = false, length = 255)
    private String city;

    public Location() {

    }

    public Location(String city) {
        this.city = city;
    }

    public Location(int id, String city) {
        this.id = id;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
