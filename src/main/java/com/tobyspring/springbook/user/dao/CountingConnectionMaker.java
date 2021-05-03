package com.tobyspring.springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 1.7 의존관계 주입(DI)
 * - 1.7.4 의존관계 주입의 응용
 * 
 * @author Yunho Jung
 * @since 2021.05.03
 *
 */
public class CountingConnectionMaker implements ConnectionMaker {
	int counter = 0;
	private ConnectionMaker realConnectionMaker;
	
	public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
		this.realConnectionMaker = realConnectionMaker;
	}

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		this.counter++;
		return realConnectionMaker.makeConnection();
	}
	
	public int getCounter() {
		return this.counter;
	}

}
