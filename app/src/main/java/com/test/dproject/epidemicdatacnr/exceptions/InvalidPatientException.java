package com.test.dproject.epidemicdatacnr.exceptions;

/**
 * Created by Subhashinie on 12/20/2016.
 */
public class InvalidPatientException extends Exception {
    public InvalidPatientException(String detailMessage) {
        super(detailMessage);
    }

    public InvalidPatientException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}

