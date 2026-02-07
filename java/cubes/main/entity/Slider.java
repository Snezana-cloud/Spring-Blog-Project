package cubes.main.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table
public class Slider {
	
	@Id
	@Column
	private int Id;
	@Column
	private String title;
	@Column
	private String link;
	@Column
	private String image;
	@Column
	private String linkTekst;
	@Column
	private int orderOfImage;
	@Column
	private boolean isOnFrontPage;
 
	
	public Slider() {}
	
	public Slider(String image, String title, String link) {
		super();
		this.image = image;
		this.title = title;
		this.link = link;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	public String getLinkTekst() {
		return linkTekst;
	}

	public void setLinkTekst(String linkTekst) {
		this.linkTekst = linkTekst;
	}

	public boolean getIsOnFrontPage() {
		return isOnFrontPage;
	}

	public void setIsOnFrontPage(boolean isOnFrontPage) {
		this.isOnFrontPage = isOnFrontPage;
	}
	

	public int getOrderOfImage() {
		return orderOfImage;
	}

	public void setOrderOfImage(int orderOfImage) {
		this.orderOfImage = orderOfImage;
	}


	@Override
	public String toString() {
		
		return title;
	}

	
}
