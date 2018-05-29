package com.friendmanagement.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.friendmanagement.exception.BaseException;
import com.friendmanagement.model.RespData;
import com.friendmanagement.model.ResponseError;


/**
 * <PRE>
 * Class name       : RequestResponseHandler
 * Description      : This class contains methods which help us to handle requests and responses.
 * Author           : Capgemini.
 * </PRE>
 */
@Component
public class RequestResponseHandler {

    /**
     * Logs the activity performed by this controller.
     */
    private static Logger log = LogManager.getLogger();

    /**
     * This method returns the success HttpStatus if if function executes
     * successfully.
     * 
     * @param object
     * @return
     */
    public ResponseEntity<RespData> getHttpsSuccessStatusCode(
            final Object responseBody) {
        log.debug(" RequestResponseHandler getHttpsSuccessStatusCode :: Start");
        RespData responseData = new RespData(responseBody, null);
        ResponseEntity<RespData> responseEntity =
                new ResponseEntity<>(responseData, HttpStatus.OK);
        log.debug(" RequestResponseHandler getHttpsSuccessStatusCode :: End");
        return responseEntity;
    }

    /**
     * This method used to create Error Info object.
     * 
     * @param RespData model class with web service response fields
     * @return
     **/
    public ResponseError setMicroServiceError(final BaseException e) {
        log.error(" RequestResponseHandler setMicroServiceError :: Start");
        ResponseError responseError = new ResponseError();
        responseError.setService(e.getErrorSystem());
        responseError.setCode(e.getErrorCode());
        responseError.setFailureMessage(e.getErrorMessage());
        log.error(" RequestResponseHandler setMicroServiceError :: End");
        return responseError;
    }

    /**
     * This method used to create Error Info object.
     * 
     * @param object
     * 
     * @param RespData model class with web service response fields
     * @return
     **/
    public ResponseError setExceptionError(final Exception e) {
        log.error(" RequestResponseHandler setExceptionError :: Start");
        ResponseError responseError = new ResponseError();
        responseError.setService(e.getMessage());
        responseError.setCode("Generic Exception");
        responseError.setFailureMessage(e.getMessage());
        log.error(" RequestResponseHandler setExceptionError :: End");
        return responseError;
    }
}
