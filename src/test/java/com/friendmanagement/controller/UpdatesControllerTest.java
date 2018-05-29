package com.friendmanagement.controller;

import static org.mockito.Matchers.any;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.friendmanagement.dto.BlockDto;
import com.friendmanagement.dto.InformationDto;
import com.friendmanagement.dto.SubscriptionDto;
import com.friendmanagement.exception.TechnicalException;
import com.friendmanagement.model.RespData;
import com.friendmanagement.model.ResponseError;
import com.friendmanagement.service.UpdatesService;
import com.friendmanagement.util.RequestResponseHandler;

/**
 * <PRE>
 * Class name       : UpdatesControllerTest
 * Description      : Test class which tests the UpdatesController methods. 
 * Author           : Capgemini.
 * </PRE>
 */
@RunWith(MockitoJUnitRunner.class)
public class UpdatesControllerTest {

    @InjectMocks
    private UpdatesController updatesController = new UpdatesController();

    @Mock
    private RequestResponseHandler requestResponseHandler;

    @Mock
    private UpdatesService updatesService;

    private static InformationDto informationDto = new InformationDto();
    private static SubscriptionDto subscriptionDto = new SubscriptionDto();
    private static BlockDto blockDto = new BlockDto();
    private static Object responseBody = null;
    private static RespData responseData = new RespData(responseBody, null);
    private static ResponseEntity<RespData> responseEntity =
            new ResponseEntity<>(responseData, HttpStatus.OK);

    /**
     * Initializes the objects before the test execution.
     * 
     */
    @BeforeClass
    public static void init() {
        informationDto.setSuccess(true);
        blockDto.setSender("ssss@gmail.com");
        blockDto.setText("asfasdf");
        informationDto.setFriends(null);
        subscriptionDto.setRequestor("ssss@gmail.com");
    }


    /**
     * Tests method subscribeForEmailUpdates.
     * 
     * Expectation is that returned satusCode equals 200.
     * 
     * @throws TechnicalException
     */
    @Test
    public void testSubscribeForEmailUpdatesSuccess()
            throws TechnicalException {
        Mockito.when(this.updatesService.subscribeForEmailUpdates(any()))
                .thenReturn(informationDto);
        Mockito.when(
                this.requestResponseHandler.getHttpsSuccessStatusCode(any()))
                .thenReturn(responseEntity);
        Assert.assertEquals(this.updatesController
                .subscribeForEmailUpdates(subscriptionDto), responseEntity);
    }

    /**
     * Tests method subscribeForEmailUpdates.
     * 
     * Expectation is that returned satusCode equals 504.
     * 
     * @throws TechnicalException
     * 
     */
    @Test
    public void testSubscribeForEmailUpdatesTechnicalException()
            throws TechnicalException {
        Mockito.when(this.updatesService.subscribeForEmailUpdates(any()))
                .thenThrow(sendTechnicalException());
        Mockito.when(this.requestResponseHandler.setExceptionError(null))
                .thenReturn(null);
        Assert.assertEquals(this.updatesController
                .subscribeForEmailUpdates(subscriptionDto).getStatusCode(),
                HttpStatus.GATEWAY_TIMEOUT);
    }

    /**
     * Tests method subscribeForEmailUpdates.
     * 
     * Expectation is that returned satusCode equals 500.
     * 
     * @throws TechnicalException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSubscribeForEmailUpdatesException()
            throws TechnicalException {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        RespData respData = new RespData();
        respData.setResponseError(responseError);
        Mockito.when(this.updatesService.subscribeForEmailUpdates(any()))
                .thenThrow(Exception.class);
        Mockito.when(this.requestResponseHandler.setExceptionError(null))
                .thenReturn(responseError);
        Assert.assertEquals(this.updatesController
                .subscribeForEmailUpdates(subscriptionDto).getStatusCode(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Tests method blockUpdates.
     * 
     * Expectation is that returned satusCode equals 200.
     * 
     * @throws TechnicalException
     */
    @Test
    public void testBlockUpdatesSuccess() throws TechnicalException {
        Mockito.when(this.updatesService.blockUpdates(any()))
                .thenReturn(informationDto);
        Mockito.when(
                this.requestResponseHandler.getHttpsSuccessStatusCode(any()))
                .thenReturn(responseEntity);
        Assert.assertEquals(
                this.updatesController.blockUpdates(subscriptionDto),
                responseEntity);
    }

