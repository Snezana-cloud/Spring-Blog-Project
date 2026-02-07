package cubes.main.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.security.Principal;
import java.sql.ResultSet;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Blog;

import cubes.main.entity.Tag;

import java.security.Principal;

@Repository
public class BlogDAOImpl implements BlogDAO {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Transactional
	@Override
	public List<Blog> getBlogList() {
		
		Session session=sessionFactory.getCurrentSession();
		Query<Blog> query=session.createQuery("from Blog",Blog.class);
		List<Blog> bloglist=query.getResultList();
		
		return bloglist;
	}

	@Transactional
	@Override
	public void deleteBlog(int id) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("delete from Blog where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	
	@Transactional
	@Override
	public void saveBlog(Blog blog) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(blog);
		
	}
	
	@Transactional
	@Override
	public Blog getBlog(int id) {
		Session session=sessionFactory.getCurrentSession();
		Blog blog=session.get(Blog.class, id);
	  
		return blog;
	}

	@Transactional
	@Override
	public List<Blog> getBlogListByCategory(int id) { 
		Session session=sessionFactory.getCurrentSession();
		Query<Blog> query=session.createQuery("from Blog blog where blog.category.id=:id AND blog.isEnable=1  ");
		query.setParameter("id", id);
		
		return query.getResultList();
	}
	@Transactional
	@Override  
	public Blog getBlogWithTag(int id) {
		Session session=sessionFactory.getCurrentSession();
		Blog blog=session.get(Blog.class, id);
		Hibernate.initialize(blog.getTag());
		return blog;
	}
	
	@Transactional
	@Override  
	public List<Blog> getBlogListByTag(int id) { 
		Session session=sessionFactory.getCurrentSession();
		Tag tag= session.get(Tag.class,id);
		Hibernate.initialize(tag.getBlogs()); 
		return tag.getBlogs();
	}
	@Transactional
	@Override  
	public List<Blog> getLastThreeBlog() {
		Session session=sessionFactory.getCurrentSession();
		  List<Blog> blogQuery = session.createQuery("select b from Blog  b where b.isEnable=1  ORDER BY b.isSeen DESC").setMaxResults(3).list();

		    for (Blog blog : blogQuery) {
		        Integer commentCount = (int) getCommentCount(blog.getId());
		        blog.setCount(commentCount);  
		    }
	       return  blogQuery;
	        		    
		}

	@Transactional
	@Override   
	public List<Blog> getLastThreeImportantBlog() {
		Session session=sessionFactory.getCurrentSession();

	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.MONTH, -1); 
	    java.sql.Date monthAgo = new java.sql.Date(calendar.getTimeInMillis());

	    List<Blog> blogQuery = session.createQuery("SELECT b FROM Blog b WHERE b.isImportant = 1 AND b.time >= :monthAgo ORDER BY b.time DESC", Blog.class)
	                                  .setParameter("monthAgo", monthAgo)
	                                  .setMaxResults(3)
	                                  .list();
		
