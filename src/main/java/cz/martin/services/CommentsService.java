package cz.martin.services;

import cz.martin.interfaces.repositories.ICommentsRepository;
import cz.martin.interfaces.services.ICommentsService;
import cz.martin.models.Comment;
import cz.martin.qualifiers.Normal;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
@Normal
public class CommentsService implements ICommentsService {

    @Inject
    @Normal
    private ICommentsRepository commentsRepository;

    private List<Comment> comments;

    @PostConstruct
    public void init() {
        this.comments = commentsRepository.load();
    }

    @Override
    public void addNewComment(Comment c) {
        this.comments.add(c);
        this.commentsRepository.save(this.comments);
    }

    @Override
    public List<Comment> getPostComments(String postId) {
        return this.comments.stream().filter(i -> i.getPostId().equals(postId)).toList();
    }
}
