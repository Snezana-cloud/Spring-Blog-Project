package cubes.main.dao;

import java.util.List;




import cubes.main.entity.Roles;
import cubes.main.entity.Users;

public interface UserDAO {
	
	public List<Users> getUserList();
	public void deleteUser(String username);
	public void saveUser(Users user);
	public void enabledUser (String username);
	public Users getUserByUsername(String username);
	public Users getUserByEmail(String email);
	public List<Roles> getRoles();
    public  Users findByResetToken(String token);
	 

}
