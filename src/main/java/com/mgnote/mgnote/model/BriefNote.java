package com.mgnote.mgnote.model;

import java.util.List;

public class BriefNote {
    private String id;
    private String topic;

    public BriefNote(String id, String topic) {
        this.id = id;
        this.topic = topic;
    }

    public BriefNote() {
    }

    public BriefNote(Note note){
        this.id = note.getId();
        this.topic = note.getTopic();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


    @Override
    public String toString() {
        return "BriefNote{" +
                "id='" + id + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
