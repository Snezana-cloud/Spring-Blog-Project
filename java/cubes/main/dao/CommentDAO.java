package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Blog;
import cubes.main.entity.Comment;

public interface CommentDAO {

	
	public List<Comment> getCommentList();
	public void enabledComment(int id);

	public List<Comment> getCommentLIstById(int id);
	public void saveComment(Comment comment,int id);
	public List<Comment> getCommentsByAuthorUsername(String username);
}
