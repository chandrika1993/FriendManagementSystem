package com.friendmanagement.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * <PRE>
 * Class name       : UserProfileDto
 * Description      : DTO for holding User Information. 
 * Author           : Capgemini.
 * </PRE>
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class UserProfileDto {

    private String userName;
    private String emailId;
    private String contactNo;
    private List<String> friends;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the contactNo
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * @param contactNo to set
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * @return the friends
     */
    public List<String> getFriends() {
        return friends;
    }

    /**
     * @param friends to set
     */
    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
