package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.user.dto.AssetVo;
import com.user.dto.ETFVo;
import com.user.dto.FactorVo;
import com.user.dto.StockVo;
import com.user.util.DBManager;

public class StockBoardDao {
	
	// 1. 생성자 private : 객체를 외부에서 만들지 못하도록
			// 2. 객체 생성 private static : 자신이 객체를 생성
			// 3. 객체 제공 기능 getInstance() : 자신의 객체(단지 1개)를 사용할 수 있도록 제공
			private StockBoardDao() {		
			}
			
			private static StockBoardDao instance = new StockBoardDao();
			
			public static StockBoardDao getInstance() {
				return instance;
			}
			
			// 개별 주식 조히 kospi, kosdaq
			//팩터 객체 
			static FactorVo fVo = new FactorVo();
			public List<StockVo> listAllStock(String market){
//				String sql = "select pwd from member where userid='"+ userid + "'";
				String sql = "select * from stocks where market_type ='"+market+"' order by code desc";
				
				List<StockVo> stockList = new ArrayList<StockVo>();
				Connection conn = null;
//				Statement stmt = null; //정적 쿼리
				ResultSet rs = null; //
				PreparedStatement pstmt=null;			
				try {			
					//step. 1
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//					//Class.forName("com.mysql.jdbc.Driver"); //MySql
//					//step. 2 database connection object
//					String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//					String uid = "ora_user";
//					String pass = "1234";
//					conn = DriverManager.getConnection(url, uid, pass);//DB연결 시도
					conn = DBManager.getConnection();	
					//step. 3 statement object
//					stmt = conn.createStatement();//객체 생성
					pstmt = conn.prepareStatement(sql);
					rs =pstmt.executeQuery();
//					System.out.println("Dao fuction:");
					while(rs.next()) {
						
						//rs.getInt('컬럼명')
//						
//						rs.getInt("code");
//						rs.getString("name");
//						rs.getInt("price");
//						rs.getString("pictureurl");
//						rs.getString("description");
//						rs.getDate("reg_date");
						
						StockVo sVo = new StockVo();
						
						sVo.setCode(rs.getString("code"));
//						System.out.println("code:"+rs.getInt("code") );
						sVo.setName(rs.getString("name"));
						sVo.setPrice(rs.getInt("price"));
						sVo.setLast_update(rs.getDate("last_update"));
						sVo.setRange_day(rs.getFloat("day_range"));
						sVo.setChange_day(rs.getFloat("day_change"));
						sVo.setEPS(rs.getInt("EPS"));
						sVo.setPER(rs.getFloat("PER"));
						sVo.setFwdEPS(rs.getInt("fwd_EPS"));
						sVo.setFwdPER(rs.getInt("fwd_PER"));
						sVo.setBPS(rs.getInt("BPS"));
						sVo.setPBR(rs.getFloat("PBR"));
						sVo.setDividend(rs.getInt("dividend"));
						sVo.setDividend_yield(rs.getFloat("dividend_yield"));
						sVo.setMaketType(rs.getString("market_type"));					
						
						stockList.add(sVo);
						
//						listArr[countList]=pVo;
//						countList++;
					}

				} catch( Exception e){
					e.printStackTrace();
					System.out.println("sql 에러 발생");

				} finally {
					DBManager.close(conn, pstmt);
//					try {//step.  5		
//						pstmt.close();		
//						conn.close();					
		//
//					}catch(SQLException e){
//						e.getMessage();
		//
//					}			

				}
				return stockList;
			}

			public List<StockVo> searchStockListByName( String market){
				return searchStockListByName( market, "",1, fVo);
			}
			
			public List<StockVo> searchStockListByName( String market, String name){
				return searchStockListByName( market,name, 1, fVo);
			}
			
			public List<StockVo> searchStockListByName( String market, int page, FactorVo fVo){
				return searchStockListByName( market, "", page, fVo);
			}
			
