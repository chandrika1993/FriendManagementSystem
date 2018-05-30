package com.friendmanagement.service.impl;

import static org.mockito.Matchers.anyString;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.PersistenceException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.friendmanagement.dao.UpdatesDao;
import com.friendmanagement.dao.impl.UpdatesDaoImpl;
import com.friendmanagement.dto.BlockDto;
import com.friendmanagement.dto.InformationDto;
import com.friendmanagement.dto.SubscriptionDto;
import com.friendmanagement.exception.TechnicalException;
import com.friendmanagement.model.BlockStatus;
import com.friendmanagement.model.Friends;
import com.friendmanagement.model.Subscription;
import com.friendmanagement.model.UserProfile;
import com.friendmanagement.service.UpdatesService;

/**
 * <PRE>
 * Class name       : UpdatesServiceImplTest
 * Description      : Test class which tests the UpdatesServiceImpl methods. 
 * Author           : Capgemini.
 * </PRE>
 */
@RunWith(MockitoJUnitRunner.class)
public class UpdatesServiceImplTest {

    @InjectMocks
    private UpdatesService updatesService = new UpdatesServiceImpl();

    @Mock
    private UpdatesDao updatesDao = new UpdatesDaoImpl();

    private static SubscriptionDto subscriptionDto = new SubscriptionDto();
    private static UserProfile userProfile = new UserProfile();
    private static InformationDto informationDto = new InformationDto();
    private static Set<BlockStatus> blockStatus = new HashSet<>();
    private static BlockStatus blockStatus2 = new BlockStatus();
    private static BlockDto blockDto = new BlockDto();
    private static Set<Subscription> emailSubscriptionList = new HashSet<>();
    private static Set<Friends> listOfFriends = new HashSet<>();
    private static Set<BlockStatus> blockList = new HashSet<>();
    private static Subscription subscription = new Subscription();
    private static Friends friends2 = new Friends();
    private Long count = 1l;


    /**
     * Initializes the objects before the test execution.
     * 
     */
    @BeforeClass
    public static void init() {
        blockStatus2.setEmailId("wwqww@gmail.com");
        subscription.setEmailId("abc@gmail.com");
        emailSubscriptionList.add(subscription);
        friends2.setEmailId("qww@gmail.com");
        listOfFriends.add(friends2);
        blockList.add(blockStatus2);
        userProfile.setListOfFriends(listOfFriends);
        userProfile.setEmailSubscriptionList(emailSubscriptionList);
        userProfile.setBlockList(blockList);
        userProfile.setUserEmailId("requestor@gmail.com");
        subscriptionDto.setRequestor("requestor@gmail.com");
        subscriptionDto.setTarget("qww@gmail.com");
        blockDto.setSender("requestor@gmail.com");
        blockDto.setText("sdfsdgf@gmail.com");
    }

    /**
     * Tests method subscribeForEmailUpdates.
     * 
     * Expectation is that returned satusCode equals 200.
     * 
     * @throws TechnicalException
     */
    @Test
    public void testSubscribeForEmailUpdatesSuccess()
            throws TechnicalException {
        blockStatus2.setEmailId("emailId@dfgf.com");
        blockStatus.add(blockStatus2);
        userProfile.setBlockList(blockStatus);
        informationDto.setSuccess(true);
        Mockito.when(this.updatesDao.countFindUsers(anyString()))
                .thenReturn(this.count);
        Mockito.when(this.updatesDao.findUsers(anyString()))
                .thenReturn(userProfile);
        Assert.assertEquals(this.updatesService
                .subscribeForEmailUpdates(subscriptionDto).isSuccess(),
                informationDto.isSuccess());
    }

    /**
     * Tests method subscribeForEmailUpdates.
     * 
     * Expectation is that returned satusCode equals 401.
     * 
     * @throws TechnicalException
     */
    @Test(expected = TechnicalException.class)
    public void testSubscribeForEmailUpdatesUnauthorized()
            throws TechnicalException {
        String emailId = "abc@gmail.com";
        subscriptionDto.setRequestor(emailId);
        subscriptionDto.setTarget(emailId);
        informationDto.setSuccess(false);
        Assert.assertEquals(this.updatesService
                .subscribeForEmailUpdates(subscriptionDto).isSuccess(),
                informationDto.isSuccess());
    }

