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
    private Date createTime;
    private Date updateTime;
    private boolean del;
    @Indexed(name = "path")
    private String path;
    private String note;

    private static SubNote newSubNote;
    private static SubNote updateSubNote;

    static {
        newSubNote = new SubNote();
        newSubNote.setDel(false);
        newSubNote.setCreateTime(new Date());
        newSubNote.setUpdateTime(new Date());

        updateSubNote = new SubNote();
        updateSubNote.setUpdateTime(new Date());
        updateSubNote.setDel(false);
    }

    public static SubNote getNewSubNote(){return newSubNote;}

    public static SubNote getUpdateSubNote(){return updateSubNote;}

    public SubNote() {
    }

    public SubNote(String id, String name, String content, Date createTime, Date updateTime, boolean del, String note) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.del = del;
        this.note = note;
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

    @Override
    public String toString() {
        return "SubNote{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", del=" + del +
                ", note='" + note + '\'' +
                '}';
    }
}
