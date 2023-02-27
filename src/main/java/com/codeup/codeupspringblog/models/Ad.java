package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ads_categories",
            joinColumns = {@JoinColumn(name = "ad_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> categories;

    public Ad() {

    }

    public Ad(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public Ad(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Ad(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Ad(String title, String description, User user, List<Category> categories) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.categories = categories;
    }

    public Ad(long id, String title, String description, User user, List<Category> categories) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.categories = categories;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}