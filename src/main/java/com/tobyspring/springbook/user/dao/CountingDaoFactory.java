package com.tobyspring.springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1.7 의존관계 주입(DI)
 * - 1.7.4 의존관계 주입의 응용
 * 
 * @author Yunho Jung
 * @since 2021.05.03
 *
 */
@Configuration
public class CountingDaoFactory {
	
	@Bean
	public UserDao userDao() {
		return new UserDao(connectionMaker());
	}
	
	@Bean
	public ConnectionMaker connectionMaker() {
		return new CountingConnectionMaker(realConnectionMaker());
	}
	
	@Bean
	public ConnectionMaker realConnectionMaker() {
		return new DConnectionMaker();
	}

}
