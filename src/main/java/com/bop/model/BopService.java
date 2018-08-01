package com.bop.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bop_detail.model.Bop_detailVO;
import com.pur.model.PurVO;



public class BopService {
	
	private Bop_Interface dao;
		
		public BopService() {
			ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
			dao =(Bop_Interface) context.getBean("bopDAO");
		}
		
		public List<BopVO> selectOfY2(){
			List<BopVO> list = null;
			list = dao.selectOfY2();
			return list;
		}
		
		public List<BopVO> selectOfN(){
			List<BopVO> list = null;
			list = dao.selectOfN();
			return list;
		}
		
		public List<BopVO> findByDate(Date begin_date, Date end_date){
			List<BopVO> list = null;
			list = dao.findByDate(begin_date, end_date);
			return list;
		}
		
		public void setStatus2(String status2, String pur_id){
			dao.setStatus2(status2, pur_id);
			return;
		}
		
		public PurVO getThePur(String pur_id){
			PurVO purVO = dao.getThePur(pur_id);
			return purVO;
			
		}
		
		public List<PurVO> selectOfY(){
			List<PurVO> list = dao.selectOfY();
			return list;
		}
		
		public BopVO insert(BopVO bopVO) {
			dao.insert(bopVO);
			return bopVO;
		}
		
		public BopVO update(BopVO bopVO) {
			dao.update(bopVO);
			return bopVO;
		}
		
		public void deletePur(String bop_id) {
			dao.delete(bop_id);
		}
		public void deleteDetail(String bop_id,String prod_id) {
			dao.deleteDetail(bop_id,prod_id);
		}		
		
		public BopVO getOneBop(String bop_id) {
			BopVO bopVO = dao.findByPrimaryKey(bop_id);
			return bopVO;
		}
		
		public void setStatus(String status, String bop_id){

			dao.setStatus(status, bop_id);
		}
		
		public List<Bop_detailVO> getBopDetail(String bop_id) {
			List<Bop_detailVO> set = dao.findDetailByPrimaryKey(bop_id);
			return set;
		}
		
		public List<BopVO> getAll() {
			List<BopVO> list = dao.getAll();
			return list;
		}
		
		public List<Object[]> getRatio(String bop_month) {
			bop_month = "%"+bop_month+"%";
			List<Object[]> list = dao.getRatio(bop_month);
			return list;
		}
		
		public List<BopVO> selectOfS() {
			List<BopVO> list = dao.selectOfS();
			return list;
		}
		
		public String getMonthCost(String bop_month) {
			bop_month = "%"+bop_month+"%";
			String co = dao.getMonthCost(bop_month);
			return co;
		}
		
		public String getComName(String com_id) {
			String comName = dao.getComName(com_id);
			return comName;
		}
	
}
