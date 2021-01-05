package com.mgnote.mgnote.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "share_note")
public class ShareNote{
    private String userId;
    private String userName;
    private String describe;
    private List<Comment> commentList;
    private String avatarUrl;
    private String noteId;

    public ShareNote() {
    }

    public ShareNote(String userId, String userName, String describe, List<Comment> commentList, String avatarUrl, String noteId) {
        this.userId = userId;
        this.userName = userName;
        this.describe = describe;
        this.commentList = commentList;
        this.avatarUrl = avatarUrl;
        this.noteId = noteId;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    @Override
    public String toString() {
        return "ShareNote{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", describe='" + describe + '\'' +
                ", commentList=" + commentList +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", noteId='" + noteId + '\'' +
                '}';
    }
}
