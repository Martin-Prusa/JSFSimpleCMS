package cz.martin.beans;

import cz.martin.models.Post;
import cz.martin.qualifiers.Normal;
import cz.martin.services.PostsService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@Named("edit")
@SessionScoped
public class EditBean implements Serializable {
    @Inject
    @Normal
    private PostsService postsService;

    private Post editedPost;

    public void editPost() throws IOException {
        this.postsService.editPost(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"), editedPost);
        this.editedPost = null;
        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("index.xhtml");
    }

    public Post getEditedPost() {
        if(editedPost == null) this.editedPost = this.postsService.getPostById(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        return this.editedPost;
    }
}
