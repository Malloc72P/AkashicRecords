package dbConnector;

import java.sql.*;
import java.util.*;


public class userDAO {
	private DBConnectionMgr connectionPool = null;
	
	public userDAO() {
		// TODO Auto-generated constructor stub
		try {
			System.out.println("DB 커넥션 풀 생성 시도 중......");
			this.connectionPool = DBConnectionMgr.getInstance();
			System.out.println("DB 커넥션 풀 생성 성공......!");
		}catch(Exception err) {
			System.err.println("db 커넥션 풀 생성과정에서 에러가 발생했습니다");
			err.printStackTrace();
		}
	}
	
	/***************************************************************
	 * DTO 무결성 체크
	 ***************************************************************/
	private boolean dtoChecker(userDTO data) {
		if(data == null) {
			System.out.println("dtoChecker>>> data is null");
			return false;
		}
		if(data.getEmail() == null) {
			System.out.println("dtoChecker>>> email is null");
			return false;
		}
		if(data.getPassword() == null) {
			System.out.println("dtoChecker>>> pw is null");
			return false;
		}
		if(data.getNickname() == null) {
			System.out.println("dtoChecker>>> nickname is null");
			return false;
		}
		if(data.getEmail().equals("")) {
			System.out.println("dtoChecker>>> email is empty");
			return false;
		}
		if(data.getPassword().equals("")) {
			System.out.println("dtoChecker>>> pw is empty");
			return false;
		}
		if(data.getNickname().equals("")) {
			System.out.println("dtoChecker>>> nickname is empty");
			return false;
		}
		
		return true;
	}
	/***************************************************************
	 * 일반 쿼리 메서드
	 ***************************************************************/
	public boolean proc_Login(String email, String passwd) {
		boolean loginChecker = false;
		
		Connection        connDB   = null;
		PreparedStatement myQuery  = null;
		ResultSet         queryRes = null;
		try {
			connDB = connectionPool.getConnection();
			String sqlStr = "select * from aka_user where email=?";
			myQuery = connDB.prepareStatement(sqlStr);
			myQuery.setString(1, email);
			queryRes = myQuery.executeQuery();
			if(queryRes.next()) {
				if(queryRes.getString("password").equals(passwd)) {
					System.out.println("dao>>> 로그인 성공");
					loginChecker = true;
				}
				else {
					System.out.println("dao>>> 로그인 실패");
					loginChecker = false;
				}
			}
		}catch(Exception err) {
			err.printStackTrace();
		}
		finally{
			try {
				connectionPool.freeConnection(connDB, myQuery, queryRes);
			}
			catch(Exception err) {
				err.printStackTrace();
			}
		}
		
		return loginChecker;
	}
	/***************************************************************
	 * 입력된 이메일 존재 여부 체크 메서드
	 ***************************************************************/
	public boolean proc_emailChecker(String tgt) {
		boolean loginChecker = false;
		
		Connection        connDB   = null;
		PreparedStatement myQuery  = null;
		ResultSet         queryRes = null;
		int               dupChecker = 0;
		try {
			connDB = connectionPool.getConnection();
			String sqlStr = "select count(*) from aka_user where email=?";
			myQuery = connDB.prepareStatement(sqlStr);
			myQuery.setString(1, tgt);
			queryRes = myQuery.executeQuery();
			if(queryRes.next()) {
				dupChecker = queryRes.getInt(1);
				System.out.println("proc_emailChecker>>> "+dupChecker);
			}
			if(dupChecker == 0) {// 일치하는 이메일 없음, 사용가능
				loginChecker = true;
			}
			else {
				loginChecker = false;
			}
		}catch(Exception err) {
			err.printStackTrace();
		}
		finally{
			try {
				connectionPool.freeConnection(connDB, myQuery, queryRes);
			}
			catch(Exception err) {
				err.printStackTrace();
			}
		}
		
		return loginChecker;
	}
	public boolean proc_nicknameChecker(String tgt) {
		boolean loginChecker = false;
		
		Connection        connDB   = null;
		PreparedStatement myQuery  = null;
		ResultSet         queryRes = null;
		int               dupChecker = 0;
		try {
			connDB = connectionPool.getConnection();
			String sqlStr = "select count(*) from aka_user where nickname=?";
			myQuery = connDB.prepareStatement(sqlStr);
			myQuery.setString(1, tgt);
			queryRes = myQuery.executeQuery();
			if(queryRes.next()) {
				dupChecker = queryRes.getInt(1);
				System.out.println("proc_nicknameChecker>>> "+dupChecker);
			}
			if(dupChecker == 0) {// 일치하는 닉네임 없음, 사용가능
				loginChecker = true;
			}
			else {
				loginChecker = false;
			}
		}catch(Exception err) {
			err.printStackTrace();
		}
		finally{
			try {
				connectionPool.freeConnection(connDB, myQuery, queryRes);
			}
			catch(Exception err) {
				err.printStackTrace();
			}
		}
		
		return loginChecker;
	}
	/***************************************************************
	 * 특정 유저의 데이터를 불러오는 메서드
	 * 유저의  id역할을 하는 email로 식별한다
	 ***************************************************************/
	public userDTO getUserData(String email) {
		userDTO userData = null;
		
		Connection        connDB   = null;
		PreparedStatement myQuery  = null;
		ResultSet         queryRes = null;
		try {
			connDB = connectionPool.getConnection();
			String sqlStr = "select * from aka_user where email=?";
			myQuery = connDB.prepareStatement(sqlStr);
			myQuery.setString(1, email);
			queryRes = myQuery.executeQuery();
			if(queryRes.next()) {
				userData = new userDTO();
				userData.setEmail(queryRes.getString("email"));
				userData.setNickname(queryRes.getString("nickname"));
				userData.setPassword(queryRes.getString("password"));
			}
		}catch(Exception err) {
			err.printStackTrace();
		}
		finally{
			try {
				connectionPool.freeConnection(connDB, myQuery, queryRes);
			}
			catch(Exception err) {
				err.printStackTrace();
			}
		}
		
		return userData;
	}
	/***************************************************************
	 * 유저데이터 입력 메서드
	 * insert into aka_user(email , password , nickname) values('ayana@gmail.com' , '1234' , 'ayana');
	 ***************************************************************/
	public boolean insertUserData(userDTO newUser) {
		boolean insertChecker = false;
		
		Connection        connDB   = null;
		PreparedStatement myQuery  = null;
		int               queryRes = 0;
		if( this.dtoChecker(newUser) == false ) {
			System.out.println("newUser data 무결성 체크 오류");
			return false;
		}
		if( this.proc_emailChecker(newUser.getEmail()) == false ) {
			System.out.println("newUser data 무결성 체크 오류");
			return false;
		}
		if( this.proc_nicknameChecker(newUser.getNickname()) == false ) {
			System.out.println("newUser data 무결성 체크 오류");
			return false;
		}
		try {
			connDB = connectionPool.getConnection();
			String sqlStr = "insert into aka_user(email , password , nickname) values(?,?,?)";
			myQuery = connDB.prepareStatement(sqlStr);
			myQuery.setString(1, newUser.getEmail());
			myQuery.setString(2, newUser.getPassword());
			myQuery.setString(3, newUser.getNickname());
			queryRes = myQuery.executeUpdate();
			if(queryRes != 0) {
				insertChecker = true;
			}
			else {
				insertChecker = false;
			}
		}catch(Exception err) {
			err.printStackTrace();
		}
		finally{
			try {
				connectionPool.freeConnection(connDB, myQuery);
			}
			catch(Exception err) {
				err.printStackTrace();
			}
		}
		
		return insertChecker;
	}
	
	
}