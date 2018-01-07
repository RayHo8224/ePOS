package com.coupon.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CouponJNDIDAO implements CouponDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/PosDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO COUPON (cpon_id,cpon_name,release_date,cpon_period,cpon_dollar,status) VALUES ([dbo].[GetCponID](),?,?,?,?,?)";
	private static final String UPDATE = "UPDATE COUPON set cpon_id=?, cpon_name=?, release_date=?, cpon_period=?, cpon_dollar=?, status=? where cpon_id = ?";
	private static final String DELETE = "DELETE FROM COUPON where cpon_id = ?";
	private static final String GET_ONE_STMT = "SELECT cpon_id,cpon_name,release_date,cpon_period,cpon_dollar,status FROM COUPON where cpon_id = ?";
	private static final String GET_ALL_STMT = "SELECT cpon_id,cpon_name,release_date,cpon_period,cpon_dollar,status FROM COUPON";
	private static final String GET_Names_STMT = "SELECT cpon_id,cpon_name,release_date,cpon_period,cpon_dollar,status FROM COUPON where cpon_name = ?";
	private static final String GET_NameGroup_STMT = "SELECT cpon_name FROM COUPON group by cpon_name";
	private static final String GET_Dates_STMT = "SELECT cpon_id,cpon_name,release_date,cpon_period,cpon_dollar,status FROM COUPON"
												+" where release_date >= ? and cpon_period <= ? order by release_date";
	private static final String GET_DollarGroup_STMT = "SELECT cpon_dollar FROM COUPON group by cpon_dollar";
	private static final String GET_Dollar_STMT = "SELECT cpon_id,cpon_name,release_date,cpon_period,cpon_dollar,status FROM COUPON where cpon_dollar = ?";
		
	@Override
	public void insert(CouponVO couponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
//改用流水號,切換原用號碼要改
//			pstmt.setString(1, couponVO.getCpon_id());
			pstmt.setString(1, couponVO.getCpon_name());
			pstmt.setDate(2, couponVO.getRelease_date());
			pstmt.setDate(3, couponVO.getCpon_period());
			pstmt.setInt(4, couponVO.getCpon_dollar());
			pstmt.setString(5, couponVO.getStatus());

			pstmt.executeUpdate();
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
	public void update(CouponVO couponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, couponVO.getCpon_id());
			pstmt.setString(2, couponVO.getCpon_name());
			pstmt.setDate(3, couponVO.getRelease_date());
			pstmt.setDate(4, couponVO.getCpon_period());
			pstmt.setInt(5, couponVO.getCpon_dollar());
			pstmt.setString(6, couponVO.getStatus());
			pstmt.setString(7, couponVO.getCpon_id()); 
			pstmt.executeUpdate();
			// Handle any driver errors
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
	public void delete(String cpon_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, cpon_id);
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NameGroup_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCpon_name(rs.getString("cpon_name"));
				list.add(couponVO);
			}

			// Handle any driver errors
		}catch (SQLException se) {
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

			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DollarGroup_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCpon_dollar(rs.getInt("cpon_dollar"));
				list.add(couponVO);
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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

			con = ds.getConnection();
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
			con = ds.getConnection();
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
		return list;
	}

		
}