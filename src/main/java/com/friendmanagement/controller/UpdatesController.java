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

import com.friendmanagement.dto.BlockDto;
import com.friendmanagement.dto.InformationDto;
import com.friendmanagement.dto.SubscriptionDto;
import com.friendmanagement.dto.SuccessStatusDto;
import com.friendmanagement.exception.TechnicalException;
import com.friendmanagement.service.UpdatesService;
import com.friendmanagement.service.impl.UpdatesServiceImpl;

/**
 * <PRE>
 * Class Name  : UpdatesController
 * Description : Implementation class for Friends Management.
 * Author      : Capgemini
 * </PRE>
 */
@RestController
@RequestMapping("/friendmanagementservice")
public class UpdatesController {

    /**
     * Logs the activity performed by this controller.
     */
    private static Logger log = LogManager.getLogger();

    @Autowired
    private UpdatesService updatesService = new UpdatesServiceImpl();

    /**
     * Rest end Point to set subscription for Email Updates.
     * 
     * @return ResponseEntity response
     */
    @RequestMapping(path = "/subscribeForEmailUpdates",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SuccessStatusDto> subscribeForEmailUpdates(
            @RequestBody SubscriptionDto subscriptionDto) {
        log.info("UpdatesController subscribeForEmailUpdates:: Start");
        ResponseEntity<SuccessStatusDto> responseEntity = null;
        SuccessStatusDto successStatusDto = null;
        try {
            successStatusDto = this.updatesService
                    .subscribeForEmailUpdates(subscriptionDto);
            responseEntity =
                    new ResponseEntity<>(successStatusDto, HttpStatus.OK);
            log.info("Subscription Created Successfully for Emails = {} & {}."
                    + subscriptionDto.getRequestor()
                    + subscriptionDto.getTarget());
        } catch (TechnicalException ex) {
            successStatusDto = new SuccessStatusDto();
            successStatusDto.setSuccess(false);
            responseEntity = new ResponseEntity<>(successStatusDto,
                    ex.getHttpErrorCode());
            log.error("UpdatesController subscribeForEmailUpdates :: Error :: ",
                    ex);
        } catch (Exception e) {
            successStatusDto = new SuccessStatusDto();
            successStatusDto.setSuccess(false);
            responseEntity = new ResponseEntity<>(successStatusDto,
                    HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("UpdatesController subscribeForEmailUpdates :: Error :: ",
                    e);
        }
        log.info("UpdatesController subscribeForEmailUpdates:: End .");
        return responseEntity;
    }

    /**
     * Rest end Point to block Updates.
     * 
     * @return ResponseEntity response
     */
    @RequestMapping(path = "/blockUpdates", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SuccessStatusDto> blockUpdates(
            @RequestBody SubscriptionDto subscriptionDto) {
        log.info("UpdatesController blockUpdates:: Start");
        ResponseEntity<SuccessStatusDto> responseEntity = null;
        SuccessStatusDto successStatusDto = null;
        try {
            successStatusDto =
                    this.updatesService.blockUpdates(subscriptionDto);
            responseEntity =
                    new ResponseEntity<>(successStatusDto, HttpStatus.OK);
            log.info("Block Updates Done Successfully for Emails = {} & {}."
                    + subscriptionDto.getRequestor()
                    + subscriptionDto.getTarget());
        } catch (TechnicalException ex) {
            successStatusDto = new SuccessStatusDto();
            successStatusDto.setSuccess(false);
            responseEntity = new ResponseEntity<>(successStatusDto,
                    ex.getHttpErrorCode());
            log.error("UpdatesController blockUpdates :: Error :: ", ex);
        } catch (Exception e) {
            successStatusDto = new SuccessStatusDto();
            successStatusDto.setSuccess(false);
            responseEntity = new ResponseEntity<>(successStatusDto,
                    HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("UpdatesController blockUpdates :: Error :: ", e);
        }
        log.info("UpdatesController createFriendConnection:: End .");
        return responseEntity;
    }

    /**
     * Rest end Point to get list of Email's with can receive updates from an
     * email address.
     * 
     * @return ResponseEntity response
     */
    @RequestMapping(path = "/getEmailsWithSubscription",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<InformationDto> getEmailsWithSubscription(
            @RequestBody BlockDto blockDto) {
        log.info("UpdatesController getEmailsWithSubscription:: Start");
        ResponseEntity<InformationDto> responseEntity = null;
        InformationDto informationDto = null;
        try {
            informationDto =
                    this.updatesService.getEmailsWithSubscription(blockDto);
            responseEntity =
                    new ResponseEntity<>(informationDto, HttpStatus.OK);
            log.info(
                    "Subscription List Extracted Successfully for Email Id = {}. "
                            + blockDto.getSender());
        } catch (TechnicalException ex) {
            informationDto = new InformationDto();
            informationDto.setSuccess(false);
            responseEntity =
                    new ResponseEntity<>(informationDto, ex.getHttpErrorCode());
            log.error(
                    "UpdatesController getEmailsWithSubscription :: Error :: ",
                    ex);
        } catch (Exception e) {
            informationDto = new InformationDto();
            informationDto.setSuccess(false);
            responseEntity = new ResponseEntity<>(informationDto,
                    HttpStatus.INTERNAL_SERVER_ERROR);
            log.error(
                    "UpdatesController getEmailsWithSubscription :: Error :: ",
                    e);
        }
        log.info("UpdatesController getEmailsWithSubscription:: End .");
        return responseEntity;
    }
}
