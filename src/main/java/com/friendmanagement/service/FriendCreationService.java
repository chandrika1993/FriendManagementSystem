package com.friendmanagement.service;

import com.friendmanagement.dto.SuccessStatusDto;
import com.friendmanagement.dto.UserProfileDto;
import com.friendmanagement.exception.TechnicalException;

/**
 * <PRE>
 * Interface name   : FriendsManagementService
 * Description      : Service interface have methods which allow user to create friend Connection.
 * Author           : Capgemini.
 * </PRE>
 */
public interface FriendCreationService {

    /**
     * This method creates friend connection between 2 email addresses.
     * 
     * @return SuccessStatusDto
     * @throws FunctionalException
     * @throws TechnicalException
     */
    SuccessStatusDto createFriendConnection(UserProfileDto userProfileDto)
            throws TechnicalException;
}
