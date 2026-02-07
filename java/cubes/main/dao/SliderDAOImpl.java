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
import cubes.main.entity.Slider;

@Repository
public class SliderDAOImpl implements SliderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Slider> getSliderList() {
		 Session session = sessionFactory.getCurrentSession();
		    Query<Slider> query = session.createQuery("from Slider", Slider.class);
		    List<Slider> sliderList=query.getResultList();
		    return sliderList;
	}

	@Transactional
	@Override
	public Slider getSlider(int id) {
		Session session=sessionFactory.getCurrentSession();
		Slider slider=session.get(Slider.class,id);
			
		return slider;
	}
	@Transactional
	@Override
	public void deleteSlider(int id) {
		Session session=sessionFactory.getCurrentSession();
		Query<Slider> slider=session.createQuery("delete from Slider where id=:id");
		slider.setParameter("id", id)	;
		slider.executeUpdate();
	}
	
	@Transactional
	@Override
	public void saveSlider(Slider slider) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(slider);
		
	}
	@Transactional
	@Override
	public List<Slider> getOrderOfSlider() {
		Session session=sessionFactory.getCurrentSession();
		Query<Slider> query=session.createQuery("select s from Slider s where s.isOnFrontPage=1 order by s.orderOfImage");
		List<Slider> list=query.getResultList();
		return list;
	
	}
}


	

	

	