    /**
     * Tests method blockUpdates.
     * 
     * Expectation is that returned satusCode equals 504.
     * 
     * @throws TechnicalException
     * 
     */
    @Test
    public void testBlockUpdatesTechnicalException() throws TechnicalException {
        Mockito.when(this.updatesService.blockUpdates(any()))
                .thenThrow(sendTechnicalException());
        Mockito.when(this.requestResponseHandler.setExceptionError(null))
                .thenReturn(null);
        Assert.assertEquals(this.updatesController.blockUpdates(subscriptionDto)
                .getStatusCode(), HttpStatus.GATEWAY_TIMEOUT);
    }

    /**
     * Tests method blockUpdates.
     * 
     * Expectation is that returned satusCode equals 500.
     * 
     * @throws TechnicalException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testBlockUpdatesException() throws TechnicalException {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        RespData respData = new RespData();
        respData.setResponseError(responseError);
        Mockito.when(this.updatesService.blockUpdates(any()))
                .thenThrow(Exception.class);
        Mockito.when(this.requestResponseHandler.setExceptionError(null))
                .thenReturn(responseError);
        Assert.assertEquals(this.updatesController.blockUpdates(subscriptionDto)
                .getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Tests method getEmailsWithSubscription.
     * 
     * Expectation is that returned satusCode equals 200.
     * 
     * @throws TechnicalException
     */
    @Test
    public void testGetEmailsWithSubscriptionSuccess()
            throws TechnicalException {
        Mockito.when(this.updatesService.getEmailsWithSubscription(any()))
                .thenReturn(informationDto);
        Mockito.when(
                this.requestResponseHandler.getHttpsSuccessStatusCode(any()))
                .thenReturn(responseEntity);
        Assert.assertEquals(
                this.updatesController.getEmailsWithSubscription(blockDto),
                responseEntity);
    }

    /**
     * Tests method getEmailsWithSubscription.
     * 
     * Expectation is that returned satusCode equals 504.
     * 
     * @throws TechnicalException
     * 
     */
    @Test
    public void testGetEmailsWithSubscriptionTechnicalException()
            throws TechnicalException {
        Mockito.when(this.updatesService.getEmailsWithSubscription(any()))
                .thenThrow(sendTechnicalException());
        Mockito.when(this.requestResponseHandler.setExceptionError(null))
                .thenReturn(null);
        Assert.assertEquals(this.updatesController
                .getEmailsWithSubscription(blockDto).getStatusCode(),
                HttpStatus.GATEWAY_TIMEOUT);
    }

    /**
     * Tests method getEmailsWithSubscription.
     * 
     * Expectation is that returned satusCode equals 500.
     * 
     * @throws TechnicalException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetEmailsWithSubscriptionException()
            throws TechnicalException {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        RespData respData = new RespData();
        respData.setResponseError(responseError);
        Mockito.when(this.updatesService.getEmailsWithSubscription(any()))
                .thenThrow(Exception.class);
        Mockito.when(this.requestResponseHandler.setExceptionError(null))
                .thenReturn(responseError);
        Assert.assertEquals(this.updatesController
                .getEmailsWithSubscription(blockDto).getStatusCode(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * Sets http error code in TechnicalException.
     * 
     * @return
     */
    private TechnicalException sendTechnicalException() {
        TechnicalException exception =
                new TechnicalException("Data not loaded");
        exception.setHttpErrorCode(HttpStatus.GATEWAY_TIMEOUT);
        return exception;
    }
}
