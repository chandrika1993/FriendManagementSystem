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
import com.friendmanagement.dao.CommonFriendListDao;
import com.friendmanagement.model.Friends;
import com.friendmanagement.model.UserProfile;

/**
 * <PRE>
 * Class name       : CommonFriendListDaoImpl
 * Description      : Implementation dao class for list of friends functionality.
 * Author           : Capgemini.
 * </PRE>
 */
@Repository
public class CommonFriendListDaoImpl extends BaseDao<Object>
        implements CommonFriendListDao {

    /**
     * Logs the activity performed by this controller.
     */
    private static Logger log = LogManager.getLogger();

    @Override
    public UserProfile getUserProfile(String emailId) {
        log.debug(" CommonFriendListDaoImpl getUserProfile :: Start");
        CriteriaBuilder criteriaBuilder =
                this.entityManager.getCriteriaBuilder();
        CriteriaQuery<UserProfile> criteriaQuery =
                criteriaBuilder.createQuery(UserProfile.class);
        Root<UserProfile> userProfileRoot =
                criteriaQuery.from(UserProfile.class);
        Predicate emailIdCheck1 = criteriaBuilder
                .equal(userProfileRoot.get(FriendsConstants.EMAIL_ID), emailId);
        criteriaQuery.where(emailIdCheck1);
        log.info("Finding Users");
        log.debug("CommonFriendListDaoImpl getUserProfile :: End");
        return this.entityManager.createQuery(criteriaQuery).getSingleResult();

    }

    @Override
    public Long getUserProfileTotalCount(String emailId) {
        log.debug(" CommonFriendListDaoImpl getUserProfileTotalCount :: Start");
        CriteriaBuilder criteriaBuilder =
                this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery =
                criteriaBuilder.createQuery(Long.class);
        Root<Friends> userProfileRoot = criteriaQuery.from(Friends.class);
        TypedQuery<Long> typedQuery = null;
        Predicate emailIdCheck1 = criteriaBuilder.equal(
                userProfileRoot.get(FriendsConstants.FRIEND_EMAIL_ID), emailId);
        criteriaQuery.where(emailIdCheck1);
        criteriaQuery.select(criteriaBuilder.count(userProfileRoot));
        typedQuery = this.entityManager.createQuery(criteriaQuery);
        log.debug("CommonFriendListDaoImpl getUserProfileTotalCount :: End");
        return typedQuery.getSingleResult();
    }

    @Override
    public void insertUserProfile(UserProfile userProfile) {
        log.debug(" CommonFriendListDaoImpl insertUserProfile :: Start");
        this.entityManager.persist(userProfile);
        log.debug(" CommonFriendListDaoImpl insertUserProfile :: End");
    }

    @Override
    public void insertFriendsProfile(Friends friends) {
        log.debug(" CommonFriendListDaoImpl insertFriendsProfile :: Start");
        this.entityManager.persist(friends);
        log.debug(" CommonFriendListDaoImpl insertFriendsProfile :: End");
    }
}
