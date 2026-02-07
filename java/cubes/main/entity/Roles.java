package cubes.main.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table
public class Roles {

	@Id
	@Column
	private String authority;


	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Roles() {}
	
	public Roles(String authority) {
		super();
		this.authority = authority;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  authority;}
	
	
	
}
