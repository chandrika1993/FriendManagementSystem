package com.friendmanagement.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;

/**
 * <PRE>
 * Class name       : UserProfileDtoTest
 * Description      : Test class which tests the {@link UserProfileDto} methods. 
 * Author           : Capgemini.
 * </PRE>
 */
public class UserProfileDtoTest {

    @InjectMocks
    private UserProfileDto userProfileDto = new UserProfileDto();

    /**
     * Tests method setUserName.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testSetUserName() {
        this.userProfileDto.setUserName("userName");
        assertEquals("userName", this.userProfileDto.getUserName());
    }

    /**
     * Tests method setEmailId.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testSetEmailId() {
        this.userProfileDto.setEmailId("userName@sds.com");
        assertEquals("userName@sds.com", this.userProfileDto.getEmailId());
    }
    

    /**
     * Tests method setContactNo.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testSetContactNo() {
        this.userProfileDto.setContactNo("9876543211");
        assertEquals("9876543211", this.userProfileDto.getContactNo());
    }
}