    /**
     * Tests method subscribeForEmailUpdates.
     * 
     * Expectation is that returned satusCode equals 503.
     * 
     * @throws TechnicalException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test(expected = TechnicalException.class)
    public void testSubscribeForEmailUpdatesTechnicalException()
            throws TechnicalException {
        informationDto.setSuccess(false);
        Mockito.when(this.updatesDao.countFindUsers(anyString()))
                .thenThrow(IllegalArgumentException.class);
        Assert.assertEquals(this.updatesService
                .subscribeForEmailUpdates(subscriptionDto).isSuccess(), false);
    }

    /**
     * Tests method subscribeForEmailUpdates.
     * 
     * Expectation is that returned satusCode equals 404.
     * 
     * @throws TechnicalException
     * 
     */
    @Test(expected = TechnicalException.class)
    public void testSubscribeForEmailUpdatesDataNotFound()
            throws TechnicalException {
        Long count = 0l;
        informationDto.setSuccess(false);
        Mockito.when(this.updatesDao.countFindUsers(anyString()))
                .thenReturn(count);
        Assert.assertEquals(this.updatesService
                .subscribeForEmailUpdates(subscriptionDto).isSuccess(), false);
    }

    /**
     * Tests method subscribeForEmailUpdates.
     * 
     * Expectation is that returned satusCode equals 401.
     * 
     * @throws TechnicalException
     * 
     */
    @Test
    public void testSubscribeForEmailUpdatesBlockedStatus()
            throws TechnicalException {
        String email1 = "dfgh";
        String email2 = "fghjfgj";
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setRequestor(email1);
        subscriptionDto.setTarget(email2);
        Set<BlockStatus> blockList = new HashSet<>();
        BlockStatus blockStatus = new BlockStatus();
        blockStatus.setEmailId(email2);
        blockList.add(blockStatus);
        UserProfile userProfile = new UserProfile();
        userProfile.setListOfFriends(listOfFriends);
        userProfile.setBlockList(blockList);
        informationDto.setSuccess(false);
        Mockito.when(this.updatesDao.countFindUsers(anyString()))
                .thenReturn(count);
        Mockito.when(this.updatesDao.findUsers(anyString()))
                .thenReturn(userProfile);
        Assert.assertEquals(this.updatesService
                .subscribeForEmailUpdates(subscriptionDto).isSuccess(), false);
    }


    /**
     * Tests method subscribeForEmailUpdates.
     * 
     * Expectation is that returned satusCode equals 503.
     * 
     * @throws TechnicalException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test(expected = TechnicalException.class)
    public void testSubscribeForEmailUpdatesPersistanceException()
            throws TechnicalException {
        InformationDto informationDto = new InformationDto();
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setRequestor("asdfdf");
        subscriptionDto.setTarget("sdgf");
        informationDto.setSuccess(false);
        Mockito.when(this.updatesDao.countFindUsers(anyString()))
                .thenReturn(this.count);
        Mockito.when(this.updatesDao.findUsers(anyString()))
                .thenThrow(PersistenceException.class);
        Assert.assertEquals(this.updatesService
                .subscribeForEmailUpdates(subscriptionDto).isSuccess(), false);
    }

    /**
     * Tests method blockUpdates.
     * 
     * Expectation is that returned satusCode equals 200.
     * 
     * @throws TechnicalException
     */
    @Test
    public void testBlockUpdatesSuccess() throws TechnicalException {
        blockStatus2.setEmailId("emailId@dfgf.com");
        blockStatus.add(blockStatus2);
        userProfile.setBlockList(blockStatus);
        informationDto.setSuccess(true);
        Mockito.when(this.updatesDao.countFindUsers(anyString()))
                .thenReturn(this.count);
        Mockito.when(this.updatesDao.findUsers(anyString()))
                .thenReturn(userProfile);
        Assert.assertEquals(
                this.updatesService.blockUpdates(subscriptionDto).isSuccess(),
                informationDto.isSuccess());
    }

    /**
     * Tests method blockUpdates.
     * 
     * Expectation is that returned satusCode equals 401.
     * 
     * @throws TechnicalException
     */
    @Test(expected = TechnicalException.class)
    public void testBlockUpdatesUnauthorized() throws TechnicalException {
        String emailId = "abc@gmail.com";
        subscriptionDto.setRequestor(emailId);
        subscriptionDto.setTarget(emailId);
        informationDto.setSuccess(false);
        Assert.assertEquals(
                this.updatesService.blockUpdates(subscriptionDto).isSuccess(),
                informationDto.isSuccess());
    }

