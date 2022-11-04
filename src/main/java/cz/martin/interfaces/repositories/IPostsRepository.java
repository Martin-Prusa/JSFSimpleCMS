package cz.martin.interfaces.repositories;

import cz.martin.models.Post;

import java.util.List;

public interface IPostsRepository {
    List<Post> loadPosts();
    void savePosts(List<Post> posts);
}
