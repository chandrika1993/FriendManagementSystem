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

import com.friendmanagement.dto.SuccessStatusDto;
import com.friendmanagement.dto.UserProfileDto;
import com.friendmanagement.exception.TechnicalException;
import com.friendmanagement.service.FriendCreationService;


/**
 * <PRE>
 * Class name       : FriendCreationControllerTest
 * Description      : Test class which tests the FriendCreationController methods. 
 * Author           : Capgemini.
 * </PRE>
 */
@RunWith(MockitoJUnitRunner.class)
public class FriendCreationControllerTest {

    @InjectMocks
    private FriendCreationController friendCreationController =
            new FriendCreationController();

    @Mock
    private FriendCreationService friendsManagementService;

    private static SuccessStatusDto successStatusDto = new SuccessStatusDto();
    private static UserProfileDto userProfileDto = new UserProfileDto();
    private static ResponseEntity<SuccessStatusDto> responseEntity =
            new ResponseEntity<>(successStatusDto, HttpStatus.OK);

    /**
     * Initializes the objects before the test execution.
     * 
     */
    @BeforeClass
    public static void init() {
        successStatusDto.setSuccess(true);
        userProfileDto.setEmailId("ssss@gmail.com");
    }


    /**
     * Tests method createFriendConnection.
     * 
     * Expectation is that returned satusCode equals 200.
     * 
     * @throws TechnicalException
     */
    @Test
    public void testCreateFriendConnectionSuccess() throws TechnicalException {
        Mockito.when(
                this.friendsManagementService.createFriendConnection(any()))
                .thenReturn(successStatusDto);
        Assert.assertEquals(this.friendCreationController
                .createFriendConnection(userProfileDto), responseEntity);
    }

    /**
     * Tests method createFriendConnection.
     * 
     * Expectation is that returned satusCode equals 500.
     * 
     * @throws TechnicalException
     * 
     */
    @Test
    public void testCreateFriendConnectionTechnicalException()
            throws TechnicalException {
        Mockito.when(
                this.friendsManagementService.createFriendConnection(any()))
                .thenThrow(sendTechnicalException());
        Assert.assertEquals(
                this.friendCreationController
                        .createFriendConnection(userProfileDto).getStatusCode(),
                HttpStatus.GATEWAY_TIMEOUT);
    }

    /**
     * Tests method createFriendConnection.
     * 
     * Expectation is that returned satusCode equals 504.
     * 
     * @throws TechnicalException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testCreateFriendConnectionException()
            throws TechnicalException {
        Mockito.when(
                this.friendsManagementService.createFriendConnection(any()))
                .thenThrow(NullPointerException.class);
        Assert.assertEquals(
                this.friendCreationController
                        .createFriendConnection(userProfileDto).getStatusCode(),
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
