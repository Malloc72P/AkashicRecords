package dbConnector;

import java.util.*;

import dbConnector.DBConnectionMgr;
import dbConnector.postDTO;

import java.sql.*;

public class postDAO {

	private DBConnectionMgr pool = null;
	
	public postDAO() {
		System.out.println("\n___________________________________");
		try {
			pool = DBConnectionMgr.getInstance();
			System.out.println("postDAO Constructor >>> " + pool);
		} catch (Exception e) {
			System.out.println("postDAO Constructor >>> "+"DBConnection 인스턴스 획득 실패");
		}
		System.out.println("___________________________________\n");
	}
	public int getArticleCount() {
		System.out.println("\n___________________________________");
		Connection connDB = null;
		PreparedStatement queryOBJ = null;
		ResultSet queryRES = null;
		int x = 0;// 갯수

		try {
			connDB = pool.getConnection();
			System.out.println("connDB=" + connDB);
			String sql = "select count(*) from aka_board";// select count(*) from member
			queryOBJ = connDB.prepareStatement(sql);
			queryRES = queryOBJ.executeQuery();
			if (queryRES.next()) {
				x = queryRES.getInt(1);// 필드로 불러오는경우 ->필드명,필드X,
									// 그룹함수->select ~ from 인덱스번호 순서로 불러옴
			}
		} catch (Exception e) {
			System.err.println("||||||||||||||||||||||||||||||||\n");
			System.err.println("getArticleCount-catch>>> 에러 발생");
			e.printStackTrace();
			System.err.println("||||||||||||||||||||||||||||||||\n");
		} finally {
			System.out.println("getArticleCount-finally>>> connection 해제 실시");
			pool.freeConnection(connDB, queryOBJ, queryRES);
			System.out.println("connection 해제 완료");
		}
		System.out.println("___________________________________\n");
		return x;
	}//getArticleCount
	
	public List getArticles(int start, int end) {
		System.out.println("\n___________________________________");
		Connection connDB = null;
		PreparedStatement queryOBJ = null;
		ResultSet queryRES = null;
		// 추가
		List articleList = null;// 갯수 //ArrayList articleList=null;

		try {
			connDB = pool.getConnection();
			System.out.println("connDB=" + connDB);
			// sql="select * from member order by name limit ?,?";
			String sql = "select * from aka_board order by num desc limit ?,?";
			queryOBJ = connDB.prepareStatement(sql);
			queryOBJ.setInt(1, start - 1);// mysql이 레코드순번은 내부적으로 0부터 시작
			queryOBJ.setInt(2, end);
			queryRES = queryOBJ.executeQuery();
			if (queryRES.next()) { // 화면에 출력할 데이터가 존재한다면
				// articleList=new List(); X
				// articleList=new 자식클래스명();
				articleList = new ArrayList(end);// end갯수만큼 메모리영역을 사용
				do {
					// 먼저 담는 로직을 사용
					postDTO article = new postDTO();
					article.setNum(queryRES.getInt("num"));
					article.setSeries(queryRES.getString("series"));
					article.setSubject(queryRES.getString("subject"));
					article.setContent(queryRES.getString("content"));
					article.setPost_img(queryRES.getString("post_img"));
					article.setReg_date(queryRES.getTimestamp("reg_date"));
					article.setReadcount(queryRES.getInt("readcount"));
					// 글쓴이의 ip주소->request.getRemoteAddr();
					// 추가
					articleList.add(article);
				} while (queryRES.next());
			}
		} catch (Exception e) {
			System.err.println("||||||||||||||||||||||||||||||||\n");
			System.err.println("getArticles-catch>>> 에러 발생");
			e.printStackTrace();
			System.err.println("||||||||||||||||||||||||||||||||\n");
		} finally {
			System.out.println("getArticles-finally>>> connection 해제 실시");
			pool.freeConnection(connDB, queryOBJ, queryRES);
			System.out.println("connection 해제 완료");
		}
		System.out.println("___________________________________\n");
		return articleList;
	}//getArticles
	
	public int insertArticle(postDTO article) {
		System.out.println("\n___________________________________");
		Connection connDB = null;
		PreparedStatement queryOBJ = null;
		ResultSet queryRES = null;// num의 최대값을 구해온다. select max(num) from board
		String sql = "";// SQL구문 저장할 변수
		// ---------------------------------------------------------------------------------

		try {
			connDB = pool.getConnection();
			sql = "insert into board(series,subject,content,post_img,reg_date)values(?,?,?,?,?)";
			queryOBJ = connDB.prepareStatement(sql);
			queryOBJ.setString(1, article.getSeries());
			queryOBJ.setString(2, article.getSubject());
			queryOBJ.setString(3, article.getContent());
			queryOBJ.setString(4, article.getPost_img());
			queryOBJ.setTimestamp(5, article.getReg_date());
			int insertChecker = queryOBJ.executeUpdate();
			System.out.println("insertArticle-query-end >>> " + insertChecker);
			System.out.println("___________________________________\n");
			return insertChecker;
		} catch (Exception e) {
			System.err.println("||||||||||||||||||||||||||||||||\n");
			System.err.println("insertArticle-catch>>> 에러 발생");
			e.printStackTrace();
			System.err.println("||||||||||||||||||||||||||||||||\n");
			return 0;
		} finally {
			System.out.println("insertArticle-finally>>> connection 해제 실시");
			pool.freeConnection(connDB, queryOBJ, queryRES);
			System.out.println("connection 해제 완료");
		}
	}//insertArticle
	
}
