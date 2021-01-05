package com.mgnote.mgnote.model;

import com.mgnote.mgnote.model.dto.BriefNote;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "notebook")
public class NoteBook{
    @Id
    private String id;
    private String name;
    private boolean del;
    private List<BriefNote> noteIdList;
    private String path;

    public NoteBook() {
    }

    public NoteBook(String id, String name, boolean del, List<BriefNote> noteIdList, String path) {
        this.id = id;
        this.name = name;
        this.del = del;
        this.noteIdList = noteIdList;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public List<BriefNote> getNoteIdList() {
        return noteIdList;
    }

    public void setNoteIdList(List<BriefNote> noteIdList) {
        this.noteIdList = noteIdList;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", del=" + del +
                ", noteIdList=" + noteIdList +
                ", path='" + path + '\'' +
                '}';
    }
}
