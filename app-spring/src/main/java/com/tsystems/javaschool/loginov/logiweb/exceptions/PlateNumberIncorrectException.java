package com.tsystems.javaschool.loginov.logiweb.exceptions;

/**
 * Custom exception class to handle situation when an entered truck plate number is not valid.
 */
public class PlateNumberIncorrectException extends Exception {

    public PlateNumberIncorrectException(String message) {
        super(message);
    }
}
