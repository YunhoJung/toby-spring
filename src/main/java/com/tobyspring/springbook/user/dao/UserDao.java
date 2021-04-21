package com.tobyspring.springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tobyspring.springbook.user.domain.User;

/**
 * 1.1 초난감 DAO
 * 
 * @author Yunho Jung
 * @since 2021.04.21
 */
public class UserDao {

	/**
	 * 
	 * @param user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void add(User user) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		// 1. getConnection
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/springbook", "Spring", "book");
		// 2. preparedStatement
		PreparedStatement ps = conn.prepareStatement("insert into users(id, name, password) valeus(?, ?, ?)");

		// 3. query
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		// 4. execute
		ps.executeUpdate();
		// ps.close
		ps.close();
		// conn.close
		conn.close();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public User get(String id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		// 1. getConnection
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/springbook", "Spring", "book");
		// 2. preparedStatement
		PreparedStatement ps = conn.prepareStatement("select * from users where id = ?");

		// 3. query
		ps.setString(1, id);

		// 4. execute
		ResultSet rs = ps.executeQuery();

		// 5. getResultSet
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getNString("password"));
		// rs.close
		rs.close();

		// ps.close
		ps.close();
		// conn.close
		conn.close();

		return user;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao userDao = new UserDao();

		User user = new User();
		user.setId("yunhojung");
		user.setName("정윤호");
		user.setPassword("1234");

		userDao.add(user);

		System.out.println(user.getId() + "등록 성공");

		User user2 = userDao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());

		System.out.println(user2.getId() + "조회 성공");

	}

}
