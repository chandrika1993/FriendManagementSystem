package com.friendmanagement.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.friendmanagement.constants.FriendsConstants;
import com.friendmanagement.dao.CommonFriendListDao;
import com.friendmanagement.dto.InformationDto;
import com.friendmanagement.dto.UserProfileDto;
import com.friendmanagement.exception.TechnicalException;
import com.friendmanagement.model.Friends;
import com.friendmanagement.model.UserProfile;
import com.friendmanagement.service.CommonFriendListService;

/**
 * <PRE>
 * Class name       : CommonFriendListServiceImpl
 * Description      : The class which performs functions for fetching friends data from db. 
 * Author           : Capgemini.
 * </PRE>
 */
@Service("commonFriendsManagementService")
public class CommonFriendListServiceImpl implements CommonFriendListService {

    /**
     * Logs the activity performed by this controller.
     */
    private static Logger log = LogManager.getLogger();

    @Autowired
    private CommonFriendListDao commonFriendListDao;

    @Override
    public InformationDto getFriends(UserProfileDto userProfiledto)
            throws TechnicalException {
        log.debug("CommonFriendListServiceImpl getFriends :: Start");
        String emailId = null;
        UserProfile userProfile;
        InformationDto informationDto = new InformationDto();
        List<Friends> friends = new ArrayList<>();
        List<String> listOfFriends = new ArrayList<>();
        List<String> listsFriends = new ArrayList<>();
        Set<String> list = new HashSet<>();
        listOfFriends.addAll(userProfiledto.getFriends());
        for (String email : listOfFriends) {
            emailId = email;
        }
        Long count;
        try {
            count = this.commonFriendListDao.getUserProfileTotalCount(emailId);
            if (count != 0l) {
                userProfile = this.commonFriendListDao.getUserProfile(emailId);
                friends.addAll(userProfile.getListOfFriends());
                for (int i = 0; i < friends.size(); i++) {
                    if (!friends.get(i).getEmailId().equals(emailId))
                        listsFriends.add(friends.get(i).getEmailId());
                }
            } else {
                informationDto.setSuccess(false);
                log.error("FriendsManagementServiceImpl getFriends error "
                        + FriendsConstants.DATA_NOT_FOUND);
                throw new TechnicalException(FriendsConstants.DATA_NOT_FOUND,
                        FriendsConstants.EMAIL_NOT_FOUND + " :: " + emailId,
                        FriendsConstants.DATA_NOT_FOUND, HttpStatus.NOT_FOUND,
                        null);
            }
            list.addAll(listsFriends);
            informationDto.setCount(listsFriends.size());
            informationDto.setFriends(list);
            informationDto.setSuccess(true);
            log.debug("CommonFriendListServiceImpl getFriends :: End");
        } catch (PersistenceException | DataIntegrityViolationException |

                IllegalArgumentException e) {
            log.error("CommonFriendListServiceImpl getFriends error " + e);
            throw new TechnicalException(FriendsConstants.DATABASE_ERROR,
                    FriendsConstants.DATABASE_ERROR_MESSAGE,
                    FriendsConstants.DATABASE_SERVICE,
                    HttpStatus.SERVICE_UNAVAILABLE, e);
        } catch (EmptyResultDataAccessException e) {
            log.error("FriendsManagementServiceImpl getFriends error " + e);
            throw new TechnicalException(FriendsConstants.DATA_NOT_FOUND,
                    FriendsConstants.EMAIL_NOT_FOUND,
                    FriendsConstants.DATA_NOT_FOUND, HttpStatus.NOT_FOUND, e);
        }
        return informationDto;
    }

    @Override
    public InformationDto getCommonFriends(UserProfileDto userProfileDto)
            throws TechnicalException {
        log.debug("CommonFriendListServiceImpl getCommonFriends :: Start");
        UserProfile userProfile;
        Long count1;
        Long count2;
        InformationDto informationDto = new InformationDto();
        List<String> listOfFriends = new ArrayList<>();
        Set<String> setOfFriends = new HashSet<>();
        List<String> emailId = new ArrayList<>();
        List<String> listsFriends = new ArrayList<>();

        try {
            listOfFriends.addAll(userProfileDto.getFriends());
            for (String email : listOfFriends) {
                emailId.add(email);
            }
            if (emailId.get(0).equals(emailId.get(1))) {
                throw new TechnicalException(FriendsConstants.UNAUTHORIZED_CODE,
                        FriendsConstants.SAME_EMAILS,
                        FriendsConstants.UNAUTHORIZED, HttpStatus.UNAUTHORIZED,
                        null);
            }
            List<Friends> friends1 = new ArrayList<>();
            List<Friends> friends2 = new ArrayList<>();
            count1 = this.commonFriendListDao
                    .getUserProfileTotalCount(emailId.get(0));
            count2 = this.commonFriendListDao
                    .getUserProfileTotalCount(emailId.get(1));
            if ((count1 != 0l) && (count2 != 0l)) {
                userProfile =
                        this.commonFriendListDao.getUserProfile(emailId.get(0));
                friends1.addAll(userProfile.getListOfFriends());
                userProfile =
                        this.commonFriendListDao.getUserProfile(emailId.get(1));
                friends2.addAll(userProfile.getListOfFriends());
                for (int i = 0; i < friends1.size(); i++) {
                    for (int j = 0; j < friends2.size(); j++) {
                        if (friends1.get(i).getEmailId()
                                .equals(friends2.get(j).getEmailId())) {
                            listsFriends.add(friends1.get(i).getEmailId());
                            informationDto.setSuccess(true);
                        }
                    }
                }
                if (listsFriends.isEmpty()) {
                    throw new TechnicalException(
                            FriendsConstants.DATA_NOT_FOUND,
                            FriendsConstants.NO_COMMON_FRIENDS,
                            FriendsConstants.DATA_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null);
                }
                setOfFriends.addAll(listsFriends);
            } else {
                informationDto.setSuccess(false);
                log.error("CommonFriendListServiceImpl getCommonFriends error "
                        + FriendsConstants.DATA_NOT_FOUND);
                throw new TechnicalException(FriendsConstants.DATA_NOT_FOUND,
                        FriendsConstants.EMAIL_NOT_FOUND,
                        FriendsConstants.DATA_NOT_FOUND, HttpStatus.NOT_FOUND,
                        null);
            }
            informationDto.setCount(listsFriends.size());
            informationDto.setFriends(setOfFriends);
        } catch (PersistenceException | DataIntegrityViolationException
                | IllegalArgumentException e) {
            log.error(
                    "CommonFriendListServiceImpl getCommonFriends error " + e);
            throw new TechnicalException(FriendsConstants.DATABASE_ERROR,
                    FriendsConstants.DATABASE_ERROR_MESSAGE,
                    FriendsConstants.DATABASE_SERVICE,
                    HttpStatus.SERVICE_UNAVAILABLE, e);
        } catch (EmptyResultDataAccessException e) {
            log.error(
                    "FriendsManagementServiceImpl getCommonFriends error " + e);
            throw new TechnicalException(FriendsConstants.DATA_NOT_FOUND,
                    FriendsConstants.EMAIL_NOT_FOUND,
                    FriendsConstants.DATA_NOT_FOUND, HttpStatus.NOT_FOUND, e);
        }
        return informationDto;
    }
}
