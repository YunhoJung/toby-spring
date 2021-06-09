package com.tobyspring.springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.tobyspring.springbook.user.domain.User;

/**
 * 1.1 초난감 DAO
 * 1.2 DAO의 분리
 * - 중복되는 코드 부분 메소드 추출 : Connection을 가져오는 중복 코드 부분 분리
 * - DB Connection 만들기 독립, 상속을 통한 확장
 * 1.3 DAO의 확장
 * - 1.3.1 클래스의 분리 : 두 개의 관심사를 독립시키면서 확장하는 방법
 * - 1.3.2 인터페이스 도입 : ConnectionMaker를 인터페이스로 추상화시켜 해당 기능 확장 용이성 확보
 * - 1.3.3 관계설정 책임의 분리 : UserDao가 ConnectionMaker 구현 클래스의 오브젝트를 외부에서 주입받게 함으로써 의존 관계 제거
 * 1.7 의존관계 주입(DI)
 * - 1.7.5 메소드를 이용한 의존관계 주입
 * 1.8 XML을 이용한 설정
 * - 1.8.3 DataSource 인터페이스로 변환
 * 2.3 개발자를 위한 테스팅 프레임워크 JUnit
 * - 2.3.2 테스트 결과의 일관성
 * 
 * @author Yunho Jung
 * @since 2021.04.21
 * 
 */
public class UserDao {
	private DataSource dataSource;
	

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void add(User user) throws SQLException {
		Connection conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into users(id, name, password) valeus(?, ?, ?)");

		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public User get(String id) throws SQLException {
		Connection conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from users where id = ?");

		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();

		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getNString("password"));
		rs.close();

		ps.close();
		conn.close();

		return user;
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	public void deleteAll() throws SQLException {
		Connection conn = dataSource.getConnection();
		
		PreparedStatement ps = conn.prepareStatement("delete from users");
		ps.executeUpdate();
		
		ps.close();
		conn.close();	
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int getCount() throws SQLException {
		Connection conn = dataSource.getConnection();
		
		PreparedStatement ps = conn.prepareStatement("select count(*) from users;");
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		
		rs.close();
		ps.close();
		conn.close();
		
		return count;
	}

}
