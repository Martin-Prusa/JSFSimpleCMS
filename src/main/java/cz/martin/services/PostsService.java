package cz.martin.services;

import cz.martin.interfaces.repositories.IPostsRepository;
import cz.martin.interfaces.services.IPostsService;
import cz.martin.models.Post;
import cz.martin.qualifiers.Normal;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Normal
public class PostsService implements IPostsService {

    @Inject
    @Normal
    private IPostsRepository postsRepository;

    private List<Post> posts = null;

    public PostsService() {
        //this.posts = new ArrayList<>(List.of(new Post[]{new Post("title", "content", "desc"), new Post("title", "# content", "desc"), new Post("title", "*content", "desc")}));
    }

    @PostConstruct
    public void init() {
        this.posts = postsRepository.loadPosts();
    }

    @Override
    public void addPost(Post post) {
        this.posts.add(post);
        this.postsRepository.savePosts(this.posts);
    }

    @Override
    public void editPost(String id, Post post) {
        int index = this.posts.indexOf(getPostById(id));
        if(index == -1) return;
        this.posts.set(index, post);
        this.postsRepository.savePosts(this.posts);
    }

    @Override
    public void deletePost(String id) {
        int index = this.posts.indexOf(getPostById(id));
        if(index == -1) return;
        this.posts.remove(index);
        this.postsRepository.savePosts(this.posts);
    }

    @Override
    public List<Post> getVisiblePosts() {
        return this.getPosts().stream().filter(Post::isVisible).toList();
    }

    @Override
    public List<Post> getHiddenPosts() {
        return this.getPosts().stream().filter(i -> !i.isVisible()).toList();
    }

    @Override
    public Post getPostById(String id) {
        Optional<Post> p = this.posts.stream().filter(i -> i.getId().equals(id)).findFirst();
        if(p.isEmpty()) return new Post(null, null, null, false);
        return p.get();
    }

    @Override
    public List<Post> getPosts() {
        if(this.posts == null) this.posts = postsRepository.loadPosts();
        return posts;
    }

}
