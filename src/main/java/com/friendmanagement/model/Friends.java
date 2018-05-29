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
 * Class name       : Friends
 * Description      : Model for holding Friends Information. 
 * Author           : Capgemini.
 * </PRE>
 */
@Entity
@Table(name = "Friends")
@XmlRootElement
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 256, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "friendEmailId", length = 256, updatable = false,
            nullable = false)
    private String emailId;

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

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

}
