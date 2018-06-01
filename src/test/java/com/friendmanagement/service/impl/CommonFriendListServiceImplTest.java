package com.friendmanagement.service.impl;

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
import org.springframework.dao.EmptyResultDataAccessException;

import com.friendmanagement.dao.CommonFriendListDao;
import com.friendmanagement.dto.InformationDto;
import com.friendmanagement.dto.UserProfileDto;
import com.friendmanagement.exception.TechnicalException;
import com.friendmanagement.model.Friends;
import com.friendmanagement.model.UserProfile;
import com.friendmanagement.service.CommonFriendListService;

/**
 * <PRE>
 * Class name       : CommonFriendListServiceImplTest
 * Description      : Test class which tests the CommonFriendListServiceImpl methods. 
 * Author           : Capgemini.
 * </PRE>
 */
@RunWith(MockitoJUnitRunner.class)
public class CommonFriendListServiceImplTest {

    @InjectMocks
    private CommonFriendListService commonFriendListService =
            new CommonFriendListServiceImpl();

    @Mock
    private CommonFriendListDao commonFriendListDao;

    private static UserProfile userProfile = new UserProfile();
    private static UserProfileDto userProfileDto = new UserProfileDto();
    private Long count = 1L;
    private static List<Friends> friends = new ArrayList<>();
    private static Friends friends1 = new Friends();
    private static Friends friends2 = new Friends();
    private static String email = "abc@gmail.com";
    private static List<String> list2 = new ArrayList<>();
    private static Set<String> list = new HashSet<>();
    private static Set<Friends> listOfFriends = new HashSet<>();
    private static InformationDto informationDto = new InformationDto();

    /**
     * Initializes the objects before the test execution.
     * 
     */
    @BeforeClass
    public static void init() {
        friends2.setEmailId("sdfd@gmail.com");
        friends1.setEmailId("sdffd");
        userProfile.setUserEmailId(email);
        listOfFriends.add(friends1);
        userProfile.setListOfFriends(listOfFriends);
        userProfileDto.setEmailId(email);
        list2.add(friends2.getEmailId());
        list2.add("asdfs@gmail.com");
        userProfileDto.setFriends(list2);
        friends.add(friends2);
        for (int i = 0; i < friends.size(); i++) {
            list.add(friends.get(i).getEmailId());
        }
        informationDto.setFriends(list);
    }


    /**
     * Tests method getFriends.
     * 
     * Expectation is that returned satusCode equals 200.
     * 
     * @throws TechnicalException
     */
    @Test
    public void testGetFriendsSuccess() throws TechnicalException {
        Mockito.when(
                this.commonFriendListDao.getUserProfileTotalCount(anyString()))
                .thenReturn(this.count);
        Mockito.when(this.commonFriendListDao.getUserProfile(anyString()))
                .thenReturn(userProfile);
        Assert.assertEquals(1, this.commonFriendListService
                .getFriends(userProfileDto).getFriends().size());
    }

    /**
     * Tests method getFriends.
     * 
     * Expectation is that returned satusCode equals 404.
     * 
     * @throws TechnicalException
     * 
     */
    @Test(expected = TechnicalException.class)
    public void testGetFriendsTechnicalException() throws TechnicalException {
        Long count = 0L;
        Mockito.when(
                this.commonFriendListDao.getUserProfileTotalCount(anyString()))
                .thenReturn(count);
        Assert.assertEquals(informationDto.getCount(),
                this.commonFriendListService.getFriends(userProfileDto)
                        .getCount());
    }

    /**
     * Tests method getFriends.
     * 
     * Expectation is that returned satusCode equals 503.
     * 
     * @throws TechnicalException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test(expected = TechnicalException.class)
    public void testGetFriendsTechnicalException1() throws TechnicalException {
        informationDto.setSuccess(false);
        Mockito.when(
                this.commonFriendListDao.getUserProfileTotalCount(anyString()))
                .thenReturn(this.count);
        Mockito.when(this.commonFriendListDao.getUserProfile(anyString()))
                .thenThrow(TechnicalException.class);
        Assert.assertEquals(false, this.commonFriendListService
                .getFriends(userProfileDto).isSuccess());
    }

    /**
     * Tests method getFriends.
     * 
     * Expectation is that returned satusCode equals 404.
     * 
     * @throws TechnicalException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test(expected = TechnicalException.class)
    public void testGetFriendsPersistanceException() throws TechnicalException {
        Mockito.when(
                this.commonFriendListDao.getUserProfileTotalCount(anyString()))
                .thenReturn(this.count);
        Mockito.when(this.commonFriendListDao.getUserProfile(anyString()))
                .thenThrow(PersistenceException.class);
        Assert.assertEquals(false, this.commonFriendListService
                .getFriends(userProfileDto).isSuccess());
    }

    /**
     * Tests method getFriends.
     * 
     * Expectation is that returned satusCode equals 404.
     * 
     * @throws TechnicalException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test(expected = TechnicalException.class)
    public void testGetFriendsEmptyResultDataAccessException()
            throws TechnicalException {
        Mockito.when(
                this.commonFriendListDao.getUserProfileTotalCount(anyString()))
                .thenReturn(this.count);
        Mockito.when(this.commonFriendListDao.getUserProfile(anyString()))
                .thenThrow(EmptyResultDataAccessException.class);
        Assert.assertEquals(false, this.commonFriendListService
                .getFriends(userProfileDto).isSuccess());
    }


    /**
     * Tests method getCommonFriends.
     * 
     * Expectation is that returned satusCode equals 200.
     * 
     * @throws TechnicalException
     */
    @Test
    public void testGetCommonFriendsSuccess() throws TechnicalException {
        Mockito.when(
                this.commonFriendListDao.getUserProfileTotalCount(anyString()))
                .thenReturn(this.count);
        Mockito.when(this.commonFriendListDao.getUserProfile(anyString()))
                .thenReturn(userProfile);
        Assert.assertEquals(informationDto.getFriends().size(),
                this.commonFriendListService.getCommonFriends(userProfileDto)
                        .getFriends().size());
    }

