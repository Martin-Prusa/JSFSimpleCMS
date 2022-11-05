package cz.martin.beans;

import cz.martin.interfaces.services.IActiveUserService;
import cz.martin.interfaces.services.IPostsService;
import cz.martin.models.Post;
import cz.martin.qualifiers.Normal;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@Named("detail")
@SessionScoped
public class DetailBean implements Serializable {
    @Inject
    @Normal
    private IPostsService postsService;

    @Inject
    @Normal
    private IActiveUserService activeUserService;

    private Post post;

    private String id;

    private boolean showAlert = false;

    public DetailBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        id = params.get("id");
    }

    @PostConstruct
    public void init() {
        getPost();
    }

    public String getFormatted(String md) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(md);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    public void deletePost() throws IOException {
        if(!activeUserService.isLoggedIn() || !activeUserService.getActiveUser().isEditor()) return;
        this.postsService.deletePost(id);
        this.showAlert = false;
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public void showAlert() {
        showAlert = true;
    }

    public void hideAlert() {
        showAlert = false;
    }

    public Post getPost() {
        if(post == null || !this.post.getId().equals(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"))) {
            this.id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            this.post = this.postsService.getPostById(id);
            this.showAlert = false;
        }
        return this.post;
    }

    public boolean isShowAlert() {
        return showAlert;
    }
}
