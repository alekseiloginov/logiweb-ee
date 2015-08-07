package com.tsystems.javaschool.loginov.logiweb.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * ArrivalController unit tests.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/test-context.xml")
public class ArrivalControllerTest {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testLanding() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("landing"));

        mockMvc.perform(get("/landing"))
                .andExpect(status().isOk())
                .andExpect(view().name("landing"));
    }

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    public void testLoginAsManager() throws Exception {
        mockMvc.perform(get("/login_manager"))
                .andExpect(status().isOk())
                .andExpect(view().name("login_manager"));
    }

    @Test
    public void testLoginAsDriver() throws Exception {
        mockMvc.perform(get("/login_driver"))
                .andExpect(status().isOk())
                .andExpect(view().name("login_driver"));
    }
}