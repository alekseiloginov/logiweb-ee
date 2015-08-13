package com.tsystems.javaschool.loginov.logiweb.authorization;

import com.tsystems.javaschool.loginov.logiweb.ejb.StatusEJB;
import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import org.picketlink.annotations.PicketLink;
import org.picketlink.authentication.BaseAuthenticator;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.model.basic.User;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * A simple PicketLink authenticator that will accept a hard coded username/password value. The
 * @PicketLink annotation is required to indicate to PicketLink that this is the default Authenticator
 * to be used.
 */
@PicketLink
public class DriverAuthenticator extends BaseAuthenticator {

    @Inject
    private DefaultLoginCredentials credentials;

    @Inject
    private FacesContext facesContext;

    @EJB
    private StatusEJB statusEJB;

    @Override
    public void authenticate() {
        int driverId = Integer.parseInt(credentials.getUserId());
        String driverPassword = credentials.getPassword();

        Driver driver = statusEJB.authenticateDriver(driverId, driverPassword);

        if (driver != null) {
            setStatus(AuthenticationStatus.SUCCESS);
            String userId = String.valueOf(driverId);
            User user = new User(userId);

            user.setFirstName(driver.getName());
            user.setLastName(driver.getSurname());
            user.setEmail(driver.getEmail());
            setAccount(user);

        } else {
            setStatus(AuthenticationStatus.FAILURE);
            facesContext.addMessage(null, new FacesMessage(
                    "Authentication Failure - Invalid email address or password"));
        }
    }
}
