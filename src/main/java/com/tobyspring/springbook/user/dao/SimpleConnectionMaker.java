package com.tobyspring.springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 1.3 DAO의 확장
 * - 1.3.1 클래스의 분리 : 두 개의 관심사를 독립시키면서 확장하는 방법
 * 
 * @author Yunho Jung
 * @since 2021.04.26
 *
 */
public class SimpleConnectionMaker {

	/**
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/springbook", "spring", "book");

		return conn;
	}

}
