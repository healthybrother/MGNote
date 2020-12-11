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

    public NoteBook(String id, BriefDirectory prevDirectory, String title, Boolean isPublic, List<BriefNote> notes) {
        this.id = id;
        this.prevDirectory = prevDirectory;
        this.title = title;
        this.isPublic = isPublic;
        this.notes = notes;
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

    @Override
    public String toString() {
        return "NoteBook{" +
                "id='" + id + '\'' +
                ", prevDirectory=" + prevDirectory +
                ", title='" + title + '\'' +
                ", isPublic=" + isPublic +
                ", notes=" + notes +
                '}';
    }
}
