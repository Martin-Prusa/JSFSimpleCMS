package cz.martin.beans;

import cz.martin.interfaces.services.IPostsService;
import cz.martin.interfaces.services.IUsersService;
import cz.martin.models.Post;
import cz.martin.models.User;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.Optional;

@Named("list")
@ApplicationScoped
public class MainBean {
    @Inject
    @Normal
    private IPostsService postsService;

    @Inject
    @Normal
    private IUsersService usersService;

    public String getUsername(String id) {
        Optional<User> user = usersService.getUserById(id);
        if(user.isEmpty()) return "";
        return user.get().getUsername();
    }

    public List<Post> getHiddenPosts() {
        return this.postsService.getHiddenPosts();
    }

    public List<Post> getPosts() {
        return this.postsService.getVisiblePosts();
    }


}
