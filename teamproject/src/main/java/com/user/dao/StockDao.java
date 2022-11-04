package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.user.dto.AssetVo;
import com.user.dto.DailyAsset;
import com.user.dto.ETFVo;
import com.user.dto.MyAssetVo;
import com.user.dto.StockVo;
import com.user.util.DBManager;

public class StockDao {
	// *** 싱글톤
		// 1. 생성자 private : 객체를 외부에서 만들지 못하도록
		// 2. 객체 생성 private static : 자신이 객체를 생성
		// 3. 객체 제공 기능 getInstance() : 자신의 객체(단지 1개)를 사용할 수 있도록 제공
		private StockDao() {		
		}
		
		private static StockDao instance = new StockDao();
		
		public static StockDao getInstance() {
			return instance;
		}
		
		public int insertTimeSeriesData(DailyAsset dVo) {
			int result = -1;
			Connection conn = null;
			// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
			PreparedStatement pstmt = null;		// 동적 쿼리
			
//			String sql_insert = "insert into company_info values(asset_seq.nextval, ?, ?, ?, ?, ?)";
			
			String sql_insert = "insert into dailyprice values(?, ?, ?,?,?,?,?,?)";

			
			try {
				conn = DBManager.getConnection();
				
				// (3 단계) Statement 객체 생성
				pstmt = conn.prepareStatement(sql_insert);
				
//				pstmt.setInt(1, pVo.getCode());
				pstmt.setDate(1, dVo.getDate());
				pstmt.setString(2, dVo.getCode());			// 정수형
				pstmt.setString(3, dVo.getName());
				pstmt.setInt(4, dVo.getClose());
				pstmt.setInt(5, dVo.getOpen());
				pstmt.setInt(6, dVo.getHigh());
				pstmt.setInt(7, dVo.getLow());
				pstmt.setLong(8, dVo.getTradeVol());
				// 날짜형
				
				// (4 단계) SQL문 실행 및 결과 처리
				// executeUpdate : 삽입(insert/update/delete)
				result = pstmt.executeUpdate();				// 쿼리 수행
			} catch(Exception e) {
				e.printStackTrace();			
			} finally {
				DBManager.close(conn, pstmt);
			}
			return result;
			
		}

		public int insertStock(List<StockVo> stockList) {
			int result = -1;
			Connection conn = null;
			// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
			PreparedStatement pstmt = null;		// 동적 쿼리
			
//			String sql_insert = "insert into company_info values(asset_seq.nextval, ?, ?, ?, ?, ?)";
			
			String sql_insert = "insert into stocks values(?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ? ,?, ?, ?, ?)";

			
			try {
				conn = DBManager.getConnection();
				
				// (3 단계) Statement 객체 생성
				pstmt = conn.prepareStatement(sql_insert);
				
				for (int i=0;i<stockList.size();i++) {
					//oracle DB
//					pstmt.setString(1, stockList.get(i).getCode());
//					pstmt.setString(2, stockList.get(i).getName());
//					pstmt.setInt(3, stockList.get(i).getPrice());
//					pstmt.setDate(4, stockList.get(i).getLast_update());
//					pstmt.setInt(5, stockList.get(i).getRange_day());
//					pstmt.setFloat(6, stockList.get(i).getChange_day());
//					pstmt.setInt(7, stockList.get(i).getEPS());
//					pstmt.setFloat(8, stockList.get(i).getPER());
//					pstmt.setInt(9, stockList.get(i).getFwdEPS());
//					pstmt.setFloat(10, stockList.get(i).getFwdPER());
//					pstmt.setInt(11, stockList.get(i).getBPS());
//					pstmt.setFloat(12, stockList.get(i).getPBR());
//					pstmt.setInt(13, stockList.get(i).getDividend());
//					pstmt.setFloat(14, stockList.get(i).getDividend_yield());
//					pstmt.setString(15, stockList.get(i).getMaketType());
					//mariadB
					pstmt.setString(1, stockList.get(i).getCode());
					pstmt.setString(2, stockList.get(i).getName());
					pstmt.setInt(3, stockList.get(i).getPrice());
					pstmt.setDate(4, stockList.get(i).getLast_update());
					pstmt.setFloat(5, stockList.get(i).getRange_day());
					pstmt.setFloat(6, stockList.get(i).getChange_day());
					pstmt.setInt(7, stockList.get(i).getEPS());
					pstmt.setFloat(8, stockList.get(i).getPER());
					pstmt.setInt(9, stockList.get(i).getFwdEPS());
					pstmt.setFloat(10, stockList.get(i).getFwdPER());
					pstmt.setInt(11, stockList.get(i).getBPS());
					pstmt.setFloat(12, stockList.get(i).getPBR());
					pstmt.setInt(13, stockList.get(i).getDividend());
					pstmt.setFloat(14, stockList.get(i).getDividend_yield());
					pstmt.setString(15, stockList.get(i).getMaketType());
					// (4 단계) SQL문 실행 및 결과 처리
					// executeUpdate : 삽입(insert/update/delete)
					result = pstmt.executeUpdate();	
				}

				// 날짜형
				
							// 쿼리 수행
			} catch(Exception e) {
				e.printStackTrace();			
			} finally {
				DBManager.close(conn, pstmt);
			}
			return result;
		}
		
