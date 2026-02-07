package cubes.main.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import jakarta.validation.constraints.Size;



@Entity
@Table
public class Category {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	@javax.validation.constraints.NotBlank
	@javax.validation.constraints.Size(min=1, max=15)
	private String name;
	@OneToMany(cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name= "idBlog")	
	private List<Blog> blog;
	@Column
	private String description;
	@Column
	private Integer  orderCategory; //redosled prikazivanja kateogrija
	
	@Transient
	private long count;
	@Column
	private boolean isOnFrontPage;
	@Transient
	private List<Blog> blogOnFrontPage;

	public Category() {}
	
	public Category(int id) {
		super();
		this.id = id;
	}

	public Category(String name, long count, boolean isOnFrontPage) {
		super();
		this.name = name;
		this.count = count;
		this.isOnFrontPage = isOnFrontPage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean getIsOnFrontPage() {
		return isOnFrontPage;
	}

	public void setIsOnFrontPage(boolean isOnFrontPage) {
		this.isOnFrontPage = isOnFrontPage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getOrderCategory() {
		return orderCategory;
	}

	public void setOrderCategory(Integer orderCategory) {
		this.orderCategory = orderCategory;
	}

	public void setCount(long count) {
		this.count = count;
	}


	public List<Blog> getBlogOnFrontPage() {
		return blogOnFrontPage;
	}

	public void setBlogOnFrontPage(List<Blog> blogOnFrontPage) {
		this.blogOnFrontPage = blogOnFrontPage;
	}

	@Override
	public String toString() {
		
		return '('+id +')'+name;
	}

}
