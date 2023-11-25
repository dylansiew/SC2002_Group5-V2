package Menu;

import Comment.CommentType;
import Controller.*;
import Menu.CampHandler.*;
import Menu.CommentHandler.*;
import Users.*;
import Program.*;

public class StaffMenu extends UserMenu {
    StaffSuggestionMenu suggestionMenu;
    StaffEnquiryMenu enquiryMenu;
    UserMenu reportMenu;

    public StaffMenu(Staff staff, AllUser allUser, AllCamp allCamp) {
        super(staff, allUser, allCamp);
        suggestionMenu = new StaffSuggestionMenu(allCamp, CommentType.COMMENTTYPES.SUGGESTION);
        enquiryMenu = new StaffEnquiryMenu(allCamp, CommentType.COMMENTTYPES.ENQUIRY);
        reportMenu = new ReportMenu(user, allUser, allCamp);
    }

    public void mainMenu() {
        StaffSuggestionMenu suggestionMenu = new StaffSuggestionMenu(allCamp, CommentType.COMMENTTYPES.SUGGESTION);
        StaffEnquiryMenu enquiryMenu = new StaffEnquiryMenu(allCamp, CommentType.COMMENTTYPES.ENQUIRY);
        UserMenu reportMenu = new ReportMenu(user, allUser, allCamp);

        String menuText = "\nWhat are we doing today?\n"
                + "(1)\tCreate camp\n"
                + "(2)\tView all camps\n"
                + "(3)\tEdit Camp\n"
                + "(4)\tView Enquiry\n"
                + "(5)\tReply Enquiry\n"
                + "(6)\tDelete Camp\n"
                + "(7)\tView students in camp\n"
                + "(8)\tGenerate Report\n"
                + "(9)\tApprove Suggestion\n"
                + "(10)\tView Suggestion\n"
                + "(11)\tExit\nChoice: ";
        System.out.print(menuText);

        int choice = MainProgram.scanInt();
        while (true) {
            switch (choice) {
                case 1:
                    campHandler.getCampModifyHandler().createCamp();
                    break;
                case 2:
                    CampSelectionBuilder.selectCamp(allCamp.getCamps());
                    break;
                case 3:
                    campHandler.getCampModifyHandler().editCamps();
                    break;
                case 4:
                    enquiryMenu.viewComment((Staff) this.user, new StaffViewEnquiry());
                    break;
                case 5:
                    enquiryMenu.replyComment((Staff) this.user, new StaffReplyEnquiry());
                    break;
                case 6:
                    campHandler.getCampModifyHandler().deleteCamp();
                    break;
                case 7:
                    campHandler.getCampViewHandler().viewCampMembers();
                    break;
                case 8:
                    reportMenu.mainMenu();
                    break;
                case 9:
                    suggestionMenu.replyComment((Staff) this.user, new StaffReplySuggestion());
                    break;
                case 10:
                    suggestionMenu.viewComment((Staff) this.user, new StaffViewSuggestion());
                    break;
                default:
                    return;
            }
            System.out.print(menuText);
            choice = MainProgram.scanInt();
        }

    }

}
