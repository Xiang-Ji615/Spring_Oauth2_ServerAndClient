package main.java.spring.boot.oauth2.bo;

import java.util.List;

import main.java.spring.boot.oauth2.model.User;


public interface IUserBo {
	void saveOrUpateUser(User user);
	void deleteUser(User user);
	List<User> listUsers();
	User findByUsername(String username);
}
