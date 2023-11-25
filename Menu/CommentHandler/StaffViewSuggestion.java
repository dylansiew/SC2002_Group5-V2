package Menu.CommentHandler;

import java.util.ArrayList;

import Camp.Camp;
import Comment.CommentType.COMMENTTYPES;
import Menu.CommentHandler.Interfaces.IViewing;
import Users.User;
import Comment.Comment;
/**
 * The `StaffViewSuggestion` class implements the `IViewing` interface to provide functionality for staff members
 * to view suggestions in a camp.
 */
public class StaffViewSuggestion implements IViewing {
    /**
     * Allows a staff member to view suggestions in a specific camp.
     * @param user The user (staff member) viewing the suggestions.
     * @param camp The camp for which suggestions are to be viewed.
     */
    public void viewComment(User user, Camp camp) {
        ArrayList<Comment> commentArrayList = camp.getCampCommentManager().getCommentArrayList(COMMENTTYPES.SUGGESTION);

        if (commentArrayList == null || commentArrayList.size() == 0) {
            System.out.println("This camp has no suggestions!");
            return;
        }
        int i = 1;
        for (Comment comment : commentArrayList) {
            System.out.printf("\n%d ->\t%s", i, comment.toString());
            i += 1;
        }
    }
}
