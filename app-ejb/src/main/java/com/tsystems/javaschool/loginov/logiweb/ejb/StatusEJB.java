package com.tsystems.javaschool.loginov.logiweb.ejb;

import javax.ejb.Stateful;

/**
 * A simple Status EJB. The EJB does not use an interface.
 */
@Stateful
public class StatusEJB {

    /**
     * This method takes a driver status and returns a personalised status message.
     *
     * @param driverStatus the name of the status to be shown
     * @return the personalised status message.
     */
    public String getDriverStatusMessage(String driverStatus) {
        if (driverStatus.equals("shift")) return "I'm IN " + driverStatus.toUpperCase();
        return "I'm " + driverStatus.toUpperCase();
    }

    /**
     * This method takes a status and returns a personalised status message.
     *
     * @param freightStatus the name of the status to be shown
     * @return the personalised status message.
     */
    public String getFreightStatusMessage(String freightStatus) {
        return "It's " + freightStatus.toUpperCase();
    }
}
