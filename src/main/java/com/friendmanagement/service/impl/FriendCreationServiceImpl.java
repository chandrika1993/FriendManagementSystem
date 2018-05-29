package com.friendmanagement.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.friendmanagement.constants.FriendsConstants;
import com.friendmanagement.dao.FriendCreationDao;
import com.friendmanagement.dto.InformationDto;
import com.friendmanagement.dto.UserProfileDto;
import com.friendmanagement.exception.TechnicalException;
import com.friendmanagement.model.Friends;
import com.friendmanagement.model.UserProfile;
import com.friendmanagement.service.FriendCreationService;

/**
 * <PRE>
 * Class name       : FriendCreationServiceImpl
 * Description      : Implementation service class for consumer to perform operation like create Friend Connection.
 * Author           : Capgemini.
 * </PRE>
 */
@Service("friendsManagementService")
public class FriendCreationServiceImpl implements FriendCreationService {

    /**
     * Logs the activity performed by this controller.
     */
    private static Logger log = LogManager.getLogger();

    @Autowired
    private FriendCreationDao friendsManagementDao;


    @Override
    public InformationDto createFriendConnection(UserProfileDto userProfileDto)
            throws TechnicalException {
        log.debug("FriendsManagementServiceImpl createConnection :: Start");
        InformationDto informationDto = new InformationDto();
        try {
            List<UserProfile> friends = new ArrayList<>();
            for (int i = 0; i < userProfileDto.getFriends().size(); i++) {
                String userEmail = userProfileDto.getFriends().get(i);
                String emails = null;
                if (userProfileDto.getFriends().get(1)
                        .equals(userProfileDto.getFriends().get(0))) {
                    informationDto.setSuccess(false);
                    throw new TechnicalException(
                            FriendsConstants.UNAUTHORIZED_CODE,
                            FriendsConstants.SAME_EMAILS,
                            FriendsConstants.DATABASE_SERVICE,
                            HttpStatus.UNAUTHORIZED, null);
                }
                if (i == 0) {
                    emails = userProfileDto.getFriends().get(1);
                } else
                    emails = userProfileDto.getFriends().get(0);
                UserProfile userProfile = null;
                int flag = 0;
                Long count;
                count = this.friendsManagementDao.countFindUsers(userEmail);
                if (count != 0l) {
                    userProfile =
                            this.friendsManagementDao.findUsers(userEmail);
                    Set<Friends> listOfFriends = null;
                    if (userProfile != null) {
                        Friends frns = new Friends();
                        frns.setEmailId(emails);
                        frns.setUserProfile(userProfile);
                        listOfFriends = userProfile.getListOfFriends();
                        for (Friends friends2 : listOfFriends) {
                            if (friends2.getEmailId().equals(emails))
                                flag++;
                        }
                        if (flag != 1)
                            listOfFriends.add(frns);
                        userProfile.setListOfFriends(listOfFriends);
                        this.friendsManagementDao.updateUser(userProfile);
                        friends.add(userProfile);
                    }
                } else {
                    UserProfile userProfile1 = new UserProfile();
                    userProfile1.setUserEmailId(userEmail);
                    Set<Friends> listOfFriends = new HashSet<>();
                    Friends frns = new Friends();
                    frns.setEmailId(emails);
                    frns.setUserProfile(userProfile1);
                    listOfFriends.add(frns);
                    userProfile1.setListOfFriends(listOfFriends);
                    this.friendsManagementDao.insertUser(userProfile1);
                    friends.add(userProfile1);
                }
            }
            informationDto.setSuccess(true);
            log.debug("FriendsManagementServiceImpl createConnection :: End");

        } catch (PersistenceException | IllegalArgumentException e) {
            log.error(
                    "FriendsManagementServiceImpl createConnection error " + e);
            throw new TechnicalException(FriendsConstants.DATABASE_ERROR,
                    FriendsConstants.DATABASE_ERROR_MESSAGE,
                    FriendsConstants.DATABASE_SERVICE,
                    HttpStatus.SERVICE_UNAVAILABLE, e);
        }
        return informationDto;
    }
}
