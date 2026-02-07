package cubes.main.entity;

import java.util.List;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users {
	
	@Id
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String name;
	@Column
	private String image;
	@Column
	private String email;
	@Column
	private String telephone;
	@Column
	private boolean enabled;
	
	@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinTable(name="authorities", joinColumns = @JoinColumn(name="username"),inverseJoinColumns = @JoinColumn(name="authority"))
	private List<Roles> authorities;

	
	public Users() {}
	
	
	public Users(String username, String image, String telephone) {
		super();
		this.username = username;
		this.image = image;
		this.telephone = telephone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	
	public List<Roles> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Roles> authorities) {
		this.authorities = authorities;
	}

	public Boolean isAdmin() {
	    
	 	List<Roles> roles = getAuthorities();
	
		for(Roles r : roles) {
			if(r.getAuthority().equals("ROLE_admin")) {
				return true;
			}
			 
		}
		 
	return false;

	}

	@Override
	public String toString() {
		
		return username;
	}
	}
