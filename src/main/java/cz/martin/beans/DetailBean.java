package cz.martin.beans;

import cz.martin.interfaces.services.IPostsService;
import cz.martin.models.Post;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Map;

@Named("detail")
@RequestScoped
public class DetailBean {
    @Inject
    @Normal
    private IPostsService postsService;

    private Post post;

    private String id;

    public DetailBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        id = params.get("id");
    }

    public Post getPost() {
        if(post == null) this.post = this.postsService.getPostById(id);
        return this.post;
    }
}
