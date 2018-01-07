package com.shiftreport.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.company.model.ComJDBCDAO;
import com.company.model.ComVO;

public class ShiftreJDBCDAO implements ShiftreDAO_interface {

	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=pos";
	String userid = "sa";
	String passwd = "P@ssw0rd";
	
	private static final String INSERT_STMT = "INSERT INTO SHIFTREPORT (Date, shift, emp_id, cash,coupon, discount, coins, deal_sum,deal_cost,deal_profit,deal_num,shift_sum)"
			+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE SHIFTREPORT set "
			+ "Date=?, shift=?, emp_id=?, cash=?,coupon=?, discount=?, coins=?, deal_sum=?,deal_cost=?,deal_profit=?,deal_num=?,shift_sum =? where Date=? and shift=? ";

	private static final String DELETE = "DELETE FROM SHIFTREPORT where Date=? and shift=? ";

	private static final String GET_ONE_STMT = "SELECT * FROM SHIFTREPORT where Date=? and shift=? ";
	
	private static final String GET_STMT_ByDate = "SELECT * FROM SHIFTREPORT where Date=?";

	private static final String GET_ALL_STMT = "SELECT * FROM SHIFTREPORT order by Date";
									     	
	@Override
	public void insert(ShiftreVO shiftreVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);		
			
