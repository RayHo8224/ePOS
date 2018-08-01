package com.promoting.model;

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

public class PromotingJNDIDAO implements PromotingDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/PosDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO PROMOTING (pro_prod_id,pro_prod_name,pro_begin,pro_end,pro_neirong) VALUES (?,?,?,?,?)";
	private static final String UPDATE = "UPDATE PROMOTING set pro_prod_id=?, pro_prod_name=?, pro_begin=?, pro_end=?, pro_neirong=? where pro_prod_id =? AND pro_begin=?";
	private static final String DELETE = "DELETE FROM PROMOTING where pro_prod_id = ? AND pro_begin=?";
	private static final String GET_ONE_STMT = "SELECT pro_prod_id,pro_prod_name,pro_begin,pro_end,pro_neirong FROM PROMOTING where pro_prod_id = ? AND pro_begin=?";
	private static final String GET_ALL_STMT = "SELECT pro_prod_id,pro_prod_name,pro_begin,pro_end,pro_neirong FROM PROMOTING";
	private static final String SEARCH = "SELECT prod_id FROM PRODUCT where prod_id=?";
	private static final String GET_DATES_STMT = "SELECT pro_prod_id,pro_prod_name,pro_begin,pro_end,pro_neirong FROM PROMOTING where pro_begin >=? and pro_end <=? ";
	private static final String GET_NAMES_STMT = "SELECT pro_prod_id,pro_prod_name,pro_begin,pro_end,pro_neirong FROM PROMOTING where pro_prod_id like ?";
	private static final String GET_IDS_STMT = "SELECT pro_prod_id,pro_prod_name,pro_begin,pro_end,pro_neirong FROM PROMOTING where pro_prod_id between ? and ? ";
	private static final String GET_BYIDGROUP_STMT = "SELECT pro_prod_id,pro_prod_name,pro_begin,pro_end,pro_neirong FROM PROMOTING where pro_prod_id = ?";
	private static final String GET_IDGROUP_STMT = "SELECT pro_prod_id FROM PROMOTING group by pro_prod_id";
	
	@Override
	public void insert(PromotingVO promotingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			String id = promotingVO.getPro_prod_id();

			pstmt = con.prepareStatement(SEARCH);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setString(1, id);
				pstmt.setString(2, promotingVO.getPro_prod_name());
				pstmt.setDate(3, promotingVO.getPro_begin());
				pstmt.setDate(4, promotingVO.getPro_end());
				pstmt.setString(5, promotingVO.getPro_neirong());

				pstmt.executeUpdate();
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	}

	@Override
	public void update(PromotingVO promotingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String id = promotingVO.getPro_prod_id();
			pstmt = con.prepareStatement(SEARCH);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pstmt = con.prepareStatement(UPDATE);
				pstmt.setString(1, id);
				pstmt.setString(2, promotingVO.getPro_prod_name());
				pstmt.setDate(3, promotingVO.getPro_begin());
				pstmt.setDate(4, promotingVO.getPro_end());
				pstmt.setString(5, promotingVO.getPro_neirong());
				pstmt.setString(6, "P00005");
				pstmt.setDate(7, Date.valueOf("2016-09-09"));
				pstmt.executeUpdate();
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	}

	@Override
	public void delete(String pro_prod_id, Date pro_begin) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, pro_prod_id);
			pstmt.setDate(2, pro_begin);
			pstmt.executeUpdate();
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
	public PromotingVO findByPrimaryKey(String pro_prod_id, Date pro_begin) {
		PromotingVO promotingVO = null;
		;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, pro_prod_id);
			pstmt.setDate(2, pro_begin);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotingVO = new PromotingVO();
				promotingVO.setPro_prod_id(rs.getString("pro_prod_id"));
				promotingVO.setPro_prod_name(rs.getString("pro_prod_name"));
				promotingVO.setPro_begin(rs.getDate("pro_begin"));
				promotingVO.setPro_end(rs.getDate("pro_end"));
				promotingVO.setPro_neirong(rs.getString("pro_neirong"));
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return promotingVO;
	}

	@Override
	public List<PromotingVO> getAll() {
		List<PromotingVO> list = new ArrayList<PromotingVO>();
		PromotingVO promotingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotingVO = new PromotingVO();
				promotingVO.setPro_prod_id(rs.getString("pro_prod_id"));
				promotingVO.setPro_prod_name(rs.getString("pro_prod_name"));
				promotingVO.setPro_begin(rs.getDate("pro_begin"));
				promotingVO.setPro_end(rs.getDate("pro_end"));
				promotingVO.setPro_neirong(rs.getString("pro_neirong"));
				list.add(promotingVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<PromotingVO> getDates(Date pro_begin, Date pro_end) {
		List<PromotingVO> list = new ArrayList<PromotingVO>();
		PromotingVO promotingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DATES_STMT);
			pstmt.setDate(1, pro_begin);
			pstmt.setDate(2, pro_end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotingVO = new PromotingVO();
				promotingVO.setPro_prod_id(rs.getString("pro_prod_id"));
				promotingVO.setPro_prod_name(rs.getString("pro_prod_name"));
				promotingVO.setPro_begin(rs.getDate("pro_begin"));
				promotingVO.setPro_end(rs.getDate("pro_end"));
				promotingVO.setPro_neirong(rs.getString("pro_neirong"));

				list.add(promotingVO);
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

	@Override
	public List<PromotingVO> getNames(String pro_prod_name) {
		List<PromotingVO> list = new ArrayList<PromotingVO>();
		PromotingVO promotingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NAMES_STMT);
			pstmt.setString(1, "%"+pro_prod_name+"%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotingVO = new PromotingVO();
				promotingVO.setPro_prod_id(rs.getString("pro_prod_id"));
				promotingVO.setPro_prod_name(rs.getString("pro_prod_name"));
				promotingVO.setPro_begin(rs.getDate("pro_begin"));
				promotingVO.setPro_end(rs.getDate("pro_end"));
				promotingVO.setPro_neirong(rs.getString("pro_neirong"));

				list.add(promotingVO);
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
	public List<PromotingVO> getIds(String pro_prod_id1, String pro_prod_id2) {
		List<PromotingVO> list = new ArrayList<PromotingVO>();
		PromotingVO promotingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_IDS_STMT);
			pstmt.setString(1, pro_prod_id1);
			pstmt.setString(2, pro_prod_id2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotingVO = new PromotingVO();
				promotingVO.setPro_prod_id(rs.getString("pro_prod_id"));
				promotingVO.setPro_prod_name(rs.getString("pro_prod_name"));
				promotingVO.setPro_begin(rs.getDate("pro_begin"));
				promotingVO.setPro_end(rs.getDate("pro_end"));
				promotingVO.setPro_neirong(rs.getString("pro_neirong"));

				list.add(promotingVO);
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

	@Override
	public List<PromotingVO> GroupByIDs() {
		List<PromotingVO> list = new ArrayList<PromotingVO>();
		PromotingVO promotingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_IDGROUP_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotingVO = new PromotingVO();
				promotingVO.setPro_prod_id(rs.getString("pro_prod_id"));
				list.add(promotingVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<PromotingVO> findByIDs(String pro_prod_id) {
		List<PromotingVO> list = new ArrayList<PromotingVO>();
		PromotingVO promotingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYIDGROUP_STMT);
			pstmt.setString(1,pro_prod_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotingVO = new PromotingVO();
				promotingVO.setPro_prod_id(rs.getString("pro_prod_id"));
				promotingVO.setPro_prod_name(rs.getString("pro_prod_name"));
				promotingVO.setPro_begin(rs.getDate("pro_begin"));
				promotingVO.setPro_end(rs.getDate("pro_end"));
				promotingVO.setPro_neirong(rs.getString("pro_neirong"));
				list.add(promotingVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
