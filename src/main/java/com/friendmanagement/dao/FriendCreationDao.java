package com.friendmanagement.dao;

import com.friendmanagement.model.UserProfile;


/**
 * <PRE>
 * Interface name   : FriendCreationDao
 * Description      : Dao have method definition for creating friends Connection.  
 * Author           : Capgemini.
 * </PRE>
 */
public interface FriendCreationDao {

    /**
     * Method finds user with emailIds in UserProfile table.
     * 
     * @return UserProfile
     */
    UserProfile findUsers(String emailId);

    /**
     * Method inserts UserProfile object in db.
     * 
     */
    void insertUser(UserProfile userProfile);

    /**
     * Method updates UserProfile object in db.
     * 
     */
    void updateUser(UserProfile findEmails);

    /**
     * Method finds the existence of email in UserProfile DB.
     * 
     */
    Long countFindUsers(String emailId);
}
