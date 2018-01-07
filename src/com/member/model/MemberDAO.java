package com.member.model;

import java.sql.Date;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;


public class MemberDAO implements MemberDAO_interface{
	
	private static final String GET_ALL_STMT = "FROM MemberVO order by mem_id";
	private static final String GET_IDS_STMT = "FROM MemberVO where mem_id between ? and ?";
	private static final String GET_K_DATES_STMT = "FROM MemberVO where key_date between ? and ?";
		
	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }

	@Override
	public void insert(MemberVO memberVO) {
		hibernateTemplate.saveOrUpdate(memberVO);
		
	}

	@Override
	public void update(MemberVO memberVO) {
		hibernateTemplate.update(memberVO);
		
	}

	@Override
	public void delete(String mem_id) {
		MemberVO memberVO = (MemberVO)hibernateTemplate.get(MemberVO.class, mem_id);
		hibernateTemplate.delete(memberVO);
	}

	@Override
	public MemberVO findByPrimaryKey(String mem_id) {
		MemberVO memberVO = (MemberVO)hibernateTemplate.get(MemberVO.class, mem_id);
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = null;
		list = hibernateTemplate.find(GET_ALL_STMT);
		return list;
	}

	@Override
	public List<MemberVO> getIds(String mem_id_1, String mem_id_2) {
		List<MemberVO> list = null;
		list = hibernateTemplate.find(GET_IDS_STMT,new String[]{mem_id_1, mem_id_2});
		return list;
	}

	@Override
	public List<MemberVO> getDates(Date key_date_1, Date key_date_2) {
		List<MemberVO> list = null;
		list = hibernateTemplate.find(GET_K_DATES_STMT,new Date[]{key_date_1, key_date_2});
		return list;
	}

}
