package Camp;

import java.io.Serializable;
import java.util.ArrayList;

import Comment.*;

public class CampCommentManager implements Serializable {
    private CampEnquiry campEnquiry = new CampEnquiry();
    private CampSuggestion campSuggestion = new CampSuggestion();

    public CampComment getComment(CommentType.COMMENTTYPES type) {
        if (type == CommentType.COMMENTTYPES.ENQUIRY) {
            return this.campEnquiry;
        } else if (type == CommentType.COMMENTTYPES.SUGGESTION) {
            return this.campSuggestion;
        }
        return null;
    }

    public ArrayList<Comment> getCommentArrayList(CommentType.COMMENTTYPES type) {
        if (type == CommentType.COMMENTTYPES.ENQUIRY) {
            return this.campEnquiry.getComments();
        } else if (type == CommentType.COMMENTTYPES.SUGGESTION) {
            return this.campSuggestion.getComments();
        } else if (type == CommentType.COMMENTTYPES.BOTH) {
            ArrayList<Comment> combinedList = new ArrayList<>();
            combinedList.addAll(this.campEnquiry.getComments());
            combinedList.addAll(this.campSuggestion.getComments());
            return combinedList;
        }
        return null;
    }

    public String toString(CommentType.COMMENTTYPES type) {
        if (type == CommentType.COMMENTTYPES.ENQUIRY) {
            return this.campEnquiry.toString();
        } else if (type == CommentType.COMMENTTYPES.SUGGESTION) {
            return this.campSuggestion.toString();
        } else if (type == CommentType.COMMENTTYPES.BOTH) {
            return this.campSuggestion.toString() + this.campEnquiry.toString();
        }
        return null;
    }
}
