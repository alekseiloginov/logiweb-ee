package com.tsystems.javaschool.loginov.logiweb.dao;

import com.tsystems.javaschool.loginov.logiweb.models.Freight;

import java.util.List;

/**
 * DAO interface to declare the methods that we will use in our project to work with the freight data.
 */
public interface FreightDao {

    Freight addFreight(Freight freight);

    Freight updateFreight(Freight freight);

    void updateFreightStatus(Freight freight);

    List<Freight> listFreights();

    Freight getFreightById(int id);

    void removeFreight(int id);

    String getFreightOptions(int orderID, String city);
}
