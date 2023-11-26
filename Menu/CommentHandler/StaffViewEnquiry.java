package Menu.CommentHandler;

import java.util.ArrayList;

import Camp.Camp;
import Comment.Comment;
import Comment.CommentType.COMMENTTYPES;
import Menu.CommentHandler.Interfaces.IViewing;
import Users.User;
/**
 * The `StaffViewEnquiry` class implements the `IViewing` interface to provide functionality for staff members
 * to view enquiries in a camp.
 */
public class StaffViewEnquiry implements IViewing {
    /**
     * Allows a staff member to view enquiries in a specific camp.
     * @param user The user (staff member) viewing the enquiries.
     * @param camp The camp for which enquiries are to be viewed.
     */
    public void viewComment(User user, Camp camp) {
        ArrayList<Comment> enquiryArrayList = camp.getCampCommentManager().getCommentArrayList(COMMENTTYPES.ENQUIRY);
        if (enquiryArrayList.isEmpty()) {
            System.out.println("This camp has no enquiries!");
            return;
        }

        int i = 1;
        for (Comment comment : enquiryArrayList) {
            System.out.printf("\n%d ->\t%s", i, comment.toString());
            i += 1;
        }
    }
}
