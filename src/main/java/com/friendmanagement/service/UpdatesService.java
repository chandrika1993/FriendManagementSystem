package com.friendmanagement.service;

import com.friendmanagement.dto.BlockDto;
import com.friendmanagement.dto.InformationDto;
import com.friendmanagement.dto.SubscriptionDto;
import com.friendmanagement.exception.TechnicalException;

/**
 * <PRE>
 * Interface name   : UpdatesService
 * Description      : Service interface have methods which allow user to fetch data from Database.
 * Author           : Capgemini.
 * </PRE>
 */
public interface UpdatesService {

    /**
     * This method creates subscription connection between 2 email addresses.
     * 
     * @param subscriptionDto
     * @return InformationDto
     * @throws TechnicalException
     */
    InformationDto subscribeForEmailUpdates(SubscriptionDto subscriptionDto)
            throws TechnicalException;

    /**
     * This method finds subscription connection for an email addresses.
     * 
     * @param blockDto
     * @return InformationDto
     * @throws TechnicalException
     */
    InformationDto getEmailsWithSubscription(BlockDto blockDto)
            throws TechnicalException;

    /**
     * This method finds block status for an subscriptionDto.
     * 
     * @param subscriptionDto
     * @return InformationDto
     * @throws TechnicalException
     */
    InformationDto blockUpdates(SubscriptionDto subscriptionDto)
            throws TechnicalException;

}
