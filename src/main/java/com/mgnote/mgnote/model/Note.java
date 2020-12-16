package com.mgnote.mgnote.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Document(collection = "note")
public class Note {
    @Id
    private String id;
    private String topic;
    private String description;
    @JsonIgnore
    private Boolean deleted;
    private Date createdAt;
    private Date updatedAt;
    private List<BriefNote> subNotes;
    private BriefNoteBook prevNoteBook;
    private BriefNote prevNote;
    private BriefUser userInfo;
    private Boolean isPublic;

    public Note() {
        this.updatedAt = new Date();
        this.createdAt = new Date();
        this.deleted = false;
        this.isPublic = false;
    }

    public Note(String id, String topic, String description, boolean deleted, Date createdAt, Date updatedAt, List<BriefNote> subNotes, BriefNoteBook prevNoteBook, BriefNote prevNote, BriefUser userInfo, Boolean isPublic) {
        this.id = id;
        this.topic = topic;
        this.description = description;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.subNotes = subNotes;
        this.prevNoteBook = prevNoteBook;
        this.prevNote = prevNote;
        this.userInfo = userInfo;
        this.isPublic = isPublic;
    }

    public Example<Note> getContainExample(){
        return Example.of(this, ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
    }

    public Note(BriefNote briefNote){
        this.id = briefNote.getId();
        this.topic = briefNote.getTopic();
    }

    public BriefUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(BriefUser userInfo) {
        this.userInfo = userInfo;
    }

    public List<BriefNote> getSubNotes() {
        return subNotes;
    }

    public void setSubNotes(List<BriefNote> subNotes) {
        this.subNotes = subNotes;
    }

    public BriefNoteBook getPrevNoteBook() {
        return prevNoteBook;
    }

    public void setPrevNoteBook(BriefNoteBook prevNoteBook) {
        this.prevNoteBook = prevNoteBook;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public BriefNote getPrevNote() {
        return prevNote;
    }

    public void setPrevNote(BriefNote prevNote) {
        this.prevNote = prevNote;
    }

    /**
     * Gets the note id.
     *
     * @return the noteId
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the note id.
     *
     * @param id the noteId to set
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Gets the topic.
     *
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Sets the topic.
     *
     * @param topic the topic to set
     */
    public void setTopic(final String topic) {
        this.topic = topic;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Checks if is deleted.
     *
     * @return the deleted
     */
    public Boolean isDeleted() {
        return deleted;
    }

    /**
     * Sets the deleted.
     *
     * @param deleted the deleted to set
     */
    public void setDeleted(final Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Gets the created at.
     *
     * @return the created date and time
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the created at.
     *
     * @param createdAt the created date and time to set
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the updated at.
     *
     * @return the updated date and time
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the updated at.
     *
     * @param updatedAt the updated date and time to set
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", topic='" + topic + '\'' +
                ", description='" + description + '\'' +
                ", deleted=" + deleted +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", subNotes=" + subNotes +
                ", prevNoteBook=" + prevNoteBook +
                ", prevNote=" + prevNote +
                ", userInfo=" + userInfo +
                ", isPublic=" + isPublic +
                '}';
    }
}