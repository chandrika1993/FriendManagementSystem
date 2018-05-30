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

import com.friendmanagement.dto.SuccessStatusDto;
import com.friendmanagement.dto.UserProfileDto;
import com.friendmanagement.exception.TechnicalException;
import com.friendmanagement.service.FriendCreationService;

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

    /**
     * Rest end Point to handle create Friend Connection request form UI
     * 
     * @return ResponseEntity response
     */
    @RequestMapping(path = "/createFriendConnection",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SuccessStatusDto> createFriendConnection(
            @RequestBody UserProfileDto userProfileDto) {
        log.info("FriendManagementController createFriendConnection:: Start");
        ResponseEntity<SuccessStatusDto> responseEntity = null;
        SuccessStatusDto successStatusDto = null;
        try {
            successStatusDto = this.friendsManagementService
                    .createFriendConnection(userProfileDto);
            responseEntity =
                    new ResponseEntity<>(successStatusDto, HttpStatus.OK);
            log.info("Friend Connection Created Successfully between :"
                    + userProfileDto.getFriends());
        } catch (TechnicalException ex) {
            successStatusDto = new SuccessStatusDto();
            successStatusDto.setSuccess(false);
            responseEntity = new ResponseEntity<>(successStatusDto,
                    ex.getHttpErrorCode());
            log.error(
                    "FriendManagementController createFriendConnection :: Error :: ",
                    ex);
        } catch (Exception e) {
            successStatusDto = new SuccessStatusDto();
            successStatusDto.setSuccess(false);
            responseEntity = new ResponseEntity<>(successStatusDto,
                    HttpStatus.INTERNAL_SERVER_ERROR);
            log.error(
                    "FriendManagementController createFriendConnection :: Error :: ",
                    e);
        }
        log.info("FriendManagementController createFriendConnection:: End .");
        return responseEntity;
    }
}
