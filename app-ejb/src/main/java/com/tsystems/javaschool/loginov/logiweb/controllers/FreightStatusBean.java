package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.tsystems.javaschool.loginov.logiweb.ejb.StatusEJB;
import com.tsystems.javaschool.loginov.logiweb.models.Freight;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple managed bean responsible to handle freight status changes.
 */
@ManagedBean
@SessionScoped
public class FreightStatusBean implements Serializable {
    private static Logger logger = Logger.getLogger(FreightStatusBean.class);
    private Freight freight;

    @EJB
    private StatusEJB statusEJB;

    @PostConstruct
    public void init() {
        freight = new Freight();
    }

    public Freight getFreight() {
        return freight;
    }

    public void setFreight(Freight freight) {
        this.freight = freight;
    }

    public List<Freight> getFreights(int driverId) throws Exception {
        return statusEJB.getFreightList(driverId);
    }

    public String updateFreight(Integer freightId, String freightStatus){

        FacesMessage message;
        try {
            String result = statusEJB.setFreightStatus(freightId, freightStatus);
            if (!result.isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Freight status successfully saved! Current status of freight with id #"
                                + freightId + " now is " + freightStatus.toUpperCase(), null);
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Can't set this freight status with the mentioned freight id.", null);
                logger.error("Exception while setting a new freight status");
            }

        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Can't set this freight status with the mentioned freight id.", null);
            logger.error("Exception while setting a new freight status", e);
        }
        FacesContext.getCurrentInstance().addMessage("freightStatusForm", message);

        return "pm:list?transition=flip";
    }

    public List<String> getFreightStatusList(String freightStatus) {
        List<String> freightStatusList = new ArrayList<>();

        if (freightStatus.equalsIgnoreCase("prepared")) {
            freightStatusList.add("shipped");
            freightStatusList.add("delivered");

        } else if (freightStatus.equalsIgnoreCase("shipped")) {
            freightStatusList.add("delivered");
        }

        return freightStatusList;
    }
}
