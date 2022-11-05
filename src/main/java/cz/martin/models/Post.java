package cz.martin.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Post {

    private String id;
    private String title;
    private String content;
    private String shortDescription;

    private LocalDateTime createdAt;

    private String author;

    private boolean isVisible;

    public Post(String title, String content, String shortDescription, boolean isVisible) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.shortDescription = shortDescription;
        this.createdAt = LocalDateTime.now();
        this.isVisible = isVisible;
    }

    public Post() {
        this.id = UUID.randomUUID().toString();
        this.title = "";
        this.content = "";
        this.shortDescription = "";
        this.createdAt = LocalDateTime.now();
        this.isVisible = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
