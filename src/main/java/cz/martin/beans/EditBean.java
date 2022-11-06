package cz.martin.beans;

import cz.martin.interfaces.services.IActiveUserService;
import cz.martin.interfaces.services.INotificationsService;
import cz.martin.interfaces.services.IPostsService;
import cz.martin.models.Notification;
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

    @Inject
    @Normal
    private INotificationsService notificationsService;

    private Post editedPost;

    public void editPost() throws IOException {
        if(!activeUserService.isLoggedIn() || !activeUserService.getActiveUser().isEditor()) return;
        this.postsService.editPost(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"), editedPost);
        this.editedPost = null;
        this.notificationsService.addNotification(new Notification("Post edited"));
        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("detail.xhtml?id="+FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
    }

    public Post getEditedPost() {
        if(!activeUserService.isLoggedIn() || !activeUserService.getActiveUser().isEditor()) return new Post();
        if(editedPost == null || !editedPost.getId().equals(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"))) this.editedPost = this.postsService.getPostById(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        return this.editedPost;
    }
}
