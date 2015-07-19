package com.tsystems.javaschool.loginov.logiweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping("/landing")
public class HelloController {

    @RequestMapping(value = "/landin", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        return "landing";
    }
}