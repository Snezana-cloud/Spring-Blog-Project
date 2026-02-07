package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Blog;
import cubes.main.entity.Category;
import cubes.main.entity.Tag;

public interface CategoryDAO {
	
	public List<Category> getCategorylist();
	public void saveCategory(Category category);
	public Category getCategory(int id);
	public void deleteCategory(int id);
	public List<Category> getCategoryOnFrontPage(Integer orderCategory);
	public List<Category> getCategoryForFilter();
	public List<Blog> getBlogListForCategory(int id);
	
	public Category getCategoryByName(String name);
	public List<Category> getCategoryByOrder(Integer orderCategory);
}
