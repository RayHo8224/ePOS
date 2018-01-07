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
import java.util.Base64;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmpJNDIDAO implements EmpDAO_interface {

	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ePOS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE (emp_id, emp_pwd, emp_name, emp_sex,emp_idnum, emp_addr,emp_mail, emp_phone, emp_bday, emp_reg,emp_due,picture,[key_id],key_date)"
			+ "VALUES ([dbo].[GetEmpID](), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE EMPLOYEE set "
			+ "emp_id=?, emp_pwd=?, emp_name=?, emp_sex=?, emp_idnum=?, emp_addr=?, emp_mail=?, emp_phone=?, emp_bday=?, emp_reg=?, emp_due=?, picture=?, [key_id]=?, key_date=? ,pass_code=? where emp_id = ?";

	private static final String DELETE = "DELETE FROM EMPLOYEE where emp_id = ?";

	private static final String GET_ONE_STMT = "SELECT * FROM EMPLOYEE where emp_id = ?";

	private static final String GET_ALL_STMT = "SELECT * FROM EMPLOYEE order by emp_id";
	
	private static final String GET_ONE_STMT_ByName = "SELECT * FROM EMPLOYEE where emp_name like ?";

	private static final String UPDATE_PASS_CODE="UPDATE EMPLOYEE SET pass_code=? where emp_id=?";


	@Override
	public void insert(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

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

			con = ds.getConnection();
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
			pstmt.setString(15, empVO.getPass_code());

			pstmt.setString(16,empVO.getEmp_id()); // ����蠟���u�s��

			pstmt.executeUpdate();

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, emp_id); // ����蠟���u�s��

			pstmt.executeUpdate();

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
	public EmpVO findByPrimaryKey(String emp_id){
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
				empVO.setPicture(rs.getString("picture"));
				empVO.setKey_id(rs.getString("key_id"));
				empVO.setKey_date(rs.getDate("key_date"));
				
				
				// byte[] bytePic = empVO.getPicture();
				// Base64.Decoder decoder = Base64.getDecoder();
				// Base64.Encoder encoder = Base64.getEncoder();
				// String picture = encoder.encodeToString(bytePic);

			}

			// getIMG(empVO);

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
	public List<EmpVO> getAll() throws IOException {

		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

				// if(empVO.getPicture()!=null)
				// getIMG(empVO);
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
	public List<EmpVO> findByName(String emp_name) {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {
				
			con = ds.getConnection();
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
			}
		
		return list;
		
	}
	
	@Override
	public void setPassCode(String pass_code,String emp_id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PASS_CODE);

				pstmt.setString(1, pass_code);
				pstmt.setString(2, emp_id);
				pstmt.executeUpdate();
				}catch (SQLException se) {
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


	// public void getIMG(EmpVO empVO) throws IOException{
	// FileOutputStream fos = new
	// FileOutputStream("C:\\Framework\\workspace\\pos\\WebContent\\images\\"+empVO.getEmp_id()+".jpg");
	// byte[] empImg = empVO.getPicture();
	// fos.write(empImg);
	// fos.close();
	// }

}
