package cubes.main.entity;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import cubes.main.dao.DateUtils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table
public class Blog {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	@javax.validation.constraints.NotEmpty
	@javax.validation.constraints.Size(min=20, max=225)
	private String title;
	
	@Column
	@javax.validation.constraints.NotEmpty
	@javax.validation.constraints.Size(min=50, max=500)
	private String description;
	
	@Column
	@javax.validation.constraints.NotEmpty
	@javax.validation.constraints.Size(min=1, max=600)
	private String content1;
	
	@Column
	@javax.validation.constraints.NotEmpty
	@javax.validation.constraints.Size(min=1, max=600)
	private String content2;
	

	
	@Column
	@javax.validation.constraints.NotEmpty  //tekst na blog-post u pravougaoniku
	@javax.validation.constraints.Size(min=1, max=600)
	private String famousWords;
	
	@Column
	@javax.validation.constraints.NotEmpty //ime autora teksta u pravougaoniku
	@javax.validation.constraints.Size(min=1, max=30)
	private String famousPerson;
	
	@Column
	@javax.validation.constraints.Size(max=400)
	private String image;
	
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name= "idAuthor")
	private Author author;
	
	@Column
	private Boolean isEnable=false; 
	@Column
	private Boolean isImportant=false;
	@Transient
	private String published;

	@Column
	private Long isSeen=0L;  
	@DateTimeFormat(pattern = "dd.MM | yyyy")
	@Column
	@javax.validation.constraints.NotNull
	private java.sql.Date time; 
	@Column
	@javax.validation.constraints.Size(max=400)
	private String imageBlog;
	@Column
	@javax.validation.constraints.Size(max=400)
	private String imageFollowing;  
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name= "idCategory")
	private Category category;
	@Transient
	private List<Category> categoryOnFrontPage;
	
	@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name="Blog_Tag", joinColumns = @JoinColumn(name="id_blog"),inverseJoinColumns = @JoinColumn(name="id_tag"))
	private List<Tag> tag;
	
	@Transient
    private String formattedDate;  //kristim za dobijanje "2 mants ago"
	
	@OneToMany(mappedBy = "blog", cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    private List<Comment> comments;
	
	@Transient
	private Integer count; //broji komentare
	
	@Transient
	@javax.validation.constraints.NotNull
	private int page; //za paginaciju
    
	

	public Blog() {}
	
	
	public Blog(@NotEmpty @Size(min = 1, max = 45) Author author) {
		super();
		this.author = author;
	}


	public Blog(int id) {
		super();
		this.id = id;
	}


	public Blog(@NotEmpty @Size(min = 20, max = 225) String title) {
		super();
		this.title = title;
	}


	public Blog(String image,String description, boolean isEnable, boolean isImportant,  long isSeen,
			String name) {
		super();
		this.image = image;
		this.description = description;
		this.isEnable = isEnable;
		this.isImportant = isImportant;
		
		this.isSeen = isSeen;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}

	public String getContent() {
		return content1;
	}


	public void setContent(String content) {
		this.content1 = content;
	}


	public String getContent1() {
		return content1;
	}


	public void setContent1(String content1) {
		this.content1 = content1;
	}


	public String getContent2() {
		return content2;
	}


	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public String getFamousWords() {
		return famousWords;
	}


	public void setFamousWords(String famousWords) {
		this.famousWords = famousWords;
	}


	public String getFamousPerson() {
		return famousPerson;
	}


	public void setFamousPerson(String famousPerson) {
		this.famousPerson = famousPerson;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public boolean getIsImportant() {
		return isImportant;
	}

	public void setIsImportant(boolean isImportant) {
		this.isImportant = isImportant;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public long getIsSeen() {
		return isSeen;
	}

	public void setIsSeen(long isSeen) {
		this.isSeen = isSeen;
	}



	public java.sql.Date getTime() {
		return time;
	}


	public void setTime(java.sql.Date time) {
		this.time = time;
	}


	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public void setImportant(boolean isImportant) {
		this.isImportant = isImportant;
	}

	public List<Category> getCategoryOnFrontPage() {
		return categoryOnFrontPage;
	}

	public void setCategoryOnFrontPage(List<Category> categoryOnFrontPage) {
		this.categoryOnFrontPage = categoryOnFrontPage;
	}

	public List<Tag> getTag() {
		return tag;
	}

	public void setTag(List<Tag> tag) {
		this.tag = tag;
	}

	public String getImageBlog() {
		return imageBlog;
	}

	public void setImageBlog(String imageBlog) {
		this.imageBlog = imageBlog;
	}

	public String getImageFollowing() {
		return imageFollowing;
	}

	public void setImageFollowing(String imageFollowing) {
		this.imageFollowing = imageFollowing;
	}



	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}
	
	 public int getCommentCount() {
	        return comments.size(); 
	    }

	 
//	public String getFormattedDate() {  // za prikaz "2manths ago"
//		 if (time != null) {
//	            // Formatiranje datuma koristeći željeni format
//			 LocalDateTime dateTime = this.time.toLocalDate().atStartOfDay(); // pretvaranje u LocalDateTime
//	            return DateUtils.getRelativeTime(dateTime);
//	        }
//	        return null;
//	}


public String getFormattedDate() { //ovo ne valja , pokazuje 14 sati manje
    if (time != null) {
        // Convert java.sql.Date to LocalDate
        LocalDate localDate = this.time.toLocalDate();
        // Convert LocalDate to LocalDateTime at the start of the day
        LocalDateTime localDateTime = localDate.atStartOfDay();
        // Convert LocalDateTime to ZonedDateTime in the Europe/Belgrade time zone
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Europe/Belgrade"));

        // Get current date-time in the Europe/Belgrade time zone
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Belgrade"));

        // Calculate the duration between the given date and now
        Duration duration = Duration.between(zonedDateTime, now);

        if (duration.toDays() > 0) {
            return duration.toDays() + " days ago";
        } else if (duration.toHours() > 0) {
            return duration.toHours() + " hours ago";
        } else if (duration.toMinutes() > 0) {
            return duration.toMinutes() + " minutes ago";
        } else {
            return duration.getSeconds() + " seconds ago";
        }
    }
    return null;
}
	 
	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}

	   // Metoda za formatirani datum u "dd.MM | yyyy" format
    @Transient
    public String getFormattedDateForDisplay() {
        if (time != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM | yyyy");
            sdf.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade")); // Postavljanje vremenske zone
            return sdf.format(new Date(time.getTime()));
        }
        return "";
    }

    @PrePersist
    protected void onCreate() {
    	 // Postavljanje trenutnog datuma u CET (Central European Time)
        LocalDate localDate = LocalDate.now(ZoneId.of("Europe/Belgrade"));
        this.time = java.sql.Date.valueOf(localDate);// Konverzija LocalDate u java.sql.Date
    }
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return '('+id+')'+title;
	}


		  public String[] splitContent(String content, int position) {
		        if (content == null || content.length() <= position) {
		            return new String[]{content, ""};
		        }

	        // Find the last space before the cutoff point
	        int lastSpace = content2.lastIndexOf(' ', position);
	        
	        if (lastSpace == -1) {
	            return new String[]{content2.substring(0, position), content2.substring(position)};
	        }

	        // Split the content into two parts
	        String part1 = content2.substring(0, lastSpace);
	        String part2 = content2.substring(lastSpace + 1);
	        return new String[]{part1, part2};
	    }
	
		  // Metoda za dobijanje prvih N reči iz sadržaja
		    public String getFirstThreeWords(String content) {
		        if (content == null || content.trim().isEmpty()) {
		            return "";
		        }
		        
		        String[] words = content.split("\\s+"); // Razdvoji tekst na reči
		        StringBuilder firstThreeWords = new StringBuilder();
		        
		        for (int i = 0; i < Math.min(words.length, 3); i++) {
		            firstThreeWords.append(words[i]).append(" ");
		        }
		        
		        return firstThreeWords.toString().trim(); // Vrati prve tri reči kao jedan string
		    }
		  
}