			pstmt.setDate(1, shiftreVO.getDate());
			pstmt.setString(2, shiftreVO.getShift());
			pstmt.setString(3, shiftreVO.getEmp_id());
			pstmt.setInt(4, shiftreVO.getCash());
			pstmt.setInt(5, shiftreVO.getCoupon());
			pstmt.setInt(6, shiftreVO.getDiscount());
			pstmt.setInt(7, shiftreVO.getCoins());
			pstmt.setInt(8, shiftreVO.getDeal_sum());
			pstmt.setInt(9,shiftreVO.getDeal_cost());
			pstmt.setInt(10,shiftreVO.getDeal_profit());
			pstmt.setInt(11,shiftreVO.getDeal_num());
			pstmt.setInt(12,shiftreVO.getShift_sum());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(ShiftreVO shiftreVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			

			pstmt.setDate(1, shiftreVO.getDate());
			pstmt.setString(2, shiftreVO.getShift());
			pstmt.setString(3, shiftreVO.getEmp_id());
			pstmt.setInt(4, shiftreVO.getCash());
			pstmt.setInt(5, shiftreVO.getCoupon());
			pstmt.setInt(6, shiftreVO.getDiscount());
			pstmt.setInt(7, shiftreVO.getCoins());
			pstmt.setInt(8, shiftreVO.getDeal_sum());
			pstmt.setInt(9,shiftreVO.getDeal_cost());
			pstmt.setInt(10,shiftreVO.getDeal_profit());
			pstmt.setInt(11,shiftreVO.getDeal_num());
			pstmt.setInt(12,shiftreVO.getShift_sum());

			pstmt.setDate(13, shiftreVO.getDate());
			pstmt.setString(14, shiftreVO.getShift());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}


	}

	@Override
	public void delete(Date Date, String shift) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setDate(1,Date);
			pstmt.setString(2, shift);
		
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ShiftreVO findByPrimaryKey(Date Date, String shift) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ShiftreVO shiftreVO = null;
	
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setDate(1,Date);
			pstmt.setString(2,shift);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				shiftreVO = new ShiftreVO();
				shiftreVO.setDate(rs.getDate("Date"));
				shiftreVO.setShift(rs.getString("shift"));
				shiftreVO.setEmp_id(rs.getString("emp_id"));
				shiftreVO.setCash(rs.getInt("cash"));
				shiftreVO.setCoupon(rs.getInt("coupon"));
				shiftreVO.setDiscount(rs.getInt("discount"));
				shiftreVO.setCoins(rs.getInt("coins"));
				shiftreVO.setDeal_sum(rs.getInt("deal_sum"));
				shiftreVO.setDeal_cost(rs.getInt("deal_cost"));
				shiftreVO.setDeal_profit(rs.getInt("deal_profit"));
				shiftreVO.setDeal_num(rs.getInt("deal_num"));
				shiftreVO.setShift_sum(rs.getInt("shift_sum"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return shiftreVO;
	}

	@Override
	public List<ShiftreVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<ShiftreVO> list = new ArrayList<ShiftreVO>();
		ShiftreVO shiftreVO=null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
		
			pstmt = con.prepareStatement(GET_ALL_STMT);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				shiftreVO = new ShiftreVO();
				shiftreVO.setDate(rs.getDate("Date"));
				shiftreVO.setShift(rs.getString("shift"));
				shiftreVO.setEmp_id(rs.getString("emp_id"));
				shiftreVO.setCash(rs.getInt("cash"));
				shiftreVO.setCoupon(rs.getInt("coupon"));
				shiftreVO.setDiscount(rs.getInt("discount"));
				shiftreVO.setCoins(rs.getInt("coins"));
				shiftreVO.setDeal_sum(rs.getInt("deal_sum"));
				shiftreVO.setDeal_cost(rs.getInt("deal_cost"));
				shiftreVO.setDeal_profit(rs.getInt("deal_profit"));
				shiftreVO.setDeal_num(rs.getInt("deal_num"));
				shiftreVO.setShift_sum(rs.getInt("shift_sum"));
				list.add(shiftreVO);
			
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;

	}

	@Override
	public List<ShiftreVO> findByDate(Date Date) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<ShiftreVO> list = new ArrayList<ShiftreVO>();
		ShiftreVO shiftreVO=null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STMT_ByDate);

			pstmt.setDate(1,Date);
		

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				shiftreVO = new ShiftreVO();
				shiftreVO.setDate(rs.getDate("Date"));
				shiftreVO.setShift(rs.getString("shift"));
				shiftreVO.setEmp_id(rs.getString("emp_id"));
				shiftreVO.setCash(rs.getInt("cash"));
				shiftreVO.setCoupon(rs.getInt("coupon"));
				shiftreVO.setDiscount(rs.getInt("discount"));
				shiftreVO.setCoins(rs.getInt("coins"));
				shiftreVO.setDeal_sum(rs.getInt("deal_sum"));
				shiftreVO.setDeal_cost(rs.getInt("deal_cost"));
				shiftreVO.setDeal_profit(rs.getInt("deal_profit"));
				shiftreVO.setDeal_num(rs.getInt("deal_num"));
				shiftreVO.setShift_sum(rs.getInt("shift_sum"));
				list.add(shiftreVO);
				
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;

	}

	public static void main(String[] args) {
		
		ShiftreJDBCDAO dao = new ShiftreJDBCDAO();
		ShiftreVO shiftreVO = new ShiftreVO();
		List<ShiftreVO> list = new ArrayList<ShiftreVO>();
		
//		�s�W
//		shiftreVO.setDate(Date.valueOf("2016-10-12"));
//		shiftreVO.setShift("B");
//		shiftreVO.setEmp_id("E00001");
//		shiftreVO.setCash(200);
//		shiftreVO.setCoupon(0);
//		shiftreVO.setDiscount(0);
//		shiftreVO.setCoins(1500);
//		shiftreVO.setDeal_sum(2000);
//		shiftreVO.setDeal_cost(1000);
//		shiftreVO.setDeal_profit(1000);
//		shiftreVO.setDeal_num(3);
//		shiftreVO.setShift_sum(2000);
//		
//		dao.insert(shiftreVO);
		
//		--------------------------------------------------------------------------
		//�ק�
		
//		shiftreVO.setDate(Date.valueOf("2016-10-12"));
//		shiftreVO.setShift("B");
//		shiftreVO.setEmp_id("E00001");
//		shiftreVO.setCash(200);
//		shiftreVO.setCoupon(0);
//		shiftreVO.setDiscount(0);
//		shiftreVO.setCoins(1500);
//		shiftreVO.setDeal_sum(2000);
//		shiftreVO.setDeal_cost(1000);
//		shiftreVO.setDeal_profit(1000);
//		shiftreVO.setDeal_num(10);
//		shiftreVO.setShift_sum(2000);
//
//		dao.update(shiftreVO);
		
//		--------------------------------------------------------------------------

//		�R��
		
//		dao.delete(Date.valueOf("2016-10-12"),"B");
		
//		--------------------------------------------------------------------------
//		�̷ӯZ�O�d��
		
		
//		shiftreVO = dao.findByPrimaryKey(Date.valueOf("2016-10-10"),"A");
//		list.add(shiftreVO);
		
		
		
//		--------------------------------------------------------------------------
//      �j�M�����Z�O����

//		list=dao.getAll();
		
//		--------------------------------------------------------------------------
//		�@����d�߳���
//		list=dao.findByDate(Date.valueOf("2016-10-10"));
		
		
		for(ShiftreVO shiftreVO2 :list){
//			System.out.println(shiftreVO2.getDate());
//			System.out.println(shiftreVO2.getShift());
//			System.out.println(shiftreVO2.getEmp_id());
//			System.out.println(shiftreVO2.getCash());
//			System.out.println(shiftreVO2.getCoupon());
//			System.out.println(shiftreVO2.getDiscount());
//			System.out.println(shiftreVO2.getCoins());
			System.out.println(shiftreVO2.getDeal_sum());
//			System.out.println(shiftreVO2.getDeal_cost());
//			System.out.println(shiftreVO2.getDeal_profit());
//			System.out.println(shiftreVO2.getDeal_num());
//			System.out.println(shiftreVO2.getShift_sum());
//			System.out.println("---------------------------");
		}
		
	}

	@Override
	public List getSumJson(Date date1, Date date2, String shift) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
