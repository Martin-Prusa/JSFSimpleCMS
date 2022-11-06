package cz.martin.interfaces.services;

import cz.martin.models.Comment;

import java.util.List;

public interface ICommentsService {
    void addNewComment(Comment c);
    List<Comment> getPostComments(String postId);
}
