package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.tsystems.javaschool.loginov.logiweb.ejb.StatusEJB;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * A simple managed bean that is used to invoke the StatusEJB and store the status.
 * The status message is obtained by invoking getStatusMessage().
 */
@ManagedBean(name="driverBean")
@SessionScoped
public class DriverManagedBean {

    @EJB
    private StatusEJB statusEJB;

    private String driverStatusMessage;
    private String freightStatusMessage;

    public void setDriverStatus(String driverStatus) {
        driverStatusMessage = statusEJB.getDriverStatusMessage(driverStatus);
    }

    public String getDriverStatusMessage() {
        return driverStatusMessage;
    }

    public void setFreightStatus(String freightStatus) {
        this.freightStatusMessage = statusEJB.getFreightStatusMessage(freightStatus);
    }

    public String getFreightStatusMessage() {
        return freightStatusMessage;
    }
}
