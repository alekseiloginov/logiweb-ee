package com.tsystems.javaschool.loginov.logiweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Spring MVC Controller to work with the freight data.
 */
@Controller
public class FreightController {
//    private ListService listService;
//    private SaveService saveService;
//    private UpdateService updateService;
//    private DeleteService deleteService;
//    private OptionService optionService;
//
//    public FreightController() {
//        listService = ListService.getInstance();
//        saveService = SaveService.getInstance();
//        updateService = UpdateService.getInstance();
//        deleteService = DeleteService.getInstance();
//        optionService = OptionService.getInstance();
//    }

    /**
     * Redirects user to the freights page.
     */
    @RequestMapping(value = "/freights", method = RequestMethod.GET)
    public String getDriverPage() {
        return "secure/manager/freights";
    }

//    /**
//     * Fetches a list of all freights using the ListService and puts it to the response map.
//     */
//    @RequestInfo(value = "FreightList.do", method = "POST")
//    public Map<String, Object> getAllFreights(Map requestParameters) {
//        String sorting = null;
//
//        if (requestParameters.containsKey("jtSorting")) {
//            sorting = ((String[]) requestParameters.get("jtSorting"))[0];
//        }
//
//        Map<String, Object> response = new HashMap<>();
//
//        List freightList = listService.getAllItems("Freight", sorting);
//        response.put("data", freightList);
//        return response;
//    }
//
//    /**
//     * Adds a freight to the database using the SaveService and puts the saved freight back to the response map.
//     */
//    @RequestInfo(value = "FreightSave.do", method = "POST")
//    public Map<String, Object> saveFreight(Map requestParameters) {
//        String name = ((String[]) requestParameters.get("name"))[0];
//        int weight = Integer.parseInt(((String[]) requestParameters.get("weight"))[0]);
//        String loadingLocation = ((String[]) requestParameters.get("loading"))[0];
//        String unloadingLocation = ((String[]) requestParameters.get("unloading"))[0];
//        String status = ((String[]) requestParameters.get("status"))[0];
//
//        Map<String, Object> response = new HashMap<>();
//
//        Object savedFreight = saveService.saveFreight(name, weight, loadingLocation, unloadingLocation, status);
//
//        response.put("datum", savedFreight);
//        return response;
//    }
//
//    /**
//     * Fetches all valid freight options using the OptionService and puts a returned JSON string to the response map.
//     */
//    @RequestInfo(value = "FreightOptions.do", method = "POST")
//    public Map<String, Object> getAllFreightOptions(Map requestParameters) {
//        int orderID = Integer.parseInt(((String[]) requestParameters.get("orderID"))[0]);
//        String city = ((String[]) requestParameters.get("city"))[0];
//
//        Map<String, Object> response = new HashMap<>();
//
//        String freightOptionJSONList = optionService.getFreightOptions(orderID, city);
//        response.put("options", freightOptionJSONList);
//        return response;
//    }
//
//    /**
//     * Updates a freight in the database using the UpdateService and puts the updated freight back to the response map.
//     */
//    @RequestInfo(value = "FreightUpdate.do", method = "POST")
//    public Map<String, Object> updateFreight(Map requestParameters) {
//        int id = Integer.parseInt(((String[]) requestParameters.get("id"))[0]);
//        String name = ((String[]) requestParameters.get("name"))[0];
//        int weight = Integer.parseInt(((String[]) requestParameters.get("weight"))[0]);
//        String loadingLocation = ((String[]) requestParameters.get("loading"))[0];
//        String unloadingLocation = ((String[]) requestParameters.get("unloading"))[0];
//        String status = ((String[]) requestParameters.get("status"))[0];
//
//        Map<String, Object> response = new HashMap<>();
//
//        Object updatedFreight =
//                updateService.updateFreight(id, name, weight, loadingLocation, unloadingLocation, status);
//
//        response.put("datum", updatedFreight);
//        return response;
//    }
//
//    /**
//     * Deletes a freight from the database using the DeleteService and puts "OK" back to the response map.
//     */
//    @RequestInfo(value = "FreightDelete.do", method = "POST")
//    public Map<String, Object> deleteFreight(Map requestParameters) {
//        int id = Integer.parseInt(((String[]) requestParameters.get("id"))[0]);
//        Map<String, Object> response = new HashMap<>();
//
//        deleteService.deleteItem("Freight", id);
//
//        response.put("OK", "OK");
//        return response;
//    }
}