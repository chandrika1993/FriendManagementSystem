package com.friendmanagement.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * <PRE>
 * Class name       : UserProfileTest
 * Description      : Test class which tests the {@link UserProfile} methods. 
 * Author           : Capgemini.
 * </PRE>
 */
@RunWith(MockitoJUnitRunner.class)
public class UserProfileTest {

    @InjectMocks
    private UserProfile userProfile = new UserProfile();

    /**
     * Tests method setUserProfileId.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testSetUserProfileId() {
        this.userProfile.setUserProfileId(6);
        assertEquals(6, this.userProfile.getUserProfileId(), 0);
    }

    /**
     * Tests method setUserName.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testSetUserName() {
        String name = "man";
        this.userProfile.setUserName(name);
        assertEquals(name, this.userProfile.getUserName());
    }

    /**
     * Tests method setContactNo.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testSetContactNo() {
        String contactNo = "112412343";
        this.userProfile.setContactNo(contactNo);
        assertEquals(contactNo, this.userProfile.getContactNo());
    }
}
