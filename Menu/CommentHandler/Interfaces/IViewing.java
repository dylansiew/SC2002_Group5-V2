package Menu.CommentHandler.Interfaces;

import Users.User;
import Camp.Camp;
/**
 * The `IViewing` interface defines a contract for viewing comments within a camp.
 * Classes that implement this interface must provide an implementation for viewing comments.
 */
public interface IViewing {
    /**
     * Views comments within a camp.
     * @param user The user who is viewing the comments.
     * @param camp The camp for which comments are being viewed.
     */
    void viewComment(User user, Camp camp);
}