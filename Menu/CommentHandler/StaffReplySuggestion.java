package Menu.CommentHandler;

import Camp.Camp;
import Comment.Comment;
import Comment.Suggestion;
import Menu.CommentHandler.Interfaces.IReplying;
import Program.MainProgram;
import Users.Committee;
import Users.Student;
import Users.User;

public class StaffReplySuggestion implements IReplying {
    public void replyComment(User user, Comment comment, Camp camp) {
        Suggestion selectedSuggestion = (Suggestion) comment;
        System.out.printf("Approve? (Y/N)\tChoice: ");
        String choice = MainProgram.sc.nextLine().toLowerCase();
        if (choice.equals("y")) {
            selectedSuggestion.setProcessed(Suggestion.STATUS.APPROVED);
            Student student = selectedSuggestion.getStudent();
            if (student != null) {
                Committee committee = student.getCommittee();
                if (committee != null) {
                    committee.addPoint();
                }
            }
            System.out.println("Suggestion has been approved");
        } else if (choice.equals("n")) {
            selectedSuggestion.setProcessed(Suggestion.STATUS.REJECTED);
            System.out.println("Suggestion has been rejected");
        } else {
            System.out.println("Unrecognised input, returning main menu...");
        }
        return;
    }
}
