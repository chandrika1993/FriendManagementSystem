package com.friendmanagement.exception;

import org.springframework.http.HttpStatus;

/**
 * <PRE>
 * CLASS NAME : BaseException 
 * DESCRIPTION :Base class Handling the exception to pass from the Technical/Functional
 * Author      :Capgemini
 * </PRE>
 */
public abstract class BaseException extends Exception {

    private static final long serialVersionUID = 1L;

    /** The error code. */
    protected String errorCode;

    /** The error message. */
    protected String errorMessage;

    /** The error system. */
    protected String errorSystem;

    /** The http error code . */
    protected HttpStatus httpErrorCode;

    /**
     * Constructor Definition
     */
    public BaseException() {}

    /**
     * Constructor Definition
     * 
     * @param errorMessage the error message
     */
    public BaseException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Constructor Definition
     * 
     * @param errorCode
     * @param errorMessage
     * @param errorSystem
     * @param httpErrorCode
     */
    public BaseException(String errorCode, String errorMessage,
            String errorSystem, HttpStatus httpErrorCode, Throwable cause) {
        super(errorSystem, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorSystem = errorSystem;
        this.httpErrorCode = httpErrorCode;
    }

    /**
     * Constructor Definition
     * 
     * @param errorCode
     * @param errorMessage
     * @param errorSystem
     * @param httpErrorCode
     */
    public BaseException(String errorCode, String errorMessage,
            String errorSystem, HttpStatus httpErrorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorSystem = errorSystem;
        this.httpErrorCode = httpErrorCode;
    }

    /**
     * @param errorSystem
     * @param e
     */
    public BaseException(String errorSystem, Exception e) {
        super(errorSystem, e);
    }

    /**
     * Gets the error code.
     * 
     * @return the errorCode
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * Sets the error code.
     * 
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets the error message.
     * 
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Sets the error message.
     * 
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return
     */
    public String getErrorSystem() {
        return this.errorSystem;
    }

    /**
     * @param errorSystem
     */
    public void setErrorSystem(String errorSystem) {
        this.errorSystem = errorSystem;
    }

    /**
     * @return
     */
    public HttpStatus getHttpErrorCode() {
        return this.httpErrorCode;
    }

    /**
     * @param httpErrorCode
     */
    public void setHttpErrorCode(HttpStatus httpErrorCode) {
        this.httpErrorCode = httpErrorCode;
    }
}
