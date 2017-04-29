package main.java.spring.boot.oauth2.dao;

import java.util.List;

import main.java.spring.boot.oauth2.model.User;



public interface IUserDao {
	void saveOrUpateUser(User user);
	void deleteUser(User user);
	List<User> listUsers();
	User findByUsername(String username);
}
