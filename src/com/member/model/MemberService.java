package com.member.model;

import java.sql.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.coupon.model.CouponDAO_interface;

public class MemberService {

	private MemberDAO_interface dao;
	
	public MemberService(){
//		dao = new MemberHIBERNATEDAO();
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(MemberDAO_interface) context.getBean("MemberDAO");
	}
	public MemberVO addMem(String mem_pwd,String mem_name,String mem_idnum,
			Date mem_bday,String mem_phone,String mem_addr,String mem_mail,
			Date mem_due,String key_id,Date key_date,String mem_um){
		MemberVO mem = new MemberVO();
//		mem.setMem_id(mem_id);
		mem.setMem_pwd(mem_pwd);
		mem.setMem_name(mem_name);
		mem.setMem_idnum(mem_idnum);
		mem.setMem_bday(mem_bday);
		mem.setMem_phone(mem_phone);
		mem.setMem_addr(mem_addr);
		mem.setMem_mail(mem_mail);
		mem.setMem_due(mem_due);
		mem.setKey_id(key_id);
		mem.setKey_date(key_date);
		mem.setMem_um(mem_um);
		dao.insert(mem);
		return mem;
	}

	public MemberVO updMem(String mem_id,String mem_pwd,String mem_name,String mem_idnum,
			Date mem_bday,String mem_phone,String mem_addr,String mem_mail,Date mem_due,
			String key_id,Date key_date,String mem_um){
		MemberVO mem = new MemberVO();
		mem.setMem_id(mem_id);
		mem.setMem_pwd(mem_pwd);
		mem.setMem_name(mem_name);
		mem.setMem_idnum(mem_idnum);
		mem.setMem_bday(mem_bday);
		mem.setMem_phone(mem_phone);
		mem.setMem_addr(mem_addr);
		mem.setMem_mail(mem_mail);
		mem.setMem_due(mem_due);
		mem.setKey_id(key_id);
		mem.setKey_date(key_date);
		mem.setMem_um(mem_um);
		dao.update(mem);
		return dao.findByPrimaryKey(mem_id);
	}

	public void delMem(String mem_id){
		dao.delete(mem_id);
	}

	public MemberVO getOneMem(String mem_id){
		return dao.findByPrimaryKey(mem_id);
	}
	
	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	
	public List<MemberVO> getIdRge(String mem_id_1,String mem_id_2) {
		return dao.getIds(mem_id_1,mem_id_2);		
	}
	
	public List<MemberVO> getKdateRge(Date key_date_1,Date key_date_2) {
		return dao.getDates(key_date_1,key_date_2);		
	}
}
