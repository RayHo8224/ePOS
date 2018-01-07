package com.promoting.model;

import java.sql.Date;
import java.util.List;

public interface PromotingDAO_interface {
    public void insert(PromotingVO promotingVO);
    public void update(PromotingVO promotingVO);
    public void delete(String pro_prod_id,Date pro_begin);
    public PromotingVO findByPrimaryKey(String pro_prod_id,Date pro_begin);
    public List<PromotingVO> getAll();
    public List<PromotingVO> getIds(String pro_prod_id1,String pro_prod_id2);
    public List<PromotingVO> getDates(Date pro_begin,Date pro_end);
    public List<PromotingVO> getNames(String pro_prod_name);
    public List<PromotingVO> GroupByIDs();
    public List<PromotingVO> findByIDs(String pro_prod_id);
    
}