		public int insertETF(List<ETFVo> etfList) {
			int result = -1;
			Connection conn = null;
			// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
			PreparedStatement pstmt = null;		// 동적 쿼리
			
		
//			String sql_insert = "insert into etf values(etf_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ? ,?, ?, ?, ?, ?, ?, ?, ?)";
			String sql_insert = "insert into etf values(?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ? ,?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				conn = DBManager.getConnection();
				
				// (3 단계) Statement 객체 생성
				pstmt = conn.prepareStatement(sql_insert);
				
				for (int i=1;i<etfList.size();i++) {
					pstmt.setString(1, etfList.get(i).getCode());
					pstmt.setString(2, etfList.get(i).getName());
					pstmt.setInt(3, etfList.get(i).getPrice());
					pstmt.setDate(4, etfList.get(i).getLast_update());
					pstmt.setInt(5, etfList.get(i).getRange_day());
					pstmt.setFloat(6, etfList.get(i).getChange_day());
					pstmt.setDouble(7, etfList.get(i).getNAV());
					pstmt.setInt(8, etfList.get(i).getOpen());
					pstmt.setInt(9, etfList.get(i).getHigh());
					pstmt.setInt(10, etfList.get(i).getLow());
					pstmt.setInt(11, etfList.get(i).getVolume());
					pstmt.setDouble(12, etfList.get(i).getTradeAMT());
					pstmt.setDouble(13, etfList.get(i).getMarketCap());
					pstmt.setDouble(14, etfList.get(i).getNetAsset());
					pstmt.setInt(15, etfList.get(i).getQty());
					pstmt.setString(16, etfList.get(i).getIndexName());
					pstmt.setFloat(17, etfList.get(i).getCloseIndex());
					pstmt.setFloat(18, etfList.get(i).getRangeIndex());
					pstmt.setFloat(19, etfList.get(i).getChangeIndex());
					// (4 단계) SQL문 실행 및 결과 처리
					// executeUpdate : 삽입(insert/update/delete)
					result = pstmt.executeUpdate();	
				}

				// 날짜형
				
							// 쿼리 수행
			} catch(Exception e) {
				e.printStackTrace();			
			} finally {
				DBManager.close(conn, pstmt);
			}
			return result;
		}


		public List<AssetVo> searchAssetbyName(String name){
//			String sql = "select * from etf where name like '"+name+"%' order by price desc";
			String sql = "select t.name, t.code, t.price from "
					+ "(select s.name, s.code, s.price from stocks s UNION ALL select e.name,e.code, e.price from etf e) t "
					+ "where name like ? order by price desc";


			List<AssetVo> list = new ArrayList<AssetVo>();
			Connection conn = null;
//			Statement stmt = null; //정적 쿼리
			ResultSet rs = null; //
			PreparedStatement pstmt=null;
			
			try {			
				//step. 1
//				Class.forName("oracle.jdbc.driver.OracleDriver");
//				//Class.forName("com.mysql.jdbc.Driver"); //MySql
//				//step. 2 database connection object
//				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//				String uid = "ora_user";
//				String pass = "1234";
//				conn = DriverManager.getConnection(url, uid, pass);//DB연결 시도
				conn = DBManager.getConnection();
				//step. 3 statement object
//				stmt = conn.createStatement();//객체 생성
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name + "%");
				rs =pstmt.executeQuery();
				while(rs.next()) {
					
					//rs.getInt('컬럼명')
//					
//					rs.getInt("code");
//					rs.getString("name");
//					rs.getInt("price");
//					rs.getString("pictureurl");
//					rs.getString("description");
//					rs.getDate("reg_date");
					
					AssetVo aVo = new AssetVo();
					
					aVo.setCode(rs.getString("code"));
//					System.out.println("code:"+rs.getInt("code") );
					aVo.setName(rs.getString("name"));
					aVo.setPrice(rs.getInt("price"));
//					aVo.setLast_update(rs.getDate("last_update"));
					list.add(aVo);
					
//					listArr[countList]=pVo;
//					countList++;
				}

			} catch( Exception e){
				e.printStackTrace();
				System.out.println("sql 에러 발생");

			} finally {
				DBManager.close(conn, pstmt);
//				try {//step.  5		
//					pstmt.close();		
//					conn.close();					
	//
//				}catch(SQLException e){
//					e.getMessage();
	//
//				}			

			}
			return list;
		}

		public AssetVo searchByCode(String code) {
			
			String sql = "select * from "
					+ "(select s.name, s.code, s.price, s.last_update from stocks s "
					+ "UNION select e.name,e.code, e.price, e.last_update from etf e) t "
					+ "where code IN(?) order by price desc";
			Connection conn = null;
//			Statement stmt = null; //정적 쿼리
			ResultSet rs = null; //
			PreparedStatement pstmt=null;
			AssetVo aVo = new AssetVo();
			try {			
				//step. 1
//				Class.forName("oracle.jdbc.driver.OracleDriver");
//				//Class.forName("com.mysql.jdbc.Driver"); //MySql
//				//step. 2 database connection object
//				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//				String uid = "ora_user";
//				String pass = "1234";
//				conn = DriverManager.getConnection(url, uid, pass);//DB연결 시도
				conn = DBManager.getConnection();
				//step. 3 statement object
//				stmt = conn.createStatement();//객체 생성
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, code);
				rs =pstmt.executeQuery();
				while(rs.next()) {
					aVo.setName(rs.getString("name"));
					aVo.setCode(rs.getString("code"));
//					System.out.println("code:"+rs.getInt("code") );
					aVo.setPrice(rs.getInt("price"));
					aVo.setLast_update(rs.getDate("last_update"));
					}
				

			} catch( Exception e){
				e.printStackTrace();
				System.out.println("sql 에러 발생");

			} finally {
				DBManager.close(conn, pstmt);
//				try {//step.  5		
//					pstmt.close();		
//					conn.close();					
	//
//				}catch(SQLException e){
//					e.getMessage();
	//
//				}			

			}
			return aVo;
			
		}

		public int addAsset(List<MyAssetVo> mlist) {
			int result = -1;
			//1. sql
			Connection conn = null;
			// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
			PreparedStatement pstmt = null;		// 동적 쿼리
			
		
//			String sql_insert = "insert into etf values(etf_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ? ,?, ?, ?, ?, ?, ?, ?, ?)";
			String sql_insert = "insert into myasset values(?, ?, ?, ?, ?, ?,?)";
			try {
				conn = DBManager.getConnection();
				
				// (3 단계) Statement 객체 생성
				pstmt = conn.prepareStatement(sql_insert);
//				pstmt.setString(1, myAsset.get);
				for (int i=0;i<mlist.size();i++) {
					pstmt.setString(1, mlist.get(i).getUserid());
					pstmt.setString(2, mlist.get(i).getCode());
					pstmt.setString(3, mlist.get(i).getName());
					System.out.println("code: " + mlist.get(i).getCode());
					pstmt.setInt(4, mlist.get(i).getPrice());
					pstmt.setDate(5, mlist.get(i).getLast_update());
					pstmt.setInt(6, mlist.get(i).getQty());
					pstmt.setInt(7, mlist.get(i).getTot_amount());
					result = pstmt.executeUpdate();						
				}
				
				

				// 날짜형
				
							// 쿼리 수행
			} catch(Exception e) {
				e.printStackTrace();			
			} finally {
				DBManager.close(conn, pstmt);
			}
			
			return result;
		}
		
		public List<MyAssetVo> selectMyAsset(){
			List<MyAssetVo> mylist = new ArrayList<MyAssetVo>();
			String sql = "select * from myasset order by userid desc";
			Connection conn = null;
//			Statement stmt = null; //정적 쿼리
			ResultSet rs = null; //
			PreparedStatement pstmt=null;			
			try {			
				//step. 1
//				Class.forName("oracle.jdbc.driver.OracleDriver");
//				//Class.forName("com.mysql.jdbc.Driver"); //MySql
//				//step. 2 database connection object
//				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//				String uid = "ora_user";
//				String pass = "1234";
//				conn = DriverManager.getConnection(url, uid, pass);//DB연결 시도
				conn = DBManager.getConnection();	
				//step. 3 statement object
//				stmt = conn.createStatement();//객체 생성
				pstmt = conn.prepareStatement(sql);
				rs =pstmt.executeQuery();
//				System.out.println("Dao fuction:");
				while(rs.next()) {
					
					
					MyAssetVo mVo = new MyAssetVo();
					
					mVo.setUserid(rs.getString("userid"));
					mVo.setCode(rs.getString("code"));
//					System.out.println("code:"+rs.getInt("code") );
					mVo.setName(rs.getString("name"));
					mVo.setPrice(rs.getInt("price"));
					mVo.setLast_update(rs.getDate("last_update"));
					mVo.setQty(rs.getInt("qty"));
					mVo.setTot_amount();
					mylist.add(mVo);
					
//					listArr[countList]=pVo;
//					countList++;
				}

			} catch( Exception e){
				e.printStackTrace();
				System.out.println("sql 에러 발생");

			} finally {
				DBManager.close(conn, pstmt);
//				try {//step.  5		
//					pstmt.close();		
//					conn.close();					
	//
//				}catch(SQLException e){
//					e.getMessage();
	//
//				}			

			}
			return mylist;
		}


		//delete함수
		
		public int deleteAsset(String userid, String code) {
			int result = -1;
			String sql = "delete from myasset where userid=? and code=? ";
			Connection conn = null;
			//		Statement stmt = null; //정적 쿼리
					ResultSet rs = null; //
					PreparedStatement pstmt=null;//동적 쿼리
			//		String sql = "select pwd from member where userid='"+ userid + "'";
			
					try {			
						//step. 1
			//			Class.forName("oracle.jdbc.driver.OracleDriver");
			//			//Class.forName("com.mysql.jdbc.Driver"); //MySql
			//			//step. 2 database connection object
			//			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//			String uid = "ora_user";
			//			String pass = "1234";
			//			conn = DriverManager.getConnection(url, uid, pass);//DB연결 시도
						conn = DBManager.getConnection();	
						//step. 3 statement object
			//			stmt = conn.createStatement();//객체 생성
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, userid);
						pstmt.setString(2, code);
						result = pstmt.executeUpdate();
						System.out.println("삭제 성공");
			
					} catch( Exception e){
						e.printStackTrace();
						System.out.println("sql 에러 발생");
			
					} finally {
						DBManager.close(conn, pstmt);
			//			try {//step.  5		
			//				pstmt.close();		
			//				conn.close();					
			//
			//			}catch(SQLException e){
			//				e.getMessage();
			//
			//			}			
			
					}
			return result;
		}

		//나의 자산 수정
		
		public int updateAsset(String userid, String code, int qty) {
			int result = -1;
			//		List<ProductVo> list = new ArrayList<ProductVo>();
			//		ProductVo[] listArr = new ProductVo[100];
			//		int countList = 0;
					
					Connection conn = null;
			//		Statement stmt = null; //정적 쿼리
				
					PreparedStatement pstmt=null;//동적 쿼리
			//		String sql = "select pwd from member where userid='"+ userid + "'";
					String sql = "update myasset set qty=? where userid='"
							+ userid
							+ "' and code = '"
							+ code+"'";
		
					try {			
						//step. 1
//						Class.forName("oracle.jdbc.driver.OracleDriver");
//						//Class.forName("com.mysql.jdbc.Driver"); //MySql
//						//step. 2 database connection object
//						String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//						String uid = "ora_user";
//						String pass = "1234";
//						conn = DriverManager.getConnection(url, uid, pass);
						conn = DBManager.getConnection();
						//step. 3 statement object
//						stmt = conn.createStatement();
						pstmt = conn.prepareStatement(sql);					
//						pstmt.setString(1, pVo.geCode());
						pstmt.setInt(1, qty);
						result = pstmt.executeUpdate();
						System.out.println("result: "+result);
						

					} catch( Exception e){
//						System.out.println("sql 에러 발생");
						e.printStackTrace();

					} finally {
						DBManager.close(conn, pstmt);
//						try {//step.  5		
//							pstmt.close();		
//							conn.close();					
			//
//						}catch(SQLException  e){
			//
//						}			

					}
			return result;
		}
}
