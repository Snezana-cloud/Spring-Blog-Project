package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Slider;

public interface SliderDAO {

	public List<Slider> getSliderList();
	
	public Slider getSlider(int id);
	public void deleteSlider(int id);
	public void saveSlider(Slider slider);
	public List<Slider> getOrderOfSlider();
	
}
