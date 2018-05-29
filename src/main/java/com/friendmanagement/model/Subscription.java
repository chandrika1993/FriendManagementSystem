package com.friendmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <PRE>
 * Class name       : Subscription
 * Description      : Model for holding Subscription Information. 
 * Author           : Capgemini.
 * </PRE>
 */
@Entity
@Table(name = "Subscription")
@XmlRootElement
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 256, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "emailId", length = 256, updatable = false, nullable = false)
    private String emailId;

    @Column(name = "subscriptionStatus", length = 256, updatable = false)
    private Character subscriptionStatus;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "userProfileId", referencedColumnName = "userProfileId")
    private UserProfile userProfile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Character getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(Character subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public String toString() {
        return "Subscription [id=" + id + ", emailId=" + emailId
                + ", subscriptionStatus=" + subscriptionStatus
                + ", userProfile=" + userProfile + "]";
    }
}
