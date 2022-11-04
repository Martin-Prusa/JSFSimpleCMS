package cz.martin.beans;

import cz.martin.interfaces.services.IPostsService;
import cz.martin.models.Post;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named("main")
@RequestScoped
public class MainBean {
    @Inject
    private IPostsService postsService;

    public List<Post> getPosts() {
        return this.postsService.getVisiblePosts();
    }


}
