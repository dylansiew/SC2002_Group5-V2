package Menu.CommentHandler.Interfaces;

import Comment.Comment;
import Users.User;
import Camp.Camp;
/**
 * The `IReplying` interface defines a contract for replying to comments within a camp.
 * Classes that implement this interface must provide an implementation for replying to comments.
 */
public interface IReplying {
    /**
     * Replies to a comment within a camp.
     * @param user    The user who is replying to the comment.
     * @param comment The comment to which the reply is being added.
     * @param camp    The camp where the comment and reply are associated.
     */
    void replyComment(User user, Comment comment, Camp camp);
}