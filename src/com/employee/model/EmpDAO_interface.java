package com.employee.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface EmpDAO_interface {
    public void insert(EmpVO empVO);
    public void update(EmpVO empVO);
    public void delete(String emp_id);
    public EmpVO findByPrimaryKey(String emp_id) throws FileNotFoundException;
    public List<EmpVO> getAll() throws IOException;
    public List<EmpVO> findByName(String emp_name);
    public void setPassCode(String pass_code,String emp_id);
}
