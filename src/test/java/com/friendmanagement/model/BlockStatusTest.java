package com.friendmanagement.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;

/**
 * <PRE>
 * Class name       : BlockStatusTest
 * Description      : Test class which tests the {@link BlockStatus} methods. 
 * Author           : Capgemini.
 * </PRE>
 */
public class BlockStatusTest {

    @InjectMocks
    private BlockStatus blockStatus = new BlockStatus();

    /**
     * Tests method setId.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testSetUserProfileId() {
        this.blockStatus.setId(6);
        assertEquals(6, this.blockStatus.getId(), 0);
    }

    /**
     * Tests method setBlockStatus.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testSetBlockStatus() {
        this.blockStatus.setBlockStatus('Y');
        assertEquals('Y', this.blockStatus.getBlockStatus(), 0);
    }

    /**
     * Tests method toString.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testToString() {
        this.blockStatus.setId(1);
        assertEquals(this.blockStatus.toString(),
                "Subscription [id=" + blockStatus.getId() + ", emailId="
                        + blockStatus.getEmailId() + ", blockInfo="
                        + blockStatus.getBlockStatus() + ", userProfile="
                        + blockStatus.getUserProfile() + "]");
    }
}
