package com.mgnote.mgnote.model;

public class BriefNoteBook {
    private String id;
    private String title;

    public BriefNoteBook(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public BriefNoteBook() {
    }

    public BriefNoteBook(NoteBook noteBook){
        this.id = noteBook.getId();
        this.title = noteBook.getTitle();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BriefNoteBook{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
