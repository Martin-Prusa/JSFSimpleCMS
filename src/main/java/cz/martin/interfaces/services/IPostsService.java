package cz.martin.interfaces.services;

import cz.martin.models.Post;

import java.util.List;

public interface IPostsService {

    void addPost(Post post);

    void editPost(String id, Post post);

    void deletePost(String id);

    List<Post> getPosts();

    List<Post> getVisiblePosts();

    Post getPostById(String id);

}
