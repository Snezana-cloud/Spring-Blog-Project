package cubes.main.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import cubes.main.entity.Roles;
import cubes.main.entity.Users;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired	
	private SessionFactory sessionFactory;

	
	@Transactional
	@Override
	public List<Users> getUserList() {
		Session session=sessionFactory.getCurrentSession();
		Query<Users> query=session.createQuery("from Users",Users.class);
		List<Users> userList=query.getResultList();
		return userList;
	}
	@Transactional
	@Override
	public void deleteUser(String username) {
		Session session=sessionFactory.getCurrentSession();
		Users user=session.get(Users.class, username);
		session.delete(user);
		
	}
	@Transactional
	@Override
	public void saveUser(Users user) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}
	@Transactional
	@Override
	public void enabledUser(String username) {
		Session session=sessionFactory.getCurrentSession();
		Users user= session.get(Users.class, username);
		user.setEnabled(!user.getEnabled()); 
		session.saveOrUpdate(user); //da se sacuva u bazi
	}

	@Transactional
	@Override
	public Users getUserByUsername(String username) {
		Session session=sessionFactory.getCurrentSession();
		Users user=session.get(Users.class, username);
		return user;
	}
	@Transactional
	@Override
	public List<Roles> getRoles() {
		Session session=sessionFactory.getCurrentSession();
		Query<Roles> query=session.createQuery("from Role", Roles.class);
		List<Roles> listRole=query.getResultList();
		return listRole;
	}
	@Transactional
	@Override
	public Users getUserByEmail(String email) {
		Session session=sessionFactory.getCurrentSession();
		Users user=session.get(Users.class, email);
		return user;
	}
	@Transactional
	@Override
	public Users findByResetToken(String token) {
		 Session session = sessionFactory.getCurrentSession();
		    Query<Users> query = session.createQuery("from Users where resetToken = :token", Users.class);
		    query.setParameter("token", token);
		    return query.uniqueResult();
	}
				
	
}
