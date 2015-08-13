package com.tsystems.javaschool.loginov.logiweb.ejb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.Freight;
import com.tsystems.javaschool.loginov.logiweb.ws.DriverWebService;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import javax.ejb.Stateless;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple Status EJB. The EJB does not use an interface.
 */
@Stateless
public class StatusEJB implements Serializable {
    private static final String WEB_SERVICE_URL = "http://localhost:8080/logiweb-ee/services/soap";
    private JaxWsProxyFactoryBean factory;
    private DriverWebService driverWebService;

    /**
     * This method takes a driver username and password and tries to process authentication using SOAP webservice.
     *
     * @param driverId
     * @param driverPassword
     * @return the result message
     */
    public Driver authenticateDriver(int driverId, String driverPassword) {

        factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(DriverWebService.class);
        factory.setAddress(WEB_SERVICE_URL);
        driverWebService = (DriverWebService) factory.create();

        return driverWebService.authenticateDriver(driverId, driverPassword);
    }

    /**
     * This method takes a driver id and status and updates the status them using SOAP webservice.
     *
     * @param driverId driver id to update his/her status
     * @param driverStatus the status to update
     * @return the result message
     */
    public String setDriverStatus(Integer driverId, String driverStatus) {

        factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(DriverWebService.class);
        factory.setAddress(WEB_SERVICE_URL);
        driverWebService = (DriverWebService) factory.create();

        return driverWebService.setDriverStatus(driverId, driverStatus);
    }

    /**
     * This method takes a driver id and gets his/her status using SOAP webservice.
     *
     * @param driverId driver id to get his/her status
     * @return the result message
     */
    public String getDriverStatus(Integer driverId) {

        factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(DriverWebService.class);
        factory.setAddress(WEB_SERVICE_URL);
        driverWebService = (DriverWebService) factory.create();

        return driverWebService.getDriverStatus(driverId);
    }

    /**
     * This method takes a freight id and status and updates the status using RESTful webservice.
     *
     * @param freightId freight id to update its status
     * @param freightStatus the status to update
     * @return the result message.
     */
    public String setFreightStatus(Integer freightId, String freightStatus) throws IOException {

        String serviceUrl =
                "http://localhost:8080/logiweb-ee/services/rest/freights/" + freightId + "/statuses/" + freightStatus;

        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(serviceUrl);
        client.executeMethod(postMethod);
        Header requestHeader = new Header();
        requestHeader.setName("content-type");
        requestHeader.setValue("application/x-www-form-urlencoded");
        requestHeader.setName("accept");
        requestHeader.setValue("application/xml");
        postMethod.addRequestHeader(requestHeader);
        client.executeMethod(postMethod);
        String output = postMethod.getResponseBodyAsString();
        postMethod.releaseConnection();

        return output;
    }

    /**
     * This method takes a driver id and responses with the driver's freight list using RESTful webservice.
     *
     * @param driverId driver id to get his/her freights
     * @return the result message.
     */
    public List<Freight> getFreightList(Integer driverId) throws IOException {
        List<Freight> freightList;

        String serviceUrl =
                "http://localhost:8080/logiweb-ee/services/rest/drivers/" + driverId + "/freights/list";

        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(serviceUrl);
        client.executeMethod(postMethod);
        Header requestHeader = new Header();
        requestHeader.setName("content-type");
        requestHeader.setValue("application/x-www-form-urlencoded");
        requestHeader.setName("accept");
        requestHeader.setValue("application/json");
        postMethod.addRequestHeader(requestHeader);
        client.executeMethod(postMethod);
        String output = postMethod.getResponseBodyAsString();
        postMethod.releaseConnection();

        if (!output.isEmpty()) {
            // parsing JSON string to Java List using Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            freightList = objectMapper.readValue(output, new TypeReference<List<Freight>>() {
            });
        } else {
            freightList = new ArrayList<>();
        }

        return freightList;
    }
}
