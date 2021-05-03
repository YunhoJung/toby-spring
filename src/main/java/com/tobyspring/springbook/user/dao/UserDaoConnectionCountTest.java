package com.tobyspring.springbook.user.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 1.7 의존관계 주입(DI)
 * - 1.7.4 의존관계 주입의 응용
 * 
 * @author Yunho Jung
 * @since 2021.05.03
 *
 */
public class UserDaoConnectionCountTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
		UserDao userDao = context.getBean("userDao", UserDao.class);
		
		//
		// DAO 사용 코드
		//
		CountingConnectionMaker connectionMaker = context.getBean("connectionMaker", CountingConnectionMaker.class);
		System.out.println("Connection counter : " + connectionMaker.getCounter());
	}

}
