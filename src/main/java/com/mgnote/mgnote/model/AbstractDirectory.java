package com.mgnote.mgnote.model;

import org.springframework.data.annotation.Id;

public class AbstractDirectory {
    @Id
    private String id;
    private String name;
    private boolean del;

    public AbstractDirectory(){}

    public AbstractDirectory(String id, String name, boolean del) {
        this.id = id;
        this.name = name;
        this.del = del;
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
}