    /**
     * Tests method blockUpdates.
     * 
     * Expectation is that returned satusCode equals 404.
     * 
     * @throws TechnicalException
     * 
     */
    @Test(expected = TechnicalException.class)
    public void testBlockUpdatesDataNotFound() throws TechnicalException {
        Long count = 0l;
        informationDto.setSuccess(false);
        Mockito.when(this.updatesDao.countFindUsers(anyString()))
                .thenReturn(count);
        Assert.assertEquals(
                this.updatesService.blockUpdates(subscriptionDto).isSuccess(),
                false);
    }

    /**
     * Tests method blockUpdates.
     * 
     * Expectation is that returned satusCode equals 503.
     * 
     * @throws TechnicalException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test(expected = TechnicalException.class)
    public void testBlockUpdatesPersistanceException()
            throws TechnicalException {
        InformationDto informationDto = new InformationDto();
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setRequestor("asdfdf");
        subscriptionDto.setTarget("sdgf");
        informationDto.setSuccess(false);
        Mockito.when(this.updatesDao.countFindUsers(anyString()))
                .thenReturn(this.count);
        Mockito.when(this.updatesDao.findUsers(anyString()))
                .thenThrow(PersistenceException.class);
        Assert.assertEquals(
                this.updatesService.blockUpdates(subscriptionDto).isSuccess(),
                false);
    }

    /**
     * Tests method getEmailsWithSubscription.
     * 
     * Expectation is that returned satusCode equals 200.
     * 
     * @throws TechnicalException
     */
    @Test
    public void testGetEmailsWithSubscriptionSuccess()
            throws TechnicalException {
        informationDto.setSuccess(true);
        blockStatus2.setEmailId("emailId@dfgf.com");
        blockStatus.add(blockStatus2);
        userProfile.setBlockList(blockStatus);
        informationDto.setSuccess(true);
        Mockito.when(this.updatesDao.countFindUsers(anyString()))
                .thenReturn(this.count);
        Mockito.when(this.updatesDao.findUsers(anyString()))
                .thenReturn(userProfile);
        Assert.assertEquals(this.updatesService
                .getEmailsWithSubscription(blockDto).isSuccess(),
                informationDto.isSuccess());
    }

    /**
     * Tests method getEmailsWithSubscription.
     * 
     * Expectation is that returned satusCode equals 503.
     * 
     * @throws TechnicalException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test(expected = TechnicalException.class)
    public void testGetEmailsWithSubscriptionTechnicalException()
            throws TechnicalException {
        informationDto.setSuccess(false);
        Mockito.when(this.updatesDao.countFindUsers(anyString()))
                .thenThrow(PersistenceException.class);
        Assert.assertEquals(this.updatesService
                .getEmailsWithSubscription(blockDto).isSuccess(), false);
    }

    /**
     * Tests method getEmailsWithSubscription.
     * 
     * Expectation is that returned satusCode equals 401.
     * 
     * @throws TechnicalException
     * 
     */
    @Test(expected = TechnicalException.class)
    public void testGetEmailsWithSubscriptionBlockedStatus()
            throws TechnicalException {
        UserProfile userProfile = new UserProfile();
        BlockDto blockDto = new BlockDto();
        Friends friends = new Friends();
        String emailId = "asfasdf";
        friends.setEmailId(emailId);
        blockDto.setSender(emailId);
        blockDto.setText("sdfsdgf@gmail.com");
        Set<BlockStatus> blockList = new HashSet<>();
        Set<Friends> listOfFriends = new HashSet<>();
        BlockStatus blockStatus = new BlockStatus();
        blockStatus.setEmailId(emailId);
        blockList.add(blockStatus);
        userProfile.setBlockList(blockList);
        listOfFriends.add(friends);
        friends.setEmailId("sdgfsdg");
        listOfFriends.add(friends);
        userProfile.setListOfFriends(listOfFriends);
        informationDto.setSuccess(false);
        Mockito.when(this.updatesDao.countFindUsers(anyString()))
                .thenReturn(count);
        Mockito.when(this.updatesDao.findUsers(anyString()))
                .thenReturn(userProfile);
        Assert.assertEquals(this.updatesService
                .getEmailsWithSubscription(blockDto).isSuccess(), false);
    }

    /**
     * Tests method getEmailsWithSubscription.
     * 
     * Expectation is that returned satusCode equals 404.
     * 
     * @throws TechnicalException
     * 
     */
    @Test(expected = TechnicalException.class)
    public void testGetEmailsWithSubscriptionDataNotFound()
            throws TechnicalException {
        Long count = 0l;
        informationDto.setSuccess(false);
        Mockito.when(this.updatesDao.countFindUsers(anyString()))
                .thenReturn(count);
        Assert.assertEquals(this.updatesService
                .getEmailsWithSubscription(blockDto).isSuccess(), false);
    }
}
