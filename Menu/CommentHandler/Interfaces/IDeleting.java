package Menu.CommentHandler.Interfaces;

import Comment.Comment;
import Users.User;
import Camp.Camp;

public interface IDeleting {
    void deleteComment(User user, Comment comment, Camp camp);
}