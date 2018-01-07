package com.product.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.company.model.ComVO;

public class ProdJDBCDAO implements ProdDAO_interface {

	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=pos";
	String userid = "sa";
	String passwd = "P@ssw0rd";
	
	
	private static final String INSERT_STMT = "INSERT INTO PRODUCT (prod_id, prod_name,com_id, prod_group, prod_mkprice, prod_cost, prod_stock,prod_q_safty,prod_spec, picture,remark)"
			+ "VALUES ([dbo].[GetProdID](), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(ProdVO prodVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String prod_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,prod_id );
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
	public ProdVO findByPrimaryKey(String prod_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ProdVO prodVO = null;
	
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		return prodVO;
	}

	@Override
	public List<ProdVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO=null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
		
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
	public List<ProdVO> findByName(String prod_name) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO=null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_ByName);

			pstmt.setString(1,prod_name);
		
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
	public List<ProdVO> findByGroup(String prod_group) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO=null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	public static void main(String[] args) throws IOException {
		ProdJDBCDAO dao = new ProdJDBCDAO();
		ProdVO prodVO = new ProdVO();
		List<ProdVO> list = new ArrayList<ProdVO>();
		
//		�s�W
		
//		// Ū���Ϥ��s�Jdata�}�C
		File f = new File("C:\\Users\\student\\Desktop\\res\\iphone.jpg");
		FileInputStream fis = new FileInputStream(f);
		byte[] data = new byte[fis.available()];
		fis.read(data);

		fis.close();
				
//		prodVO.setProd_name("LG �G���q�� 23�T");
//		prodVO.setCom_id("C00001");
//		prodVO.setProd_group("�a�q");
//		prodVO.setProd_mkprice(6999);
//		prodVO.setProd_cost(4000);
//		prodVO.setProd_stock(3);
//		prodVO.setProd_q_safty(1);
//		prodVO.setProd_spec("Full HD");
//		prodVO.setPicture(Base64.getEncoder().encodeToString(data));
//		prodVO.setRemark("�ɴ��P�P");
//		
//		dao.insert(prodVO);
		
//		--------------------------------------------------------------------------
		 
//		 �ק�
		 
//		prodVO.setProd_id("P00006");
//		prodVO.setProd_name("LG �G���q�� 23�T");
//		prodVO.setCom_id("C00001");
//		prodVO.setProd_group("�a�q");
//		prodVO.setProd_mkprice(6999);
//		prodVO.setProd_cost(4000);
//		prodVO.setProd_stock(3);
//		prodVO.setProd_q_safty(1);
//		prodVO.setProd_spec("Full HD");
//		prodVO.setPicture(Base64.getEncoder().encodeToString(data));
//		prodVO.setRemark("�ɴ��P�P");
//		prodVO.setStatus("N");
//		 
//		dao.update(prodVO);
		

//		--------------------------------------------------------------------------
//		�R��
		
//		dao.delete("P00009");
		
//		--------------------------------------------------------------------------
//		�̷Ӱӫ~�s���d��
		
//		prodVO = dao.findByPrimaryKey("P00001");
//		list.add(prodVO);
		
		
//		--------------------------------------------------------------------------
//      �j�M�����ӫ~

//		list=dao.getAll();
		
//		--------------------------------------------------------------------------
//		�ӫ~�W������r�d��
//		list=dao.findByName("%iphone%");
		
//		--------------------------------------------------------------------------
//		�ӫ~��������r�d��
		list=dao.findByGroup("%���%");
		
		for(ProdVO prodVO2 :list){
		System.out.println(prodVO2.getProd_id());
		System.out.println(prodVO2.getProd_name());
		System.out.println(prodVO2.getCom_id());
		System.out.println(prodVO2.getProd_group());
		System.out.println(prodVO2.getProd_mkprice());
		System.out.println(prodVO2.getProd_cost());
		System.out.println(prodVO2.getProd_stock());
		System.out.println(prodVO2.getProd_q_safty());
		System.out.println(prodVO2.getProd_spec());
		System.out.println(prodVO2.getRemark());
		System.out.println(prodVO2.getStatus());
		System.out.println("---------------------------");
	}
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
