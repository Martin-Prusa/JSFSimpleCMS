package cz.martin.interfaces.repositories;

import cz.martin.models.Comment;

import java.util.List;

public interface ICommentsRepository {
    void save(List<Comment> comments);
    List<Comment> load();
}
