package com.friendmanagement.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.friendmanagement.dto.InformationDto;
import com.friendmanagement.dto.UserProfileDto;
import com.friendmanagement.exception.TechnicalException;
import com.friendmanagement.model.RespData;
import com.friendmanagement.service.FriendCreationService;
import com.friendmanagement.util.RequestResponseHandler;

/**
 * <PRE>
 * Class Name  : FriendManagementController
 * Description : Implementation class for Friend Management.
 * Author      : Capgemini
 * </PRE>
 */
@RestController
@RequestMapping("/friendmanagementservice")
public class FriendCreationController {

    /**
     * Logs the activity performed by this controller.
     */
    private static Logger log = LogManager.getLogger();

    @Autowired
    private FriendCreationService friendsManagementService;

    @Autowired
    private RequestResponseHandler requestResponseHandler;

    /**
     * Rest end Point to handle create Friend Connection request form UI
     * 
     * @return ResponseEntity response
     */
    @RequestMapping(path = "/createConnection", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RespData> createConnection(
            @RequestBody UserProfileDto userProfileDto) {
        log.info("FriendManagementController createFriendConnection:: Start");
        RespData respData = null;
        ResponseEntity<RespData> responseEntity = null;
        InformationDto informationDto = null;
        try {
            informationDto = this.friendsManagementService
                    .createConnection(userProfileDto);
            responseEntity = this.requestResponseHandler
                    .getHttpsSuccessStatusCode(informationDto);
            log.info("Friend Connection Created Successfully between :"
                    + userProfileDto.getFriends());
        } catch (TechnicalException ex) {
            respData = new RespData();
            respData.setResponseError(
                    requestResponseHandler.setMicroServiceError(ex));
            responseEntity =
                    new ResponseEntity<>(respData, ex.getHttpErrorCode());
            log.error(
                    "FriendManagementController createFriendConnection :: Error :: ",
                    ex);
        } catch (Exception e) {
            respData = new RespData();
            respData.setResponseError(
                    requestResponseHandler.setExceptionError(e));
            responseEntity = new ResponseEntity<>(respData,
                    HttpStatus.INTERNAL_SERVER_ERROR);
            log.error(
                    "FriendManagementController createFriendConnection :: Error :: ",
                    e);
        }
        log.info("FriendManagementController createFriendConnection:: End .");
        return responseEntity;
    }
}
