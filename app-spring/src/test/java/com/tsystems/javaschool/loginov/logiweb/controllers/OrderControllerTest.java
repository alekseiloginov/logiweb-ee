package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.tsystems.javaschool.loginov.logiweb.services.DriverService;
import com.tsystems.javaschool.loginov.logiweb.services.OrderService;
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
 * OrderController unit tests.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/test/resources/test-context.xml")
public class OrderControllerTest {
    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @Mock
    private GsonParser gsonParser;

    @InjectMocks
    private OrderController orderController;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testSaveOrder() throws Exception {
        mockMvc.perform(post("/OrderSave.do")
                .param("completed", "0")
                .param("truck", "truck"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateOrder() throws Exception {
        mockMvc.perform(post("/OrderSave.do")
                .param("id", "0")
                .param("completed", "0")
                .param("truck", "truck"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteOrder() throws Exception {
        mockMvc.perform(post("/OrderDelete.do")
                .param("id", "0"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllOrderWaypoints() throws Exception {
        mockMvc.perform(post("/OrderWaypointList.do")
                .param("orderID", "0"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveOrderWaypoint() throws Exception {
        mockMvc.perform(post("/OrderWaypointSave.do")
                .param("orderID", "0")
                .param("location", "location")
                .param("freight", "freight"))
                .andExpect(status().isOk());
    }
}