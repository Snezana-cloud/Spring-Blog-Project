package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Contact;

public interface ContactDAO {
	
	public void saveContact(Contact contact);
	public List<Contact> getContactList();
	public long getUnSeenComment();
	public Contact getContact(int id);

}
