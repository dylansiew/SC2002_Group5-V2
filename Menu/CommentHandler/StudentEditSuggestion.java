package Menu.CommentHandler;

import Comment.Comment;
import Comment.Suggestion;
import Menu.CommentHandler.Interfaces.IEditing;
import Program.MainProgram;
import Users.User;
/**
 * The `StudentEditSuggestion` class implements the `IEditing` interface and is responsible for allowing students to edit
 * their own suggestion comments in the application.
 * The class provides a method `editComment` that takes a user and a comment (suggestion) as input. It displays the
 * current suggestion, prompts the user to enter a new suggestion, and updates the suggestion if the user chooses to do so.
 */
public class StudentEditSuggestion implements IEditing {
    /**
     * Edits a suggestion comment if the user chooses to do so.
     * @param user    The user attempting to edit the comment.
     * @param comment The comment (suggestion) to be edited.
     */
    public void editComment(User user, Comment comment) {
        Suggestion selectedSuggestion = (Suggestion) comment;
        System.out.printf("Current suggestion:\n%s", selectedSuggestion.toString());
        System.out.printf("Enter your new suggestion: ");
        String newSuggestion = MainProgram.sc.nextLine();
        selectedSuggestion.editComment(newSuggestion);
        System.out.println("Your suggestion has been updated successfully");
    }
}
