package com.mgnote.mgnote.model;

import com.mgnote.mgnote.model.dto.BriefNote;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "note")
public class Note{
    @Id
    private String id;
    private String name;
    private String content;
    private Date createTime;
    private Date updateTime;
    private Boolean del;
    private String notebook;

    private static final Note newNote;
    private static final Note updateNote;

    static {
        newNote = new Note();
        newNote.setDel(false);
        newNote.setCreateTime(new Date());
        newNote.setUpdateTime(new Date());

        updateNote = new Note();
        updateNote.setUpdateTime(new Date());
        updateNote.setCreateTime(null);
        updateNote.setId(null);
    }

    public static Note getNewNote(){return newNote;}

    public static Note getUpdateNote(){return updateNote;}

    public Note(String id, String name, String content, Date createTime, Date updateTime, boolean del, String notebook) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.del = del;
        this.notebook = notebook;
    }

    public Note() {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public String getNotebook() {
        return notebook;
    }

    public void setNotebook(String notebook) {
        this.notebook = notebook;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", del=" + del +
                ", notebook='" + notebook + '\'' +
                '}';
    }
}
