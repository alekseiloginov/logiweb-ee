package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.tsystems.javaschool.loginov.logiweb.models.Driver;
import com.tsystems.javaschool.loginov.logiweb.models.Location;
import com.tsystems.javaschool.loginov.logiweb.models.Truck;
import com.tsystems.javaschool.loginov.logiweb.services.DriverService;
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

import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * DriverController unit tests.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/test/resources/test-context.xml")
public class DriverControllerTest {
    private MockMvc mockMvc;

    @Mock
    private DriverService driverService;

    @Mock
    private GsonParser gsonParser;

    @InjectMocks
    private DriverController driverController;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(driverController).build();
    }

    @Test
    public void testGetDriverPage() throws Exception {
        mockMvc.perform(get("/drivers"))
                .andExpect(status().isOk())
                .andExpect(view().name("secure/manager/drivers"));
    }

    @Test
    public void testGetAllDrivers() throws Exception {
        mockMvc.perform(post("/DriverList.do"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveDriver() throws Exception {
        mockMvc.perform(post("/DriverSave.do")
                .param("name", "Vasya")
                .param("surname", "Pupkin")
                .param("email", "abcd@abc.com")
                .param("password", "1234")
                .param("worked_hours", "40")
                .param("status", "free")
                .param("location", "Moscow")
                .param("truck", "AB12345"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateDriver() throws Exception {
        mockMvc.perform(post("/DriverUpdate.do")
                .param("id", "0")
                .param("name", "Vasya")
                .param("surname", "Pupkin")
                .param("email", "abcd@abc.com")
                .param("password", "1234")
                .param("worked_hours", "40")
                .param("status", "free")
                .param("location", "Moscow")
                .param("truck", "AB12345"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteDriver() throws Exception {
        mockMvc.perform(post("/DriverDelete.do")
                .param("id", "0"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllOrderTruckDrivers() throws Exception {
        mockMvc.perform(post("/OrderTruckDriverList.do")
                .param("orderID", "0"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveOrderTruckDriver() throws Exception {
        mockMvc.perform(post("/OrderTruckDriverSave.do")
                .param("orderID", "0")
                .param("email", "email"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllDriverOptions() throws Exception {
        mockMvc.perform(post("/DriverOptions.do")
                .param("orderID", "0"))
                .andExpect(status().isOk());
    }
}