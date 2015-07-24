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
    public void addFreight(Freight freight) {
        this.freightDao.addFreight(freight);
    }

    @Transactional
    public void updateFreight(Freight freight) {
        this.freightDao.updateFreight(freight);
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
}