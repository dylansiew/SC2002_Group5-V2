package Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import Camp.*;
import Comment.Comment;
import Users.*;
import Comment.Enquiry;
import Comment.CommentType.COMMENTTYPES;

/**
 * The `Report` class generates a report for the specified camp.
 * It generates different types of report based on user's perference
 */

public class Report {
	
	/**
	 * Specify the directory to store the report generated
	 */
    private static final String REPORT_FOLDER = "Report_Output";

    /**
     * Generates report of a camp according to the filter (reportType) given from ReportMenu
     * @param camp camp object
     * @param reportType enum that used to filter the report type
     */
    public static void generateListReport(Camp camp, Roles.ROLES reportType) {
        if (camp == null || reportType == null) {
            return;
        }

        String fileName = camp.getCampInformation().getName().getString().trim().replace(" ", "_") + "_";
        if (reportType == Roles.ROLES.ATTENDEE) {
            fileName += "attendee_list_report.txt";
        } else if (reportType == Roles.ROLES.COMMITTEE) {
            fileName += "committee_list_report.txt";
        } else if (reportType == Roles.ROLES.STUDENT) {
            fileName += "attendee_and_committee_lists_report.txt";
        }

        String workingDir = System.getProperty("user.dir");
        String reportFolderPath = Paths.get(workingDir, REPORT_FOLDER).toString();
        new File(reportFolderPath).mkdirs();

        String filePath = Paths.get(reportFolderPath, fileName).toString();

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(camp.toString());
            writer.append(camp.getUserManager().getParticipantManager().toString(reportType));
            writer.close();

            System.out.println("Report is generated successfully at " + filePath + ".");

        } catch (IOException e) {
            System.out.println("An error occurred while writing the report: " + e.getMessage());
        }

    }
    
    /**
     * Generate committee performance report
     * @param camp 
     */

    public static void generatePerformanceReport(Camp camp) {
        if (camp == null) {
            return;
        }
        ArrayList<User> committees = camp.getUserManager().getParticipantManager()
                .getParticipantsArrayList(Roles.ROLES.COMMITTEE);

        String fileName = camp.getCampInformation().getName().getString().trim().replace(" ", "_")
                + "_committee_performance_report.txt";
        String workingDir = System.getProperty("user.dir");
        String reportFolderPath = Paths.get(workingDir, REPORT_FOLDER).toString();
        new File(reportFolderPath).mkdirs();
        String filePath = Paths.get(reportFolderPath, fileName).toString();

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(camp.toString());
            writer.append("\nCommittee Performance List:\n");
            int i = 1;
            for (User user : committees) {
                if (!(user instanceof Student) || ((Student) user).getCommittee() == null
                        || !(((Student) user).getCommittee().getCamp().equals(camp))) {
                    continue;
                }
                Committee committee = ((Student) user).getCommittee();
                writer.append(i++ + ". " + user.toString() + "| Points: " + committee.getPoints()
                        + "\n");
            }

            writer.close();

            System.out.println("Report is generated successfully at " + filePath + ".");

        } catch (IOException e) {
            System.out.println("An error occurred while writing the report: " + e.getMessage());
        }
    }
    
    /**
     * Generates student enquiry report
     * @param camp camp object
     */
    public static void generateStudentEnquiryReport(Camp camp) {
        if (camp == null) {
            return;
        }

        String fileName = camp.getCampInformation().getName().getString().trim().replace(" ", "_")
                + "_student_enquiry_report.txt";
        String workingDir = System.getProperty("user.dir");
        String reportFolderPath = Paths.get(workingDir, REPORT_FOLDER).toString();
        new File(reportFolderPath).mkdirs();
        String filePath = Paths.get(reportFolderPath, fileName).toString();

        ArrayList<Comment> enquiries = camp.getCampCommentManager().getCommentArrayList(COMMENTTYPES.ENQUIRY);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(camp.toString());

            writer.append("\nStudent Enquiries: \n");

            int i = 1;
            if (enquiries == null || enquiries.isEmpty()) {
                writer.append("There is no enquiries on this camp.\n");
            } else {
                for (Comment enquiry : enquiries) {
                    Enquiry en = (Enquiry) enquiry;
                    writer.append(i++ + ". " + en.toString() + "\n");
                }
            }

            System.out.println("Report is generated successfully at " + filePath + ".");

            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred while writing the report: " + e.getMessage());
        }
    }

}