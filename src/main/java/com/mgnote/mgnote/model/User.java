package com.mgnote.mgnote.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String userName;
    private String password;
    private String mail;
    private String avatarUrl;
    private String directoryId;
    private List<BriefUser> friends;

    public User() {
    }

    public User(String id, String userName, String password, String mail, String avatarUrl, String directoryId, List<BriefUser> friends) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.avatarUrl = avatarUrl;
        this.directoryId = directoryId;
        this.friends = friends;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(String directoryId) {
        this.directoryId = directoryId;
    }

    public List<BriefUser> getFriends() {
        return friends;
    }

    public void setFriends(List<BriefUser> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", directoryId='" + directoryId + '\'' +
                ", friends=" + friends +
                '}';
    }
}
