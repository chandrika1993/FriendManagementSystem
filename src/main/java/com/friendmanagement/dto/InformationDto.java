package com.friendmanagement.dto;

import java.util.Set;

/**
 * <PRE>
 * Class name       : InformationDto
 * Description      : DTO for holding Information. 
 * Author           : Capgemini.
 * </PRE>
 */
public class InformationDto {

    private boolean success;
    private int count;
    private Set<String> friends;

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