			public List<StockVo> searchStockListByName( String market, String name, int page,FactorVo fVo){

//				String sql = "select * from  stocks "
//						+ "where market_type ='"+market+"' AND "+" name like ? order by price desc";
				String sql = "SELECT * FROM ("
						+ "SELECT ROWNUM N, b.* "
						+ " FROM (SELECT * FROM stocks where market_type ='"+ market+ "' AND "
								+ " name like ? AND PER >= ? AND PER <= ?"
								+ " AND PBR >= ? AND PBR <= ? "
								+ " AND dividend_yield >= ?"
								+ " order by price desc) b "
						+ ") "
						+ " WHERE N BETWEEN ? AND ?";


				List<StockVo> list = new ArrayList<StockVo>();
				Connection conn = null;
//				Statement stmt = null; //정적 쿼리
				ResultSet rs = null; //
				PreparedStatement pstmt=null;
				
				try {			
					//step. 1
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//					//Class.forName("com.mysql.jdbc.Driver"); //MySql
//					//step. 2 database connection object
//					String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//					String uid = "ora_user";
//					String pass = "1234";
//					conn = DriverManager.getConnection(url, uid, pass);//DB연결 시도
					conn = DBManager.getConnection();
					//step. 3 statement object
//					stmt = conn.createStatement();//객체 생성
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%"+name+"%");
					pstmt.setFloat(2,  fVo.getPerL());
//					System.out.println("PER LOW: " +fVo.getPerL() );
					pstmt.setFloat(3, fVo.getPerU());
//					System.out.println("PER UPPER: " +fVo.getPerU() );
					pstmt.setFloat(4, fVo.getPbrL());
//					System.out.println("PBR LOWER: " +fVo.getPbrL() );
					pstmt.setFloat(5, fVo.getPbrU());
//					System.out.println("PBR UPPER: " +fVo.getPbrU() );
					pstmt.setFloat(6, fVo.getDivYield());
//					System.out.println("Dividend: " +fVo.getDivYield() );
					pstmt.setInt(7, 1+(page-1)*10);
					pstmt.setInt(8, 1+page*10);
					rs =pstmt.executeQuery();
					while(rs.next()) {
						
						//rs.getInt('컬럼명')
//		
//						rs.getDate("reg_date");
						StockVo sVo = new StockVo();
						
						sVo.setCode(rs.getString("code"));
//						System.out.println("code:"+rs.getInt("code") );
						sVo.setName(rs.getString("name"));
						sVo.setPrice(rs.getInt("price"));
						sVo.setLast_update(rs.getDate("last_update"));
						sVo.setRange_day(rs.getFloat("day_range"));
						sVo.setChange_day(rs.getFloat("day_change"));
						sVo.setEPS(rs.getInt("EPS"));
						sVo.setPER(rs.getFloat("PER"));
						sVo.setFwdEPS(rs.getInt("fwd_EPS"));
						sVo.setFwdPER(rs.getInt("fwd_PER"));
						sVo.setBPS(rs.getInt("BPS"));
						sVo.setPBR(rs.getFloat("PBR"));
						sVo.setDividend(rs.getInt("dividend"));
						sVo.setDividend_yield(rs.getFloat("dividend_yield"));
						sVo.setMaketType(rs.getString("market_type"));					
						
						list.add(sVo);	

					}

				} catch( Exception e){
					e.printStackTrace();
					System.out.println("sql 에러 발생");

				} finally {
					DBManager.close(conn, pstmt);
//					try {//step.  5		
//						pstmt.close();		
//						conn.close();					
		//
//					}catch(SQLException e){
//						e.getMessage();
		//
//					}			

				}
				return list;
			}
			
			
			//ETF 조회
			
			public List<ETFVo> searchETFListByName(){
				
				return searchETFListByName("",1);
			}
			
			public List<ETFVo> searchETFListByName(int page){
				
				return searchETFListByName("",page);
			}
			
			public List<ETFVo> searchETFListByName(String name, int page){
//				String sql = "select * from etf where name like "
//						+ "? order by marketCap desc";
				String sql = "SELECT * FROM ("
						+ "SELECT ROWNUM N, b.* "
						+ " FROM (SELECT * FROM etf where "
								+ " name like ? order by price desc) b "
						+ ") "
						+ " WHERE N BETWEEN ? AND ?";
				List<ETFVo> etfList = new ArrayList<ETFVo>();
				Connection conn = null;
//				Statement stmt = null; //정적 쿼리
				ResultSet rs = null; //
				PreparedStatement pstmt=null;			
				try {			
					//step. 1
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//					//Class.forName("com.mysql.jdbc.Driver"); //MySql
//					//step. 2 database connection object
//					String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//					String uid = "ora_user";
//					String pass = "1234";
//					conn = DriverManager.getConnection(url, uid, pass);//DB연결 시도
					conn = DBManager.getConnection();	
					//step. 3 statement object
//					stmt = conn.createStatement();//객체 생성
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%"+name+"%");
					pstmt.setInt(2, 1+(page-1)*10);
					pstmt.setInt(3, 1+page*10);
					
					rs =pstmt.executeQuery();
//					System.out.println("sql: "+ sql);
//					System.out.println("Dao fuction:");
					while(rs.next()) {
						
						//rs.getInt('컬럼명')
//						
//						rs.getInt("code");
//						rs.getString("name");
//						rs.getInt("price");
//						rs.getString("pictureurl");
//						rs.getString("description");
//						rs.getDate("reg_date");
						
						ETFVo eVo = new ETFVo();
						
						eVo.setCode(rs.getString("code"));
//						System.out.println("code:"+rs.getInt("code") );
						eVo.setName(rs.getString("name"));
						eVo.setPrice(rs.getInt("price"));
						eVo.setLast_update(rs.getDate("last_update"));
						eVo.setRange_day(rs.getInt("day_range"));
						eVo.setChange_day(rs.getFloat("day_change"));
						eVo.setNAV(rs.getInt("NAV"));
						eVo.setOpen(rs.getInt("open"));
						eVo.setHigh(rs.getInt("high"));
						eVo.setLow(rs.getInt("low"));
						eVo.setVolume(rs.getInt("volume"));
						eVo.setTradeAMT(rs.getLong("tradeAMT"));
						eVo.setMarketCap(rs.getLong("marketCap"));
						eVo.setNetAsset(rs.getLong("netAsset"));
						eVo.setQty(rs.getInt("qty"));	
						eVo.setIndexName(rs.getString("indexName"));
						eVo.setCloseIndex(rs.getInt("closeIndex"));
						eVo.setRangeIndex(rs.getFloat("rangeIndex"));
						eVo.setChangeIndex(rs.getFloat("changeIndex"));
						
						etfList.add(eVo);
						
//						listArr[countList]=pVo;
//						countList++;
					}

				} catch( Exception e){
					e.printStackTrace();
					System.out.println("sql 에러 발생");

				} finally {
					DBManager.close(conn, pstmt);
//					try {//step.  5		
//						pstmt.close();		
//						conn.close();					
		//
//					}catch(SQLException e){
//						e.getMessage();
		//
//					}			

				}
				return etfList;
				
			}
			

