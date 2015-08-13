package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.tsystems.javaschool.loginov.logiweb.ejb.StatusEJB;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple managed bean that is used to invoke the StatusEJB and store the status.
 * The status message is obtained by invoking getStatusMessage().
 */
@ManagedBean(name="driverBean")
@SessionScoped
public class DriverManagedBean {
    private static final Logger LOG = Logger.getLogger(DriverManagedBean.class);
    private FacesMessage message;
    private String result = null;

    @EJB
    private StatusEJB statusEJB;

    /**
     * Gets driver status list
     *
     * @param driverId
     * @return
     */
    public List<String> getDriverStatusList(Integer driverId) {
        List<String> driverStatusList = new ArrayList<>(Arrays.asList(new String[]{"free", "shift", "driving"}));
        String currentDriverStatus = getDriverStatus(driverId).toLowerCase();
        driverStatusList.remove(currentDriverStatus);
        return driverStatusList;
    }

    /**
     * Sets driver status
     *
     * @param driverId
     * @param driverStatus
     */
    public void setDriverStatus(Integer driverId, String driverStatus) {

        if (driverStatus.isEmpty()) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry, the driver is already in that status!", null);
            FacesContext.getCurrentInstance().addMessage("driverStatusForm", message);
            return;
        }

        result = statusEJB.setDriverStatus(driverId, driverStatus);
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, result + " Current status of driver with id #"
                + driverId + " now is " + driverStatus.toUpperCase(), null);

        FacesContext.getCurrentInstance().addMessage("driverStatusForm", message);
    }

    /**
     * Gets driver status
     *
     * @param driverId
     * @return
     */
    public String getDriverStatus(Integer driverId) {
        return statusEJB.getDriverStatus(driverId);
    }

    /**
     *
     * @param freightId
     * @param freightStatus
     */
    public void setFreightStatus(Integer freightId, String freightStatus) {

        try {
            result = statusEJB.setFreightStatus(freightId, freightStatus);
            if (!result.isEmpty()) {

                message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Freight status successfully saved! Current status of freight with id #"
                                + freightId + " now is " + freightStatus.toUpperCase(), null);
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Can't set this freight status with the mentioned freight id.", null);
                LOG.error("Exception while setting a new freight status");
            }

        } catch (IOException e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "Can't set this freight status with the mentioned freight id.", null);
            LOG.error("Exception while setting a new freight status", e);
        }
        FacesContext.getCurrentInstance().addMessage("freightStatusForm", message);
    }
}
