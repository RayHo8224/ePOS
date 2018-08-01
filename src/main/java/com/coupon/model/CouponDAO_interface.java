package com.coupon.model;

import java.sql.Date;
import java.util.List;

public interface CouponDAO_interface {
    public void insert(CouponVO couponVO);
    public void update(CouponVO couponVO);
    public void delete(String cpon_id);
    public CouponVO findByPrimaryKey(String cpon_id);
    public List<CouponVO> getAll();
    public List<CouponVO> GroupByNames();    
    public List<CouponVO> findByNames(String cpon_name); 
    public List<CouponVO> GroupByDollar();
    public List<CouponVO> findByDollar(int cpon_dollar);
    public List<CouponVO> findByDateRange(Date release_date,Date cpon_period);
    
}
