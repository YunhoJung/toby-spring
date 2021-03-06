package com.tobyspring.springbook.user.test;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.tobyspring.springbook.user.dao.ConnectionMaker;
import com.tobyspring.springbook.user.dao.DConnectionMaker;
import com.tobyspring.springbook.user.dao.DaoFactory;
import com.tobyspring.springbook.user.dao.UserDao;
import com.tobyspring.springbook.user.domain.User;

/**
 * 1.3 DAO 확장
 * - 1.3.3 관계설정 책임의 분리 : UserDao가 ConnectionMaker 구현 클래스의 오브젝트를 외부에서 주입받게 함으로써 의존 관계 제거
 * 1.4 제어의 역전(IoC)
 * - 1.4.1 오브젝트 팩토리
 * - 1.4.2 오브젝트 팩토리의 활용 : ConnectionMaker의 구현 클래스를 결정하고 오브젝트를 만드는 코드 분리
 * 1.5 스프링의 IoC
 * - 1.5.1 오브젝트 팩토리를 이용한 스프링 IoC
 * 1.8 XML을 이용한 설정
 * - 1.8.2 XML을 이용하는 애플리케이션 컨텍스트
 * - 1.8.4 프로퍼티 값의 주입
 * 2.2 UserDaoTest 개선
 * - 2.2.1 테스트 검증의 자동화
 * 
 * @author Yunho Jung
 * @since 2021.04.29
 *
 */
public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		UserDao userDao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("yunhojung");
		user.setName("정윤호");
		user.setPassword("1234");

		userDao.add(user);

		System.out.println(user.getId() + "등록 성공");

		User user2 = new User();
		user2 = userDao.get(user.getId());
		
		if(!user.getName().equals(user2.getName())) {
			System.out.println("테스트 실패 (name)");
		} else if (!user.getPassword().equals(user2.getPassword())) {
			System.out.println("테스트 실패 (password)");
		} else {
			System.out.println("조회 테스트 성공");
		}
	}

}
