package cubes.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Contact {  
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column

	@javax.validation.constraints.NotEmpty(message="required")
	private String name;
	@Column
	private String email;
	@Column
	@NotNull
	@NotEmpty(message = "required")
	private String myMessage;
	@Column
	private boolean isSeen;
	
	public Contact() {}
	
	public Contact(String name, String email, String myMessage) {
		super();
		this.name = name;
		this.email = email;
		this.myMessage = myMessage;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMyMessage() {
		return myMessage;
	}

	public void setMyMessage(String myMessage) {
		this.myMessage = myMessage;
	}
	

	public boolean getIsSeen() {
		return isSeen;
	}

	public void setIsSeen(boolean isSeen) {
		this.isSeen = isSeen;
	}

	@Override
	public String toString() {
		
		return '('+id+')'+name;
	}
}
