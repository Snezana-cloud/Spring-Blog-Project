package cubes.main.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cubes.main.dao.CategoryDAO;
import cubes.main.entity.Category;
import cubes.main.entity.Roles;
import cubes.main.exceptions.NotFoundExcepton;
import javassist.NotFoundException;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	
	@GetMapping("/categories") 
	public List<Category> getCategories() {
		
		List<Category> list=categoryDAO.getCategorylist();

		return list;
	}
	
	@GetMapping("/categories/{id}")
	public Category getCategoryById(@PathVariable int id) {
		Category category=categoryDAO.getCategory(id);
		
		if(category == null) { 
			throw new NotFoundExcepton("no category for id- "+id);
		}
		return category;
	}
	
	
	@PostMapping("/categories")  
	public Category addCategory(@RequestBody Category category) {
		category.setId(0);
		categoryDAO.saveCategory(category);
		return category;
	}
	
	@PutMapping("/categories")  
	public Category updateCategory(@RequestBody Category category) { 
		
		categoryDAO.saveCategory(category);
		return category;
	}
	
	@DeleteMapping("/categories/{id}")  
	public MessageResponse deleteCategory(@PathVariable int id) throws NotFoundException { 
		Category cat= categoryDAO.getCategory(id);
	
		if(cat==null) {
			throw new NotFoundException("no category for id "+id);
		}
		categoryDAO.deleteCategory(id);
		return new MessageResponse(HttpStatus.OK.value(),"success deleted",System.currentTimeMillis());
	}
	@GetMapping("/categories/{name}")
	public Category getCategoryByName(String name) {
		Category category=categoryDAO.getCategoryByName(name);
		return category;
	}
}
