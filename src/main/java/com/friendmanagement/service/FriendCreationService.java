package com.friendmanagement.service;

import com.friendmanagement.dto.InformationDto;
import com.friendmanagement.dto.UserProfileDto;
import com.friendmanagement.exception.FunctionalException;
import com.friendmanagement.exception.TechnicalException;

/**
 * <PRE>
 * Interface name   : FriendsManagementService
 * Description      : Service interface have methods which allow user to fetch data from Database.
 * Author           : Capgemini.
 * </PRE>
 */
public interface FriendCreationService {

    /**
     * This method creates friend connection between 2 email addresses.
     * 
     * @return InformationDto
     * @throws FunctionalException
     * @throws TechnicalException
     */
    InformationDto createConnection(UserProfileDto userProfileDto)
            throws TechnicalException;

}