    /**
     * Tests method getCommonFriends.
     * 
     * Expectation is that returned satusCode equals 404.
     * 
     * @throws TechnicalException
     * 
     */
    @Test(expected = TechnicalException.class)
    public void testGetCommonFriendsTechnicalException()
            throws TechnicalException {
        Long count = 0L;
        Mockito.when(
                this.commonFriendListDao.getUserProfileTotalCount(anyString()))
                .thenReturn(count);
        Mockito.when(this.commonFriendListDao.getUserProfile(anyString()))
                .thenReturn(userProfile);
        Assert.assertEquals(informationDto.getCount(),
                this.commonFriendListService.getCommonFriends(userProfileDto)
                        .getCount());
    }

    /**
     * Tests method getCommonFriends.
     * 
     * Expectation is that returned satusCode equals 404.
     * 
     * @throws TechnicalException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test(expected = TechnicalException.class)
    public void testGetCommonFriendsPeristenceException()
            throws TechnicalException {
        Mockito.when(
                this.commonFriendListDao.getUserProfileTotalCount(anyString()))
                .thenReturn(this.count);
        Mockito.when(this.commonFriendListDao.getUserProfile(anyString()))
                .thenThrow(PersistenceException.class);
        Assert.assertEquals(false, this.commonFriendListService
                .getCommonFriends(userProfileDto).isSuccess());
    }

    /**
     * Tests method getCommonFriends.
     * 
     * Expectation is that returned satusCode equals 404.
     * 
     * @throws TechnicalException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test(expected = TechnicalException.class)
    public void testGetCommonFriendsEmptyResultDataAccessException()
            throws TechnicalException {
        Mockito.when(
                this.commonFriendListDao.getUserProfileTotalCount(anyString()))
                .thenReturn(this.count);
        Mockito.when(this.commonFriendListDao.getUserProfile(anyString()))
                .thenThrow(EmptyResultDataAccessException.class);
        Assert.assertEquals(false, this.commonFriendListService
                .getCommonFriends(userProfileDto).isSuccess());
    }

    /**
     * Tests method getCommonFriends.
     * 
     * Expectation is that returned satusCode equals 503.
     * 
     * @throws TechnicalException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test(expected = TechnicalException.class)
    public void testGetCommonFriendsTechnicalException1()
            throws TechnicalException {
        Mockito.when(
                this.commonFriendListDao.getUserProfileTotalCount(anyString()))
                .thenReturn(this.count);
        Mockito.when(this.commonFriendListDao.getUserProfile(anyString()))
                .thenThrow(TechnicalException.class);
        Assert.assertEquals(false, this.commonFriendListService
                .getCommonFriends(userProfileDto).isSuccess());
    }

    /**
     * Tests method getCommonFriends.
     * 
     * Expectation is that returned satusCode equals 401.
     * 
     * @throws TechnicalException
     * 
     */
    @Test(expected = TechnicalException.class)
    public void testGetCommonFriendsSameEmailsException1()
            throws TechnicalException {
        String emailId = "FSDFD";
        UserProfileDto userProfileDto = new UserProfileDto();
        Friends friends = new Friends();
        friends.setEmailId(emailId);
        List<String> listOfFriends = new ArrayList<>();
        listOfFriends.add(friends.getEmailId());
        listOfFriends.add(friends.getEmailId());
        userProfileDto.setFriends(listOfFriends);
        Assert.assertEquals(false, this.commonFriendListService
                .getCommonFriends(userProfileDto).isSuccess());
    }

    /**
     * Tests method getCommonFriends.
     * 
     * Expectation is that returned satusCode equals 401.
     * 
     * @throws TechnicalException
     * 
     */
    @Test(expected = TechnicalException.class)
    public void testGetCommonFriendsNoCommonFriends()
            throws TechnicalException {
        String emailId = "FSDFD";
        UserProfileDto userProfileDto = new UserProfileDto();
        UserProfile profile = new UserProfile();
        Set<Friends> frnds = new HashSet<>();
        Friends friends = new Friends();
        Friends friends1 = new Friends();
        friends1.setEmailId("dfsdf");
        friends.setEmailId(emailId);
        friends.setUserProfile(profile);
        friends.setEmailId("afsdfsdF");
        profile.setListOfFriends(frnds);
        List<String> listOfFriends = new ArrayList<>();
        listOfFriends.add(friends.getEmailId());
        listOfFriends.add(friends1.getEmailId());
        userProfileDto.setFriends(listOfFriends);
        Mockito.when(
                this.commonFriendListDao.getUserProfileTotalCount(anyString()))
                .thenReturn(count);
        Mockito.when(this.commonFriendListDao.getUserProfile(anyString()))
                .thenReturn(profile);
        Assert.assertEquals(false, this.commonFriendListService
                .getCommonFriends(userProfileDto).isSuccess());
    }
}
