package com.mgnote.mgnote.model;

import java.util.Date;

public class Comment {
    private String id;
    private String userName;
    private String content;
    private String avatarUrl;
    private Date createTime;

    public Comment() {
    }

    public Comment(String id, String userName, String content, String avatarUrl, Date createTime) {
        this.id = id;
        this.userName = userName;
        this.content = content;
        this.avatarUrl = avatarUrl;
        this.createTime = createTime;
    }

    public Comment(BriefUser briefUser){
        this.avatarUrl = briefUser.getAvatarUrl();
        this.userName = briefUser.getUserName();
        this.createTime = new Date();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
