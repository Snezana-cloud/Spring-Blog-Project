package cubes.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import cubes.main.entity.Blog;
import cubes.main.entity.Category;

@Repository  
public class CategoryDaoImpl implements CategoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional  
	@Override
	public List<Category> getCategorylist() {
	
		Session session=sessionFactory.getCurrentSession();
		Query<Category> query=session.createQuery("from Category", Category.class);
		List<Category> categoryList=query.getResultList();
		return categoryList;
	}

	@Transactional
	@Override
	public void saveCategory(Category category) {
		
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(category); 
	}
	@Transactional
	@Override
	public Category getCategory(int id) {
		Session session=sessionFactory.getCurrentSession();
		Category category=session.get(Category.class,id);
		return category;
	}
	@Transactional
	@Override
	public void deleteCategory(int id) {
		Session session=sessionFactory.getCurrentSession();
		Query query= session.createQuery("delete from Category where id=:id");
		query.setParameter("id", id);  
		
		query.executeUpdate();
	}

	@Transactional
	@Override
	public List<Category> getCategoryOnFrontPage(Integer orderCategory) {
		Session session=sessionFactory.getCurrentSession();
		Query<Category> query=session.createQuery("select c from Category c where c.isOnFrontPage=1",Category.class);
		List<Category> list=query.getResultList();
		
		for (Category c: list)
		{  
			Query<Blog> blogQuery=session.createQuery("select b from Blog b where b.isEnable=1 AND b.category.id=:id ",Blog.class);
			blogQuery.setParameter("id", c.getId());
		
			c.setBlogOnFrontPage(blogQuery.getResultList());
		}
		return list;
	}
	@Transactional
	@Override
	public List<Category> getCategoryForFilter() {
		Session session=sessionFactory.getCurrentSession();
		Query<Category> query=session.createQuery("select c from Category c where c.isOnFrontPage=1 order by orderCategory"); 
		List<Category> listCategory=query.getResultList();
		
		for(Category cat: listCategory ) {  
			Query queryCount=session.createQuery("select count(blog.id) from Blog blog where blog.category.id=:id");
			queryCount.setParameter("id",cat.getId());
			cat.setCount((long)queryCount.uniqueResult());
		}
		return listCategory;
	}

		@Transactional
		@Override
		public List<Blog> getBlogListForCategory(int id) {
			 Session session = sessionFactory.getCurrentSession();
		     Query<Blog> query = session.createQuery("from Blog b where b.category.id = :id", Blog.class);
		     query.setParameter("id", id);
		 
		     return query.getResultList();
		}
		@Transactional  
		@Override
		public Category getCategoryByName(String name) {
			Session session = sessionFactory.getCurrentSession();
		    Query<Category> query = session.createQuery("from Category where name = :name", Category.class);
		    query.setParameter("name", name);
		    return query.uniqueResult();
		}
		@Transactional
		@Override
		public List<Category> getCategoryByOrder(Integer orderCategory) {
			Session session=sessionFactory.getCurrentSession();
			Query<Category> query=session.createQuery("from Category c where c.orderCategory=:orderCategory ",Category.class);
			query.setParameter("orderCategory", orderCategory);
			return query.getResultList();
		}

}
