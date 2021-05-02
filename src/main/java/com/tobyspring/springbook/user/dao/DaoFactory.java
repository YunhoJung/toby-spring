package com.tobyspring.springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1.4 제어의 역전(IoC)
 * - 1.4.1 오브젝트 팩토리
 * - 1.4.2 오브젝트 팩토리의 활용 : ConnectionMaker의 구현 클래스를 결정하고 오브젝트를 만드는 코드 분리
 * 1.5 스프링의 IoC
 * - 1.5.1 오브젝트 팩토리를 이용한 스프링 IoC
 * 
 * @author Yunho Jung
 * @since 2021.04.30
 *
 */
@Configuration
public class DaoFactory {
	
	@Bean
	public UserDao userDao() {
		return new UserDao(connectionMaker());
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}

}
