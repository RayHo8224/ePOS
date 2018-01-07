package com.company.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ComJNDIDAO implements ComDAO_interface {
	
	private static DataSource ds=null;
	static{
		try {
			Context ctx = new InitialContext();
			ds=(DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO COMPANY (com_id, com_name, com_um, com_phone,com_addr, com_mail, picture, key_id,key_date)"
			+ "VALUES ([dbo].[GetComID](), ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE COMPANY set "
			+ "com_id=?, com_name=?, com_um=?, com_phone=?, com_addr=?, com_mail=?, picture=?, key_id=?, key_date=? where com_id = ?";

	private static final String DELETE = "DELETE FROM COMPANY where com_id = ?";

	private static final String GET_ONE_STMT = "SELECT * FROM COMPANY where com_id = ?";
	
	private static final String GET_ONE_STMT_ByName = "SELECT * FROM COMPANY where com_name like ?";

	private static final String GET_ALL_STMT = "SELECT * FROM COMPANY order by com_id";
	
	@Override
	public void insert(ComVO comVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setString(1, comVO.getCom_id());
			pstmt.setString(1, comVO.getCom_name());
			pstmt.setString(2, comVO.getCom_um());
			pstmt.setString(3, comVO.getCom_phone());
			pstmt.setString(4, comVO.getCom_addr());
			pstmt.setString(5, comVO.getCom_mail());
			pstmt.setString(6, comVO.getPicture());
			pstmt.setString(7, comVO.getKey_id());
			pstmt.setDate(8, comVO.getKey_date());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void update(ComVO comVO) {		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, comVO.getCom_id());
			pstmt.setString(2, comVO.getCom_name());
			pstmt.setString(3, comVO.getCom_um());
			pstmt.setString(4, comVO.getCom_phone());
			pstmt.setString(5, comVO.getCom_addr());
			pstmt.setString(6, comVO.getCom_mail());
			pstmt.setString(7, comVO.getPicture());
			pstmt.setString(8, comVO.getKey_id());
			pstmt.setDate(9, comVO.getKey_date());

			pstmt.setString(10,comVO.getCom_id());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String com_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,com_id );
		

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public ComVO findByPrimaryKey(String com_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ComVO comVO = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,com_id);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				comVO = new ComVO();
				comVO.setCom_id(rs.getString("com_id"));
				comVO.setCom_name(rs.getString("com_name"));
				comVO.setCom_um(rs.getString("com_um"));
				comVO.setCom_phone(rs.getString("com_phone"));
				comVO.setCom_addr(rs.getString("com_addr"));
				comVO.setCom_mail(rs.getString("com_mail"));
				comVO.setPicture(rs.getString("picture"));
				comVO.setKey_id(rs.getString("key_id"));
				comVO.setKey_date(rs.getDate("key_date"));
				
			}

			// Handle any driver errors
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
		return comVO;
	}

	@Override
	public List<ComVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<ComVO> list = new ArrayList<ComVO>();
		ComVO comVO=null;
		try {
			con = ds.getConnection();		
			pstmt = con.prepareStatement(GET_ALL_STMT);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				comVO = new ComVO();
				comVO.setCom_id(rs.getString("com_id"));
				comVO.setCom_name(rs.getString("com_name"));
				comVO.setCom_um(rs.getString("com_um"));
				comVO.setCom_phone(rs.getString("com_phone"));
				comVO.setCom_addr(rs.getString("com_addr"));
				comVO.setCom_mail(rs.getString("com_mail"));
				comVO.setPicture(rs.getString("picture"));
				comVO.setKey_id(rs.getString("key_id"));
				comVO.setKey_date(rs.getDate("key_date"));
				list.add(comVO);
				
			}

			// Handle any driver errors
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
	public List<ComVO> findByName(String com_name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<ComVO> list = new ArrayList<ComVO>();
		ComVO comVO=null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_ByName);

			pstmt.setString(1,"%"+com_name+"%");
		

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				comVO = new ComVO();
				comVO.setCom_id(rs.getString("com_id"));
				comVO.setCom_name(rs.getString("com_name"));
				comVO.setCom_um(rs.getString("com_um"));
				comVO.setCom_phone(rs.getString("com_phone"));
				comVO.setCom_addr(rs.getString("com_addr"));
				comVO.setCom_mail(rs.getString("com_mail"));
				comVO.setPicture(rs.getString("picture"));
				comVO.setKey_id(rs.getString("key_id"));
				comVO.setKey_date(rs.getDate("key_date"));
				
				list.add(comVO);
			}
			
			// Handle any driver errors
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
	
}