			public int getStockCount(String market) {
				return  getStockCount(market, "", fVo);
			}
			public int getStockCount(String market, String keyword) {
				return  getStockCount(market, keyword, fVo);
			}
			
			public int  getStockCount(String market, String keyword, FactorVo fVo) {
				int count = 0;
				String sql = "SELECT COUNT(code) count FROM ("
						+ "SELECT ROWNUM N, b.*"
						+ "    FROM(SELECT * FROM  stocks where market_type ='"+market+"' AND "+" name like ? "
								+ " AND PER >= ? AND PER <= ?"
								+ " AND PBR >= ? AND PBR <= ? "
								+ " AND dividend_yield >= ? "
						+ "order by price desc) b"
						+ ") ";

				Connection conn = null;
//				Statement stmt = null; //정적 쿼리
				ResultSet rs = null; //
				PreparedStatement pstmt=null;//동적 쿼리
//				String sql = "select pwd from member where userid='"+ userid + "'";
				try {			
					//step. 1
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//					//Class.forName("com.mysql.jdbc.Driver"); //MySql
//					//step. 2 database connection object
//					String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//					String uid = "ora_user";
//					String pass = "1234";
//					conn = DriverManager.getConnection(url, uid, pass);//DB연결 시도
					conn = DBManager.getConnection();	
					//step. 3 statement object
//					stmt = conn.createStatement();//객체 생성			
					pstmt = conn.prepareStatement(sql);
					pstmt .setString(1, "%"+keyword+"%");
					pstmt.setFloat(2,  fVo.getPerL());
					pstmt.setFloat(3, fVo.getPerU());
					pstmt.setFloat(4, fVo.getPbrL());
					pstmt.setFloat(5, fVo.getPbrU());
					pstmt.setFloat(6, fVo.getDivYield());					
					rs =pstmt.executeQuery();
					if(rs.next()) {
						count = rs.getInt("count");
					}

				} catch( Exception e){
					e.printStackTrace();
					System.out.println("sql 에러 발생");

				} finally {
					DBManager.close(conn, pstmt);
//					try {//step.  5		
//						pstmt.close();		
//						conn.close();					
		//
//					}catch(SQLException e){
//						e.getMessage();
		//
//					}			

				}
				return count;
				
				
			}
			
			public int getETFCount() {
				return getETFCount("");
			}
			public int getETFCount(String keyword) {
				int count=0;

				String sql = "SELECT COUNT(code) count FROM ("
						+ "SELECT ROWNUM N, b.*"
						+ "    FROM(SELECT * FROM  etf where name like ? "
						+ "order by marketCap desc) b"
						+ ") ";

				Connection conn = null;
//				Statement stmt = null; //정적 쿼리
				ResultSet rs = null; //
				PreparedStatement pstmt=null;//동적 쿼리
//				String sql = "select pwd from member where userid='"+ userid + "'";
				try {			
					//step. 1
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//					//Class.forName("com.mysql.jdbc.Driver"); //MySql
//					//step. 2 database connection object
//					String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//					String uid = "ora_user";
//					String pass = "1234";
//					conn = DriverManager.getConnection(url, uid, pass);//DB연결 시도
					conn = DBManager.getConnection();	
					//step. 3 statement object
//					stmt = conn.createStatement();//객체 생성			
					pstmt = conn.prepareStatement(sql);
					pstmt .setString(1, "%"+keyword+"%");
					rs =pstmt.executeQuery();
//					System.out.println("Dao fuction:");
					if(rs.next()) {
						count = rs.getInt("count");
					}

				} catch( Exception e){
					e.printStackTrace();
					System.out.println("sql 에러 발생");

				} finally {
					DBManager.close(conn, pstmt);
//					try {//step.  5		
//						pstmt.close();		
//						conn.close();					
		//
//					}catch(SQLException e){
//						e.getMessage();
		//
//					}			

				}
				
				
				return count;
			}
}
