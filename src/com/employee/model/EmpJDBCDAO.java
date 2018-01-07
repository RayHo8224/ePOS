package com.employee.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmpJDBCDAO implements EmpDAO_interface {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=pos";
	String userid = "sa";
	String passwd = "P@ssw0rd";

	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE (emp_id, emp_pwd, emp_name, emp_sex,emp_idnum, emp_addr,emp_mail, emp_phone, emp_bday, emp_reg,emp_due,picture,[key_id],key_date)"
			+ "VALUES ([dbo].[GetEmpID](), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE EMPLOYEE set "
			+ "emp_id=?, emp_pwd=?, emp_name=?, emp_sex=?, emp_idnum=?, emp_addr=?, emp_mail=?, emp_phone=?, emp_bday=?, emp_reg=?, emp_due=?, picture=?, [key_id]=?, key_date=? where emp_id = ?";

	private static final String DELETE = "DELETE FROM EMPLOYEE where emp_id = ?";

	private static final String GET_ONE_STMT = "SELECT * FROM EMPLOYEE where emp_id = ?";
	
	private static final String GET_ONE_STMT_ByName = "SELECT * FROM EMPLOYEE where emp_name like ?";

	private static final String GET_ALL_STMT = "SELECT * FROM EMPLOYEE order by emp_id";
	
	private static final String UPDATE_PASS_CODE="UPDATE EMPLOYEE SET pass_code=? where emp_id=?";

	@Override
	public void insert(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setString(1, empVO.getEmp_id());
			pstmt.setString(1, empVO.getEmp_pwd());
			pstmt.setString(2, empVO.getEmp_name());
			pstmt.setString(3, empVO.getEmp_sex());
			pstmt.setString(4, empVO.getEmp_idnum());
			pstmt.setString(5, empVO.getEmp_addr());
			pstmt.setString(6, empVO.getEmp_mail());
			pstmt.setString(7, empVO.getEmp_phone());
			pstmt.setDate(8, empVO.getEmp_bday());
			pstmt.setDate(9, empVO.getEmp_reg());
			pstmt.setDate(10, empVO.getEmp_due());
			pstmt.setString(11, empVO.getPicture());
			pstmt.setString(12, empVO.getKey_id());
			pstmt.setDate(13, empVO.getKey_date());

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
	public void update(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmp_id());
			pstmt.setString(2, empVO.getEmp_pwd());
			pstmt.setString(3, empVO.getEmp_name());
			pstmt.setString(4, empVO.getEmp_sex());
			pstmt.setString(5, empVO.getEmp_idnum());
			pstmt.setString(6, empVO.getEmp_addr());
			pstmt.setString(7, empVO.getEmp_mail());
			pstmt.setString(8, empVO.getEmp_phone());
			pstmt.setDate(9, empVO.getEmp_bday());
			pstmt.setDate(10, empVO.getEmp_reg());
			pstmt.setDate(11, empVO.getEmp_due());
			pstmt.setString(12, empVO.getPicture());
			pstmt.setString(13, empVO.getKey_id());
			pstmt.setDate(14, empVO.getKey_date());

			pstmt.setString(15, "E00006"); // ����蠟���u�s��

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
	public void delete(String emp_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, emp_id); // ����蠟���u�s��

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
	public EmpVO findByPrimaryKey(String emp_id) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, emp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				empVO = new EmpVO();
				empVO.setEmp_id(rs.getString("emp_id"));
				empVO.setEmp_pwd(rs.getString("emp_pwd"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_sex(rs.getString("emp_sex"));
				empVO.setEmp_idnum(rs.getString("emp_idnum"));
				empVO.setEmp_addr(rs.getString("emp_addr"));
				empVO.setEmp_mail(rs.getString("emp_mail"));
				empVO.setEmp_phone(rs.getString("emp_phone"));
				empVO.setEmp_bday(rs.getDate("emp_bday"));
				empVO.setEmp_reg(rs.getDate("emp_reg"));
				empVO.setEmp_due(rs.getDate("emp_due"));
				empVO.setPicture(rs.getString("picture"));
				empVO.setKey_id(rs.getString("key_id"));
				empVO.setKey_date(rs.getDate("key_date"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {

		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				empVO = new EmpVO();
				empVO.setEmp_id(rs.getString("emp_id"));
				empVO.setEmp_pwd(rs.getString("emp_pwd"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_sex(rs.getString("emp_sex"));
				empVO.setEmp_idnum(rs.getString("emp_idnum"));
				empVO.setEmp_addr(rs.getString("emp_addr"));
				empVO.setEmp_mail(rs.getString("emp_mail"));
				empVO.setEmp_phone(rs.getString("emp_phone"));
				empVO.setEmp_bday(rs.getDate("emp_bday"));
				empVO.setEmp_reg(rs.getDate("emp_reg"));
				empVO.setEmp_due(rs.getDate("emp_due"));
				empVO.setPicture(rs.getString("picture"));
				empVO.setKey_id(rs.getString("key_id"));
				empVO.setKey_date(rs.getDate("key_date"));
				list.add(empVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return list;
	}
	
	@Override
	public List<EmpVO> findByName(String emp_name) {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT_ByName);
				pstmt.setString(1, "%"+emp_name+"%");
				
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO �]�٬� Domain objects
					empVO = new EmpVO();
					empVO.setEmp_id(rs.getString("emp_id"));
					empVO.setEmp_pwd(rs.getString("emp_pwd"));
					empVO.setEmp_name(rs.getString("emp_name"));
					empVO.setEmp_sex(rs.getString("emp_sex"));
					empVO.setEmp_idnum(rs.getString("emp_idnum"));
					empVO.setEmp_addr(rs.getString("emp_addr"));
					empVO.setEmp_mail(rs.getString("emp_mail"));
					empVO.setEmp_phone(rs.getString("emp_phone"));
					empVO.setEmp_bday(rs.getDate("emp_bday"));
					empVO.setEmp_reg(rs.getDate("emp_reg"));
					empVO.setEmp_due(rs.getDate("emp_due"));
					empVO.setPicture(rs.getString("picture"));
					empVO.setKey_id(rs.getString("key_id"));
					empVO.setKey_date(rs.getDate("key_date"));
					list.add(empVO); // Store the row in the list
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		return list;
		
	}
	
	@Override
	public void setPassCode(String pass_code,String emp_id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_PASS_CODE);
				pstmt.setString(1, pass_code);
				pstmt.setString(2, emp_id);
				pstmt.executeUpdate();
				}catch (ClassNotFoundException e) {
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
	
	

	public static void main(String[] args) throws IOException {

		EmpJDBCDAO dao = new EmpJDBCDAO();
		EmpVO empVO = new EmpVO();
		String emp_id;

//		// Ū���Ϥ��s�Jdata�}�C
//		File f = new File("C:\\Framework\\workspace\\epos\\res\\images\\E0006.jpg");
//		FileInputStream fis = new FileInputStream(f);
//		byte[] data = new byte[fis.available()];
//		fis.read(data);
//
//		fis.close();

		// ------------------------------------------------------------------------------

		// �s�W

//		 empVO.setEmp_id("E0006");
//		 empVO.setEmp_pwd("9999");
//		 empVO.setEmp_name("�P�x��");
//		 empVO.setEmp_sex("�k");
//		 empVO.setEmp_idnum("A126780019");
//		 empVO.setEmp_addr("�x�_�����s�Ϥ@��");
//		 empVO.setEmp_mail("ho1234@yahoo.com.tw");
//		 empVO.setEmp_phone("0988705215");
//		 empVO.setEmp_bday(Date.valueOf("1993-02-05"));
//		 empVO.setEmp_reg(Date.valueOf("2015-06-27"));
//		 empVO.setEmp_due(null);
//		 empVO.setPicture(data);
//		 empVO.setKey_id("E00002");
//		 empVO.setKey_date(Date.valueOf("2015-06-27"));
//		
//		 dao.insert(empVO);

		// ------------------------------------------------------------------------------

		// �ק�

//		 empVO.setEmp_id("E00006");
//		 empVO.setEmp_pwd("9999");
//		 empVO.setEmp_name("���Ǥ@");
//		 empVO.setEmp_sex("�k");
//		 empVO.setEmp_idnum("A126780019");
//		 empVO.setEmp_addr("�x�_�����s�Ϥ@��");
//		 empVO.setEmp_mail("ho1234@yahoo.com.tw");
//		 empVO.setEmp_phone("0988705215");
//		 empVO.setEmp_bday(Date.valueOf("1993-02-05"));
//		 empVO.setEmp_reg(Date.valueOf("2015-06-27"));
//		 empVO.setEmp_due(null);
//		 empVO.setPicture(null);
//		 empVO.setKey_id("E00002");
//		 empVO.setKey_date(Date.valueOf("2015-06-27"));
//		
//		 dao.update(empVO);
		
		// ------------------------------------------------------------------------------

		// �R��
		//
		// emp_id="E0006";
		// dao.delete(emp_id);

		// ------------------------------------------------------------------------------

		// �̷�emp_id�j�M
		
//		EmpVO empVO1 = dao.findByPrimaryKey("E0006");

		// Ū���Ӥ��s����w���|
//		FileOutputStream fos = new FileOutputStream("C:/Framework/workspace/epos/res/p2.jpg");
//		byte[] data1 = empVO1.getPicture();
//		fos.write(data1);
//		fos.close();
//
//		System.out.println(empVO1.getEmp_id());
//		System.out.println(empVO1.getEmp_pwd());
//		System.out.println(empVO1.getEmp_name());
//		System.out.println(empVO1.getEmp_sex());
//		System.out.println(empVO1.getEmp_idnum());
//		System.out.println(empVO1.getEmp_addr());
//		System.out.println(empVO1.getEmp_mail());
//		System.out.println(empVO1.getEmp_phone());
//		System.out.println(empVO1.getEmp_bday());
//		System.out.println(empVO1.getEmp_reg());
//		System.out.println(empVO1.getEmp_due());
//		System.out.println(empVO1.getKey_id());
//		System.out.println(empVO1.getKey_date());

// ------------------------------------------------------------------------------

		//�j�M����
		
//		List<EmpVO> list = dao.getAll();
//		for(EmpVO empVO2 :list){
//			System.out.println(empVO2.getEmp_id());
//			System.out.println(empVO2.getEmp_pwd());
//			System.out.println(empVO2.getEmp_name());
//			System.out.println(empVO2.getEmp_idnum());
//			System.out.println(empVO2.getEmp_addr());
//			System.out.println(empVO2.getEmp_mail());
//			System.out.println(empVO2.getEmp_phone());
//			System.out.println(empVO2.getEmp_bday());
//			System.out.println(empVO2.getEmp_reg());
//			System.out.println(empVO2.getEmp_due());
//			System.out.println(empVO2.getKey_id());
//			System.out.println(empVO2.getKey_date());
//			System.out.println("---------------------------");
//		}

		
		
		// ------------------------------------------------------------------------------

				//�ѩm�W�j�M
				
		        
//		
//				List<EmpVO> list = dao.findByName("��");
//				
//				for(EmpVO empVO2 :list){
//					System.out.println(empVO2.getEmp_id());
//					System.out.println(empVO2.getEmp_pwd());
//					System.out.println(empVO2.getEmp_name());
//					System.out.println(empVO2.getEmp_sex());
//					System.out.println(empVO2.getEmp_idnum());
//					System.out.println(empVO2.getEmp_addr());
//					System.out.println(empVO2.getEmp_mail());
//					System.out.println(empVO2.getEmp_phone());
//					System.out.println(empVO2.getEmp_bday());
//					System.out.println(empVO2.getEmp_reg());
//					System.out.println(empVO2.getEmp_due());
//					System.out.println(empVO2.getKey_id());
//					System.out.println(empVO2.getKey_date());
//					System.out.println("---------------------------");
//				}

		// ------------------------------------------------------------------------------
		
		
				//�ᤩ�v��
				
		emp_id="E00002";
		String pass_code="A";
				dao.setPassCode(pass_code, emp_id);
	}

}
