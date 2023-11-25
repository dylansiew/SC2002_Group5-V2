package Menu;

import Camp.*;
import Comment.*;
import Controller.*;
import Users.*;
/**
 * The abstract base class for menus related to comments within the Camp Management System.
 * Subclasses of this class provide specific functionality for different types of comment menus.
 */
public abstract class CommentMenu {
    protected AllCamp allCamp;
    protected CommentType.COMMENTTYPES commentType;
    /**
     * Initializes a new instance of the CommentMenu class with the specified parameters.
     * @param allCamp     The `AllCamp` instance.
     * @param commentType The type of comments (e.g., enquiry or suggestion) handled by this menu.
     */
    public CommentMenu(AllCamp allCamp, CommentType.COMMENTTYPES commentType) {
        this.allCamp = allCamp;
        this.commentType = commentType;
    }
    /**
     * Get the `AllCamp` instance associated with this menu.
     * @return The `AllCamp` instance.
     */
    public AllCamp getAllCamp() {
        return this.allCamp;
    }
    /**
     * Select a camp based on user input.
     *
     * @param user The user making the selection.
     * @return The selected camp.
     */
    protected abstract Camp selectCamp(User user);
    /**
     * Select a comment within a specific camp based on user input.
     * @param camp The camp associated with the comments.
     * @param user The user making the selection.
     * @return The selected comment.
     */
    protected abstract Comment selectComment(Camp camp, User user);
}
