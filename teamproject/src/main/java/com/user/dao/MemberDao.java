package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.util.DBManager;
import com.user.dto.BbsVo;
import com.user.dto.MemberVo;

public class MemberDao {
	// 필드
	private static MemberDao instance = new MemberDao();

	// 생성자
	private MemberDao() {

	}

	// 메소드

	public static MemberDao getInstance() {
		return instance;
	}

	// 로그인시 사용할 메서드
	public int checkUser(String userid, String pwd) {

		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select pw from members where id= ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			// 4 퀴리문 실행 결과 처리 execute -> 조회
			rs = pstmt.executeQuery();
			// rs.next 대음 행을 확인 rs.getting 컬럼명
			if (rs.next()) {
				if (rs.getString("pw") != null && rs.getString("pw").equals(pwd)) {
					result = 1;// 암호 일치
				} else {
					result = 0;// 암호 불일치
				}
			} else {
				result = -1;// userid없음
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);

		}
		return result;
	}

	// 회원 가입 db에 회원 정보를 삽입
	public int insertMember(MemberVo mVo) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql_insert = "insert into members values(?,?,?,?,?,?,?,?,?,?)";

		try {
			conn = DBManager.getConnection();
			// 3 statement 객체 생성
			pstmt = conn.prepareStatement(sql_insert);
			pstmt.setString(1, mVo.getName());
			pstmt.setString(2, mVo.getId());
			pstmt.setString(3, mVo.getPw());
			pstmt.setInt(4, mVo.getPay());
			pstmt.setString(5, mVo.getPhone1());
			pstmt.setString(6, mVo.getPhone2());
			pstmt.setString(7, mVo.getPhone3());
			pstmt.setString(8, mVo.getEmail());
			pstmt.setInt(9, mVo.getGender());
			pstmt.setInt(10, 1);

			// 4 sql 문 실행및 결과처리

			// executeUpdate -> insert/update/delete가능
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return result;
	}

	public MemberVo getMember(String userid) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql_get = "select * from members where id= ?";
		MemberVo mVo = null;
		try {
			conn = DBManager.getConnection();
			// 3 statement 객체 생성
			pstmt = conn.prepareStatement(sql_get);

			pstmt.setString(1, userid);

			// 4 sql 문 실행및 결과처리

			// executeUpdate -> insert/update/delete가능
			rs = pstmt.executeQuery();

			if (rs.next()) {
				mVo = new MemberVo();
				mVo.setName(rs.getString("name"));
				mVo.setId(rs.getString("id"));
				mVo.setPw(rs.getString("pw"));
				mVo.setPay(rs.getInt("pay"));
				mVo.setEmail(rs.getString("email"));
				mVo.setPhone1(rs.getString("phone1"));
				mVo.setPhone2(rs.getString("phone2"));
				mVo.setPhone3(rs.getString("phone3"));
				mVo.setAdmin(rs.getInt("admin"));

			} else {
				result = -1;// db에 id없음
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return mVo;
	}

	public int UpdateMember(MemberVo mVo) {

		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql_update = "update members set pw = ?,pay = ?, phone1 = ?, phone2 = ?, phone3 = ?, email = ?" + "where id = ?";
		try {
			conn = DBManager.getConnection();
			// 3 statement 객체 생성
			pstmt = conn.prepareStatement(sql_update);
			pstmt.setString(1, mVo.getPw());
			pstmt.setInt(2, mVo.getPay());
			pstmt.setString(3, mVo.getPhone1());
			pstmt.setString(4, mVo.getPhone2());
			pstmt.setString(5, mVo.getPhone3());
			pstmt.setString(6, mVo.getEmail());
			pstmt.setString(7, mVo.getId());
			;

			// 4 sql 문 실행및 결과처리

			// executeUpdate -> insert/update/delete가능
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return result;
	}

	public int DeleteMember(MemberVo mVo) {

		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql_delete = "delete from members where id = ? and pw = ?";
		try {
			conn = DBManager.getConnection();
			// 3 statement 객체 생성
			pstmt = conn.prepareStatement(sql_delete);
			pstmt.setString(1, mVo.getId());
			pstmt.setString(2, mVo.getPw());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return result;
	}

	public int confirmID(String userid) {

		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql_get = "select id from members where id= ?";
		try {
			conn = DBManager.getConnection();
			// 3 statement 객체 생성
			pstmt = conn.prepareStatement(sql_get);

			pstmt.setString(1, userid);

			// 4 sql 문 실행및 결과처리

			// executeUpdate -> insert/update/delete가능
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = 1;// db에 id있음

			} else {
				result = -1;// db에 id없음
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return result;
	}

	public String findpw(String userid, String phone1, String phone2, String phone3, String email) {

		String resultpw = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select pw from members where id= ? and phone1 =? and phone2 =? and  phone3=? and email=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, phone1);
			pstmt.setString(3, phone2);
			pstmt.setString(4, phone3);
			pstmt.setString(5, email);
			// 4 퀴리문 실행 결과 처리 execute -> 조회
			rs = pstmt.executeQuery();
			// rs.next 대음 행을 확인 rs.getting 컬럼명
			if(rs.next()) {
				resultpw = rs.getString("pw");
				}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);

		}
		return resultpw;
	}

	public String findid(String name, String phone1, String phone2, String phone3) {

		String resultid = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select id from members where name= ? and phone1 =? and phone2 =? and  phone3=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone1);
			pstmt.setString(3, phone2);
			pstmt.setString(4, phone3);
			// 4 퀴리문 실행 결과 처리 execute -> 조회
			rs = pstmt.executeQuery();
			// rs.next 대음 행을 확인 rs.getting 컬럼명
			if(rs.next()) {
			resultid = rs.getString("id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);

		}
		return resultid;
	}
	public List<MemberVo> getMemberList() {
		return getMemberList("title", "", 1);
	}
	public List<MemberVo> getMemberList(int page) {
		return getMemberList("title", "", page);
	}
	public List<MemberVo> getMemberList(String column, String keyword, int page) {
		List<MemberVo> list = new ArrayList<MemberVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM (" + "SELECT ROWNUM N, b.* " + "FROM (SELECT * FROM members where " + column
				+ " like ? order by id desc) b" + ") " + "WHERE   N BETWEEN ? AND ?";

	try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, 1+(page-1)*5);		
			pstmt.setInt(3, page*5);		
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MemberVo mVo = new MemberVo();
				mVo.setName(rs.getString("name"));
				mVo.setId(rs.getString("id"));
				mVo.setPw(rs.getString("pw"));
				mVo.setPay(rs.getInt("pay"));
				mVo.setPhone1(rs.getString("phone1"));
				mVo.setPhone2(rs.getString("phone2"));
				mVo.setPhone3(rs.getString("phone3"));
				mVo.setEmail(rs.getString("email"));
				mVo.setGender(rs.getInt("gender"));
				mVo.setAdmin(rs.getInt("admin"));
				
				list.add(mVo); 
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt);
		}
		return list;
		}
		
	public int UpdateaAdmin(String id,String name, int pay, int admin) {

		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql_update = "update members set pay = ?, admin = ? , name=?  where id = ?";
		try {
			conn = DBManager.getConnection();		
			// 3 statement 객체 생성
			pstmt = conn.prepareStatement(sql_update);

			pstmt.setInt(1, pay);
			pstmt.setInt(2, admin);
			pstmt.setString(3, name);
			pstmt.setString(4, id);
		
			// 4 sql 문 실행및 결과처리

			// executeUpdate -> insert/update/delete가능
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return result;
	}

	// 전체 게시물 수 조회
		public int getmemberCount() {
			return getmemberCount("title", "");
		}

		// 특정 컬럼의 키워드를 통해 전체 게시물 수 조회
		public int getmemberCount(String column, String keyword) {
			int count = 0;
			String sql = "SELECT COUNT(pay) count FROM (" + "SELECT ROWNUM N, b.* " + "FROM (SELECT * FROM members where "
					+ column + " like ? order by id desc) b" + ") ";

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
}