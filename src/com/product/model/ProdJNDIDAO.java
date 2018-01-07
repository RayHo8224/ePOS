package com.product.model;

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


public class ProdJNDIDAO implements ProdDAO_interface {

	private static DataSource ds=null;
	static{
		try {
			Context ctx = new InitialContext();
			ds=(DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO PRODUCT (prod_id, prod_name,com_id, prod_group, prod_mkprice, prod_cost, prod_stock,prod_q_safty,prod_spec, picture,remark,status)"
			+ "VALUES ([dbo].[GetProdID](), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE PRODUCT set "
			+ "prod_id=?, prod_name=?, com_id=?, prod_group=?, prod_mkprice=?, prod_cost=?, prod_stock=?, prod_q_safty=?, prod_spec=?, picture=?, remark=?, status=? where prod_id = ?";

	private static final String DELETE = "DELETE FROM PRODUCT where prod_id = ?";

	private static final String GET_ONE_STMT = "SELECT * FROM PRODUCT where prod_id = ?";
	
	private static final String GET_ONE_STMT_ByName = "SELECT * FROM PRODUCT where prod_name like ?";

	private static final String GET_ONE_STMT_ByGroup = "SELECT * FROM PRODUCT where prod_group like ?";

	private static final String GET_ALL_STMT = "SELECT * FROM PRODUCT order by prod_id";
	

	@Override
	public void insert(ProdVO prodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setString(1, prodVO.getProd_name());
			pstmt.setString(2, prodVO.getCom_id());
			pstmt.setString(3, prodVO.getProd_group());
			pstmt.setInt(4, prodVO.getProd_mkprice());
			pstmt.setInt(5, prodVO.getProd_cost());
			pstmt.setInt(6, prodVO.getProd_stock());
			pstmt.setInt(7, prodVO.getProd_q_safty());
			pstmt.setString(8, prodVO.getProd_spec());
			pstmt.setString(9, prodVO.getPicture());
			pstmt.setString(10, prodVO.getRemark());
			pstmt.setString(11, prodVO.getStatus());

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
	public void update(ProdVO prodVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
		
			pstmt.setString(1, prodVO.getProd_id());
			pstmt.setString(2, prodVO.getProd_name());
			pstmt.setString(3, prodVO.getCom_id());
			pstmt.setString(4, prodVO.getProd_group());
			pstmt.setInt(5, prodVO.getProd_mkprice());
			pstmt.setInt(6, prodVO.getProd_cost());
			pstmt.setInt(7, prodVO.getProd_stock());
			pstmt.setInt(8, prodVO.getProd_q_safty());
			pstmt.setString(9, prodVO.getProd_spec());
			pstmt.setString(10, prodVO.getPicture());
			pstmt.setString(11, prodVO.getRemark());
			pstmt.setString(12, prodVO.getStatus());

			pstmt.setString(13, prodVO.getProd_id());

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
	public void delete(String prod_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,prod_id );
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
	public ProdVO findByPrimaryKey(String prod_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ProdVO prodVO = null;
	
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,prod_id);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				prodVO = new ProdVO();

				prodVO.setProd_id(rs.getString("prod_id"));
				prodVO.setProd_name(rs.getString("prod_name"));
				prodVO.setCom_id(rs.getString("com_id"));
				prodVO.setProd_group(rs.getString("prod_group"));
				prodVO.setProd_mkprice(rs.getInt("prod_mkprice"));
				prodVO.setProd_cost(rs.getInt("prod_cost"));
				prodVO.setProd_stock(rs.getInt("prod_stock"));
				prodVO.setProd_q_safty(rs.getInt("prod_q_safty"));
				prodVO.setProd_spec(rs.getString("prod_spec"));
				prodVO.setPicture(rs.getString("picture"));
				prodVO.setRemark(rs.getString("remark"));
				prodVO.setStatus(rs.getString("status"));

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
		return prodVO;
	}

	@Override
	public List<ProdVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO=null;
		try {

			con = ds.getConnection();
		
			pstmt = con.prepareStatement(GET_ALL_STMT);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				prodVO = new ProdVO();

				prodVO.setProd_id(rs.getString("prod_id"));
				prodVO.setProd_name(rs.getString("prod_name"));
				prodVO.setCom_id(rs.getString("com_id"));
				prodVO.setProd_group(rs.getString("prod_group"));
				prodVO.setProd_mkprice(rs.getInt("prod_mkprice"));
				prodVO.setProd_cost(rs.getInt("prod_cost"));
				prodVO.setProd_stock(rs.getInt("prod_stock"));
				prodVO.setProd_q_safty(rs.getInt("prod_q_safty"));
				prodVO.setProd_spec(rs.getString("prod_spec"));
				prodVO.setPicture(rs.getString("picture"));
				prodVO.setRemark(rs.getString("remark"));
				prodVO.setStatus(rs.getString("status"));
				list.add(prodVO);
				
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
	public List<ProdVO> findByName(String prod_name) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO=null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_ByName);

			pstmt.setString(1,"%"+prod_name+"%");
		
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				prodVO = new ProdVO();

				prodVO.setProd_id(rs.getString("prod_id"));
				prodVO.setProd_name(rs.getString("prod_name"));
				prodVO.setCom_id(rs.getString("com_id"));
				prodVO.setProd_group(rs.getString("prod_group"));
				prodVO.setProd_mkprice(rs.getInt("prod_mkprice"));
				prodVO.setProd_cost(rs.getInt("prod_cost"));
				prodVO.setProd_stock(rs.getInt("prod_stock"));
				prodVO.setProd_q_safty(rs.getInt("prod_q_safty"));
				prodVO.setProd_spec(rs.getString("prod_spec"));
				prodVO.setPicture(rs.getString("picture"));
				prodVO.setRemark(rs.getString("remark"));
				prodVO.setStatus(rs.getString("status"));
				list.add(prodVO);
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
	public List<ProdVO> findByGroup(String prod_group) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO=null;
		try {

			ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_ByGroup);

			pstmt.setString(1,prod_group);
		
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				prodVO = new ProdVO();

				prodVO.setProd_id(rs.getString("prod_id"));
				prodVO.setProd_name(rs.getString("prod_name"));
				prodVO.setCom_id(rs.getString("com_id"));
				prodVO.setProd_group(rs.getString("prod_group"));
				prodVO.setProd_mkprice(rs.getInt("prod_mkprice"));
				prodVO.setProd_cost(rs.getInt("prod_cost"));
				prodVO.setProd_stock(rs.getInt("prod_stock"));
				prodVO.setProd_q_safty(rs.getInt("prod_q_safty"));
				prodVO.setProd_spec(rs.getString("prod_spec"));
				prodVO.setPicture(rs.getString("picture"));
				prodVO.setRemark(rs.getString("remark"));
				prodVO.setStatus(rs.getString("status"));
				list.add(prodVO);
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
	public void update2(Integer prod_stock, String prod_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProdVO> selectByGroup() {
		// TODO Auto-generated method stub
		return null;
	}


}
