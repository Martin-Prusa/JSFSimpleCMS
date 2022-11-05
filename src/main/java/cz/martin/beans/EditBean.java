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

@Named("edit")
@RequestScoped
public class EditBean {
    @Inject
    @Normal
    private IPostsService postsService;

    @Inject
    @Normal
    private IActiveUserService activeUserService;

    private Post editedPost;

    public void editPost() throws IOException {
        if(!activeUserService.isLoggedIn() || !activeUserService.getActiveUser().isEditor()) return;
        this.postsService.editPost(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"), editedPost);
        this.editedPost = null;
        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("index.xhtml");
    }

    public Post getEditedPost() {
        if(!activeUserService.isLoggedIn() || !activeUserService.getActiveUser().isEditor()) return new Post();
        if(editedPost == null || !editedPost.getId().equals(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"))) this.editedPost = this.postsService.getPostById(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        return this.editedPost;
    }
}
