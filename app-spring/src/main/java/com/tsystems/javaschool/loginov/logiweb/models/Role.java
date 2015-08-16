package com.tsystems.javaschool.loginov.logiweb.models;

import javax.persistence.*;

/**
 * Simple java bean that will hold role information.
 */
@Entity
@Table(name = "roles", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "title"})})
public class Role extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    public Role() {

    }

    public Role(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
