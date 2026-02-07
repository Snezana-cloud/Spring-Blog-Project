package cubes.main.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.query.Query;

import cubes.main.entity.Blog;
import cubes.main.entity.Category;
import cubes.main.entity.Comment;
import cubes.main.entity.Contact;

@Repository
public class CommentDAOImpl implements CommentDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Comment> getCommentList() {
		Session session=sessionFactory.getCurrentSession();
		Query<Comment> query=session.createQuery("from Comment",Comment.class);
		return query.getResultList();
		
	}

	@Transactional
	@Override
	public void enabledComment(int id) {
		Session session=sessionFactory.getCurrentSession();
		Comment comment= session.get(Comment.class, id);
		comment.setEnabled(!comment.getEnabled()); 
		session.saveOrUpdate(comment); 
	}
	

	@Transactional
	@Override
	public List<Comment> getCommentLIstById(int id) {
		Session session=sessionFactory.getCurrentSession();
		Query<Comment> query=session.createQuery("from Comment c where c.blog.id= :id AND enabled=1 order by c.date DESC ",Comment.class);
		query.setParameter("id",id);   
		List<Comment> list=query.getResultList();
		return list;
	}
	@Transactional
	@Override
	public void saveComment(Comment comment,int id) {
	Session session=sessionFactory.getCurrentSession();
	
    Blog blog = session.get(Blog.class, id);
    comment.setBlog(blog);
    comment.setEnabled(true);  
	session.save(comment);

	}

	@Transactional
	@Override
	public List<Comment> getCommentsByAuthorUsername(String username) {
	    Session session = sessionFactory.getCurrentSession();
	    String queryString = "select c from Comment c " +
	                         "join c.blog b " +
	                         "join b.author a " +
	                         "where a.name like concat(:username, '%')";

	    Query<Comment> query = session.createQuery(queryString, Comment.class);
	    query.setParameter("username", username);
	    
	    return query.getResultList();
	}

}
