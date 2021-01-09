package com.mgnote.mgnote.model;

import com.mgnote.mgnote.model.dto.BriefNote;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "sub_note")
public class SubNote{
    @Id
    private String id;
    private String name;
    private String content;
    private boolean del;
    @Indexed(name = "path")
    private String path;
    private String note;
    private String userId;

    private static final SubNote newSubNote;
    private static final SubNote updateSubNote;

    static {
        newSubNote = new SubNote();
        newSubNote.setDel(false);

        updateSubNote = new SubNote();
        updateSubNote.setDel(false);
    }

    public static SubNote getNewSubNote(){return newSubNote;}

    public static SubNote getUpdateSubNote(){return updateSubNote;}

    public SubNote() {
    }

    public SubNote(String id, String name, String content, boolean del, String note, String userId) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.del = del;
        this.note = note;
        this.userId = userId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SubNote{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", del=" + del +
                ", note='" + note + '\'' +
                '}';
    }
}
