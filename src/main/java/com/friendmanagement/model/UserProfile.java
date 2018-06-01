package com.friendmanagement.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <PRE>
 * Class name       : UserProfile
 * Description      : Model for holding User Information. 
 * Author           : Capgemini.
 * </PRE>
 */
@Entity
@Table(name = "UserProfile")
@XmlRootElement
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userProfileId", length = 256, updatable = false,
            nullable = false)
    private Integer userProfileId;

    @Column(name = "userName", length = 256)
    private String userName;

    @Column(name = "userEmailId", length = 256)
    private String userEmailId;

    @Column(name = "contactNo", length = 256)
    private String contactNo;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private Set<Friends> listOfFriends;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private Set<Subscription> emailSubscriptionList;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private Set<BlockStatus> blockList;

    public Integer getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Integer userProfileId) {
        this.userProfileId = userProfileId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Set<Friends> getListOfFriends() {
        return listOfFriends;
    }

    public void setListOfFriends(Set<Friends> listOfFriends) {
        this.listOfFriends = listOfFriends;
    }

    public Set<Subscription> getEmailSubscriptionList() {
        return emailSubscriptionList;
    }

    public Set<BlockStatus> getBlockList() {
        return blockList;
    }

    public void setBlockList(Set<BlockStatus> blockList) {
        this.blockList = blockList;
    }

    public void setEmailSubscriptionList(
            Set<Subscription> emailSubscriptionList) {
        this.emailSubscriptionList = emailSubscriptionList;
    }
}
