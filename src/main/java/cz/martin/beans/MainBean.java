package cz.martin.beans;

import cz.martin.interfaces.services.IPostsService;
import cz.martin.models.Post;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named("list")
@ApplicationScoped
public class MainBean {
    @Inject
    @Normal
    private IPostsService postsService;

    public List<Post> getPosts() {
        return this.postsService.getVisiblePosts();
    }


}
