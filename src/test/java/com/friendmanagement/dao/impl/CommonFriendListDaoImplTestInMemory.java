
package com.friendmanagement.dao.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.InMemoryDBConfig;
import com.friendmanagement.dao.CommonFriendListDao;
import com.friendmanagement.model.Friends;
import com.friendmanagement.model.UserProfile;

/**
 * <PRE>
 * Class name       : CommonFriendListDaoImplTestInMemory
 * Description      : Test class which tests the CommonFriendListDaoImpl methods. 
 * Author           : Capgemini.
 * </PRE>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InMemoryDBConfig.class },
        initializers = ConfigFileApplicationContextInitializer.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
@WebAppConfiguration
@Transactional
public class CommonFriendListDaoImplTestInMemory {


    @Autowired
    private CommonFriendListDao commonFriendListDao;


    private static UserProfile userProfile = new UserProfile();

    /**
     * Initializes the objects before the test execution.
     */
    @BeforeClass
    public static void init() {
        userProfile.setUserEmailId("abc@gmail.com");
    }

    /**
     * Tests method getUserProfile.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testGetUserProfile() {
        this.commonFriendListDao.insertUserProfile(userProfile);
        Assert.assertEquals(userProfile, this.commonFriendListDao
                .getUserProfile(userProfile.getUserEmailId()));
    }

    /**
     * Tests method getUserProfileTotalCount.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testGetUserProfileTotalCount() {
        Long count = 0l;
        UserProfile userProfile = new UserProfile();
        Friends friends = new Friends();
        userProfile.setUserEmailId("abc@gmail.com");
        friends.setUserProfile(userProfile);
        this.commonFriendListDao.insertUserProfile(userProfile);
        this.commonFriendListDao.insertFriendsProfile(friends);
        this.commonFriendListDao.insertUserProfile(userProfile);
        Assert.assertEquals(count, this.commonFriendListDao
                .getUserProfileTotalCount(userProfile.getUserEmailId()));
    }
}

