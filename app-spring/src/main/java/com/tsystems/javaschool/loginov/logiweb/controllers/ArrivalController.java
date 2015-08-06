package com.tsystems.javaschool.loginov.logiweb.controllers;

import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Spring MVC Controller to manage entrance to the site.
 */
@Controller
public class ArrivalController {
    private static final String ERROR_MESSAGE = "error";
    private static final String SUCCESS_MESSAGE = "success";
    private static final String INVALID_EMAIL_OR_LOGIN_ERROR_MESSAGE = "Invalid email address or password";
    private static final String SUCCESSFUL_LOGOUT_MESSAGE = "You've been logged out successfully!";

    /**
     * Redirects a user to the landing page.
     */
    @RequestMapping(value = {"/", "/landing"}, method = RequestMethod.GET)
    public ModelAndView landing(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                ModelAndView model) {

        if (error != null) {
            model.addObject(ERROR_MESSAGE, INVALID_EMAIL_OR_LOGIN_ERROR_MESSAGE);
        }

        if (logout != null) {
            model.addObject(SUCCESS_MESSAGE, SUCCESSFUL_LOGOUT_MESSAGE);
        }

        model.setViewName("landing");

        return model;
    }

    /**
     * Redirects a user to the sign-up page.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView register(@RequestParam(value = "error", required = false) String error,
                                 ModelAndView model) {

        // register a manager
        if (error != null) {
            model.addObject(ERROR_MESSAGE, "Such user is already registered");
        }

        model.setViewName("registration");
        return model;

    }

    /**
     * Redirects authorized user to his welcome page.
     */
    @RequestMapping(value = "/login_manager", method = RequestMethod.GET)
    public ModelAndView loginAsManager(@RequestParam(value = "error", required = false) String error,
                                       @RequestParam(value = "logout", required = false) String logout,
                                       ModelAndView model) {

        if (error != null) {
            model.addObject(ERROR_MESSAGE, INVALID_EMAIL_OR_LOGIN_ERROR_MESSAGE);
        }

        if (logout != null) {
            model.addObject(SUCCESS_MESSAGE, SUCCESSFUL_LOGOUT_MESSAGE);
        }

        model.setViewName("login_manager");

        return model;

    }

    @RequestMapping(value = "/login_driver", method = RequestMethod.GET)
    public ModelAndView loginAsDriver(@RequestParam(value = "error", required = false) String error,
                                      @RequestParam(value = "logout", required = false) String logout,
                                      ModelAndView model) {

        if (error != null) {
            model.addObject(ERROR_MESSAGE, INVALID_EMAIL_OR_LOGIN_ERROR_MESSAGE);
        }

        if (logout != null) {
            model.addObject(SUCCESS_MESSAGE, SUCCESSFUL_LOGOUT_MESSAGE);
        }

        model.setViewName("login_driver");

        return model;

    }

    /**
     * Redirects authorized user to his welcome page.
     */
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView welcome(HttpServletRequest request, ModelAndView model) {

        SecurityContextHolderAwareRequestWrapper wrapper = new SecurityContextHolderAwareRequestWrapper(request, "");

        if (wrapper.isUserInRole("ROLE_MANAGER")) {
            model.setViewName("secure/manager/welcome");

        } else if (wrapper.isUserInRole("ROLE_DRIVER")) {
            model.setViewName("secure/driver/welcome");
        }

        return model;
    }
}
