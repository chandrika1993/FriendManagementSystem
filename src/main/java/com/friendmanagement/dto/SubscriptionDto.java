package com.friendmanagement.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * <PRE>
 * Class name       : SubscriptionDto
 * Description      : DTO for holding subscription Information. 
 * Author           : Capgemini.
 * </PRE>
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class SubscriptionDto {
    
    private String requestor;

    private String target;

    /**
     * @return the requestor
     */
    public String getRequestor() {
        return requestor;
    }

    /**
     * @param requestor the requestor to set
     */
    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(String target) {
        this.target = target;
    }
}
