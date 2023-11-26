package Menu.CommentHandler;

import Camp.Camp;
import Comment.Comment;
import Comment.Suggestion;
import Menu.CommentHandler.Interfaces.IReplying;
import Program.MainProgram;
import Users.Committee;
import Users.Student;
import Users.User;
/**
 * The `StaffReplySuggestion` class implements the `IReplying` interface to provide functionality for staff members
 * to reply to suggestions made by students in a camp.
 */
public class StaffReplySuggestion implements IReplying {
    /**
     * Allows a staff member to reply to a suggestion made by a student in a camp.
     * @param user    The user (staff member) replying to the suggestion.
     * @param comment The suggestion comment to be replied to.
     * @param camp    The camp associated with the suggestion.
     */
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
