package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Tag;


public interface TagDAO {
	
	public List<Tag> getTagList();
	public List<Tag> getTagListWithBlog();
	public Tag getTag(int id);
	public void deleteTag(int id);
	public void saveTag(Tag tag);
	public List<Tag> getTagOnFrontPage();
	public List<Tag> getTagsByID(List<Integer> ids);
	public List<Tag> getTagsByBlogID(Integer id);


}
