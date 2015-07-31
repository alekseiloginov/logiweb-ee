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
//    private static Logger logger = Logger.getLogger(UserController.class);
//    private AuthService authService;
//    private RegService regService;
//
//    public UserController() {
//        authService = AuthService.getInstance();
//        regService = RegService.getInstance();
//    }

//    /**
//     * Validates user's log in input via AuthService, catches any exceptions
//     * and puts the page to view with any error messages to the response map.
//     */
//    @RequestInfo(value = "Login.go", method = "POST")
//    public Map<String, Object> authenticate(Map requestParameters) {
//        String email = ((String[]) requestParameters.get("email"))[0];
//        String password = ((String[]) requestParameters.get("password"))[0];
//        String role = ((String[]) requestParameters.get("role"))[0];
//        Map<String, Object> response = new HashMap<>();
//        logger.info("Fetching user with the email: " + email + ", role: " + role);
//        Object user;
//
//        try {
//            user = authService.authenticate(email, password, role);
//            response.put("user", user);
//
//            if (role.equals("manager")) {
//                response.put("page", "/WEB-INF/jsp/secure/manager/welcome.jsp");
//            } else if (role.equals("driver")) {
//                response.put("page", "/WEB-INF/jsp/secure/driver/welcome.jsp");
//            }
//
//        } catch (UserNotFoundException e) {
//            logger.error("User not found with email: " + email, e);
//            response.put("error", "User not found in the database");
//
//            if (role.equals("manager")) {
//                response.put("page", "/login_manager.jsp");
//            } else {
//                response.put("page", "/login_driver.jsp");
//            }
//
//        } catch (PasswordIncorrectException e) {
//            logger.error("Password incorrect for email: " + email, e);
//            response.put("error", "Password incorrect for the given email");
//
//            if (role.equals("manager")) {
//                response.put("page", "/login_manager.jsp");
//            } else {
//                response.put("page", "/login_driver.jsp");
//            }
//        }
//        return response;
//    }
//
//    /**
//     * Validates user's sign-up input via RegService, catches any exceptions
//     * and puts the page to view with the success or error message to the response map.
//     */
//    @RequestInfo(value = "Register.go", method = "POST")
//    public Map<String, Object> register(Map requestParameters) {
//        String name = ((String[]) requestParameters.get("name"))[0];
//        String surname = ((String[]) requestParameters.get("surname"))[0];
//        String email = ((String[]) requestParameters.get("email"))[0];
//        String password = ((String[]) requestParameters.get("password"))[0];
//        Map<String, Object> response = new HashMap<>();
//
//        logger.info("Saving manager with the name: " + name + ", surname: " + surname + ", email: " + email);
//
//        try {
//            regService.register(name, surname, email, password);
//            response.put("success", "Registration successful!");
//            response.put("page", "/login_manager.jsp");
//
//        } catch (UsedEmailException e) {
//            logger.error("Manager tries to sign-up with the already used email: " + email, e);
//            response.put("error", "This email is already used");
//            response.put("page", "/registration.jsp");
//        }
//
//        return response;
//    }

    /**
     * Redirects a user to the landing page.
     */
    @RequestMapping(value = {"/", "/landing"}, method = RequestMethod.GET)
    public ModelAndView landing(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                ModelAndView model) {

        if (error != null) {
            model.addObject("error", "Invalid email address or password");
        }

        if (logout != null) {
            model.addObject("success", "You've been logged out successfully!");
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
            model.addObject("error", "Such user is already registered");
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
            model.addObject("error", "Invalid email address or password");
        }

        if (logout != null) {
            model.addObject("success", "You've been logged out successfully!");
        }

        model.setViewName("login_manager");

        return model;

    }

    @RequestMapping(value = "/login_driver", method = RequestMethod.GET)
    public ModelAndView loginAsDriver(@RequestParam(value = "error", required = false) String error,
                                      @RequestParam(value = "logout", required = false) String logout,
                                      ModelAndView model) {

        if (error != null) {
            model.addObject("error", "Invalid email address or password");
        }

        if (logout != null) {
            model.addObject("success", "You've been logged out successfully!");
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
