package com.discount.model;

import java.util.List;

import com.coupon.model.CouponVO;

public interface DiscountDAO_interface {
    public void insert(DiscountVO discountVO);
    public void update(DiscountVO discountVO);
    public void delete(String dis_id);
    public DiscountVO findByPrimaryKey(String dis_id);
    public List<DiscountVO> getAll();
    public List<DiscountVO> GroupByPrice();
    public List<DiscountVO> findByPrice(float dis_price);
}


