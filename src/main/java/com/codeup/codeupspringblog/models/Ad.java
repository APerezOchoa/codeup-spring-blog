package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ads") //this annotation is optional, if not autogenerate called ad
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 100, nullable = false) //character limit for title & not null
    private String title;
    @Column(nullable = false) //description can't be null
    private String description;
    @Column(nullable = false) //userId can't be null
    private int userId;

    public Ad() {
    }

    public Ad(long id, String title, String description, int userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userId = userId;
    }

    public Ad(String title, String description, int userId) {
        this.title = title;
        this.description = description;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}