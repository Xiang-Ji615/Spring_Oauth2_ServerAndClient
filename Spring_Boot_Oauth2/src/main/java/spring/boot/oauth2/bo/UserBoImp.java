package main.java.spring.boot.oauth2.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.spring.boot.oauth2.dao.IUserDao;
import main.java.spring.boot.oauth2.model.User;



@Component
public class UserBoImp implements IUserBo {

	@Autowired
	IUserDao userDao;

	@Override
	public void saveOrUpateUser(User user) {
		userDao.saveOrUpateUser(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	@Override
	public List<User> listUsers() {
		return userDao.listUsers();
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
