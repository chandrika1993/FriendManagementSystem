package com.friendmanagement.dao.impl;

import org.junit.Assert;
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
import com.friendmanagement.dao.UpdatesDao;
import com.friendmanagement.model.UserProfile;


/**
 * <PRE>
 * Class name       : UpdatesDaoImplTestInMemory
 * Description      : Test class which tests the FriendCreationDaoImpl methods. 
 * Author           : Capgemini.
 * </PRE>
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
@ContextConfiguration(classes = { InMemoryDBConfig.class },
        initializers = ConfigFileApplicationContextInitializer.class)
@WebAppConfiguration
@Transactional
public class UpdatesDaoImplTestInMemory {

    @Autowired
    private UpdatesDao updatesDao;

    /**
     * Tests method countFindUsers.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testCountFindUsers() {
        Long count = 1l;
        UserProfile userProfile = new UserProfile();
        userProfile.setUserEmailId("abc@gmail.com");
        this.updatesDao.insertUserProfile(userProfile);
        Assert.assertEquals(
                this.updatesDao.countFindUsers(userProfile.getUserEmailId()),
                count);
    }

    /**
     * Tests method findUsers.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testFindUsers() {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserEmailId("abc@gmail.com");
        this.updatesDao.insertUserProfile(userProfile);
        Assert.assertEquals(
                this.updatesDao.findUsers(userProfile.getUserEmailId()),
                userProfile);
    }

    /**
     * Tests method subscribeForEmailUpdates.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testSubscribeForEmailUpdates() {
        Long count = 1l;
        UserProfile userProfile = new UserProfile();
        userProfile.setUserEmailId("abc@gmail.com");
        this.updatesDao.subscribeForEmailUpdates(userProfile);
        Assert.assertEquals(
                this.updatesDao.countFindUsers(userProfile.getUserEmailId()),
                count);
    }

    /**
     * Tests method blockUpdates.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testBlockUpdates() {
        Long count = 1l;
        UserProfile userProfile = new UserProfile();
        userProfile.setUserEmailId("abc@gmail.com");
        this.updatesDao.blockUpdates(userProfile);
        Assert.assertEquals(
                this.updatesDao.countFindUsers(userProfile.getUserEmailId()),
                count);
    }
}
