package com.tobyspring.springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tobyspring.springbook.user.domain.User;

/**
 * 1.1 초난감 DAO
 * 1.2 DAO의 분리
 * - 중복되는 코드 부분 메소드 추출 : Connection을 가져오는 중복 코드 부분 분리
 * - DB Connection 만들기 독립, 상속을 통한 확장
 * 1.3 DAO의 확장
 * - 1.3.1 클래스의 분리 : 두 개의 관심사를 독립시키면서 확장하는 방법
 * 
 * @author Yunho Jung
 * @since 2021.04.21
 * 
 */
public class UserDao {
	private SimpleConnectionMaker simpleConnectionMaker;
	
	public UserDao() {
		simpleConnectionMaker = new SimpleConnectionMaker(); 
	}

	/**
	 * 
	 * @param user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection conn = simpleConnectionMaker.makeNewConnection();
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
	 * @return User
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection conn = simpleConnectionMaker.makeNewConnection();
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
	 * 중복되는 코드 부분 메소드 추출 : Connection을 가져오는 중복 코드 부분 분리
	 * 
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
//	private Connection getConnection() throws ClassNotFoundException, SQLException {
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/springbook", "Spring", "book");
//
//		return conn
//
//	}
//	public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		UserDao userDao = new UserDao();
//
//		User user = new User();
//		user.setId("yunhojung");
//		user.setName("정윤호");
//		user.setPassword("1234");
//
//		userDao.add(user);
//
//		System.out.println(user.getId() + "등록 성공");
//
//		User user2 = userDao.get(user.getId());
//		System.out.println(user2.getName());
//		System.out.println(user2.getPassword());
//
//		System.out.println(user2.getId() + "조회 성공");
//
//	}

}
