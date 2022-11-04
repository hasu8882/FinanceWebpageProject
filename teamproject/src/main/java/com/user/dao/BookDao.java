package com.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.dto.IncomeVo; 
import com.user.dto.OutlayVo; 

public class BookDao {	
	
	
	// 지출 입력
	public int insertOutlay(OutlayVo outlayVo) throws Exception{
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		try{
			connection = getConnection();
			
			// StringBuffer 사용
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO OUTLAYTABLE (ID,NUM,USE1,CASH,CARD,USED_DISPOSAL,DATE1,MEMO1) \n");
			sql.append("VALUES(?,?,?,?,?,?,?,?) \n");
						
			pstmt = connection.prepareStatement(sql.toString());	
			
			pstmt.setString(1, outlayVo.getId());
			pstmt.setInt(2, outlayVo.getNum());
			pstmt.setString(3, outlayVo.getUse1());
			pstmt.setInt(4, outlayVo.getCash());
			pstmt.setInt(5, outlayVo.getCard());
			pstmt.setString(6, outlayVo.getUsed_disposal());
			pstmt.setString(7, outlayVo.getDate1());
			pstmt.setString(8, outlayVo.getMemo1());
			
			result = pstmt.executeUpdate();
		} catch(SQLException e){
			System.out.println(e);
		} finally{
			//DB 작업 종료 후 statement 들과 resultSet,connection 을 종료
			if(pstmt!=null){
				try{
					pstmt.close();
					} catch(Exception e){
						
					}
				}
			if(connection!=null){
				try{
					connection.close();
					} catch(Exception e){
						
					}
				}
		}
		return result;
	}
	
	// 수입 입력
	public int insertIncome(IncomeVo incomeVo) throws Exception{
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		try{
			connection = getConnection();
			
			// StringBuffer 사용
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO INCOMETABLE (ID,NUM,USE2,AMOUNT,BANKBOOK,IMPORT_DISPOSAL,DATE2,MEMO2) \n");
			sql.append("VALUES(?,?,?,?,?,?,?,?) \n");
			
			pstmt = connection.prepareStatement(sql.toString());	
			
			pstmt.setString(1, incomeVo.getId());
			pstmt.setInt(2, incomeVo.getNum());
			pstmt.setString(3, incomeVo.getUse2());
			pstmt.setInt(4, incomeVo.getAmount());
			pstmt.setString(5, incomeVo.getBankbook());
			pstmt.setString(6, incomeVo.getImport_disposal());
			pstmt.setString(7, incomeVo.getDate2());
			pstmt.setString(8, incomeVo.getMemo2());
			
			result = pstmt.executeUpdate();
		} catch(SQLException e){
			System.out.println(e);
		} finally{
			//DB 작업 종료 후 statement 들과 resultSet,connection 을 종료
			if(pstmt!=null){
				try{
					pstmt.close();
					} catch(Exception e){
						
					}
				}
			if(connection!=null){
				try{
					connection.close();
					} catch(Exception e){
						
					}
				}
		}
		return result;
	}	
	
