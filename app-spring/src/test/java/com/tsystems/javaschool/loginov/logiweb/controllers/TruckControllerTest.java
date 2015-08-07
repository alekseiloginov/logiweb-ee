package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.tsystems.javaschool.loginov.logiweb.services.DriverService;
import com.tsystems.javaschool.loginov.logiweb.services.TruckService;
import com.tsystems.javaschool.loginov.logiweb.utils.GsonParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * TruckController unit tests.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/test-context.xml")
public class TruckControllerTest {
    private MockMvc mockMvc;

    @Mock
    private TruckService truckService;

    @Mock
    private GsonParser gsonParser;

    @InjectMocks
    private TruckController truckController;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(truckController).build();
    }

    @Test
    public void testGetTruckPage() throws Exception {
        mockMvc.perform(get("/trucks"))
                .andExpect(status().isOk())
                .andExpect(view().name("secure/manager/trucks"));
    }

    @Test
    public void testGetAllTrucks() throws Exception {
        mockMvc.perform(post("/TruckList.do"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveTruck() throws Exception {
        mockMvc.perform(post("/TruckSave.do")
                .param("plate_number", "plate_number")
                .param("driver_number", "0")
                .param("capacity", "0")
                .param("drivable", "0")
                .param("location", "location"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTruck() throws Exception {
        mockMvc.perform(post("/TruckUpdate.do")
                .param("id", "0")
                .param("plate_number", "plate_number")
                .param("driver_number", "0")
                .param("capacity", "0")
                .param("drivable", "0")
                .param("location", "location"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteTruck() throws Exception {
        mockMvc.perform(post("/TruckDelete.do")
                .param("id", "0"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllTruckOptions() throws Exception {
        mockMvc.perform(post("/TruckOptions.do"))
                .andExpect(status().isOk());
    }
}