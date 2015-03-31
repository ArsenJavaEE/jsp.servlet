package com.test.dao;

import com.test.model.User;
import com.test.model.Users;

public interface UserDAO {
	
	Users getAllUsers();
	
	void saveUser(User user);

}
