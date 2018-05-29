package com.friendmanagement.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * <PRE>
 * Class Name  : RespData
 * Description : Creating the response data as json. @link ResponseError
 * Author      : Capgemini
 * </PRE>
 */

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class RespData {

    @JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NAME)
    private Object responseData;

    private ResponseError responseError;

    /**
     * No-argument constructor required by Jackson API.
     */
    public RespData() {
        // Default Constructor
    }

    public RespData(Object responseData, ResponseError responseError) {
        this.responseError = responseError;
        this.responseData = responseData;
    }

    @JsonProperty("responseError")
    public ResponseError getResponseError() {
        return this.responseError;
    }

    public void setResponseError(ResponseError responseError) {
        this.responseError = responseError;
    }

    @JsonProperty("responseData")
    public Object getResponseData() {
        return this.responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    @Override
    public String toString() {
        return "RespData [responseData=" + responseData + ", responseError="
                + responseError + "]";
    }
}
