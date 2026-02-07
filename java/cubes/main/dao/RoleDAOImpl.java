package cubes.main.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import cubes.main.entity.Roles;

@Repository
public class RoleDAOImpl implements RoleDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Roles> getRoleList() {
		Session session=sessionFactory.getCurrentSession();
		Query<Roles> query=session.createQuery("from Roles",Roles.class);
		List<Roles> role=query.getResultList();
		return role;
	}
	@Transactional
	@Override
	public void saveRole(Roles role) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(role);
		
	}
	@Transactional
	@Override
	public void deleteRole(int id) {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Role where id=:id");		
		query.setParameter("roleId", id);		
		query.executeUpdate();
		
	}
	@Transactional
	@Override
	public Roles getRoleByAuthority(String authority) {
		Session session=sessionFactory.getCurrentSession();
		Roles role=session.get(Roles.class, authority);
		return role;
	}

}