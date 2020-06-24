package derek.bookmark.dao;

import derek.bookmark.DataStore;
import derek.bookmark.entities.User;

public class UserDao {
	public User[] getUsers() {
		return DataStore.getUsers();
	}
}
