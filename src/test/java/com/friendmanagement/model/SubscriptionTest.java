package com.friendmanagement.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * <PRE>
 * Class name       : SubscriptionTest
 * Description      : Test class which tests the {@link Subscription} methods. 
 * Author           : Capgemini.
 * </PRE>
 */
@RunWith(MockitoJUnitRunner.class)
public class SubscriptionTest {

    @InjectMocks
    private Subscription subscription = new Subscription();

    /**
     * Tests method setId.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testSetId() {
        this.subscription.setId(6);
        assertEquals(6, this.subscription.getId(), 0);
    }

    /**
     * Tests method setEmailId.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testSetEmailId() {
        String emailId = "asdasd";
        this.subscription.setEmailId(emailId);
        assertEquals(emailId, this.subscription.getEmailId());
    }

    /**
     * Tests method setSubscriptionStatus.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testSetSubscriptionStatus() {
        this.subscription.setSubscriptionStatus('Y');
        assertEquals('Y', this.subscription.getSubscriptionStatus(), 0);
    }

    /**
     * Tests method setUserProfile.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testSetUserProfile() {
        String emailId = "asdasd";
        UserProfile userProfile = new UserProfile();
        userProfile.setUserEmailId(emailId);
        this.subscription.setUserProfile(userProfile);
        assertEquals(userProfile, this.subscription.getUserProfile());
    }

    /**
     * Tests method toString.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testToString() {
        this.subscription.setId(1);
        assertEquals(this.subscription.toString(),
                "Subscription [id=" + subscription.getId() + ", emailId="
                        + subscription.getEmailId() + ", subscriptionStatus="
                        + subscription.getSubscriptionStatus()
                        + ", userProfile=" + subscription.getUserProfile()
                        + "]");
    }
}
