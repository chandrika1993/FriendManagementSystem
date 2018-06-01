package com.friendmanagement.service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

import com.friendmanagement.dao.FriendCreationDao;
import com.friendmanagement.dto.InformationDto;
import com.friendmanagement.dto.UserProfileDto;
import com.friendmanagement.exception.TechnicalException;
import com.friendmanagement.model.BlockStatus;
import com.friendmanagement.model.Friends;
import com.friendmanagement.model.UserProfile;
import com.friendmanagement.service.FriendCreationService;

/**
 * <PRE>
 * Class name       : FriendCreationServiceImplTest
 * Description      : Test class which tests the FriendCreationServiceImpl methods. 
 * Author           : Capgemini.
 * </PRE>
 */
@RunWith(MockitoJUnitRunner.class)
public class FriendCreationServiceImplTest {

    @InjectMocks
    private FriendCreationService friendCreationService =
            new FriendCreationServiceImpl();

    @Mock
    private FriendCreationDao friendsManagementDao;

    private static UserProfile userProfile = new UserProfile();
    private static List<Friends> friends = new ArrayList<>();
    private static List<String> friends1 = new ArrayList<>();
    private static Friends friends2 = new Friends();
    private static String email = "abc@gmail.com";
    private static Set<String> list = new HashSet<>();
    private static Set<Friends> listOfFriends = new HashSet<>();
    private static Set<BlockStatus> blockList = new HashSet<>();
    private static UserProfileDto userProfileDto = new UserProfileDto();
    private static InformationDto informationDto = new InformationDto();
    private static BlockStatus blockStatus = new BlockStatus();

    private Long count = 1L;

    /**
     * Initializes the objects before the test execution.
     * 
     */
    @BeforeClass
    public static void init() {
        listOfFriends.add(friends2);
        listOfFriends.addAll(friends);
        blockStatus.setEmailId("qqq@gmail.com");
        blockList.add(blockStatus);
        userProfile.setBlockList(blockList);
        userProfile.setUserEmailId("xyz@gmail.com");
        userProfile.setListOfFriends(listOfFriends);
        userProfile.setEmailSubscriptionList(null);
        userProfile.setUserEmailId(email);
        friends2.setEmailId(email);
        friends.add(friends2);
        friends.add(friends2);
        friends1.add(friends2.getEmailId());
        friends1.add("xyz@gmail.com");
        list.add(friends2.getEmailId());
        list.add(friends2.getEmailId());
        informationDto.setFriends(list);
        informationDto.setCount(1);
        userProfileDto.setEmailId("xyz@gmail.com");
        userProfileDto.setFriends(friends1);

    }

    /**
     * Tests method createFriendConnection.
     * 
     * Expectation is that returned satusCode equals 200.
     * 
     * @throws TechnicalException
     */
    @Test
    public void testCreateFriendConnectionSuccess() throws TechnicalException {
        informationDto.setSuccess(true);
        Mockito.when(this.friendsManagementDao.countFindUsers(anyString()))
                .thenReturn(this.count);
        Mockito.when(this.friendsManagementDao.findUsers(anyString()))
                .thenReturn(userProfile);
        Mockito.doNothing().when(this.friendsManagementDao)
                .updateUser(any(UserProfile.class));
        Assert.assertEquals(informationDto.isSuccess(),
                this.friendCreationService
                        .createFriendConnection(userProfileDto).isSuccess());
    }

    /**
     * Tests method createFriendConnection.
     * 
     * Expectation is that returned satusCode equals 200.
     * 
     * @throws TechnicalException
     */
    @Test
    public void testCreateFriendConnectionSuccess2() throws TechnicalException {
        Long count = 0L;
        informationDto.setSuccess(true);
        Mockito.when(this.friendsManagementDao.countFindUsers(anyString()))
                .thenReturn(count);
        Mockito.doNothing().when(this.friendsManagementDao)
                .insertUser(any(UserProfile.class));
        Assert.assertEquals(informationDto.isSuccess(),
                this.friendCreationService
                        .createFriendConnection(userProfileDto).isSuccess());
    }

    /**
     * Tests method createFriendConnection.
     * 
     * Expectation is that returned satusCode equals 503.
     * 
     * @throws TechnicalException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test(expected = TechnicalException.class)
    public void testCreateFriendConnectionTechnicalException1()
            throws TechnicalException {
        informationDto.setSuccess(false);
        Mockito.when(this.friendsManagementDao.countFindUsers(anyString()))
                .thenThrow(PersistenceException.class);
        Assert.assertEquals(false, this.friendCreationService
                .createFriendConnection(userProfileDto).isSuccess());
    }

    /**
     * Tests method createFriendConnection.
     * 
     * Expectation is that returned satusCode equals 401.
     * 
     * @throws TechnicalException
     * 
     */
    @Test(expected = TechnicalException.class)
    public void testCreateFriendConnectionUnAuthorized()
            throws TechnicalException {
        UserProfileDto userProfileDto = new UserProfileDto();
        List<String> friends = new ArrayList<>();
        friends.add(email);
        friends.add(email);
        userProfileDto.setFriends(friends);
        informationDto.setSuccess(false);
        Assert.assertEquals(false, this.friendCreationService
                .createFriendConnection(userProfileDto).isSuccess());
    }

    /**
     * Tests method createFriendConnection.
     * 
     * Expectation is that returned satusCode equals 401.
     * 
     * @throws TechnicalException
     * 
     */
    @Test(expected = TechnicalException.class)
    public void testCreateFriendConnectionWhenBlocked()
            throws TechnicalException {
        UserProfile userProfile = new UserProfile();
        BlockStatus blockStatus = new BlockStatus();
        Set<Friends> listOfFriends = new HashSet<>();
        Set<BlockStatus> blockList = new HashSet<>();
        UserProfileDto userProfileDto = new UserProfileDto();
        List<String> friends = new ArrayList<>();
        friends.add("asdsad@gmail.com");
        friends.add(email);
        listOfFriends.add(friends2);
        blockStatus.setEmailId(email);
        blockList.add(blockStatus);
        userProfile.setBlockList(blockList);
        userProfile.setListOfFriends(listOfFriends);
        userProfileDto.setFriends(friends);
        Mockito.when(this.friendsManagementDao.countFindUsers(anyString()))
                .thenReturn(this.count);
        Mockito.when(this.friendsManagementDao.findUsers(anyString()))
                .thenReturn(userProfile);
        Mockito.doNothing().when(this.friendsManagementDao)
                .updateUser(any(UserProfile.class));
        Assert.assertEquals(informationDto.isSuccess(),
                this.friendCreationService
                        .createFriendConnection(userProfileDto).isSuccess());
    }
}

