package cz.martin.beans;

import cz.martin.interfaces.services.IPostsService;
import cz.martin.models.Post;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.List;

@Named("main")
@ApplicationScoped
public class MainBean {
    @Inject
    @Normal
    private IPostsService postsService;

    public String getFormatted(String md) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(md);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
    public List<Post> getPosts() {
        return this.postsService.getVisiblePosts();
    }


}
