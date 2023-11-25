package Menu.CommentHandler;

import Comment.Comment;
import Comment.Suggestion;
import Menu.CommentHandler.Interfaces.IEditing;
import Program.MainProgram;
import Users.User;

public class StudentEditSuggestion implements IEditing {
    public void editComment(User user, Comment comment) {
        Suggestion selectedSuggestion = (Suggestion) comment;
        System.out.printf("Current suggestion:\n%s", selectedSuggestion.toString());
        System.out.printf("Enter your new suggestion: ");
        String newSuggestion = MainProgram.sc.nextLine();
        selectedSuggestion.editComment(newSuggestion);
        System.out.println("Your suggestion has been updated successfully");
    }
}
