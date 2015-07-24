package com.tsystems.javaschool.loginov.logiweb.ejb;

import javax.ejb.Stateful;

/**
 * A simple Driver Status EJB. The EJB does not use an interface.
 */
@Stateful
public class DriverStatusEJB {
    /**
     * This method takes a status and returns a personalised status message.
     *
     * @param status the name of the status to be shown
     * @return the personalised status message.
     */
    public String getStatusMessage(String status) {
        return "I'm " + status.toUpperCase();
    }
}
