package Menu.CommentHandler.Interfaces;

import Users.User;
import Camp.Camp;
/**
 * The `IAdding` interface defines a contract for adding comments to a camp.
 * Classes that implement this interface must provide an implementation for adding comments.
 */
public interface IAdding {
    /**
     * Adds a comment to a camp by a user.
     * @param user The user who is adding the comment.
     * @param camp The camp to which the comment is being added.
     */
    void addComment(User user, Camp camp);
}