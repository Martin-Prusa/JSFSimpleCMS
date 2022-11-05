package cz.martin.beans;

import cz.martin.interfaces.services.IActiveUserService;
import cz.martin.interfaces.services.IPostsService;
import cz.martin.models.Post;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;

@Named("create")
@RequestScoped
public class CreateBean {
    @Inject
    @Normal
    private IPostsService postsService;

    @Inject
    @Normal
    private IActiveUserService activeUserService;

    private Post newPost = new Post();

    public void addNewPost() throws IOException {
        if(!activeUserService.isLoggedIn() || !activeUserService.getActiveUser().isEditor()) return;
        this.newPost.setAuthor(this.activeUserService.getActiveUser().getId());
        this.postsService.addPost(this.newPost);
        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("index.xhtml");
    }

    public Post getNewPost() {
        return newPost;
    }
}
