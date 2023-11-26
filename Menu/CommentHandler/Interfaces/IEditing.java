package Menu.CommentHandler.Interfaces;

import Users.User;
import Comment.Comment;
/**
 * The `IEditing` interface defines a contract for editing comments.
 * Classes that implement this interface must provide an implementation for editing comments.
 */
public interface IEditing {
    /**
     * Edits a comment by a user.
     * @param user    The user who is editing the comment.
     * @param comment The comment to be edited.
     */
    void editComment(User user, Comment comment);
}