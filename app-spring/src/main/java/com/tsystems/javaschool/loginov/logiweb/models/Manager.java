package com.tsystems.javaschool.loginov.logiweb.models;

import javax.persistence.*;

/**
 * Simple java bean that will hold manager information.
 */
@Entity
@Table(name = "managers", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "email"})})
public class Manager extends AbstractUser {

    public Manager() {

    }

    public Manager(String name, String surname, String email, String password) {
        super(name, surname, email, password);
    }

    public Manager(int id, String name, String surname, String email, String password) {
        super(id, name, surname, email, password);
    }
}
