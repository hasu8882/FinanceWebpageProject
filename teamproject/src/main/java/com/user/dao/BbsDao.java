package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import com.user.dto.BbsVo;
import com.user.util.DBManager;

public class BbsDao {

	private BbsDao() {

	}

	private static BbsDao instance = new BbsDao();

	public static BbsDao getInstance() {
		return instance;
	}

	// 싱글톤

//		게시물 등록
//		입력값 : 전체 게시물 정보
//		반환값 : 쿼리 수행 결과
	public int insertbbs(BbsVo bVo) {

		int result = -1;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null; // 동적 쿼리

		String sql = "INSERT INTO bbs(code, id, title, pictureurl, content, reg_date) "
				+ "VALUES(bbs_seq.nextval, ?, ?, ?, ?, sysdate)";

		try {
			conn = DBManager.getConnection();

			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bVo.getId());
			pstmt.setString(2, bVo.getTitle());
			pstmt.setString(3, bVo.getPictureurl());
			pstmt.setString(4, bVo.getContent());

			// (4 단계) SQL문 실행 및 결과 처리
			result = pstmt.executeUpdate(); // 쿼리 수행
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}

//		게시물 목록 조회
//		반환값 : 게시물 데이터 정보
	public List<BbsVo> selectAllBbs() {
		String sql = "SELECT * FROM bbs order by code desc";
		BbsVo bVo = null;
		List<BbsVo> list = new ArrayList<BbsVo>(); // List 컬렉션 객체 생성

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();

			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			while (rs.next()) {
				bVo = new BbsVo();
				// 디비로부터 회원 정보 획득
				bVo.setCode(rs.getInt("code"));
				bVo.setId(rs.getString("id"));
				bVo.setTitle(rs.getString("title"));
				bVo.setPictureurl(rs.getString("pictureurl"));
				bVo.setContent(rs.getString("content"));
				bVo.setReg_date(rs.getDate("reg_date"));
				list.add(bVo); // list 객체에 데이터 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

//		한 페이지에 10개의 게시물 표시
//		게시판 하단에 페이지 표시

	// 게시글 검색
	public List<BbsVo> getBbsList() {
		return getBbsList("title", "", 1);
	}

	// 페이지 별 리스트 표시
	public List<BbsVo> getBbsList(int page) {
		return getBbsList("title", "", page);
	}

	// 검색 기능과 페이징을 구현하여 게시물 리스트 획득
	public List<BbsVo> getBbsList(String column, String keyword, int page) {
		String sql = "SELECT * FROM (" + "SELECT ROWNUM N, b.* " + "FROM (SELECT * FROM bbs where " + column
				+ " like ? order by code desc) b" + ") " + "WHERE   N BETWEEN ? AND ?";
//			첫번째 ? => 1, 11, 21, 31, 41, => an = 1+(page-1)*10 
//			등차수열의 n에 대한 식은 첫째항 A 공차가 B인 경우 => A + B(n-1)
//			두번째 ? => 10, 20, 30, 40 => page*10

		BbsVo bVo = null;
		List<BbsVo> list = new ArrayList<BbsVo>(); // List 컬렉션 객체 생성

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();

			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%"); // keyword
			pstmt.setInt(2, 1 + (page - 1) * 5); // startNum 1, 6, 11, 16, ...
			pstmt.setInt(3, page * 5); // 현재 페이지에서 표시할 마지막 번호 5, 10, 15, ...

			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			while (rs.next()) {
				bVo = new BbsVo();
				// 디비로부터 회원 정보 획득
				bVo.setCode(rs.getInt("code"));
				bVo.setId(rs.getString("id"));
				bVo.setTitle(rs.getString("title"));
				bVo.setPictureurl(rs.getString("pictureurl"));
				bVo.setContent(rs.getString("content"));
				bVo.setReg_date(rs.getDate("reg_date"));
				System.out.println(bVo);

				list.add(bVo); // list 객체에 데이터 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;
	}

	// 전체 게시물 수 조회
	public int getBbsCount() {
		return getBbsCount("title", "");
	}

	// 특정 컬럼의 키워드를 통해 전체 게시물 수 조회
	public int getBbsCount(String column, String keyword) {
		int count = 0;
		String sql = "SELECT COUNT(code) count FROM (" + "SELECT ROWNUM N, b.* " + "FROM (SELECT * FROM bbs where "
				+ column + " like ? order by code desc) b" + ") ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();

			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");

			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return count;
	}

	// 게시물 번호로 특정 게시물 다음 게시물 데이터 조회
	public BbsVo getNextBbs(int code) {
		BbsVo bVo = null;
		return bVo;
	}

	// 게시물 번호로 특정 게시물 이전 게시물 데이터 조회
	public BbsVo getPrevBbs(int code) {
		BbsVo bVo = null;
		return bVo;
	}

	

	public BbsVo selectbbsByCode(String code) {
		BbsVo bVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql_selectByCode = "select * from bbs where code=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql_selectByCode);

			pstmt.setString(1, code);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bVo = new BbsVo();

				bVo.setCode(rs.getInt("code"));
				bVo.setId(rs.getString("id"));
				bVo.setTitle(rs.getString("title"));
				bVo.setPictureurl(rs.getString("pictureurl"));
				bVo.setContent(rs.getString("content"));
				bVo.setReg_date(rs.getDate("reg_date"));
				System.out.println(bVo);

				
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return bVo;
	}

	public int updatebbs(BbsVo bVo) {

		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql_update = "update bbs set id = ?, title = ?, pictureurl   = ?, content = ?" + "where code = ?";
		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql_update);
			pstmt.setString(1, bVo.getId());
			pstmt.setString(2, bVo.getTitle());
			pstmt.setString(3, bVo.getPictureurl());
			pstmt.setString(4, bVo.getContent());
			pstmt.setInt(5, bVo.getCode());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return result;
	}

	public int deletebbs(String code) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql_delete = "delete from bbs where code=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql_delete);
			pstmt.setString(1, code);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}

	

}