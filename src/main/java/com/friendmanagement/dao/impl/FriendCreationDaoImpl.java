package com.friendmanagement.dao.impl;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.friendmanagement.constants.FriendsConstants;
import com.friendmanagement.dao.BaseDao;
import com.friendmanagement.dao.FriendCreationDao;
import com.friendmanagement.model.UserProfile;

/**
 * <PRE>
 * Class name       : FriendCreationDaoImpl
 * Description      : Implementation dao class for friend management functionality.
 * Author           : Capgemini.
 * </PRE>
 */
@Repository
public class FriendCreationDaoImpl extends BaseDao<Object>
        implements FriendCreationDao {

    /**
     * Logs the activity performed by this controller.
     */
    private static Logger log = LogManager.getLogger();

    @Override
    public UserProfile findUsers(String emailId) {
        log.debug(" FriendsManagementDaoImpl findUsers :: Start");
        CriteriaBuilder criteriaBuilder =
                this.entityManager.getCriteriaBuilder();
        CriteriaQuery<UserProfile> criteriaQuery =
                criteriaBuilder.createQuery(UserProfile.class);
        Root<UserProfile> userProfileRoot =
                criteriaQuery.from(UserProfile.class);
        Predicate emailIdCheck1 = criteriaBuilder
                .equal(userProfileRoot.get(FriendsConstants.EMAIL_ID), emailId);
        criteriaQuery.where(emailIdCheck1);
        UserProfile profile =
                this.entityManager.createQuery(criteriaQuery).getSingleResult();
        log.info("Finding Users");
        log.debug("FriendsManagementDaoImpl findUsers :: End");
        return profile;
    }

    @Override
    public Long countFindUsers(String emailId) {
        log.debug(" FriendsManagementDaoImpl countFindUsers :: Start");
        CriteriaBuilder criteriaBuilder =
                this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery =
                criteriaBuilder.createQuery(Long.class);
        Root<UserProfile> userProfileRoot =
                criteriaQuery.from(UserProfile.class);
        TypedQuery<Long> typedQuery = null;
        Predicate emailIdCheck1 = criteriaBuilder
                .equal(userProfileRoot.get(FriendsConstants.EMAIL_ID), emailId);
        criteriaQuery.where(emailIdCheck1);
        criteriaQuery.select(criteriaBuilder.count(userProfileRoot));
        typedQuery = this.entityManager.createQuery(criteriaQuery);
        log.info("Finding Users");
        log.debug("FriendsManagementDaoImpl countFindUsers :: End");
        return typedQuery.getSingleResult();
    }

    @Override
    public void insertUser(UserProfile userProfile) {
        log.debug(" FriendsManagementDaoImpl insertUser :: Start");
        this.entityManager.persist(userProfile);
        log.info("insertUser for emailId = {}.", userProfile.getUserEmailId());
        log.debug(" FriendsManagementDaoImpl insertUser :: End");

    }

    @Override
    public void updateUser(UserProfile findEmails) {
        log.debug(" FriendsManagementDaoImpl updateUser :: Start");
        this.entityManager.merge(findEmails);
        log.info("updateUser for emailId = {}.", findEmails.getUserEmailId());
        log.debug(" FriendsManagementDaoImpl updateUser :: End");
    }
}
