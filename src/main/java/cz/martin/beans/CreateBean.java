package cz.martin.beans;

import cz.martin.models.Post;
import cz.martin.qualifiers.Normal;
import cz.martin.services.PostsService;
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
    private PostsService postsService;

    private Post newPost = new Post();

    public void addNewPost() throws IOException {
        this.postsService.addPost(this.newPost);
        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("index.xhtml");
    }

    public Post getNewPost() {
        return newPost;
    }
}
