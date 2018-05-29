package com.friendmanagement.exception;

import org.springframework.http.HttpStatus;

/**
 * <PRE>
 * CLASS NAME  : TechnicalException 
 * DESCRIPTION :TechnicalException class Handling only technical exception form the
 * micro service
 * Author      : Capgemini
 * </PRE>
 */
public class TechnicalException extends BaseException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor Definition
     * 
     * @param errorCode
     * @param errorMessage
     * @param errorSystem
     * @param httpErrorCode
     */
    public TechnicalException(String errorCode, String errorMessage,
            String errorSystem, HttpStatus httpErrorCode, Throwable cause) {
        super(errorCode, errorMessage, errorSystem, httpErrorCode, cause);
    }

    /**
     * Constructor Definition
     * 
     * @param message
     */
    public TechnicalException(String message) {
        super(message);
    }
}
