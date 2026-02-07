package cubes.main.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Tag {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	@javax.validation.constraints.NotBlank
	@javax.validation.constraints.Size(min=1, max=15)
	private String name;
	@Column
	private boolean isOnFrontPage;

	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})  //ovo je za front, za  dao-gettagListWithBlog
	@JoinTable(name="Blog_Tag", joinColumns = @JoinColumn(name="id_tag"),inverseJoinColumns = @JoinColumn(name="id_blog"))
	private List<Blog> blogs;
	
	@Transient
	private long count;
	
	public Tag() {}
	
	
	public Tag(String name,
			boolean isOnFrontPage) {
		super();
		this.name = name;
		this.isOnFrontPage = isOnFrontPage;
	}

	public Tag(String id) {
		this.id=Integer.parseInt(id);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsOnFrontPage() {
		return isOnFrontPage;
	}

	public void setIsOnFrontPage(boolean isOnFrontPage) {
		this.isOnFrontPage = isOnFrontPage;
	}
	
	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}


	public void setOnFrontPage(boolean isOnFrontPage) {
		this.isOnFrontPage = isOnFrontPage;
	}

	
	public long getCount() {
		return count;
	}


	public void setCount(long count) {
		this.count = count;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}


	

	

}
