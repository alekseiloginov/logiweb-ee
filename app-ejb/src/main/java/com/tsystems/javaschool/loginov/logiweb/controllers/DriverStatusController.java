package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.tsystems.javaschool.loginov.logiweb.ejb.DriverStatusEJB;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * A simple managed bean that is used to invoke the DriverStatusEJB and store the status.
 * The status message is obtained by invoking getStatusMessage().
 */
@ManagedBean(name="driverStatus")
@SessionScoped
public class DriverStatusController {

    /**
     * Injected DriverStatusEJB client
     */
    @EJB
    private DriverStatusEJB driverStatusEJB;

    private String message;

    public void setStatus(String status) {
        message = driverStatusEJB.getStatusMessage(status);
    }

    public String getMessage() {
        return message;
    }
}
