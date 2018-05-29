package com.friendmanagement.dto;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;

/**
 * <PRE>
 * Class name       : FriendsDtoTest
 * Description      : Test class which tests the {@link FriendsDto} methods. 
 * Author           : Capgemini.
 * </PRE>
 */
public class FriendsDtoTest {

    @InjectMocks
    private FriendsDto friendsDto = new FriendsDto();

    /**
     * Tests method setEmails.
     * 
     * It is expected that the function will execute without any exception.
     */
    @Test
    public void testSetUserProfileId() {
        List<String> emails = new ArrayList<>();
        emails.add("abab@gmail.com");
        this.friendsDto.setEmails(emails);
        assertEquals(emails, this.friendsDto.getEmails());
    }
}
