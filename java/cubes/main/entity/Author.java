  package cubes.main.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Author {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  id;  
	@Column
	private String name;
	@Column
	private String image;
	@OneToMany(cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name= "idBlog")
	@Transient
	private List<Blog> blog;
	//proba za novi commit
	
	public Author() {};
	
	public Author(int id) {
		super();
		this.id = id;
	}
	
	public Author(List<Blog> blog) {
		super();
		this.blog = blog;
	}
	public Author(String name, String image) {
		super();
		this.name = name;
		this.image = image;
	}
	public Integer  getId() {
		return id;
	}
	public void setId(Integer  id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public List<Blog> getBlog() {
		return blog;
	}
	public void setBlog(List<Blog> blog) {
		this.blog = blog;
	}
	

	@Override
	public String toString() {
		
		return name;
	}
	
	
}
