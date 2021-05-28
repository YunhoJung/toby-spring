package com.tobyspring.springbook.user.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 * 1.4 제어의 역전(IoC)
 * - 1.4.1 오브젝트 팩토리
 * - 1.4.2 오브젝트 팩토리의 활용 : ConnectionMaker의 구현 클래스를 결정하고 오브젝트를 만드는 코드 분리
 * 1.5 스프링의 IoC
 * - 1.5.1 오브젝트 팩토리를 이용한 스프링 IoC
 * 1.7 의존관계 주입(DI)
 * - 1.7.5 메소드를 이용한 의존관계 주입
 * 1.8 XML을 이용한 설정
 * - 1.8.3 DataSource 인터페이스로 변환
 * 
 * @author Yunho Jung
 * @since 2021.04.30
 *
 */
@Configuration
public class DaoFactory {
	
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jbc:mysql://localhost:3306/springbook?serverTimezone=UTC");
		dataSource.setUsername("spring");
		dataSource.setPassword("book");
		
		return dataSource;
	}
	
	@Bean
	public UserDao userDao() {
//		return new UserDao(connectionMaker());
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());
		return userDao;
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}

}
