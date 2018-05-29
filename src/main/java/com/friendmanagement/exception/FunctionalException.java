package com.friendmanagement.exception;

import org.springframework.http.HttpStatus;

/**
 * <PRE>
 * CLASS NAME    : FunctionalException 
 * DESCRIPTION   : FunctionalException class Handling the functional exception from the micro service
 * Author        : Capgemini
 * </PRE>
 */
public class FunctionalException extends BaseException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor Definition
     * 
     * @param errorCode
     * @param errorMessage
     * @param errorSystem
     * @param httpErrorCode
     */
    public FunctionalException(String errorCode, String errorMessage,
            String errorSystem, HttpStatus httpErrorCode) {
        super(errorCode, errorMessage, errorSystem, httpErrorCode);
    }

    /**
     * Constructor Definition
     */
    public FunctionalException() {}

}
