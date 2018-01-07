package com.member.model;

import java.sql.Date;
import java.util.List;

public interface MemberDAO_interface {
    public void insert(MemberVO memberVO);
    public void update(MemberVO memberVO);
    public void delete(String mem_id);
    public MemberVO findByPrimaryKey(String mem_id);
    public List<MemberVO> getAll();
    public List<MemberVO> getIds(String mem_id_1,String mem_id_2);
    public List<MemberVO> getDates(Date key_date_1,Date key_date_2);
}
