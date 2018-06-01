package com.friendmanagement.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.friendmanagement.constants.FriendsConstants;
import com.friendmanagement.dao.UpdatesDao;
import com.friendmanagement.dao.impl.UpdatesDaoImpl;
import com.friendmanagement.dto.BlockDto;
import com.friendmanagement.dto.InformationDto;
import com.friendmanagement.dto.SubscriptionDto;
import com.friendmanagement.dto.SuccessStatusDto;
import com.friendmanagement.exception.TechnicalException;
import com.friendmanagement.model.BlockStatus;
import com.friendmanagement.model.Subscription;
import com.friendmanagement.model.UserProfile;
import com.friendmanagement.service.UpdatesService;

/**
 * <PRE>
 * Class name       : UpdatesServiceImpl
 * Description      : Implementation service class for subscription functionality.
 * Author           : Capgemini.
 * </PRE>
 */
@Service
public class UpdatesServiceImpl implements UpdatesService {

    /**
     * Logs the activity performed by this controller.
     */
    private static Logger log = LogManager.getLogger();

    @Autowired
    private UpdatesDao updatesDao = new UpdatesDaoImpl();

    @Override
    public SuccessStatusDto subscribeForEmailUpdates(
            SubscriptionDto subscriptionDto) throws TechnicalException {
        log.debug("UpdatesServiceImpl subscribeForEmailUpdates :: Start");
        SuccessStatusDto successStatusDto = new SuccessStatusDto();
        UserProfile userProfileRequestor = new UserProfile();
        UserProfile userProfileTarget = new UserProfile();
        List<String> emailIds = new ArrayList<>();
        Subscription subscription = new Subscription();
        Set<Subscription> emailSubscriptionList = new HashSet<>();
        Set<BlockStatus> blockStatus = null;
        emailIds.add(subscriptionDto.getRequestor());
        emailIds.add(subscriptionDto.getTarget());
        Long countRequestor;
        Long countTarget;
        try {
            if (subscriptionDto.getRequestor()
                    .equals(subscriptionDto.getTarget())) {
                log.debug("UpdatesServiceImpl subscribeForEmailUpdates ::"
                        + FriendsConstants.SAME_EMAILS);
                throw new TechnicalException(FriendsConstants.UNAUTHORIZED_CODE,
                        FriendsConstants.SAME_EMAILS,
                        FriendsConstants.UNAUTHORIZED, HttpStatus.UNAUTHORIZED,
                        null);
            }
            countRequestor = this.updatesDao
                    .countFindUsers(subscriptionDto.getRequestor());
            countTarget =
                    this.updatesDao.countFindUsers(subscriptionDto.getTarget());
            if ((countRequestor != 0l) && (countTarget != 0l)) {
                userProfileRequestor = this.updatesDao
                        .findUsers(subscriptionDto.getRequestor());
                userProfileTarget =
                        this.updatesDao.findUsers(subscriptionDto.getTarget());
                blockStatus = userProfileRequestor.getBlockList();
                for (BlockStatus blockStatus2 : blockStatus) {
                    if (blockStatus2.getEmailId()
                            .equals(userProfileTarget.getUserEmailId())) {
                        log.debug(
                                "UpdatesServiceImpl subscribeForEmailUpdates ::"
                                        + FriendsConstants.BLOCKED_STATUS);
                        throw new TechnicalException(
                                FriendsConstants.UNAUTHORIZED_CODE,
                                FriendsConstants.BLOCKED_STATUS,
                                FriendsConstants.BLOCKED_STATUS,
                                HttpStatus.UNAUTHORIZED, null);
                    }
                }
                Set<Subscription> subscriptions = null;
                subscription.setSubscriptionStatus('Y');
                subscription.setEmailId(userProfileTarget.getUserEmailId());
                subscription.setUserProfile(userProfileRequestor);
                emailSubscriptionList.add(subscription);
                subscriptions = userProfileRequestor.getEmailSubscriptionList();
                for (Subscription subscription2 : subscriptions) {
                    if (!subscription2.getEmailId()
                            .equals(subscriptionDto.getTarget())) {
                        userProfileRequestor.setEmailSubscriptionList(
                                emailSubscriptionList);
                        this.updatesDao
                                .subscribeForEmailUpdates(userProfileRequestor);
                        successStatusDto.setSuccess(true);
                    }
                }
            } else {
                throw new TechnicalException(FriendsConstants.DATA_NOT_FOUND,
                        FriendsConstants.EMAIL_NOT_FOUND,
                        FriendsConstants.DATA_NOT_FOUND, HttpStatus.NOT_FOUND,
                        null);
            }
            log.debug("UpdatesServiceImpl subscribeForEmailUpdates :: End");
        } catch (PersistenceException | IllegalArgumentException e) {
            log.error("UpdatesServiceImpl subscribeForEmailUpdates Error " + e);
            throw new TechnicalException(FriendsConstants.DATABASE_ERROR,
                    FriendsConstants.DATABASE_ERROR_MESSAGE,
                    FriendsConstants.DATABASE_SERVICE,
                    HttpStatus.SERVICE_UNAVAILABLE, e);
        }
        return successStatusDto;
    }

