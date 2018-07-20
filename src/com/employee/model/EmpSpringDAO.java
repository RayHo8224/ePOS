package com.employee.model;


import java.io.IOException;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;






public class EmpSpringDAO implements EmpDAO_interface {


	private static final String GET_ALL_STMT = "from EmpVO order by emp_id";

	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }
	@Override
	public void insert(EmpVO empVO) {
		hibernateTemplate.saveOrUpdate(empVO);
		
	}

	@Override
	public void update(EmpVO empVO) {
			hibernateTemplate.saveOrUpdate(empVO);
		
	}
	
	@Override
	public void delete(String emp_id) {
			EmpVO empvo = (EmpVO) hibernateTemplate.get(EmpVO.class, emp_id);
			hibernateTemplate.delete(empvo);
		
	}

	@Override
	public EmpVO findByPrimaryKey(String emp_id) {
			EmpVO empVO = null;
			empVO = (EmpVO) hibernateTemplate.get(EmpVO.class, emp_id);
		
			return empVO;
	}

	@Override
	public List<EmpVO> getAll() throws IOException {
		List<EmpVO> list = null;
		list= (List<EmpVO>) hibernateTemplate.find(GET_ALL_STMT);
		
		return list;
	}
	@Override
	public List<EmpVO> findByName(String emp_name) {
		List<EmpVO> list = null;
			list= (List<EmpVO>) hibernateTemplate.find("from EmpVO where emp_name like '%"+emp_name+"%'");
		
		return list;
	}

	
	@Override
	public void setPassCode(String pass_code,String emp_id) {
		hibernateTemplate.bulkUpdate("update EmpVO set pass_code='"+pass_code+"'where emp_id='"+emp_id+"'");
		

	}

	// public void getIMG(EmpVO empVO) throws IOException{
	// FileOutputStream fos = new
	// FileOutputStream("C:\\Framework\\workspace\\pos\\WebContent\\images\\"+empVO.getEmp_id()+".jpg");
	// byte[] empImg = empVO.getPicture();
	// fos.write(empImg);
	// fos.close();
	// }

}
