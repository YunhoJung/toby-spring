package com.tobyspring.springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 1.2 DAO의 분리
 * - 중복되는 코드 부분 메소드 추출 : Connection을 가져오는 중복 코드 부분 분리
 * - DB Connection 만들기 독립, 상속을 통한 확장
 * 
 * @author Yunho Jung
 * @since 2021.04.24
 */
//public class DUserDao extends UserDao{

//	@Override
//	public Connection getConnection() throws ClassNotFoundException, SQLException {
//		// TODO N사 DB Connection 생성코드 작성
//		return null;
//	}

//}
