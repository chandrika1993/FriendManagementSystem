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
import com.friendmanagement.dao.FriendCreationDao;
import com.friendmanagement.model.UserProfile;


/**
 * <PRE>
 * Class name       : FriendCreationDaoImplTestInMemory
 * Description      : Test class which tests the FriendCreationDaoImpl methods. 
 * Author           : Capgemini.
 * </PRE>
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InMemoryDBConfig.class },
        initializers = ConfigFileApplicationContextInitializer.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
@WebAppConfiguration
@Transactional
public class FriendCreationDaoImplTestInMemory {

    @Autowired
    private FriendCreationDao friendCreationDao;


    private static UserProfile userProfile = new UserProfile();

    /**
     * Initializes the objects before the test execution.
     */
    @BeforeClass
    public static void init() {
        userProfile.setUserEmailId("abc@gmail.com");
    }

    /**
     * Tests method insertUser.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testInsertUser() {
        Long count = 1L;
        UserProfile userProfile = new UserProfile();
        userProfile.setUserEmailId("abc@gmail.com");
        this.friendCreationDao.insertUser(userProfile);
        Assert.assertEquals(count, this.friendCreationDao
                .countFindUsers(userProfile.getUserEmailId()));
    }

    /**
     * Tests method findUsers.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testFindUser() {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserEmailId("abc@gmail.com");
        this.friendCreationDao.insertUser(userProfile);
        Assert.assertEquals(userProfile,
                this.friendCreationDao.findUsers(userProfile.getUserEmailId()));
    }

    /**
     * Tests method updateUser.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testUpdateUser() {
        Long count = 1L;
        userProfile.setUserEmailId("abc@gmail.com");
        this.friendCreationDao.updateUser(userProfile);
        Assert.assertEquals(count, this.friendCreationDao
                .countFindUsers(userProfile.getUserEmailId()));
    }
}
