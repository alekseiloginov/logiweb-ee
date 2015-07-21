package com.tsystems.javaschool.loginov.logiweb.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Spring MVC Controller to work with the truck data.
 */
@Controller
public class TruckController {
    private static Logger logger = Logger.getLogger(TruckController.class);
//    private ListService listService;
//    private SaveService saveService;
//    private UpdateService updateService;
//    private DeleteService deleteService;
//    private OptionService optionService;
//
//    public TruckController() {
//        listService = ListService.getInstance();
//        saveService = SaveService.getInstance();
//        updateService = UpdateService.getInstance();
//        deleteService = DeleteService.getInstance();
//        optionService = OptionService.getInstance();
//    }

    /**
     * Redirects user to the truck page.
     */
    @RequestMapping(value = "/trucks", method = RequestMethod.GET)
    public String getTruckPage() {
        return "secure/manager/trucks";
    }

//    /**
//     * Fetches a list of all trucks using the ListService and puts it to the response map.
//     */
//    @RequestInfo(value = "TruckList.do", method = "POST")
//    public Map<String, Object> getAllTrucks(Map requestParameters) {
//        String sorting = null;
//
//        if (requestParameters.containsKey("jtSorting")) {
//            sorting = ((String[]) requestParameters.get("jtSorting"))[0];
//        }
//
//        Map<String, Object> response = new HashMap<>();
//
//        List truckList = listService.getAllItems("Truck", sorting);
//        response.put("data", truckList);
//        return response;
//    }
//
//    /**
//     * Adds a truck to the database using the SaveService and puts the saved object back to the response map.
//     */
//    @RequestInfo(value = "TruckSave.do", method = "POST")
//    public Map<String, Object> saveTruck(Map requestParameters) {
//        String plate_number = ((String[]) requestParameters.get("plate_number"))[0];
//        int driver_number = Integer.parseInt(((String[]) requestParameters.get("driver_number"))[0]);
//        int capacity = Integer.parseInt(((String[]) requestParameters.get("capacity"))[0]);
//        int drivable = Integer.parseInt(((String[]) requestParameters.get("drivable"))[0]);
//        String city = ((String[]) requestParameters.get("location"))[0];
//
//        Map<String, Object> response = new HashMap<>();
//
//        try {
//            Object savedTruck = saveService.saveTruck(plate_number, driver_number, capacity, drivable, city);
//            response.put("datum", savedTruck);
//
//        } catch (PlateNumberIncorrectException e) {
//            logger.error("Plate number incorrect: " + plate_number, e);
//            response.put("jTableError", "Plate number should contain 2 letters and 5 digits.");
//        } catch (DuplicateEntryException e) {
//            logger.error("Duplicate entry: " + plate_number, e);
//            response.put("jTableError", "Plate number is unique and this one is already present in the database.");
//        }
//
//        return response;
//    }
//
//    /**
//     * Updates a truck in the database using the UpdateService and puts the updated truck back to the response map.
//     */
//    @RequestInfo(value = "TruckUpdate.do", method = "POST")
//    public Map<String, Object> updateTruck(Map requestParameters) {
//        int id = Integer.parseInt(((String[]) requestParameters.get("id"))[0]);
//        String plate_number = ((String[]) requestParameters.get("plate_number"))[0];
//        int driver_number = Integer.parseInt(((String[]) requestParameters.get("driver_number"))[0]);
//        int capacity = Integer.parseInt(((String[]) requestParameters.get("capacity"))[0]);
//        int drivable = Integer.parseInt(((String[]) requestParameters.get("drivable"))[0]);
//        String city = ((String[]) requestParameters.get("location"))[0];
//
//        Map<String, Object> response = new HashMap<>();
//
//        try {
//            Object updatedTruck = updateService.updateTruck(id, plate_number, driver_number, capacity, drivable, city);
//            response.put("datum", updatedTruck);
//
//        } catch (PlateNumberIncorrectException e) {
//            logger.error("Plate number incorrect: " + plate_number, e);
//            response.put("jTableError", "Plate number should contain 2 letters and 5 digits.");
//        } catch (DuplicateEntryException e) {
//            logger.error("Duplicate entry: " + plate_number, e);
//            response.put("jTableError", "Plate number is unique and this one is already present in the database.");
//        }
//
//        return response;
//    }
//
//    /**
//     * Deletes a truck from the database using the DeleteService and puts "OK" back to the response map.
//     */
//    @RequestInfo(value = "TruckDelete.do", method = "POST")
//    public Map<String, Object> deleteTruck(Map requestParameters) {
//        int id = Integer.parseInt(((String[]) requestParameters.get("id"))[0]);
//        Map<String, Object> response = new HashMap<>();
//
//        deleteService.deleteItem("Truck", id);
//
//        response.put("OK", "OK");
//        return response;
//    }
//
//    /**
//     * Fetches a list of valid truck options using the OptionService and puts a returned JSON string to the response map.
//     */
//    @RequestInfo(value = "TruckOptions.do", method = "POST")
//    public Map<String, Object> getAllTruckOptions(Map requestParameters) {
//        Map<String, Object> response = new HashMap<>();
//
//        String truckOptionJSONList = optionService.getTruckOptions();
//        response.put("options", truckOptionJSONList);
//        return response;
//    }
}
