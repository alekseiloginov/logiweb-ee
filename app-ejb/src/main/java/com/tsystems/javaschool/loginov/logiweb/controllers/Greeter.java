package com.tsystems.javaschool.loginov.logiweb.controllers;

import com.tsystems.javaschool.loginov.logiweb.ejb.HelloEJB;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="HelloJSFBean")
@RequestScoped
public class Greeter implements java.io.Serializable {

    @EJB
    private HelloEJB helloEJB;

    public String getMessage() {
        return helloEJB.hello();
    }
}
