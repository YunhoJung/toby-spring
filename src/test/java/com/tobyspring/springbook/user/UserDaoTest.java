package com.tobyspring.springbook.user;

import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.tobyspring.springbook.user.dao.UserDao;
import com.tobyspring.springbook.user.domain.User;

/**
 * 2.2 UserDaoTest 개선
 * - 2.2.2 테스트의 효율적인 수행과 결과 관리
 * 
 * @author Yunho Jung
 * @since 2021.06.07
 *
 */
public class UserDaoTest {
	
	@Test
	public void addAndGet() throws SQLException {
		ApplicationContext context = new GenericXmlApplicationContext(
				"applicationContext.xml");
		
		UserDao userDao = context.getBean("userDao", UserDao.class);
		User user = new User();
		user.setId("yunhojung");
		user.setName("정윤호");
		user.setPassword("1234");
		
		userDao.add(user);
		
		User user2 = userDao.get(user.getId());
		
		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
	}

}
