package com.friendmanagement.dao;

import com.friendmanagement.model.Friends;
import com.friendmanagement.model.UserProfile;

/**
 * <PRE>
 * Interface name   : CommonFriendListDao
 * Description      : Dao have method definition for fetching friends details.  
 * Author           : Capgemini.
 * </PRE>
 */
public interface CommonFriendListDao {

    /**
     * Method finds user profile corresponding to an emailId.
     */
    UserProfile getUserProfile(String emailId);

    /**
     * Method finds whether a userProfile with specified emailId exists in DB.
     */
    Long getUserProfileTotalCount(String emailId);

    /**
     * Method which inserts a friends object in DB.
     */
    void insertFriendsProfile(Friends friends);

    /**
     * Method which inserts a userprofile object in DB.
     */
    void insertUserProfile(UserProfile userProfile);
}
