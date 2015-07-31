package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.tsystems.javaschool.loginov.logiweb.ejb.StatusEJB;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * A simple managed bean that is used to invoke the StatusEJB and store the status.
 * The status message is obtained by invoking getStatusMessage().
 */
@ManagedBean(name="driverBean")
@ApplicationScoped
public class DriverManagedBean {
    private static Logger logger = Logger.getLogger(DriverManagedBean.class);
    FacesMessage message;
    String result = null;

    @EJB
    private StatusEJB statusEJB;

    public void setDriverStatus(Integer driverId, String driverStatus) {

        try {
            result = statusEJB.setDriverStatus(driverId, driverStatus);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, result + " Current status of driver with id #"
                                        + driverId + " now is " + driverStatus.toUpperCase(), null);

        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "Can't set this driver status with the current driver id.", null);
            logger.error("Exception while setting a new driver status", e);
            e.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage("driverStatusForm", message);
    }

    public void setFreightStatus(Integer freightId, String freightStatus) {

        try {
            result = statusEJB.setFreightStatus(freightId, freightStatus);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                                        "Freight status successfully saved! Current status of freight with id #"
                                        + freightId + " now is " + freightStatus.toUpperCase(), null);

        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "Can't set this freight status with the mentioned freight id.", null);
            logger.error("Exception while setting a new freight status", e);
            e.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage("freightStatusForm", message);
    }
}
