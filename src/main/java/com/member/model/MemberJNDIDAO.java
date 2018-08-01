package com.member.model;

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

public class MemberJNDIDAO implements MemberDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/PosDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO MEMBER (mem_id,mem_pwd,mem_name,mem_idnum,mem_bday,mem_phone,mem_addr,mem_mail,mem_due,key_id,key_date,mem_um) VALUES ([dbo].[GetMemID](),?,?,?,?,?,?,?,?,?,?,?)";
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
//			pstmt.setString(1, memberVO.getMem_id());
			pstmt.setString(1, memberVO.getMem_pwd());
			pstmt.setString(2, memberVO.getMem_name());
			pstmt.setString(3, memberVO.getMem_idnum());
			pstmt.setDate(4, memberVO.getMem_bday());
			pstmt.setString(5, memberVO.getMem_phone());
			pstmt.setString(6, memberVO.getMem_addr());
			pstmt.setString(7, memberVO.getMem_mail());
			pstmt.setDate(8, memberVO.getMem_due());
			pstmt.setString(9, memberVO.getKey_id());
			pstmt.setDate(10, memberVO.getKey_date());
			pstmt.setString(11, memberVO.getMem_um());

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
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
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
			pstmt.setString(13, memberVO.getMem_id()); 
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
	public void delete(String mem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, mem_id);
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
	public MemberVO findByPrimaryKey(String mem_id) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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


public List<MemberVO> getIds(String mem_id_1,String mem_id_2){
	List<MemberVO> list = new ArrayList<MemberVO>();
	MemberVO memberVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		con = ds.getConnection();
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

		con = ds.getConnection();
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

}