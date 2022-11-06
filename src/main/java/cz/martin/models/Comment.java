package cz.martin.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
    private String id;
    private String postId;
    private String userId;
    private String text;
    private LocalDateTime time;

    public Comment(String postId, String userId, String text) {
        this.id = UUID.randomUUID().toString();
        this.postId = postId;
        this.userId = userId;
        this.text = text;
        this.time = LocalDateTime.now();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
