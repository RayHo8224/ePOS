package com.invo.model;

import java.util.*;
public interface InvoDAO_interface {
	  public void insert(InvoVO invoVO);
      public void update(InvoVO invoVO);
      public void delete(String invoice_id);
      public InvoVO findByPrimaryKey(String invoice_id);
      public List<InvoVO> getAll();       
}
