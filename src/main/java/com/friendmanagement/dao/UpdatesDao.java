package com.friendmanagement.dao;

import com.friendmanagement.model.UserProfile;

/**
 * <PRE>
 * Interface name   : UpdatesDao
 * Description      : Dao have method definition for updates fuctionality.  
 * Author           : Capgemini.
 * </PRE>
 */
public interface UpdatesDao {

    /**
     * Method finds user with the given email Id in UserProfile table.
     * 
     * @return count
     */
    Long countFindUsers(String email);

    /**
     * Method finds user with emailIds in UserProfile table.
     * 
     * @return UserProfile
     */
    UserProfile findUsers(String emailId);

    /**
     * Method subscribe for email updates.
     * 
     * @param userProfile
     */
    void subscribeForEmailUpdates(UserProfile userProfile);

    /**
     * Method blocks user.
     * 
     * @param userProfile
     */
    void blockUpdates(UserProfile userProfile);

    /**
     * Method saves userProfile object in DB.
     * 
     * @param userProfile
     */
    void insertUserProfile(UserProfile userProfile);
}
