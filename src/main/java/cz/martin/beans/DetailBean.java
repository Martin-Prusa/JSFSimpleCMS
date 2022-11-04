package cz.martin.beans;

import cz.martin.interfaces.services.IPostsService;
import cz.martin.models.Post;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

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

    public String getFormatted(String md) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(md);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    public Post getPost() {
        if(post == null) this.post = this.postsService.getPostById(id);
        return this.post;
    }
}
