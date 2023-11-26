package Menu.CommentHandler;

import Camp.Camp;
import Comment.CommentType.COMMENTTYPES;
import Comment.Suggestion;
import Menu.CommentHandler.Interfaces.IAdding;
import Program.MainProgram;
import Users.*;
/**
 * The `StudentAddSuggestion` class implements the `IAdding` interface to provide functionality for students to add
 * a suggestion to a camp.
 */
public class StudentAddSuggestion implements IAdding {
    /**
     * Allows a student to add a suggestion to a specific camp.
     * @param user The user (student) adding the suggestion.
     * @param camp The camp to which the suggestion is added.
     */
    public void addComment(User user, Camp camp) {
        Student student = (Student) user;
        System.out.print("Enter your suggestion: ");
        String comment = MainProgram.sc.nextLine();
        Committee committee = student.getCommittee();
        if (committee == null) {
            return;
        }
        Suggestion suggestion = new Suggestion(student, camp, comment);
        camp.getCampCommentManager().getComment(COMMENTTYPES.SUGGESTION).addComment(suggestion);
        committee.addPoint();
        System.out.printf("Suggestion submitted\n");
        return;
    }
}
