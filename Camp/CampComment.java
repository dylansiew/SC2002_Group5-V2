package Camp;

import java.io.Serializable;
import java.util.ArrayList;
import Comment.*;

public abstract class CampComment implements Serializable {
    private ArrayList<Comment> comments = new ArrayList<>();
    private CommentType.COMMENTTYPES commentType;

    public CampComment(CommentType.COMMENTTYPES type) {
        this.commentType = type;
    }

    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    public void addComment(Comment comments) {
        if (comments == null) {
            return;
        }
        this.comments.add(comments);
    }

    public void removeComment(Comment comments) {
        if (comments == null || !this.comments.contains(comments)) {
            return;
        }
        this.comments.remove(comments);
    }

    public int getCommentSize() {
        return this.comments.size();
    }

    public String toString() {
        String info = "\n" + commentType + " (" + comments.size() + "):\n";
        for (Comment comment : comments) {
            info += "\t" + comment.toString() + "\n";
        }
        return info;
    }
}
