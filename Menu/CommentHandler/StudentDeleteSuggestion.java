package Menu.CommentHandler;

import Comment.Comment;
import Comment.CommentType.COMMENTTYPES;
import Comment.Suggestion;
import Menu.CommentHandler.Interfaces.IDeleting;
import Program.MainProgram;
import Users.*;
import Camp.Camp;

public class StudentDeleteSuggestion implements IDeleting {
    public void deleteComment(User user, Comment comment, Camp camp) {
        Student student = (Student) user;
        Suggestion selectedSuggestion = (Suggestion) comment;

        System.out.printf("Selected suggestion:\n%s", selectedSuggestion.toString());

        System.out.printf("Are you sure you want to delete this suggestion? Y/N\tChoice: ");
        String choice = MainProgram.sc.nextLine().toLowerCase();
        if (choice.equals("y")) {
            camp.getCampCommentManager().getComment(COMMENTTYPES.SUGGESTION).removeComment(comment);
            student.getCommittee().minusPoint();
            System.out.println("Suggestion has been deleted");
        } else if (choice.equals("n")) {
            System.out.println("Suggestion not deleted");
        } else {
            System.out.println("Unrecognised input, returning main menu...");
        }
        return;
    }
}
