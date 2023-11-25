package Menu;

import Comment.CommentType;
import Controller.*;
import Menu.CommentHandler.StudentAddSuggestion;
import Menu.CommentHandler.StudentDeleteSuggestion;
import Menu.CommentHandler.StudentEditSuggestion;
import Menu.CommentHandler.StudentReplyEnquiry;
import Menu.CommentHandler.StudentViewSuggestion;
import Program.*;
import Users.*;

public class CommitteeMenu extends UserMenu {
    public CommitteeMenu(Student student, AllUser allUser, AllCamp allCamp) {
        super(student, allUser, allCamp);
    }

    public void mainMenu() {
        StudentSuggestionMenu suggestionMenu = new StudentSuggestionMenu(allCamp, CommentType.COMMENTTYPES.SUGGESTION);
        StudentEnquiryMenu enquiryMenu = new StudentEnquiryMenu(allCamp, CommentType.COMMENTTYPES.ENQUIRY);
        UserMenu reportMenu = new ReportMenu(user, allUser, allCamp);
        String menuText = "\nWhat are we doing today?\n"
                + "(1)\tSubmit suggestions\n"
                + "(2)\tEdit suggestions\n"
                + "(3)\tDelete my suggestions\n"
                + "(4)\tView my suggestions\n"
                + "(5)\tRespond to enquiries\n"
                + "(6)\tGenerate report\n"
                + "(7)\tExit\n"
                + "Choice: ";
        int choice;
        do {
            System.out.print(menuText);

            choice = MainProgram.scanInt();
            switch (choice) {
                case 1:
                    suggestionMenu.addComment((Student) this.user, new StudentAddSuggestion());
                    break;
                case 2:
                    suggestionMenu.editComment((Student) this.user, new StudentEditSuggestion());
                    break;
                case 3:
                    suggestionMenu.deleteComment((Student) this.user, new StudentDeleteSuggestion());
                    break;
                case 4:
                    suggestionMenu.viewComment((Student) this.user, new StudentViewSuggestion());
                    break;
                case 5:
                    enquiryMenu.replyComment((Student) this.user, new StudentReplyEnquiry());
                    break;
                case 6: // generate report
                    reportMenu.mainMenu();
                    break;
                case 7: // quit program
                    return;
                default:
                    System.out.println("Sorry, please enter a valid input.");

            }
        } while (true);

    }
}
