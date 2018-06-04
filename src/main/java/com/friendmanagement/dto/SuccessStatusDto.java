package com.friendmanagement.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * <PRE>
 * Class name       : SuccessStatusDto
 * Description      : DTO for holding Success Status Information. 
 * Author           : Capgemini.
 * </PRE>
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class SuccessStatusDto {

    private boolean success;

    /**
     * @return the status
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param status the status to set
     */
    public void setSuccess(boolean status) {
        this.success = status;
    }

}
