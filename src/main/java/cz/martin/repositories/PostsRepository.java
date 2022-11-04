package cz.martin.repositories;

import cz.martin.interfaces.repositories.IPostsRepository;
import cz.martin.models.Post;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
@Normal
public class PostsRepository implements IPostsRepository {
    @Override
    public List<Post> loadPosts() {
        return null;
    }

    @Override
    public void savePosts(List<Post> posts) {

    }
}
