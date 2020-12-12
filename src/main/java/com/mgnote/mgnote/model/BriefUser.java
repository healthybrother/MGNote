package com.mgnote.mgnote.model;

public class BriefUser {
    private String userId;
    private String userName;
    private String mail;
    private String avatarUrl;

    public BriefUser(String userId, String userName, String mail, String avatarUrl) {
        this.userId = userId;
        this.userName = userName;
        this.mail = mail;
        this.avatarUrl = avatarUrl;
    }

    public BriefUser() {
    }

    public BriefUser(User user){
        this.avatarUrl = user.getAvatarUrl();
        this.mail = user.getMail();
        this.userId = user.getId();
        this.userName = user.getUserName();
    }

    @Override
    public String toString() {
        return "BriefUserService{" +
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
