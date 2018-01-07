package com.member.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberJDBCDAO implements MemberDAO_interface {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=pos";
	String userid = "sa";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO MEMBER (mem_id,mem_pwd,mem_name,mem_idnum,mem_bday,mem_phone,mem_addr,mem_mail,mem_due,key_id,key_date,mem_um) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE MEMBER set mem_id=?,mem_pwd=?,mem_name=?,mem_idnum=?,mem_bday=?,mem_phone=?,mem_addr=?,mem_mail=?,mem_due=?,key_id=?,key_date=?,mem_um=? where mem_id = ?";
	private static final String DELETE = "DELETE FROM MEMBER where mem_id = ?";
	private static final String GET_ONE_STMT = "SELECT mem_id,mem_pwd,mem_name,mem_idnum,mem_bday,mem_phone,mem_addr,mem_mail,mem_due,key_id,key_date,mem_um FROM MEMBER where mem_id = ?";
	private static final String GET_ALL_STMT = "SELECT mem_id,mem_pwd,mem_name,mem_idnum,mem_bday,mem_phone,mem_addr,mem_mail,mem_due,key_id,key_date,mem_um FROM MEMBER";
	private static final String GET_IDS_STMT = "SELECT mem_id,mem_pwd,mem_name,mem_idnum,mem_bday,mem_phone,mem_addr,mem_mail,mem_due,key_id,key_date,mem_um FROM MEMBER where mem_id between ? and ?";
	private static final String GET_K_DATES_STMT = "SELECT mem_id,mem_pwd,mem_name,mem_idnum,mem_bday,mem_phone,mem_addr,mem_mail,mem_due,key_id,key_date,mem_um FROM MEMBER where key_date between ? and ?";
	
	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memberVO.getMem_id());
			pstmt.setString(2, memberVO.getMem_pwd());
			pstmt.setString(3, memberVO.getMem_name());
			pstmt.setString(4, memberVO.getMem_idnum());
			pstmt.setDate(5, memberVO.getMem_bday());
			pstmt.setString(6, memberVO.getMem_phone());
			pstmt.setString(7, memberVO.getMem_addr());
			pstmt.setString(8, memberVO.getMem_mail());
			pstmt.setDate(9, memberVO.getMem_due());
			pstmt.setString(10, memberVO.getKey_id());
			pstmt.setDate(11, memberVO.getKey_date());
			pstmt.setString(12, memberVO.getMem_um());

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
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberVO.getMem_id());
			pstmt.setString(2, memberVO.getMem_pwd());
			pstmt.setString(3, memberVO.getMem_name());
			pstmt.setString(4, memberVO.getMem_idnum());
			pstmt.setDate(5, memberVO.getMem_bday());
			pstmt.setString(6, memberVO.getMem_phone());
			pstmt.setString(7, memberVO.getMem_addr());
			pstmt.setString(8, memberVO.getMem_mail());
			pstmt.setDate(9, memberVO.getMem_due());
			pstmt.setString(10, memberVO.getKey_id());
			pstmt.setDate(11, memberVO.getKey_date());
			pstmt.setString(12, memberVO.getMem_um());
			pstmt.setString(13,"M00021");  //�g��
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
	public void delete(String mem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, mem_id);
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
	public MemberVO findByPrimaryKey(String mem_id) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_id(rs.getString("mem_id"));
				memberVO.setMem_pwd(rs.getString("mem_pwd"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_idnum(rs.getString("mem_idnum"));
				memberVO.setMem_bday(rs.getDate("mem_bday"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				memberVO.setMem_addr(rs.getString("mem_addr"));
				memberVO.setMem_mail(rs.getString("mem_mail"));
				memberVO.setMem_due(rs.getDate("mem_due"));
				memberVO.setKey_id(rs.getString("key_id"));
				memberVO.setKey_date(rs.getDate("key_date"));
				memberVO.setMem_um(rs.getString("mem_um"));
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_id(rs.getString("mem_id"));
				memberVO.setMem_pwd(rs.getString("mem_pwd"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_idnum(rs.getString("mem_idnum"));
				memberVO.setMem_bday(rs.getDate("mem_bday"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				memberVO.setMem_addr(rs.getString("mem_addr"));
				memberVO.setMem_mail(rs.getString("mem_mail"));
				memberVO.setMem_due(rs.getDate("mem_due"));
				memberVO.setKey_id(rs.getString("key_id"));
				memberVO.setKey_date(rs.getDate("key_date"));
				memberVO.setMem_um(rs.getString("mem_um"));
				list.add(memberVO);
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
		return list;
	}
	
	public List<MemberVO> getIds(String mem_id_1,String mem_id_2){
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_IDS_STMT);
			pstmt.setString(1, mem_id_1);
			pstmt.setString(2, mem_id_2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_id(rs.getString("mem_id"));
				memberVO.setMem_pwd(rs.getString("mem_pwd"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_idnum(rs.getString("mem_idnum"));
				memberVO.setMem_bday(rs.getDate("mem_bday"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				memberVO.setMem_addr(rs.getString("mem_addr"));
				memberVO.setMem_mail(rs.getString("mem_mail"));
				memberVO.setMem_due(rs.getDate("mem_due"));
				memberVO.setKey_id(rs.getString("key_id"));
				memberVO.setKey_date(rs.getDate("key_date"));
				memberVO.setMem_um(rs.getString("mem_um"));
				list.add(memberVO);
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
		return list;
	}
	
	public List<MemberVO> getDates(Date key_date_1,Date key_date_2){
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_K_DATES_STMT);
			pstmt.setDate(1, key_date_1);
			pstmt.setDate(2, key_date_2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_id(rs.getString("mem_id"));
				memberVO.setMem_pwd(rs.getString("mem_pwd"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_idnum(rs.getString("mem_idnum"));
				memberVO.setMem_bday(rs.getDate("mem_bday"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				memberVO.setMem_addr(rs.getString("mem_addr"));
				memberVO.setMem_mail(rs.getString("mem_mail"));
				memberVO.setMem_due(rs.getDate("mem_due"));
				memberVO.setKey_id(rs.getString("key_id"));
				memberVO.setKey_date(rs.getDate("key_date"));
				memberVO.setMem_um(rs.getString("mem_um"));
				list.add(memberVO);
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
		return list;
	}
	
	
	public static void main(String[] args) {

//		MemberJDBCDAO dao = new MemberJDBCDAO();

		//insert
//		MemberVO memberVO1 = new MemberVO();
//		memberVO1.setMem_id("M00022");
//		memberVO1.setMem_pwd("dsfsdlofhl");
//		memberVO1.setMem_name("test");
//		memberVO1.setMem_idnum("A126445872");
//		memberVO1.setMem_bday(Date.valueOf("1999-01-01"));
//		memberVO1.setMem_phone("0987654321");
//		memberVO1.setMem_addr("taipei");
//		memberVO1.setMem_mail("kalhfl@gmail.om");
//		memberVO1.setMem_due(Date.valueOf("1999-01-01"));
//		memberVO1.setKey_id("E00002");
//		memberVO1.setKey_date(Date.valueOf("1999-01-01"));
//		memberVO1.setMem_um("12345678");
//		dao.insert(memberVO1);

		//update
//		MemberVO memberVO2 = new MemberVO();
//		memberVO2.setMem_id("M00023");
//		memberVO2.setMem_pwd("dsfsdlofhl");
//		memberVO2.setMem_name("test");
//		memberVO2.setMem_idnum("A126445872");
//		memberVO2.setMem_bday(Date.valueOf("1999-01-01"));
//		memberVO2.setMem_phone("0987654321");
//		memberVO2.setMem_addr("taipei");
//		memberVO2.setMem_mail("kalhfl@gmail.om");
//		memberVO2.setMem_due(Date.valueOf("1999-01-01"));
//		memberVO2.setKey_id("E00002");
//		memberVO2.setKey_date(Date.valueOf("1999-01-01"));
//		memberVO2.setMem_um("12345678");
//		dao.update(memberVO2);

		//delete
//		dao.delete("M00022");

		//getone
//		MemberVO memberVO3 = dao.findByPrimaryKey("M00022");
//		System.out.print(memberVO3.getMem_id() + ",");
//		System.out.print(memberVO3.getMem_pwd() + ",");
//		System.out.print(memberVO3.getMem_name() + ",");
//		System.out.print(memberVO3.getMem_idnum() + ",");
//		System.out.print(memberVO3.getMem_bday() + ",");
//		System.out.print(memberVO3.getMem_phone() + ",");
//		System.out.print(memberVO3.getMem_addr() + ",");
//		System.out.print(memberVO3.getMem_mail() + ",");
//		System.out.print(memberVO3.getMem_due() + ",");
//		System.out.print(memberVO3.getKey_id() + ",");
//		System.out.print(memberVO3.getKey_date() + ",");
//		System.out.println(memberVO3.getMem_um());
//		System.out.println("---------------------");

		//getall
//		List<MemberVO> list = dao.getAll();
//		for (MemberVO memberVO4 : list) {
//			System.out.print(memberVO4.getMem_id() + ",");
//			System.out.print(memberVO4.getMem_pwd() + ",");
//			System.out.print(memberVO4.getMem_name() + ",");
//			System.out.print(memberVO4.getMem_idnum() + ",");
//			System.out.print(memberVO4.getMem_bday() + ",");
//			System.out.print(memberVO4.getMem_phone() + ",");
//			System.out.print(memberVO4.getMem_addr() + ",");
//			System.out.print(memberVO4.getMem_mail() + ",");
//			System.out.print(memberVO4.getMem_due() + ",");
//			System.out.print(memberVO4.getKey_id() + ",");
//			System.out.print(memberVO4.getKey_date() + ",");
//			System.out.print(memberVO4.getMem_um());
//			System.out.println();
//		}
	}	
	
}
