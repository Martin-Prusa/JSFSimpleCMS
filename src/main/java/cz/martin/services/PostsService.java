package cz.martin.services;

import cz.martin.interfaces.repositories.IPostsRepository;
import cz.martin.interfaces.services.IPostsService;
import cz.martin.models.Post;
import cz.martin.repositories.PostsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PostsService implements IPostsService {

    @Inject
    private IPostsRepository postsRepository;

    private List<Post> posts;

    public PostsService() {
        this.posts = postsRepository.loadPosts();
    }

    @Override
    public List<Post> getVisiblePosts() {
        return null;
    }

    @Override
    public List<Post> getPosts() {
        return posts;
    }

}
