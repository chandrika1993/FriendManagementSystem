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
import com.friendmanagement.service.CommonFriendListService;

/**
 * <PRE>
 * Class Name  : CommonFriendListController
 * Description : Implementation class for Common Friend.
 * Author      : Capgemini
 * </PRE>
 */
@RestController
@RequestMapping("/friendmanagementservice")
public class CommonFriendListController {

    /**
     * Logs the activity performed by this controller.
     */
    private static Logger log = LogManager.getLogger();

    @Autowired
    private CommonFriendListService commonFriendListService;

    /**
     * Rest end Point to get Friend list for an email Id.
     * 
     * @param emailId
     * @return ResponseEntity response
     */
    @RequestMapping(path = "/getFriends", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<InformationDto> getFriends(
            @RequestBody UserProfileDto userProfileDto) {
        log.debug("CommonFriendListController getFriends :: Start");
        InformationDto informationDto = new InformationDto();
        ResponseEntity<InformationDto> responseEntity = null;
        try {
            informationDto =
                    this.commonFriendListService.getFriends(userProfileDto);
            responseEntity =
                    new ResponseEntity<>(informationDto, HttpStatus.OK);
            log.info("Extracted friends for the Email Id {}. "
                    + userProfileDto.getFriends());
        } catch (TechnicalException ex) {
            informationDto = new InformationDto();
            informationDto.setSuccess(false);
            responseEntity =
                    new ResponseEntity<>(informationDto, ex.getHttpErrorCode());
            log.error("CommonFriendListController getFriends :: Error :: ", ex);
        } catch (Exception e) {
            informationDto = new InformationDto();
            informationDto.setSuccess(false);
            responseEntity = new ResponseEntity<>(informationDto,
                    HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("CommonFriendListController getFriends :: Error :: ", e);
        }
        return responseEntity;
    }

    /**
     * Rest end Point to get Common Friends between 2 email Id.
     * 
     * @param emailId
     * @return ResponseEntity response
     */
    @RequestMapping(path = "/getCommonFriends", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<InformationDto> getCommonFriends(
            @RequestBody UserProfileDto userProfileDto) {
        log.debug("CommonFriendListController getCommonFriends :: Start");
        InformationDto informationDto = null;
        ResponseEntity<InformationDto> responseEntity = null;
        try {
            informationDto = this.commonFriendListService
                    .getCommonFriends(userProfileDto);
            responseEntity =
                    new ResponseEntity<>(informationDto, HttpStatus.OK);
            log.info("Extracted common friends for the given Email Id's {}. "
                    + userProfileDto.getFriends());
        } catch (TechnicalException ex) {
            informationDto = new InformationDto();
            informationDto.setSuccess(false);
            responseEntity =
                    new ResponseEntity<>(informationDto, ex.getHttpErrorCode());
            log.error(
                    "CommonFriendListController getCommonFriends :: Error :: ",
                    ex);
        } catch (Exception e) {
            informationDto = new InformationDto();
            informationDto.setSuccess(false);
            responseEntity = new ResponseEntity<>(informationDto,
                    HttpStatus.INTERNAL_SERVER_ERROR);
            log.error(
                    "CommonFriendListController getCommonFriends :: Error :: ",
                    e);
        }
        return responseEntity;
    }
}
