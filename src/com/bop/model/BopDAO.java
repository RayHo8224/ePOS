package com.bop.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.bop_detail.model.Bop_detailVO;
import com.company.model.ComVO;
import com.pur.model.PurVO;

public class BopDAO implements Bop_Interface {
	
	private static final String GETALL = "from BopVO order by bop_id";
	private static final String GET_DETAIL_BOPID = "from Bop_detailVO where bop_id=?";
	private static final String SELECT_OF_Y = "from PurVO where status = 'Y'";
	private static final String FIND_BY_DATE ="from BopVO where key_date between ? and ? ";
	private static final String SELECT_OF_N = "from BopVO where status = 'N'";
	private static final String SELECT_OF_Y2 = "from BopVO where status = 'Y'";
	private static final String GET_RATIO = "select com_id, sum(remark) from BopVO where bop_id like ? and status = 'S' group by com_id";
	private static final String SELECT_OF_S = "from BopVO where status = 'S'";
	private static final String GET_MONTH_COST = "select sum(remark) from BopVO where bop_id like ? and status = 'S'";
	private static final String GET_COM_ID = "from ComVO where com_id = ?";
	
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void insert(BopVO bopVO) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(bopVO);

	}

	@Override
	public void update(BopVO bopVO) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(bopVO);

	}

	@Override
	public void delete(String bop_id) {
		// TODO Auto-generated method stub
		BopVO bopVO = (BopVO) hibernateTemplate.get(BopVO.class, bop_id);
		hibernateTemplate.delete(bopVO);

	}

	@Override
	public void deleteDetail(String bop_id,String prod_id) {
		hibernateTemplate.bulkUpdate("DELETE Bop_detailVO WHERE bop_id=? AND prod_id=?",new Object[]{bop_id,prod_id});	
		
	}
	
	@Override
	public BopVO findByPrimaryKey(String bop_id) {
		// TODO Auto-generated method stub
		BopVO bopVO = (BopVO) hibernateTemplate.get(BopVO.class, bop_id);
		return bopVO;
	}

	@Override
	public List<Bop_detailVO> findDetailByPrimaryKey(String bop_id) {
		// TODO Auto-generated method stub
//		Set<Bop_detailVO> set = findByPrimaryKey(bop_id).getBops();
		List<Bop_detailVO> set=(List<Bop_detailVO>) hibernateTemplate.find(GET_DETAIL_BOPID,bop_id);
		return set;
	}

	@Override
	public List<BopVO> getAll() {
		// TODO Auto-generated method stub
		List<BopVO> list = null;
		list = (List<BopVO>) hibernateTemplate.find(GETALL);
		return list;
	}
	
	@Override
	public void setStatus(String status, String bop_id){
		hibernateTemplate.bulkUpdate("update BopVO set status=? where bop_id=?",new Object[]{status,bop_id});
	}

	@Override
	public List<PurVO> selectOfY() {
		// TODO Auto-generated method stub
		List<PurVO> list = (List<PurVO>) hibernateTemplate.find(SELECT_OF_Y);
		return list;
	}

	@Override
	public PurVO getThePur(String pur_id) {
		// TODO Auto-generated method stub
		PurVO purVO = (PurVO) hibernateTemplate.get(PurVO.class, pur_id);
		return purVO;
	}

	@Override
	public void setStatus2(String status2, String pur_id) {
		// TODO Auto-generated method stub
		hibernateTemplate.bulkUpdate("update PurVO set status=? where pur_id=?",new Object[]{status2,pur_id});
	}

	@Override
	public List<BopVO> findByDate(Date begin_date, Date end_date) {
		// TODO Auto-generated method stub
		List<BopVO> list = null;
		list = (List<BopVO>) hibernateTemplate.find(FIND_BY_DATE, new Object[]{begin_date,end_date});
		return list;
	}

	@Override
	public List<BopVO> selectOfN() {
		// TODO Auto-generated method stub
		List<BopVO> list = null;
		list = (List<BopVO>) hibernateTemplate.find(SELECT_OF_N);
		return list;
	}

	@Override
	public List<BopVO> selectOfY2() {
		// TODO Auto-generated method stub
		List<BopVO> list = null;
		list = (List<BopVO>) hibernateTemplate.find(SELECT_OF_Y2);
		return list;
	}

	@Override
	public List<Object[]> getRatio(String bop_month) {
		// TODO Auto-generated method stub
		
		List<Object[]> list = null;
		list = (List<Object[]>) hibernateTemplate.find(GET_RATIO, bop_month);
		return list;
	}

	@Override
	public List<BopVO> selectOfS() {
		// TODO Auto-generated method stub
		List<BopVO> list = null;
		list = (List<BopVO>) hibernateTemplate.find(SELECT_OF_S);
		return list;
	}

	@Override
	public String getMonthCost(String bop_month) {
		// TODO Auto-generated method stub
		List<Object> list = null;
		list = (List<Object>) hibernateTemplate.find(GET_MONTH_COST, bop_month);
		String co = null;
		for(Object co0 : list){
			co = String.valueOf(co0);
		}
		return co;
	}

	@Override
	public String getComName(String com_id) {
		// TODO Auto-generated method stub
		List<ComVO> list = null;
		list = (List<ComVO>) hibernateTemplate.find(GET_COM_ID, com_id);
		String comName = list.get(0).getCom_name();
		return comName;
	}
	
	
	

}
