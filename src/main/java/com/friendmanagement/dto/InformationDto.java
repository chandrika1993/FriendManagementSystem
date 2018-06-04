package com.friendmanagement.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * <PRE>
 * Class name       : InformationDto
 * Description      : DTO for holding Information. 
 * Author           : Capgemini.
 * </PRE>
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class InformationDto {

    private boolean success;
    private Set<String> friends;
    private int count;

    /**
     * @return the friends
     */
    public Set<String> getFriends() {
        return friends;
    }

    /**
     * @param friends the friends to set
     */
    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param i the count to set
     */
    public void setCount(int i) {
        this.count = i;
    }
}
