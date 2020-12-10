package com.mgnote.mgnote.model;

public class NoteContent {
    private String id;
    private String content;

    public NoteContent(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public NoteContent() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NoteContent{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
