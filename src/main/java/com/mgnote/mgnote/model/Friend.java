package com.mgnote.mgnote.model;

public class Friend {
    private String userId;
    private String userName;
    private String mail;
    private String avatarUrl;

    public Friend(String userId, String userName, String mail, String avatarUrl) {
        this.userId = userId;
        this.userName = userName;
        this.mail = mail;
        this.avatarUrl = avatarUrl;
    }

    public Friend() {
    }

    @Override
    public String toString() {
        return "Friend{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", mail='" + mail + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
