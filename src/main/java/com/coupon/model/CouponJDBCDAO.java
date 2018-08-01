package com.coupon.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CouponJDBCDAO implements CouponDAO_interface {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=pos";
	String userid = "sa";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO COUPON (cpon_id,cpon_name,release_date,cpon_period,cpon_dollar,status) VALUES (?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE COUPON set cpon_id=?, cpon_name=?, release_date=?, cpon_period=?, cpon_dollar=?, status=? where cpon_id = ?";
	private static final String DELETE = "DELETE FROM COUPON where cpon_id = ?";
	private static final String GET_ONE_STMT = "SELECT cpon_id,cpon_name,release_date,cpon_period,cpon_dollar,status FROM COUPON where cpon_id = ?";
	private static final String GET_ALL_STMT = "SELECT cpon_id,cpon_name,release_date,cpon_period,cpon_dollar,status FROM COUPON";
	private static final String GET_Names_STMT = "SELECT cpon_id,cpon_name,release_date,cpon_period,cpon_dollar,status FROM COUPON where cpon_name = ?";
	private static final String GET_NameGroup_STMT = "SELECT cpon_name FROM COUPON group by cpon_name";
	private static final String GET_Dates_STMT = "SELECT cpon_id,cpon_name,release_date,cpon_period,cpon_dollar,status"
			+" FROM COUPON where release_date >= ? and cpon_period <= ? order by release_date";
	private static final String GET_DollarGroup_STMT = "SELECT cpon_dollar FROM COUPON group by cpon_dollar";
	private static final String GET_Dollar_STMT = "SELECT cpon_id,cpon_name,release_date,cpon_period,cpon_dollar,status FROM COUPON where cpon_dollar = ?";

	
	@Override
	public void insert(CouponVO couponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, couponVO.getCpon_id());
			pstmt.setString(2, couponVO.getCpon_name());
			pstmt.setDate(3, couponVO.getRelease_date());
			pstmt.setDate(4, couponVO.getCpon_period());
			pstmt.setInt(5, couponVO.getCpon_dollar());
			pstmt.setString(6, couponVO.getStatus());
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
	public void update(CouponVO couponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, couponVO.getCpon_id());
			pstmt.setString(2, couponVO.getCpon_name());
			pstmt.setDate(3, couponVO.getRelease_date());
			pstmt.setDate(4, couponVO.getCpon_period());
			pstmt.setInt(5, couponVO.getCpon_dollar());
			pstmt.setString(6, couponVO.getStatus());
			pstmt.setString(7, "CPa00015"); //�ثe�g��,��諸ID
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
	public void delete(String cpon_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, cpon_id);
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public CouponVO findByPrimaryKey(String cpon_id) {
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, cpon_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCpon_id(rs.getString("cpon_id"));
				couponVO.setCpon_name(rs.getString("cpon_name"));
				couponVO.setRelease_date(rs.getDate("release_date"));
				couponVO.setCpon_period(rs.getDate("cpon_period"));
				couponVO.setCpon_dollar(rs.getInt("cpon_dollar"));
				couponVO.setStatus(rs.getString("status"));
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return couponVO;
	}

	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCpon_id(rs.getString("cpon_id"));
				couponVO.setCpon_name(rs.getString("cpon_name"));
				couponVO.setRelease_date(rs.getDate("release_date"));
				couponVO.setCpon_period(rs.getDate("cpon_period"));
				couponVO.setCpon_dollar(rs.getInt("cpon_dollar"));
				couponVO.setStatus(rs.getString("status"));
				list.add(couponVO);
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public List<CouponVO> GroupByNames() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_NameGroup_STMT);			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCpon_name(rs.getString("cpon_name"));
				list.add(couponVO);
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public List<CouponVO> findByNames(String cpon_name) {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Names_STMT);
			pstmt.setString(1, cpon_name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCpon_id(rs.getString("cpon_id"));
				couponVO.setCpon_name(rs.getString("cpon_name"));
				couponVO.setRelease_date(rs.getDate("release_date"));
				couponVO.setCpon_period(rs.getDate("cpon_period"));
				couponVO.setCpon_dollar(rs.getInt("cpon_dollar"));
				couponVO.setStatus(rs.getString("status"));
				list.add(couponVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public List<CouponVO> findByDateRange(Date release_date,Date cpon_period) {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Dates_STMT);
			pstmt.setDate(1, release_date);
			pstmt.setDate(2, cpon_period);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCpon_id(rs.getString("cpon_id"));
				couponVO.setCpon_name(rs.getString("cpon_name"));
				couponVO.setRelease_date(rs.getDate("release_date"));
				couponVO.setCpon_period(rs.getDate("cpon_period"));
				couponVO.setCpon_dollar(rs.getInt("cpon_dollar"));
				couponVO.setStatus(rs.getString("status"));
				list.add(couponVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public List<CouponVO> GroupByDollar() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_DollarGroup_STMT);			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCpon_dollar(rs.getInt("cpon_dollar"));
				list.add(couponVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public List<CouponVO> findByDollar(int cpon_dollar) {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Dollar_STMT);
			pstmt.setInt(1, cpon_dollar);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCpon_id(rs.getString("cpon_id"));
				couponVO.setCpon_name(rs.getString("cpon_name"));
				couponVO.setRelease_date(rs.getDate("release_date"));
				couponVO.setCpon_period(rs.getDate("cpon_period"));
				couponVO.setCpon_dollar(rs.getInt("cpon_dollar"));
				couponVO.setStatus(rs.getString("status"));
				list.add(couponVO);
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
//		CouponJDBCDAO dao = new CouponJDBCDAO();

		// insert
//		CouponVO couponVO1 = new CouponVO();
//		couponVO1.setCpon_id("CPa00014");
//		couponVO1.setCpon_name("����");
//		couponVO1.setRelease_date(Date.valueOf("2016-09-01"));
//		couponVO1.setCpon_period(Date.valueOf("2016-10-15"));
//		couponVO1.setCpon_dollar(500);
//		couponVO1.setStatus("Y");
//		dao.insert(couponVO1);

		// update
//		CouponVO couponVO2 = new CouponVO();
//		couponVO2.setCpon_id("CPa00013");
//		couponVO2.setCpon_name("����");
//		couponVO2.setRelease_date(Date.valueOf("2016-09-01"));
//		couponVO2.setCpon_period(Date.valueOf("2016-10-15"));
//		couponVO2.setCpon_dollar(500);
//		couponVO2.setStatus("Y");
//		dao.update(couponVO2);

		// delete
//		dao.delete("CPa00014");

		// getone
//		CouponVO couponVO3 = dao.findByPrimaryKey("CPa00012");
//		System.out.print(couponVO3.getCpon_id() + ",");
//		System.out.print(couponVO3.getCpon_name() + ",");
//		System.out.print(couponVO3.getRelease_date() + ",");		
//		System.out.print(couponVO3.getCpon_period() + ",");		
//		System.out.print(couponVO3.getCpon_dollar() + ",");		
//		System.out.println(couponVO3.getStatus());
//		System.out.println("---------------------");

		// getall
//		List<CouponVO> list = dao.getAll();
//		for (CouponVO couponVO4 : list) {
//		System.out.print(couponVO4.getCpon_id() + ",");
//		System.out.print(couponVO4.getCpon_name() + ",");
//		System.out.print(couponVO4.getRelease_date() + ",");		
//		System.out.print(couponVO4.getCpon_period() + ",");		
//		System.out.print(couponVO4.getCpon_dollar() + ",");		
//		System.out.println(couponVO4.getStatus());
//			System.out.println();
//		}
		
	}


}
