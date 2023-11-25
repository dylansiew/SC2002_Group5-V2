package Menu.CommentHandler;

import Comment.Comment;
import Comment.Enquiry;
import Menu.CommentHandler.Interfaces.IEditing;
import Program.MainProgram;
import Users.User;
/**
 * The `StudentEditEnquiry` class implements the `IEditing` interface and is responsible for editing an existing
 * enquiry comment in the application. It allows students to edit their own enquiries as long as the enquiry has
 * not been processed.
 */
public class StudentEditEnquiry implements IEditing {
    /**
     * Edits an enquiry comment if it has not been processed.
     * @param user    The user attempting to edit the comment.
     * @param comment The comment to be edited, which should be an enquiry.
     */
    public void editComment(User user, Comment comment) {
        Enquiry enquiry = (Enquiry) comment;
        if (enquiry.getProcessed()) {
            System.out.println("Enquiry has already been processed!");
            return;
        }
        System.out.println("Enquiry to edit:\n" + enquiry.toString());
        System.out.print("Enquiry to replace with: ");

        String editedEnquiry = MainProgram.sc.nextLine();
        enquiry.editComment(editedEnquiry);
        System.out.println("\nEnquiry successfully edited!");
        return;
    }
}
