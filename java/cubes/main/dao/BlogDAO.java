package cubes.main.dao;

import java.security.Principal;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Blog;



public interface BlogDAO {

	public List<Blog> getBlogList();
	public void deleteBlog(int id);
	public void saveBlog(Blog blog);
	public Blog getBlog(int id);
	public Blog getBlogWithTag(int id);
	public List<Blog> getLastThreeBlog();
	public List<Blog> getLastThreeImportantBlog();
	public List<Blog> getBlogListByCategory(int id);
	public List<Blog> getBlogListByTag(int id);   
	public List<Blog> getLastSixBlog();
	public List<Blog> getBlogListByTitle(int titleId);  
	public List<Blog> getBlogListByEnabledStatus(Boolean enabledId);  
	public List<Blog> getBlogListByEnabledStatus(); 
	public List<Blog> getBlogListForAuthor(int id);   
	public void enabledBlog(int blogId);
	public void importantBlog(int id); 
	public List<Blog> getBlogListForAuthor(Integer idAuthor);
	
	public long getSeenBlogsCount(Integer id);
	public long getCommentCount(Integer id);
	public Blog getPreviousBlogById(int id);
	public Blog getNextBlogById(int id);
	public List<Blog> getBlogListBySearch(String searchText);
	
	public List<Blog> getBlogsByUsername(Principal principal);

}