	// 지출 리스트 조회
	public List<OutlayVo> outlayList(OutlayVo outlayVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		List<OutlayVo> outlayList = new ArrayList<OutlayVo>();
		try{
			connection = getConnection();
			
			// StringBuffer 사용
			StringBuffer sql = new StringBuffer();
			
			sql.append("	SELECT * FROM OUTLAYTABLE	\n");			
			sql.append("	WHERE 1=1					\n");	// if문으로 where절을 여러개 추가할때 에러를 방지하기 위한
			if(outlayVo.getDate1() != null) {
				sql.append(" AND DATE1 =  ?				\n");	
			}
			if(outlayVo.getId() != null) {
				sql.append(" AND ID = ? 				\n");
			}
			sql.append("	ORDER BY NUM				\n");
			
			preparedStatement = connection.prepareStatement(sql.toString());
			if(outlayVo.getDate1() != null) {
				preparedStatement.setString(1, outlayVo.getDate1());
			}
			if(outlayVo.getId() != null) {
				preparedStatement.setString(2, outlayVo.getId());
			}
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				OutlayVo tempVo = new OutlayVo();
				
				tempVo.setId(resultSet.getString("id"));
				tempVo.setNum(resultSet.getInt("num"));
				tempVo.setUse1(resultSet.getString("use1"));
				tempVo.setCard(resultSet.getInt("card"));
				tempVo.setCash(resultSet.getInt("cash"));
				tempVo.setUsed_disposal(resultSet.getString("used_disposal"));
				tempVo.setDate1(resultSet.getString("date1"));
				tempVo.setMemo1(resultSet.getString("memo1"));
				
				outlayList.add(tempVo);
			}
		} catch(SQLException e){
		} finally{
			//DB 작업 종료 후 statement과 resultSet,connection 을 종료
			if(resultSet!=null){
				try{
					resultSet.close();
					} catch(Exception e){
						
					}
				}
			if(preparedStatement!=null){
				try{
					preparedStatement.close();
					} catch(Exception e){
						
					}
				}
			if(connection!=null){
				try{
					connection.close();
					} catch(Exception e){
						
					}
				}
		}
		
