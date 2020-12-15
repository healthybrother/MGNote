package com.mgnote.mgnote.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "notebook")
public class NoteBook {
    @Id
    private String id;
    private BriefDirectory prevDirectory;
    private String title;
    private Boolean isPublic;
    private List<BriefNote> notes;
    private String userId;

    public static final NoteBook DEFAULT = new NoteBook();

    static{
        DEFAULT.setPublic(false);
        DEFAULT.setTitle("新建笔记本");
    }

    public NoteBook(String id, BriefDirectory prevDirectory, String title, Boolean isPublic, List<BriefNote> notes, String userId) {
        this.id = id;
        this.prevDirectory = prevDirectory;
        this.title = title;
        this.isPublic = isPublic;
        this.notes = notes;
        this.userId = userId;
    }

    public NoteBook() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BriefDirectory getPrevDirectory() {
        return prevDirectory;
    }

    public void setPrevDirectory(BriefDirectory prevDirectory) {
        this.prevDirectory = prevDirectory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public List<BriefNote> getNotes() {
        return notes;
    }

    public void setNotes(List<BriefNote> notes) {
        this.notes = notes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "NoteBook{" +
                "id='" + id + '\'' +
                ", prevDirectory=" + prevDirectory +
                ", title='" + title + '\'' +
                ", isPublic=" + isPublic +
                ", notes=" + notes +
                ", userId='" + userId + '\'' +
                '}';
    }
}