    @Override
    public SuccessStatusDto blockUpdates(SubscriptionDto subscriptionDto)
            throws TechnicalException {
        Long countRequestor;
        Long countTarget;
        int flag = 0;
        log.debug("UpdatesServiceImpl blockUpdates :: Start");
        SuccessStatusDto successStatusDto = new SuccessStatusDto();
        UserProfile userProfileRequestor = new UserProfile();
        UserProfile userProfileTarget = new UserProfile();
        Set<BlockStatus> blockList = new HashSet<>();
        BlockStatus blockStatus = new BlockStatus();
        try {
            if (subscriptionDto.getRequestor()
                    .equals(subscriptionDto.getTarget())) {
                log.debug("UpdatesServiceImpl blockUpdates :: "
                        + FriendsConstants.SAME_EMAILS);
                throw new TechnicalException(FriendsConstants.UNAUTHORIZED_CODE,
                        FriendsConstants.SAME_EMAILS,
                        FriendsConstants.DATABASE_SERVICE,
                        HttpStatus.UNAUTHORIZED, null);
            }
            countRequestor = this.updatesDao
                    .countFindUsers(subscriptionDto.getRequestor());
            countTarget =
                    this.updatesDao.countFindUsers(subscriptionDto.getTarget());
            if ((countRequestor != 0l) && (countTarget != 0l)) {
                Set<BlockStatus> listOfBlock = null;
                blockStatus.setBlockStatus('Y');
                blockStatus.setEmailId(subscriptionDto.getTarget());
                userProfileTarget =
                        this.updatesDao.findUsers(subscriptionDto.getTarget());
                userProfileRequestor = this.updatesDao
                        .findUsers(subscriptionDto.getRequestor());
                blockStatus.setUserProfile(userProfileRequestor);
                listOfBlock = userProfileRequestor.getBlockList();
                for (BlockStatus blockStatus2 : listOfBlock) {
                    if (blockStatus2.getEmailId()
                            .equals(userProfileTarget.getUserEmailId())) {
                        flag++;
                    }
                }
                if (flag == 0) {
                    blockList.add(blockStatus);
                }
                userProfileRequestor.setBlockList(blockList);
                this.updatesDao.blockUpdates(userProfileRequestor);
                successStatusDto.setSuccess(true);
            } else {
                log.debug("UpdatesServiceImpl blockUpdates :: "
                        + FriendsConstants.EMAIL_NOT_FOUND);
                throw new TechnicalException(FriendsConstants.DATA_NOT_FOUND,
                        FriendsConstants.EMAIL_NOT_FOUND,
                        FriendsConstants.DATA_NOT_FOUND, HttpStatus.NOT_FOUND,
                        null);
            }
            log.debug("UpdatesServiceImpl blockUpdates :: End");
        } catch (PersistenceException | IllegalArgumentException e) {
            log.error("UpdatesServiceImpl blockUpdates error " + e);
            throw new TechnicalException(FriendsConstants.DATABASE_ERROR,
                    FriendsConstants.DATABASE_ERROR_MESSAGE,
                    FriendsConstants.DATABASE_SERVICE,
                    HttpStatus.SERVICE_UNAVAILABLE, e);
        }
        return successStatusDto;
    }

    @Override
    public InformationDto getEmailsWithSubscription(BlockDto blockDto)
            throws TechnicalException {
        log.debug("UpdatesServiceImpl getEmailsWithSubscription :: Start");
        InformationDto informationDto = new InformationDto();
        UserProfile userProfileRequestor;
        Set<String> listOfFriends = new HashSet<>();
        Set<Subscription> subscriptions;
        Set<BlockStatus> blockStatus;
        Long count;
        int flag1 = 0;
        Matcher m = Pattern
                .compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+")
                .matcher(blockDto.getText());
        while (m.find()) {
            listOfFriends.add(m.group());
        }
        try {
            count = this.updatesDao.countFindUsers(blockDto.getSender());
            if (count != 0l) {
                userProfileRequestor =
                        this.updatesDao.findUsers(blockDto.getSender());
                subscriptions = userProfileRequestor.getEmailSubscriptionList();
                blockStatus = userProfileRequestor.getBlockList();
                for (BlockStatus blockStatus2 : blockStatus) {
                    if (blockStatus2.getEmailId().equals(blockDto.getSender()))
                        flag1++;
                }
                if (flag1 == 0) {
                    for (Subscription subscription : subscriptions) {
                        listOfFriends.add(subscription.getEmailId());
                    }
                    informationDto.setCount(listOfFriends.size());
                    informationDto.setFriends(listOfFriends);
                } else {
                    log.debug("UpdatesServiceImpl getEmailsWithSubscription :: "
                            + FriendsConstants.BLOCKED_STATUS);
                    throw new TechnicalException(
                            FriendsConstants.UNAUTHORIZED_CODE,
                            FriendsConstants.BLOCKED_STATUS,
                            FriendsConstants.BLOCKED_STATUS,
                            HttpStatus.UNAUTHORIZED, null);
                }
            } else {
                log.debug("UpdatesServiceImpl getEmailsWithSubscription :: "
                        + FriendsConstants.EMAIL_NOT_FOUND);
                throw new TechnicalException(FriendsConstants.DATA_NOT_FOUND,
                        FriendsConstants.EMAIL_NOT_FOUND,
                        FriendsConstants.DATA_NOT_FOUND, HttpStatus.NOT_FOUND,
                        null);
            }
            informationDto.setSuccess(true);
            log.debug("UpdatesServiceImpl getEmailsWithSubscription :: End");
        } catch (PersistenceException | IllegalArgumentException e) {
            log.error("UpdatesServiceImpl subscribeForEmailUpdates error " + e);
            throw new TechnicalException(FriendsConstants.DATABASE_ERROR,
                    FriendsConstants.DATABASE_ERROR_MESSAGE,
                    FriendsConstants.DATABASE_SERVICE,
                    HttpStatus.SERVICE_UNAVAILABLE, e);
        }
        return informationDto;
    }
}
