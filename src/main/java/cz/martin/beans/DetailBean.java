package cz.martin.beans;

import cz.martin.interfaces.services.IActiveUserService;
import cz.martin.interfaces.services.ICommentsService;
import cz.martin.interfaces.services.INotificationsService;
import cz.martin.interfaces.services.IPostsService;
import cz.martin.models.Comment;
import cz.martin.models.Notification;
import cz.martin.models.Post;
import cz.martin.qualifiers.Normal;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
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

    @Inject
    @Normal
    private INotificationsService notificationsService;

    @Inject
    @Normal
    private ICommentsService commentsService;

    private Post post;

    private List<Comment> comments;
    private String id;

    private String commentText = "";
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

    public void addComment() {
        if(!activeUserService.isLoggedIn()) return;
        this.commentsService.addNewComment(new Comment(this.post.getId(), this.activeUserService.getActiveUser().getId(), this.commentText));
        this.commentText = "";
        this.comments = this.commentsService.getPostComments(this.post.getId());
    }

    public void deletePost() throws IOException {
        if(!activeUserService.isLoggedIn() || !activeUserService.getActiveUser().isEditor()) return;
        this.postsService.deletePost(id);
        this.showAlert = false;
        this.notificationsService.addNotification(new Notification("Post deleted"));
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
            this.comments = this.commentsService.getPostComments(this.post.getId());
            this.commentText = "";
            this.showAlert = false;
        }
        if(!this.post.isVisible() && (!activeUserService.isLoggedIn() || !activeUserService.getActiveUser().isEditor())) return new Post();
        return this.post;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public boolean isShowAlert() {
        return showAlert;
    }
}
