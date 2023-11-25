package Menu.CommentHandler;

import Comment.Comment;
import Comment.Enquiry;
import Menu.CommentHandler.Interfaces.IEditing;
import Program.MainProgram;
import Users.User;

public class StudentEditEnquiry implements IEditing {
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
