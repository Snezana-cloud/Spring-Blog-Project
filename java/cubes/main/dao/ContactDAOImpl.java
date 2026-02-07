package cubes.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Contact;

@Repository
public class ContactDAOImpl implements ContactDAO {

	@Autowired
	private SessionFactory sessionfactory;
	@Transactional
	@Override
	public void saveContact(Contact contact) {
		Session session= sessionfactory.getCurrentSession();
		session.saveOrUpdate(contact);
		
	}
	@Transactional
	@Override
	public List<Contact> getContactList() {
		Session session= sessionfactory.getCurrentSession();
		Query<Contact> query=session.createQuery(" from Contact",Contact.class);
		
		return query.getResultList();
	}
	@Transactional
	@Override
	public long getUnSeenComment() {
		Session session= sessionfactory.getCurrentSession();
		Query query=session.createQuery("select count(*) from Contact c where c.isSeen=0");
		
		return (long) query.uniqueResult();
	}
	@Transactional
	@Override
	public Contact getContact(int id) {
		Session session= sessionfactory.getCurrentSession();
		
		return session.get(Contact.class, id);
	}


}
