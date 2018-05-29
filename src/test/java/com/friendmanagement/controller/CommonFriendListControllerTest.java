package com.friendmanagement.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

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

import com.friendmanagement.dto.InformationDto;
import com.friendmanagement.dto.UserProfileDto;
import com.friendmanagement.exception.TechnicalException;
import com.friendmanagement.model.RespData;
import com.friendmanagement.service.CommonFriendListService;
import com.friendmanagement.util.RequestResponseHandler;

/**
 * <PRE>
 * Class name       : CommonFriendListControllerTest
 * Description      : Test class which tests the CommonFriendListController methods. 
 * Author           : Capgemini.
 * </PRE>
 */
@RunWith(MockitoJUnitRunner.class)
public class CommonFriendListControllerTest {
    @InjectMocks
    private CommonFriendListController commonFriendListController =
            new CommonFriendListController();

    @Mock
    private RequestResponseHandler requestResponseHandler;

    @Mock
    private CommonFriendListService commonFriendListService;

    private static InformationDto informationDto = new InformationDto();
    private static UserProfileDto userProfileDto = new UserProfileDto();
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
        informationDto.setFriends(null);
        userProfileDto.setEmailId("ssss@gmail.com");
    }

    /**
     * Tests method getFriends.
     * 
     * Expectation is that returned satusCode equals 200.
     * 
     * @throws TechnicalException
     */
    @Test
    public void testGetFriendsSuccess() throws TechnicalException {
        Mockito.when(this.commonFriendListService.getFriends(any()))
                .thenReturn(informationDto);
        Mockito.when(this.requestResponseHandler
                .getHttpsSuccessStatusCode(anyString()))
                .thenReturn(responseEntity);
        Assert.assertEquals(this.commonFriendListController
                .getFriends(userProfileDto).getStatusCode(), HttpStatus.OK);
    }

    /**
     * Tests method getFriends.
     * 
     * Expectation is that returned satusCode equals 206.
     * 
     * @throws FunctionException
     */
    @Test
    public void testGetFriendsTechnicalException() throws TechnicalException {
        Mockito.when(this.commonFriendListService.getFriends(any()))
                .thenThrow(sendTechnicalException());
        Assert.assertEquals(this.commonFriendListController
                .getFriends(userProfileDto).getStatusCode(),
                HttpStatus.GATEWAY_TIMEOUT);
    }

    /**
     * Tests method getFriends.
     * 
     * Expectation is that returned satusCode equals 500.
     * 
     * @throws TechnicalException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetFriendsException() throws TechnicalException {
        Mockito.when(this.commonFriendListService.getFriends(any()))
                .thenThrow(NullPointerException.class);
        Assert.assertEquals(this.commonFriendListController
                .getFriends(userProfileDto).getStatusCode(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Tests method getCommonFriends.
     * 
     * Expectation is that returned satusCode equals 200.
     * 
     * @throws TechnicalException
     */
    @Test
    public void testGetCommonFriendsSuccess() throws TechnicalException {
        Mockito.when(this.commonFriendListService.getCommonFriends(any()))
                .thenReturn(informationDto);
        Mockito.when(this.requestResponseHandler
                .getHttpsSuccessStatusCode(anyString()))
                .thenReturn(responseEntity);
        Assert.assertEquals(this.commonFriendListController
                .getCommonFriends(userProfileDto).getStatusCode(),
                HttpStatus.OK);
    }

    /**
     * Tests method getCommonFriends.
     * 
     * Expectation is that returned satusCode equals 206.
     * 
     * @throws FunctionException
     */
    @Test
    public void testGetCommonFriendsTechnicalException()
            throws TechnicalException {
        Mockito.when(this.commonFriendListService.getCommonFriends(any()))
                .thenThrow(sendTechnicalException());
        Assert.assertEquals(this.commonFriendListController
                .getCommonFriends(userProfileDto).getStatusCode(),
                HttpStatus.GATEWAY_TIMEOUT);
    }

    /**
     * Tests method getCommonFriends.
     * 
     * Expectation is that returned satusCode equals 500.
     * 
     * @throws TechnicalException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetCommonFriendsException() throws TechnicalException {
        Mockito.when(this.commonFriendListService.getCommonFriends(any()))
                .thenThrow(NullPointerException.class);
        Assert.assertEquals(this.commonFriendListController
                .getCommonFriends(userProfileDto).getStatusCode(),
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
