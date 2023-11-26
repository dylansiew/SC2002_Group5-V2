package Menu.CommentHandler;

import java.util.ArrayList;

import Camp.Camp;
import Comment.Comment;
import Comment.CommentType.COMMENTTYPES;
import Menu.CommentHandler.Interfaces.IViewing;
import Users.Student;
import Users.User;
/**
 * The `StudentViewSuggestion` class implements the `IViewing` interface and allows students to view their own suggestion comments within a camp.
 */
public class StudentViewSuggestion implements IViewing {
    /**
     * Allows a student to view their own suggestion comments within a specific camp.
     * @param user The student user viewing their own suggestions.
     * @param camp The camp for which the student wants to view suggestions.
     */
    public void viewComment(User user, Camp camp) {
        Student student = (Student) user;
        ArrayList<Comment> commentArrayList = camp.getCampCommentManager().getCommentArrayList(COMMENTTYPES.SUGGESTION);

        if (commentArrayList == null || commentArrayList.size() == 0) {
            System.out.println("This camp has no suggestions!");
            return;
        }
        int i = 1;
        for (Comment comment : commentArrayList) {
            if (!(student.equals(comment.getStudent()))) {
                continue;
            }
            System.out.printf("\n%d ->\t%s", i, comment.toString());
            i += 1;
        }
    }
}
