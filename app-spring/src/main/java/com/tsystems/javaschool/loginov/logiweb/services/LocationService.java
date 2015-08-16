package com.tsystems.javaschool.loginov.logiweb.services;

import com.tsystems.javaschool.loginov.logiweb.dao.LocationDao;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class that uses Hibernate DAO classes to work with Location objects.
 */
@Service
public class LocationService {
    private LocationDao locationDao;

    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Transactional
    public Location addLocation(Location location) {
        return this.locationDao.addLocation(location);
    }

    @Transactional
    public Location updateLocation(Location location) {
        return this.locationDao.updateLocation(location);
    }

    @Transactional
    public List<Location> listLocations() {
        return this.locationDao.listLocations();
    }

    @Transactional
    public Location getLocationById(int id) {
        return this.locationDao.getLocationById(id);
    }

    @Transactional
    public void removeLocation(int id) {
        this.locationDao.removeLocation(id);
    }

    @Transactional
    public String getLocationOptions() {
        List locationList = locationDao.listLocations();

        // Creating a JSON string
        int optionCount = 0;
        String locationOptionJSONList = "[";

        if (locationList.isEmpty()) {
            locationOptionJSONList += "]";

        } else {

            for (Object location : locationList) {
                locationOptionJSONList += "{\"DisplayText\":\"";
                locationOptionJSONList += ((Location) location).getCity();
                locationOptionJSONList += "\",\"Value\":\"";
                locationOptionJSONList += ((Location) location).getCity();
                ++optionCount;

                if (optionCount < locationList.size()) {
                    locationOptionJSONList += "\"},";
                } else {
                    locationOptionJSONList += "\"}]";
                }
            }
        }
        return locationOptionJSONList;
    }
}
