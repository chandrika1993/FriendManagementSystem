package com.friendmanagement.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * <PRE>
 * Class name       : FriendsDto
 * Description      : DTO for holding Friends Information. 
 * Author           : Capgemini.
 * </PRE>
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class FriendsDto {

    private List<String> emails;

    /**
     * @return the emails
     */
    public List<String> getEmails() {
        return emails;
    }

    /**
     * @param emails the email list to set
     */
    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
