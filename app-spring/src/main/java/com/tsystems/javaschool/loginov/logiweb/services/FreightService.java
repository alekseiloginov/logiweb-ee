package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.dao.FreightDao;
import com.tsystems.javaschool.loginov.logiweb.models.Freight;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class that uses Hibernate DAO classes to work with Freight objects.
 */
@Service
public class FreightService {

    private FreightDao freightDao;

    public void setFreightDao(FreightDao freightDao) {
        this.freightDao = freightDao;
    }

    @Transactional
    public Freight addFreight(Freight freight) {
        return this.freightDao.addFreight(freight);
    }

    @Transactional
    public Freight updateFreight(Freight freight) {
        return this.freightDao.updateFreight(freight);
    }

    @Transactional
    public void updateFreightStatus(Freight freight) {
        this.freightDao.updateFreightStatus(freight);
    }

    @Transactional
    public List<Freight> listFreights() {
        return this.freightDao.listFreights();
    }

    @Transactional
    public Freight getFreightById(int id) {
        return this.freightDao.getFreightById(id);
    }

    @Transactional
    public void removeFreight(int id) {
        this.freightDao.removeFreight(id);
    }

    @Transactional
    public String getFreightOptions(int orderID, String city) {
        return this.freightDao.getFreightOptions(orderID, city);
    }
}
