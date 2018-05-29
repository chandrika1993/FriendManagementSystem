package com.friendmanagement.service;

import com.friendmanagement.dto.InformationDto;
import com.friendmanagement.dto.UserProfileDto;
import com.friendmanagement.exception.TechnicalException;

/**
 * <PRE>
 * Interface name   : CommonFriendListService
 * Description      : Service interface have methods which allow user to fetch list of friends from Database.
 * Author           : Capgemini.
 * </PRE>
 */
public interface CommonFriendListService {

    /**
     * This method finds list of friends for an email addresses.
     * 
     * @param userProfileDto
     * @return InformationDto
     * @throws TechnicalException
     */
    InformationDto getFriends(UserProfileDto userProfile)
            throws TechnicalException;

    /**
     * This method finds common list of friends for 2 email addresses.
     * 
     * @param userProfileDto
     * @return InformationDto
     * @throws TechnicalException
     */
    InformationDto getCommonFriends(UserProfileDto userProfileDto)
            throws TechnicalException;

}
