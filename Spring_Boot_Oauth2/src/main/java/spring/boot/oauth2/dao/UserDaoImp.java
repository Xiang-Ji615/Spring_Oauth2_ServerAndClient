package main.java.spring.boot.oauth2.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import main.java.spring.boot.oauth2.model.User;


@Component
public class UserDaoImp implements IUserDao {

	@Autowired
	@Qualifier("sessionFactorySecurity")
	SessionFactory sessionFactory;

	@Override
	public void saveOrUpateUser(User user) {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			tx.setTimeout(5);
			session.saveOrUpdate(user);
			tx.commit();

		} catch (RuntimeException e) {
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
				System.out.println("Couldn’t roll back transaction : " + rbe);
			}
		} finally {
			if (session != null) {
				session.close();

			}
		}
	}

	@Override
	public void deleteUser(User user) {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			tx.setTimeout(5);
			session.delete(user);
			tx.commit();

		} catch (RuntimeException e) {
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
				System.out.println("Couldn’t roll back transaction : " + rbe);
			}
		} finally {
			if (session != null) {
				session.close();

			}
		}
	}

	@Override
	public List<User> listUsers() {
		Session session = null;
		Transaction tx = null;
		List<User> ret = new ArrayList<User>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			tx.setTimeout(5);
			ret = session.createQuery("from User").list();
			tx.commit();

		} catch (RuntimeException e) {
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
				System.out.println("Couldn’t roll back transaction : " + rbe);
			}
		} finally {
			if (session != null) {
				session.close();

			}
		}
		return ret;
	}

	@Override
	public User findByUsername(String username) {
		Session session = null;
		Transaction tx = null;
		List<User> users = new ArrayList<User>();
		User ret = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			tx.setTimeout(5);
			Query q = session.createQuery("from User user JOIN FETCH user.userRole where user.username=? ");
			q.setParameter(0, username);
			users = q.list();
			if (users.size() > 0) {
				return users.get(0);
			} else {
				return null;
			}

		} catch (RuntimeException e) {
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
				System.out.println("Couldn’t roll back transaction : " + rbe);
			}
		} finally {
			if (session != null) {
				session.close();

			}
		}
		return null;
	}

}
