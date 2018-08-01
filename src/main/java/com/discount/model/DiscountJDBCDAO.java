package com.discount.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coupon.model.CouponVO;

public class DiscountJDBCDAO implements DiscountDAO_interface {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=pos";
	String userid = "sa";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO DISCOUNT (dis_id,dis_price) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE DISCOUNT set dis_id=?, dis_price=? where dis_id = ?";
	private static final String DELETE = "DELETE FROM DISCOUNT where dis_id = ?";
	private static final String GET_ONE_STMT = "SELECT dis_id , dis_price FROM DISCOUNT where dis_id = ?";
	private static final String GET_ALL_STMT = "SELECT dis_id , dis_price FROM DISCOUNT";
	private static final String GROUP_PRICE_STMT = "SELECT dis_price FROM DISCOUNT group by dis_price";
	private static final String GET_BYPRICE_STMT = "SELECT dis_id , dis_price FROM DISCOUNT  where dis_price = ?";
	  
	@Override
	public void insert(DiscountVO discountVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, discountVO.getDis_id());
			pstmt.setFloat(2, discountVO.getDis_price());
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
	public void update(DiscountVO discountVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, discountVO.getDis_id());
			pstmt.setFloat(2, discountVO.getDis_price());
			pstmt.setString(3,"����"); 
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
	public void delete(String dis_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, dis_id);
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
	public DiscountVO findByPrimaryKey(String dis_id) {
		DiscountVO discountVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, dis_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				discountVO = new DiscountVO();
				discountVO.setDis_id(rs.getString("dis_id"));
				discountVO.setDis_price(rs.getFloat("dis_price"));
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
		return discountVO;
	}

	@Override
	public List<DiscountVO> getAll() {
		List<DiscountVO> list = new ArrayList<DiscountVO>();
		DiscountVO discountVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				discountVO = new DiscountVO();
				discountVO.setDis_id(rs.getString("dis_id"));
				discountVO.setDis_price(rs.getFloat("dis_price"));
				list.add(discountVO);
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
	public List<DiscountVO> GroupByPrice() {
		List<DiscountVO> list = new ArrayList<DiscountVO>();
		DiscountVO discountVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GROUP_PRICE_STMT);			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				discountVO = new DiscountVO();
				discountVO.setDis_price(rs.getFloat("dis_price"));
				list.add(discountVO);
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
	public List<DiscountVO> findByPrice(float dis_price) {
		List<DiscountVO> list = new ArrayList<DiscountVO>();
		DiscountVO discountVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BYPRICE_STMT);
			pstmt.setFloat(1, dis_price);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				discountVO = new DiscountVO();
				discountVO.setDis_id(rs.getString("dis_id"));
				discountVO.setDis_price(rs.getFloat("dis_price"));
				list.add(discountVO);
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

//		DiscountJDBCDAO dao = new DiscountJDBCDAO();

		//新增
//		DiscountVO discountVO = new DiscountVO();
//		discountVO.setDis_id("測試");
//		discountVO.setDis_price(Float.parseFloat("0.5"));
//		dao.insert(discountVO);

		//修改
//		DiscountVO discountVO2 = new DiscountVO();
//		discountVO2.setDis_id("測試");
//		discountVO2.setDis_price(Float.parseFloat("0.4"));	
//		dao.update(discountVO2);

		//刪除
//		dao.delete("測試");

		//查一筆
//		DiscountVO discountVO3 = dao.findByPrimaryKey("�¹D");
//		System.out.print(discountVO3.getDis_id() + ",");
//		System.out.println(discountVO3.getDis_price());
//		System.out.println("---------------------");

		//查多筆
//		List<DiscountVO> list = dao.getAll();
//		for (DiscountVO discountVO4 : list) {
//			System.out.print(discountVO4.getDis_id() + ",");
//			System.out.println(discountVO4.getDis_price());
//			System.out.println();
//		}
	
	}

	
}
