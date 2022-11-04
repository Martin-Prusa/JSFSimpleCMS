package cz.martin.interfaces.services;

import cz.martin.models.Post;

import java.util.List;

public interface IPostsService {
    List<Post> getPosts();

    List<Post> getVisiblePosts();

    Post getPostById(String id);

    void addPost(Post post);
}
