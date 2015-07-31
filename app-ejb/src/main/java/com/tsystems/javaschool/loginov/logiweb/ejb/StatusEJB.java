package com.tsystems.javaschool.loginov.logiweb.ejb;

import com.tsystems.javaschool.loginov.logiweb.ws.DriverWebService;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import javax.ejb.Stateful;

/**
 * A simple Status EJB. The EJB does not use an interface.
 */
@Stateful
public class StatusEJB {

    /**
     * This method takes a driver id and status and saves them using SOAP webservice.
     *
     * @param driverId the id to be saves
     * @param driverStatus the status to be saves
     * @return the result message
     */
    public String setDriverStatus(Integer driverId, String driverStatus) {

        String serviceUrl = "http://localhost:8080/logiweb-ee/services/soap";
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(DriverWebService.class);
        factory.setAddress(serviceUrl);
        DriverWebService driverWebService = (DriverWebService) factory.create();

        return driverWebService.setDriverStatus(driverId, driverStatus);
    }

    /**
     * This method takes a freight id and status and saves them using RESTful webservice.
     *
     * @param freightId the id to be saves
     * @param freightStatus the status to be saves
     * @return the result message.
     */
    public String setFreightStatus(Integer freightId, String freightStatus) throws Exception {

        String serviceUrl =
                "http://localhost:8080/logiweb-ee/services/rest/freight/" + freightId + "/status/" + freightStatus;

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
}
