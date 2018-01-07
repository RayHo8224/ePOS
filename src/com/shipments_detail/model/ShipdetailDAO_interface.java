package com.shipments_detail.model;

import java.util.List;


public interface ShipdetailDAO_interface {

	public void insert(ShipdetailVO shipdetailVO);

	public void update(ShipdetailVO shipdetailVO);

	public void delete(String ship_id,String prod_id );

	public List<ShipdetailVO> findByShipId(String ship_id);
	
	public List<ShipdetailVO> getAll() ;


}
