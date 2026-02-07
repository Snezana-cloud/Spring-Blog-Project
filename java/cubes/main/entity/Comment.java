package cubes.main.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Table
@Entity
public class Comment {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	@javax.validation.constraints.NotEmpty
	@Size(min = 3, max = 45, message = "Comment must be between 3 and 45 characters")
	private String name;
	@Column
	@NotEmpty
	@Size(min = 3, message = "Comment must be at least 3 characters")
	private String comment;
	
	@DateTimeFormat(pattern = "MM | yyyy")
	@Column
	private java.sql.Date date;
	@Column
	private boolean enabled;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name= "idBlog")
	private Blog blog;

	@Column	
	@NotNull(message="Unesite ispravnu email adresu.")
	@Pattern(regexp=".+@.+\\..+", message="Unesite ispravnu email adresu.")
	private String email;

	
	public Comment() {}
	
	public Comment(String name, String comment, Date date) {
		super();
		this.name = name;
		this.comment = comment;
		this.date = date;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

    public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Transient
    public String getFormattedDateForDisplay() {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM | yyyy");
            return sdf.format(new java.util.Date(date.getTime()));
        }
        return "";
    }
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return comment;
	}
	
}
