package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.user.dto.ReplyVo;
import com.user.util.DBManager;

public class replyDao {
	private replyDao() {

	}

	private static replyDao instance = new replyDao();

	public static replyDao getInstance() {
		return instance;
	}
	
	public int insertreply(ReplyVo rVo) {

		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null; 

		String sql = "INSERT INTO reply(code, comment_code ,id, content, reg_date) "
				+ "VALUES(?, reply_seq.nextval, ?, ?, sysdate)";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rVo.getCode());
			pstmt.setString(2, rVo.getId());
			pstmt.setString(3, rVo.getContent());

			
			result = pstmt.executeUpdate(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	public List<ReplyVo> getreplyList(String code) {
		String sql = "select * from reply where code=" +code;
		ReplyVo rVo = null;
		List<ReplyVo> list = new ArrayList<ReplyVo>(); // List 컬렉션 객체 생성

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();

			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);

			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				rVo = new ReplyVo();
				
				rVo.setCode(rs.getInt("code"));
				rVo.setComment_code(rs.getInt("comment_code"));
				rVo.setId(rs.getString("id"));
				rVo.setContent(rs.getString("content"));
				rVo.setReg_date(rs.getDate("reg_date"));
				list.add(rVo); // list 객체에 데이터 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	public int deletereply(String comment_code) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql_delete = "delete from reply where comment_code=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql_delete);
			pstmt.setString(1, comment_code);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	public int updatereply(String id, String content, String comment_code) {

		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql_update = "update reply set id = ?, content = ?" + "where comment_code = ?";
		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql_update);
			pstmt.setString(1, id);
			pstmt.setString(2, content);
			pstmt.setString(3, comment_code);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return result;
	}
	public ReplyVo getreply(String comment_code) {
		String sql = "select * from reply where comment_code= " +comment_code;
		ReplyVo rVo = null;
		

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();

			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);

			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				rVo = new ReplyVo();
				
				rVo.setCode(rs.getInt("code"));
				rVo.setComment_code(rs.getInt("comment_code"));
				rVo.setId(rs.getString("id"));
				rVo.setContent(rs.getString("content"));
				rVo.setReg_date(rs.getDate("reg_date"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return rVo;
	}
}
