package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.tsystems.javaschool.loginov.logiweb.services.FreightService;
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

/**
 * FreightController unit tests.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/test/resources/test-context.xml")
public class FreightControllerTest {
    private MockMvc mockMvc;

    @Mock
    private FreightService freightService;

    @Mock
    private GsonParser gsonParser;

    @InjectMocks
    private FreightController freightController;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(freightController).build();
    }

    @Test
    public void testGetDriverPage() throws Exception {
        mockMvc.perform(get("/freights"))
                .andExpect(status().isOk())
                .andExpect(view().name("secure/manager/freights"));
    }

    @Test
    public void testGetAllFreights() throws Exception {
        mockMvc.perform(post("/FreightList.do"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveFreight() throws Exception {
        mockMvc.perform(post("/FreightSave.do")
                .param("name", "name")
                .param("weight", "0")
                .param("status", "status")
                .param("loading", "loading")
                .param("unloading", "unloading"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateFreight() throws Exception {
        mockMvc.perform(post("/FreightUpdate.do")
                .param("id", "0")
                .param("name", "name")
                .param("weight", "0")
                .param("status", "status")
                .param("loading", "loading")
                .param("unloading", "unloading"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFreight() throws Exception {
        mockMvc.perform(post("/FreightDelete.do")
                .param("id", "0"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllFreightOptions() throws Exception {
        mockMvc.perform(post("/FreightOptions.do")
                .param("orderID", "0")
                .param("city", "city"))
                .andExpect(status().isOk());
    }
}