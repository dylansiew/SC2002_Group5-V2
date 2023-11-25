package Menu.CommentHandler.Interfaces;

import Comment.Comment;
import Users.User;
import Camp.Camp;
/**
 * The `IDeleting` interface defines a contract for deleting comments from a camp.
 * Classes that implement this interface must provide an implementation for deleting comments.
 */
public interface IDeleting {
    /**
     * Deletes a comment from a camp by a user.
     * @param user    The user who is deleting the comment.
     * @param comment The comment to be deleted.
     * @param camp    The camp from which the comment is being deleted.
     */
    void deleteComment(User user, Comment comment, Camp camp);
}