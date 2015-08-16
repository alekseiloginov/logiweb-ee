package com.tsystems.javaschool.loginov.logiweb.exceptions;

/**
 * Custom exception class to handle situation when an entered driver's plate number is not found in the database.
 */
public class PlateNumberNotFoundException extends Exception {

    public PlateNumberNotFoundException(String message) {
        super(message);
    }
}
