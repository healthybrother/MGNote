package com.mgnote.mgnote.model.dto;

import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.NoteContent;

public class AddNoteInput {
    private String userId;
    private String id;
    private Note note;
    private NoteContent noteContent;

    public AddNoteInput(String userId, String id, Note note, NoteContent noteContent) {
        this.userId = userId;
        this.id = id;
        this.note = note;
        this.noteContent = noteContent;
    }

    public AddNoteInput() {
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public NoteContent getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(NoteContent noteContent) {
        this.noteContent = noteContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AddNoteInput{" +
                "userId='" + userId + '\'' +
                ", id='" + id + '\'' +
                ", note=" + note +
                ", noteContent=" + noteContent +
                '}';
    }
}
