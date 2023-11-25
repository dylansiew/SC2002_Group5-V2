package Menu;

import java.util.ArrayList;

import Camp.Camp;
import Comment.Comment;
import Comment.CommentType;
import Controller.AllCamp;
import Users.Committee;
import Users.Student;
import Users.User;
import Menu.CommentHandler.Interfaces.*;
import Program.MainProgram;

public class StudentSuggestionMenu extends CommentMenu {
    public StudentSuggestionMenu(AllCamp allCamp, CommentType.COMMENTTYPES commentType) {
        super(allCamp, commentType);
    }

    public AllCamp getAllCamp() {
        return this.allCamp;
    }

    protected Camp selectCamp(User user) {
        Student student = (Student) user;
        if (student == null) {
            return null;
        }
        Committee committee = student.getCommittee();
        if (committee == null) {
            return null;
        }
        return committee.getCamp();
    }

    protected Comment selectComment(Camp camp, User user) {

        if (camp == null) {
            return null;
        }
        ArrayList<Comment> suggestionArrayList = camp.getCampCommentManager()
                .getCommentArrayList(CommentType.COMMENTTYPES.SUGGESTION);
        if (suggestionArrayList.isEmpty()) {
            System.out.println("No available suggestions!");
            return null;
        }
        if (user instanceof Student) {
            Student student = (Student) user;
            ArrayList<Comment> studentSuggestionArrayList = new ArrayList<>();
            for (Comment comment : suggestionArrayList) {
                if (student.equals(comment.getStudent())) {
                    studentSuggestionArrayList.add(comment);
                }
            }
            suggestionArrayList = studentSuggestionArrayList;
        }
        System.out.println("\nSelect Comment:");

        int i = 1;
        for (Comment comment : suggestionArrayList) {
            System.out.printf("\n%d ->\t%s\n", i, comment.toString());
            System.out.println();
            i += 1;
        }
        System.out.printf("%d ->\tExit\n", i);
        System.out.print("\nChoice: ");
        int choice = MainProgram.scanInt();
        if (choice == i) {
            return null;
        }
        while (choice > suggestionArrayList.size() || choice <= 0) {
            System.out.print("Invalid input\nChoice: ");
            choice = MainProgram.scanInt();
        }
        Comment targetComment = suggestionArrayList.get(choice - 1);

        return targetComment;
    }

    public void addComment(Student student, IAdding adder) {
        Camp camp = this.selectCamp(student);
        adder.addComment(student, camp);
        return;
    }

    public void viewComment(Student student, IViewing viewer) {
        Camp targetCamp = this.selectCamp(student);
        viewer.viewComment(student, targetCamp);
        return;
    }

    public void editComment(Student student, IEditing editor) {
        if (student == null) {
            return;
        }
        Camp camp = selectCamp(student);
        if (camp == null) {
            return;
        }
        Comment selectedComment = selectComment(camp, student);
        if (selectedComment == null) {
            return;
        }
        editor.editComment(student, selectedComment);
        return;
    }

    public void deleteComment(Student student, IDeleting deleter) {
        if (student == null) {
            return;
        }
        Camp camp = selectCamp(student);
        if (camp == null) {
            return;
        }
        Comment selectedComment = selectComment(camp, student);
        if (selectedComment == null) {
            return;
        }
        deleter.deleteComment(student, selectedComment, camp);
        return;
    }
}
