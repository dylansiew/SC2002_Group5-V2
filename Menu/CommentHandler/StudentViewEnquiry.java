package Menu.CommentHandler;

import java.util.ArrayList;

import Camp.Camp;
import Comment.Comment;
import Comment.CommentType.COMMENTTYPES;
import Menu.CommentHandler.Interfaces.IViewing;
import Users.Student;
import Users.User;
/**
 * The `StudentViewEnquiry` class implements the `IViewing` interface and allows students to view their own enquiry comments within a camp.
 */
public class StudentViewEnquiry implements IViewing {
    /**
     * Allows a student to view their own enquiry comments within a specific camp.
     * @param user The student user viewing their own enquiries.
     * @param camp The camp for which the student wants to view enquiries.
     */
    public void viewComment(User user, Camp camp) {
        Student student = (Student) user;
        ArrayList<Comment> enquiryArrayList = camp.getCampCommentManager().getCommentArrayList(COMMENTTYPES.ENQUIRY);
        if (enquiryArrayList.isEmpty()) {
            System.out.println("This camp has no enquiries!");
            return;
        }
        int i = 1;
        for (Comment comment : enquiryArrayList) {
            if (!student.equals(comment.getStudent())) {
                continue;
            }
            System.out.printf("\n%d ->\t%s", i, comment.toString());
            i += 1;
        }
    }
}
