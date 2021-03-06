package org.wei.spring.jdbc.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.wei.spring.jdbc.configuration.DevAppConfigurationUsingSpringJpa;
import org.wei.spring.jdbc.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(
	    loader=AnnotationConfigContextLoader.class,
	    classes={
	    	DevAppConfigurationUsingSpringJpa.class
	    })
@ActiveProfiles("devJpa")
public class UserSpringJpaDaoUsingJavaConfigTest {
	
	@Autowired
	@Qualifier("userSpringJpaDao")
	private IUserDao userDAO;

	@Test
	public void testSelectCount() {
		int count = userDAO.getCount();
		assertEquals(3,  count);
	}
	
	@Test
	public void testSelectUserByPin() {
		User user = userDAO.selectUserByPin(101);	
		assertEquals("Address 1", user.getAddress());
	}
	
	@Test
	public void testSelectAllUsers() {
		List<User> users = userDAO.selectAllUsers();
		assertEquals(3, users.size());
		assertEquals(102, users.get(1).getPin());
	}
	
	@Test
	public void testUpdateUserByPin() {
		int i = userDAO.updateUserName(102,  "New User");
		assertEquals(1,  i);
		
		User user = userDAO.selectUserByPin(102);
		assertEquals("New User",  user.getName());
	}
	
	@Test
	public void testInsertUser() {
		int count = userDAO.getCount();
		assertEquals(3, count);
		
		User user = new User();
		user.setName("Add a new user");
		user.setPin(110);
		userDAO.insertUser(user);
		
		count = userDAO.getCount();
		assertEquals(4, count);
	
		userDAO.deleteUser(user.getId());
	}
}
