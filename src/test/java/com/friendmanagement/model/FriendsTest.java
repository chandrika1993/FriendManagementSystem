package com.friendmanagement.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;

/**
 * <PRE>
 * Class name       : FriendsTest
 * Description      : Test class which tests the {@link Friends} methods. 
 * Author           : Capgemini.
 * </PRE>
 */
public class FriendsTest {

    @InjectMocks
    private Friends friends = new Friends();

    /**
     * Tests method setId.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testSetId() {
        this.friends.setId(6);
        assertEquals(6, this.friends.getId(), 0);
    }

    /**
     * Tests method setUserProfile.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testSetUserProfile() {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserEmailId("email@gmail.com");
        this.friends.setUserProfile(userProfile);
        assertEquals(userProfile, this.friends.getUserProfile());
    }
}
