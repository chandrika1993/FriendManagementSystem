package com.friendmanagement.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <PRE>
 * Class name       : ResponseError
 * Description      : A jpa repository to interact with the DB and perform the operations  
 * Author           : Capgemini.
 * </PRE>
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ResponseError {

    private String service;
    private String code;
    private String failureMessage;

    public ResponseError() {
        // Default Constructor
    }

    /**
     * @param service
     * @param code
     * @param failureMessage
     */
    public ResponseError(String service, String code, String failureMessage) {
        this.service = service;
        this.code = code;
        this.failureMessage = failureMessage;
    }

    @JsonProperty("code")
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("failureMessage")
    public String getFailureMessage() {
        return this.failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    @JsonProperty("service")
    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
