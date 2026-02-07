package cubes.main.dao;

import java.util.List;


import cubes.main.entity.Roles;

public interface RoleDAO {
	
	public List<Roles> getRoleList();
	public void saveRole(Roles role);
	public void deleteRole(int id);
	public Roles getRoleByAuthority(String authority);
	

}
