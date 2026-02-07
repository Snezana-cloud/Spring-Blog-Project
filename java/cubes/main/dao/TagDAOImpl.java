package cubes.main.dao;


import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import cubes.main.entity.Blog;
import cubes.main.entity.Tag;

@Transactional
@Repository
public class TagDAOImpl implements TagDAO{
	
	@Autowired
	private SessionFactory sessionfactory;
	
	@Transactional
	@Override
	public List<Tag> getTagList() {
		
		Session session=sessionfactory.getCurrentSession();
		Query<Tag> query = session.createQuery("from Tag", Tag.class);
		List<Tag> tagList=query.getResultList();
		return tagList;
	}

	@Transactional
	@Override
	public Tag getTag(int id) {
		Session session=sessionfactory.getCurrentSession();
		Tag tag=session.get(Tag.class, id);
		return tag;
	}

	@Transactional
	@Override
	public void deleteTag(int id) {
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("delete from Tag where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public void saveTag(Tag tag) {
		Session session=sessionfactory.getCurrentSession();
		session.saveOrUpdate(tag);
		
	}
	@Transactional
	@Override
	public List<Tag> getTagListWithBlog() {               
		Session session=sessionfactory.getCurrentSession();
		Query<Tag> query=session.createQuery("select t from Tag t where t.isOnFrontPage=1");
		List<Tag> tags=query.getResultList();
	
		for(Tag tag: tags) {
			Hibernate.initialize(tag.getBlogs());
		}
		return tags;
	}
	
	@Transactional
	@Override
	public List<Tag> getTagOnFrontPage() {
		Session session=sessionfactory.getCurrentSession();
		Query<Tag> query=session.createQuery("select t from Tag t where t.isOnFrontPage=1");
		List<Tag> listTag=query.getResultList();
		return listTag;
	}
	@Transactional 
	@Override  
	public List<Tag> getTagsByID(List<Integer> ids) {
	
			Session session=sessionfactory.getCurrentSession();
			Query<Tag> query=session.createQuery("select t from Tag t where t.id IN (:ids)");
			
			query.setParameter("ids", ids);
			List<Tag> tags=query.getResultList();
			
			
			return tags;
		}
	@Transactional 
	@Override
	public List<Tag> getTagsByBlogID(Integer id) {
		Session session=sessionfactory.getCurrentSession();
		Blog blog=session.get(Blog.class, id);
		Hibernate.initialize(blog.getTag());
		return blog.getTag();
	}

}
