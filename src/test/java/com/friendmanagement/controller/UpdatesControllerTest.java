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
import com.friendmanagement.dto.SuccessStatusDto;
import com.friendmanagement.exception.TechnicalException;
import com.friendmanagement.model.ResponseError;
import com.friendmanagement.service.UpdatesService;

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
    private UpdatesService updatesService;

    private static InformationDto informationDto = new InformationDto();
    private static SuccessStatusDto successStatusDto = new SuccessStatusDto();
    private static SubscriptionDto subscriptionDto = new SubscriptionDto();
    private static BlockDto blockDto = new BlockDto();
    private static ResponseEntity<InformationDto> responseEntity =
            new ResponseEntity<>(informationDto, HttpStatus.OK);
    private static ResponseEntity<SuccessStatusDto> responseEntitySuccess =
            new ResponseEntity<>(successStatusDto, HttpStatus.OK);

    /**
     * Initializes the objects before the test execution.
     * 
     */
    @BeforeClass
    public static void init() {
        informationDto.setSuccess(true);
        successStatusDto.setSuccess(true);
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
        successStatusDto.setSuccess(true);
        Mockito.when(this.updatesService.subscribeForEmailUpdates(any()))
                .thenReturn(successStatusDto);
        Assert.assertEquals(responseEntitySuccess, this.updatesController
                .subscribeForEmailUpdates(subscriptionDto));
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
        Assert.assertEquals(HttpStatus.GATEWAY_TIMEOUT, this.updatesController
                .subscribeForEmailUpdates(subscriptionDto).getStatusCode());
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
        Mockito.when(this.updatesService.subscribeForEmailUpdates(any()))
                .thenThrow(Exception.class);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                this.updatesController.subscribeForEmailUpdates(subscriptionDto)
                        .getStatusCode());
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
                .thenReturn(successStatusDto);
        Assert.assertEquals(responseEntitySuccess,
                this.updatesController.blockUpdates(subscriptionDto));
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
        Assert.assertEquals(HttpStatus.GATEWAY_TIMEOUT, this.updatesController
                .blockUpdates(subscriptionDto).getStatusCode());
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
        Mockito.when(this.updatesService.blockUpdates(any()))
                .thenThrow(Exception.class);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                this.updatesController.blockUpdates(subscriptionDto)
                        .getStatusCode());
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
        Assert.assertEquals(responseEntity,
                this.updatesController.getEmailsWithSubscription(blockDto));
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
        Assert.assertEquals(HttpStatus.GATEWAY_TIMEOUT, this.updatesController
                .getEmailsWithSubscription(blockDto).getStatusCode());
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
        Mockito.when(this.updatesService.getEmailsWithSubscription(any()))
                .thenThrow(Exception.class);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                this.updatesController.getEmailsWithSubscription(blockDto)
                        .getStatusCode());
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
