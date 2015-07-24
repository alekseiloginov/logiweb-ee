package com.tsystems.javaschool.loginov.logiweb.ejb;

import javax.ejb.Stateless;

@Stateless
public class HelloEJB {
    public String hello() {
        return "Hello from " + this;
    }
}