		return outlayList;
	}
	
	// 수입 리스트 조회
	public List<IncomeVo> incomeList(IncomeVo incomeVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		List<IncomeVo> incomeList = new ArrayList<IncomeVo>();
		try{
			connection = getConnection();
			
			// StringBuffer 사용
			StringBuffer sql = new StringBuffer();
			
			sql.append("	SELECT * FROM INCOMETABLE	\n");
			sql.append("	WHERE 1=1					\n");	// if문으로 where절을 여러개 추가할때 에러를 방지하기 위한
			if(incomeVo.getDate2() != null) {
				sql.append(" AND DATE2 =  ?				\n");
			}
			if(incomeVo.getId() != null) {
				sql.append(" AND ID = ? 				\n");
			}
			sql.append("	ORDER BY NUM				\n");
			
			preparedStatement = connection.prepareStatement(sql.toString());
			if(incomeVo.getDate2() != null) {
				preparedStatement.setString(1, incomeVo.getDate2());
			}
			if(incomeVo.getId() != null) {
				preparedStatement.setString(2, incomeVo.getId());
			}
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				IncomeVo tempVo = new IncomeVo();
				
				tempVo.setId(resultSet.getString("id"));
				tempVo.setNum(resultSet.getInt("num"));
				tempVo.setUse2(resultSet.getString("use2"));
				tempVo.setAmount(resultSet.getInt("amount"));
				tempVo.setBankbook(resultSet.getString("bankbook"));
				tempVo.setImport_disposal(resultSet.getString("import_disposal"));
				tempVo.setDate2(resultSet.getString("date2"));
				tempVo.setMemo2(resultSet.getString("memo2"));
				
				incomeList.add(tempVo);
			}
		} catch(SQLException e){
		} finally{
			//DB 작업 종료 후 statement과 resultSet,connection 을 종료
			if(resultSet!=null){
				try{
					resultSet.close();
					} catch(Exception e){
						
					}
				}
			if(preparedStatement!=null){
				try{
					preparedStatement.close();
					} catch(Exception e){
						
					}
				}
			if(connection!=null){
				try{
					connection.close();
					} catch(Exception e){
						
					}
				}
		}
		
		return incomeList;
	}	
	
	// 지출 리스트 수정
	public int updateOutlay(OutlayVo outlayVo) throws Exception{
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		try{
			connection = getConnection();
			
			// StringBuffer 사용
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE OUTLAYTABLE 					\n");
			sql.append("SET NUM = ?, USE1 = ?, CASH = ?, CARD = ?, USED_DISPOSAL = ?, DATE1 = ?, MEMO1 = ? \n");
			sql.append("WHERE ID = ? AND NUM = ? \n");
			
			pstmt = connection.prepareStatement(sql.toString());	
			
			pstmt.setInt(1, outlayVo.getNum());
			pstmt.setString(2, outlayVo.getUse1());
			pstmt.setInt(3, outlayVo.getCash());
			pstmt.setInt(4, outlayVo.getCard());
			pstmt.setString(5, outlayVo.getUsed_disposal());
			pstmt.setString(6, outlayVo.getDate1());
			pstmt.setString(7, outlayVo.getMemo1());
			pstmt.setString(8, outlayVo.getId());
			pstmt.setInt(9, outlayVo.getOld_num());
			
			result = pstmt.executeUpdate();
		} catch(SQLException e){
			System.out.println(e);
		} finally{
			//DB 작업 종료 후 statement 들과 resultSet,connection 을 종료
			if(pstmt!=null){
				try{
					pstmt.close();
					} catch(Exception e){
						
					}
				}
			if(connection!=null){
				try{
					connection.close();
					} catch(Exception e){
						
					}
				}
		}
		return result;
	}
	
	// 수입 리스트 수정
	public int updateIncome(IncomeVo incomeVo) throws Exception{
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		try{
			connection = getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE INCOMETABLE			\n");
			sql.append("SET NUM = ? , USE2 = ?, AMOUNT = ?, BANKBOOK = ?, IMPORT_DISPOSAL = ?, DATE2 = ?, MEMO2 = ? \n");
			sql.append("WHERE ID = ? AND NUM = ? \n");
			pstmt = connection.prepareStatement(sql.toString());	
			
			pstmt.setInt(1, incomeVo.getNum());
			pstmt.setString(2, incomeVo.getUse2());
			pstmt.setInt(3, incomeVo.getAmount());
			pstmt.setString(4, incomeVo.getBankbook());
			pstmt.setString(5, incomeVo.getImport_disposal());
			pstmt.setString(6, incomeVo.getDate2());
			pstmt.setString(7, incomeVo.getMemo2());
			pstmt.setString(8, incomeVo.getId());
			pstmt.setInt(9, incomeVo.getOld_num());
			
			result = pstmt.executeUpdate();
		} catch(SQLException e){
			System.out.println(e);
		} finally{
			//DB 작업 종료 후 statement 들과 resultSet,connection 을 종료. 작업이 끝난 후 각 프로세스가 남아있는것을 방지하기 위함
			if(pstmt!=null){
				try{
					pstmt.close();
					} catch(Exception e){
						
					}
				}
			if(connection!=null){
				try{
					connection.close();
					} catch(Exception e){
						
					}
				}
		}
		return result;
	}
	
	// 지출 수정 후 
	public OutlayVo outlay(OutlayVo outlayVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		OutlayVo tempVo = new OutlayVo();
		try{
			connection = getConnection();
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("	SELECT * FROM OUTLAYTABLE	\n");
			sql.append("	WHERE 1=1					\n");	// if문으로 where절을 여러개 추가할때 에러를 방지하기 위한
			sql.append("	AND ID =  ? AND NUM = ?		\n");
			
			preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setString(1, outlayVo.getId());
			preparedStatement.setInt(2, outlayVo.getNum());
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				tempVo.setId(resultSet.getString("id"));
				tempVo.setNum(resultSet.getInt("num"));
				tempVo.setUse1(resultSet.getString("use1"));
				tempVo.setCard(resultSet.getInt("card"));
				tempVo.setCash(resultSet.getInt("cash"));
				tempVo.setUsed_disposal(resultSet.getString("used_disposal"));
				tempVo.setDate1(resultSet.getString("date1"));
				tempVo.setMemo1(resultSet.getString("memo1"));
				
			}
		} catch(SQLException e){
		} finally{
			//DB 작업 종료 후 statement과 resultSet,connection 을 종료
			if(resultSet!=null){
				try{
					resultSet.close();
					} catch(Exception e){
						
					}
				}
			if(preparedStatement!=null){
				try{
					preparedStatement.close();
					} catch(Exception e){
						
					}
				}
			if(connection!=null){
				try{
					connection.close();
					} catch(Exception e){
						
					}
				}
		}
		
		return tempVo;
	}
	
	// 수입 수정 후
	public IncomeVo income(IncomeVo incomeVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		IncomeVo tempVo = new IncomeVo();
		try{
			connection = getConnection();
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("	SELECT * FROM INCOMETABLE	\n");
			sql.append("	WHERE 1=1					\n");	// if문으로 where절을 여러개 추가할때 에러를 방지하기 위한
			sql.append("	AND ID =  ? AND NUM = ?		\n");
			
			preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setString(1, incomeVo.getId());
			preparedStatement.setInt(2, incomeVo.getNum());
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				tempVo.setId(resultSet.getString("id"));
				tempVo.setNum(resultSet.getInt("num"));
				tempVo.setUse2(resultSet.getString("use2"));
				tempVo.setAmount(resultSet.getInt("amount"));
				tempVo.setBankbook(resultSet.getString("bankbook"));
				tempVo.setImport_disposal(resultSet.getString("import_disposal"));
				tempVo.setDate2(resultSet.getString("date2"));
				tempVo.setMemo2(resultSet.getString("memo2"));
				
			}
		} catch(SQLException e){
		} finally{
			//DB 작업 종료 후 statement과 resultSet,connection 을 종료
			if(resultSet!=null){
				try{
					resultSet.close();
					} catch(Exception e){
						
					}
				}
			if(preparedStatement!=null){
				try{
					preparedStatement.close();
					} catch(Exception e){
						
					}
				}
			if(connection!=null){
				try{
					connection.close();
					} catch(Exception e){
						
					}
				}
		}
		
		return tempVo;
	}
			
	// 지출 삭제
	public int deleteOutlay(OutlayVo outlayVo) throws Exception{
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		try{
			connection = getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM  OUTLAYTABLE \n");
			sql.append("WHERE ID = ? AND NUM = ? \n");
			pstmt = connection.prepareStatement(sql.toString());	
			
			pstmt.setString(1, outlayVo.getId());
			pstmt.setInt(2, outlayVo.getNum());
			
			result = pstmt.executeUpdate();
		} catch(SQLException e){
			System.out.println(e);
		} finally{
			//DB 작업 종료 후 statement 들과 resultSet,connection 을 종료
			if(pstmt!=null){
				try{
					pstmt.close();
					} catch(Exception e){
						
					}
				}
			if(connection!=null){
				try{
					connection.close();
					} catch(Exception e){
						
					}
				}
		}
		return result;
	}
	
	// 수입 삭제 
	public int deleteIncome(IncomeVo incomeVo) throws Exception{
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		try{
			connection = getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM  INCOMETABLE \n");
			sql.append("WHERE ID = ? AND NUM = ? \n");
			pstmt = connection.prepareStatement(sql.toString());	
			
			pstmt.setString(1, incomeVo.getId());
			pstmt.setInt(2, incomeVo.getNum());
			
			result = pstmt.executeUpdate();
		} catch(SQLException e){
			System.out.println(e);
		} finally{
			//DB 작업 종료 후 statement 들과 resultSet,connection 을 종료
			if(pstmt!=null){
				try{
					pstmt.close();
					} catch(Exception e){
						
					}
				}
			if(connection!=null){
				try{
					connection.close();
					} catch(Exception e){
						
					}
				}
		}
		return result;
	}
	
	
	
	
	
	public Connection getConnection() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e){
			e.printStackTrace();
		}
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String username= "ora_user" ;
		String password= "1234";
		
		Connection connection = null;
		try{
			connection = DriverManager.getConnection(url, username, password);
		}catch(SQLException e){
			System.out.println(e);
		}
		return connection;
	}
}
