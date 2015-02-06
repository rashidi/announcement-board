package com.gf.st.ab.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author Rashidi Zin
 */
@Document
public class Announcement {

    @Id
    private String id;

    private String title;
    private String message;
    private Date created = new Date();

    public Announcement() {
    }

    public Announcement(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public Announcement(String id, String title, String message, Date created) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.created = created;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
