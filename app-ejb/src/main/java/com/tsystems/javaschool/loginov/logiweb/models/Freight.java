package com.tsystems.javaschool.loginov.logiweb.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Simple java bean that will hold freight information.
 */
@Entity
@Table(name = "freights", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@XmlRootElement(name="Freight")
public class Freight extends AbstractModel implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "status", nullable = false, length = 255)
    private String status;

    // below 2 fields are only for easy data manipulation, not for persistence
    @Transient
    private String loading;
    @Transient
    private String unloading;

    public Freight() {

    }

    public Freight(String name, int weight, String status) {
        this.name = name;
        this.weight = weight;
        this.status = status;
    }

    public Freight(String name, int weight, String status, String loading, String unloading) {
        this.name = name;
        this.weight = weight;
        this.status = status;
        this.loading = loading;
        this.unloading = unloading;
    }

    public Freight(int id, String name, int weight, String status, String loading, String unloading) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.status = status;
        this.loading = loading;
        this.unloading = unloading;
    }

    public String getLoading() {
        return loading;
    }

    public void setLoading(String loading) {
        this.loading = loading;
    }

    public String getUnloading() {
        return unloading;
    }

    public void setUnloading(String unloading) {
        this.unloading = unloading;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
