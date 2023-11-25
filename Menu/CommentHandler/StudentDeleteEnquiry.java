package Menu.CommentHandler;

import Comment.Comment;
import Comment.CommentType.COMMENTTYPES;
import Comment.Enquiry;
import Menu.CommentHandler.Interfaces.IDeleting;
import Program.MainProgram;
import Users.User;
import Camp.Camp;

public class StudentDeleteEnquiry implements IDeleting {
    public void deleteComment(User user, Comment comment, Camp camp) {
        Enquiry enquiry = (Enquiry) comment;
        if (enquiry.getProcessed()) {
            System.out.println("Enquiry has already been processed!");
            return;
        }
        System.out.print("Are you sure to delete this enquiry? Y/N\tChoice: ");

        String choice = MainProgram.sc.nextLine();
        if (choice.toLowerCase().equals("y")) {
            camp.getCampCommentManager().getComment(COMMENTTYPES.ENQUIRY).removeComment(comment);
            System.out.println("\n Enquiry deleted!");
        } else {
            System.out.println("Enquiry deletion failed.");
        }
        return;
    }
}
