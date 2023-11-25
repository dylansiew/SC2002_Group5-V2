package Menu.CommentHandler.Interfaces;

import Comment.Comment;
import Users.User;
import Camp.Camp;

public interface IReplying {
    void replyComment(User user, Comment comment, Camp camp);
}