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
import com.friendmanagement.dao.UpdatesDao;
import com.friendmanagement.model.UserProfile;

/**
 * <PRE>
 * Class name       : UpdatesDaoImpl
 * Description      : Implementation dao class for updates functionality.
 * Author           : Capgemini.
 * </PRE>
 */
@Repository
public class UpdatesDaoImpl extends BaseDao<Object> implements UpdatesDao {

    /**
     * Logs the activity performed by this controller.
     */
    private static Logger log = LogManager.getLogger();

    @Override
    public Long countFindUsers(String email) {
        log.debug(" UpdatesDaoImpl countFindUsers :: Start");
        CriteriaBuilder criteriaBuilder =
                this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery =
                criteriaBuilder.createQuery(Long.class);
        Root<UserProfile> userProfileRoot =
                criteriaQuery.from(UserProfile.class);
        TypedQuery<Long> typedQuery = null;
        Predicate emailIdCheck1 = criteriaBuilder
                .equal(userProfileRoot.get(FriendsConstants.EMAIL_ID), email);
        criteriaQuery.where(emailIdCheck1);
        criteriaQuery.select(criteriaBuilder.count(userProfileRoot));
        typedQuery = this.entityManager.createQuery(criteriaQuery);
        log.info("Count Users");
        log.debug("UpdatesDaoImpl countFindUsers :: End");
        return typedQuery.getSingleResult();
    }

    @Override
    public UserProfile findUsers(String email) {
        log.debug(" UpdatesDaoImpl findUsers :: Start");
        CriteriaBuilder criteriaBuilder =
                this.entityManager.getCriteriaBuilder();
        CriteriaQuery<UserProfile> criteriaQuery =
                criteriaBuilder.createQuery(UserProfile.class);
        Root<UserProfile> userProfileRoot =
                criteriaQuery.from(UserProfile.class);
        Predicate emailIdCheck1 = criteriaBuilder
                .equal(userProfileRoot.get(FriendsConstants.EMAIL_ID), email);
        criteriaQuery.where(emailIdCheck1);
        UserProfile profile =
                this.entityManager.createQuery(criteriaQuery).getSingleResult();
        log.info("Finding Users");
        log.debug("UpdatesDaoImpl findUsers :: End");
        return profile;
    }

    @Override
    public void subscribeForEmailUpdates(UserProfile userProfile) {
        log.debug(" UpdatesDaoImpl subscribeForEmailUpdates :: Start");
        this.entityManager.merge(userProfile);
        log.info("subscribeForEmailUpdates for emailId = {}.",
                userProfile.getEmailSubscriptionList());
        log.debug("UpdatesDaoImpl subscribeForEmailUpdates :: End");
    }

    @Override
    public void blockUpdates(UserProfile userProfileRequestor) {
        log.debug(" UpdatesDaoImpl blockUpdates :: Start");
        this.entityManager.merge(userProfileRequestor);
        log.info("subscribeForEmailUpdates for emailId = {}.",
                userProfileRequestor.getBlockList());
        log.debug("UpdatesDaoImpl blockUpdates :: End");
    }

    @Override
    public void insertUserProfile(UserProfile userProfile) {
        log.debug(" UpdatesDaoImpl insertUserProfile :: Start");
        this.entityManager.persist(userProfile);
        log.debug(" UpdatesDaoImpl insertUserProfile :: Start");
    }
}
