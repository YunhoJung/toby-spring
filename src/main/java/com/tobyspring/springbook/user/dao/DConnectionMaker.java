package com.tobyspring.springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 1.3 DAO 확장
 * - 1.3.2 인터페이스 도입 : ConnectionMaker를 인터페이스로 추상화시켜 해당 기능 확장 용이성 확보
 * 
 * @author Yunho Jung
 * @since 2021.04.28
 *
 */
public class DConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}


}
