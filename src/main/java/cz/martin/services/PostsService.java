package cz.martin.services;

import cz.martin.interfaces.repositories.IPostsRepository;
import cz.martin.interfaces.services.IPostsService;
import cz.martin.models.Post;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Normal
public class PostsService implements IPostsService {

    @Inject
    @Normal
    private IPostsRepository postsRepository;

    private List<Post> posts;

    public PostsService() {
        this.posts = new ArrayList<>(List.of(new Post[]{new Post("title", "content", "desc"), new Post("title", "# content", "desc"), new Post("title", "*content", "desc")}));
    }

    @Override
    public void addPost(Post post) {
        this.posts.add(post);
        this.postsRepository.savePosts(this.posts);
    }

    @Override
    public List<Post> getVisiblePosts() {
        return this.getPosts();
    }

    @Override
    public Post getPostById(String id) {
        Optional<Post> p = this.posts.stream().filter(i -> i.getId().equals(id)).findFirst();
        if(p.isEmpty()) return new Post(null, null, null);
        return p.get();
    }

    @Override
    public List<Post> getPosts() {
        if(this.posts == null) this.posts = postsRepository.loadPosts();
        return posts;
    }

}
