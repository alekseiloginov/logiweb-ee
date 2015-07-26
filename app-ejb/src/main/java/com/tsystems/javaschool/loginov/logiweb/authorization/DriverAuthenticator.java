package com.tsystems.javaschool.loginov.logiweb.authorization;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
import com.tsystems.javaschool.loginov.logiweb.models.Truck;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.picketlink.annotations.PicketLink;
import org.picketlink.authentication.BaseAuthenticator;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.model.basic.User;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

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

    @Override
    public void authenticate() {
        int ID = Integer.parseInt(credentials.getUserId());
        String password = credentials.getPassword();
        String encryptedPassword = null;

        // Password encryption using MD5
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] bytes = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();

            for (byte aByte : bytes) {
                stringBuilder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            encryptedPassword = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Creating a Hibernate SessionFactory
        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        try {
            properties.load(DriverAuthenticator.class.getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Driver.class);
        configuration.addAnnotatedClass(Location.class);
        configuration.addAnnotatedClass(Truck.class);
        ServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Driver.class);
        criteria.add(Restrictions.eq("id", ID))
                .add(Restrictions.eq("password", encryptedPassword))
                .setMaxResults(1);
        Driver driver = (Driver) criteria.uniqueResult();

        session.getTransaction().commit();

        if (driver != null) {
            setStatus(AuthenticationStatus.SUCCESS);
            String userId = String.valueOf(driver.getId());
            User user = new User(userId);

            user.setFirstName(driver.getName());
            user.setLastName(driver.getSurname());
            user.setEmail(driver.getEmail());
            setAccount(user);

        } else {
            setStatus(AuthenticationStatus.FAILURE);
            facesContext.addMessage(null, new FacesMessage(
                    "Authentication Failure - The username or password you provided were invalid."));
        }
    }
}