	       return  blogQuery;
	 
	}
	@Transactional
	@Override
	public List<Blog> getLastSixBlog() {  
	
			Session session=sessionFactory.getCurrentSession();
			  List<Blog> blogQuery = session.createQuery("select b from Blog b WHERE b.isEnable=1 ORDER BY b.time DESC").setMaxResults(12).list();
		
		       return  blogQuery;			    
			
	}
	@Transactional
	@Override
	public List<Blog> getBlogListByTitle(int titleId) {
		Session session=sessionFactory.getCurrentSession();
		Query<Blog> query=session.createQuery("from Blog blog where blog.id=:titleId",Blog.class);
		query.setParameter("titleId", titleId);
		
		return query.getResultList();
	}


	@Transactional
	@Override
	public List<Blog> getBlogListForAuthor(int id) {
		Session session=sessionFactory.getCurrentSession();
		Query<Blog> query=session.createQuery("from Blog blog where blog.author.id=:id");
		query.setParameter("id", id);
		return query.getResultList();
	}


	@Transactional  
	@Override
	public List<Blog> getBlogListByEnabledStatus(Boolean enabledId) {
		Session session=sessionFactory.getCurrentSession();
		Query<Blog> query=session.createQuery("from Blog blog where blog.isEnable=:enabledId");
		query.setParameter("enabledId", enabledId);
		return query.getResultList();
	}
	@Transactional
	@Override
	public List<Blog> getBlogListByEnabledStatus() {
		Session session=sessionFactory.getCurrentSession();
		Query<Blog> query=session.createQuery("select b from Blog b where b.isEnable=1");
		List<Blog> list=query.getResultList();
		
		return list;
	}

	@Transactional
	@Override
	public void enabledBlog(int id) {
		Session session=sessionFactory.getCurrentSession();
		Blog blog= session.get(Blog.class, id);
		blog.setIsEnable(!blog.getIsEnable()); 
		session.saveOrUpdate(blog); 
	}
	
	@Transactional
	@Override
	public void importantBlog(int id) {
		Session session=sessionFactory.getCurrentSession();
		Blog blog= session.get(Blog.class, id);
		blog.setIsImportant(!blog.getIsImportant()); 
		session.saveOrUpdate(blog); 
	}

	@Transactional
	@Override
	public List<Blog> getBlogListForAuthor(Integer authorId) {
	    Session session = sessionFactory.getCurrentSession();
	    Query<Blog> query = session.createQuery("from Blog b where b.author.id = :authorId", Blog.class);
	    query.setParameter("authorId", authorId);
	    return query.getResultList();
	}


	@Transactional
	@Override
	public long getSeenBlogsCount(Integer id) {  
	    Session session = sessionFactory.getCurrentSession();
	    Blog blog = session.get(Blog.class, id);
	    blog.setIsSeen(blog.getIsSeen() + 1);
	    session.update(blog);    
	    return blog.getIsSeen();
	}

	@Transactional
	@Override
	public long getCommentCount(Integer id) {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(c.id) from Comment c where c.blog.id = :id AND c.enabled=1 ");
	    query.setParameter("id", id);
	    return (long) query.uniqueResult();
	}

	
	
	@Transactional
	@Override
	public Blog getPreviousBlogById(int id) {
	    Session session = sessionFactory.getCurrentSession();
	    
	    Query<Blog> query = session.createQuery("from Blog b where b.id < :id order by b.id DESC", Blog.class);
	    query.setParameter("id", id);
	    query.setMaxResults(1);
	    
	    return query.uniqueResultOptional().orElse(null);
	}

	
	@Transactional
	@Override
	public Blog getNextBlogById(int id) {
	    Session session = sessionFactory.getCurrentSession();
	    Query<Blog> query = session.createQuery("from Blog b where b.id > :id order by b.id ASC", Blog.class);
	    query.setParameter("id", id);
	    query.setMaxResults(1);

	    return query.uniqueResultOptional().orElse(null);
	}

	@Transactional
	@Override
	public List<Blog> getBlogListBySearch(String searchText) {
	    Session session = sessionFactory.getCurrentSession();

	    String stringQuery = "from Blog b where b.title LIKE :searchText OR b.description LIKE :searchText  OR b.content1 LIKE :searchText OR b.content2 LIKE :searchText";
	    
	    Query<Blog> query = session.createQuery(stringQuery, Blog.class);

	    query.setParameter("searchText", "%" + searchText + "%");
	    
	    List<Blog> blogs = query.getResultList();
	    for (Blog b : blogs) {
	        Hibernate.initialize(b.getTag());
	    }
	    
	    return blogs;
	}

	@Transactional
	@Override
	public List<Blog> getBlogsByUsername(Principal principal){
			
	    Session session = sessionFactory.getCurrentSession();
	    String username = principal.getName(); 

	    
	    String queryString = "select b from Blog b " +
	                         "join Author a on b.author.id = a.id " +   
	                         "where a.name like concat('%', :username, '%')";
	    
	    
	    Query<Blog> query = session.createQuery(queryString, Blog.class);
	    query.setParameter("username", username);
	    
	    return query.getResultList();
	}


	}
	







