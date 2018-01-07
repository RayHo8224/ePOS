package generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class BILL_OF_PURCHASE_Generator implements IdentifierGenerator {
	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
		
		//�N���ɦW�令   "table�W_Generator" ��ק���j�u���T��{�� 
		//�s�Ф@�� package ���W "generator" �N���ɩ�m�䤺
		//�ק� xxxVO.java�U���M�g��      <generator class="generator.table�W_Generator" /> 
		
		
		//------------------------------------------------
		String tableName="BILL_OF_PURCHASE"; //table�W��
		String fieldName="bop_id";      //"�s��"���W��
		String name="B";                //�s���M�ݶ}�Y�N��
		//------------------------------------------------------
		
		String newID;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT top 1 "+fieldName+" as nextval from "
			+tableName+" ORDER BY "+ fieldName+" DESC");
			rs.next();
			
			
			//ex:R2016092900001-------------------------------------------------
			
			String id=rs.getString("nextval");
			String date=id.substring(1, 9);
			Integer idNo=Integer.parseInt(id.substring(9))+1;
			
			
			java.util.Date utilDate = new Date(); 
			  java.sql.Timestamp stp = new java.sql.Timestamp(utilDate.getTime());
			  String today = ((stp.toString().split(" "))[0].split("-"))[0]+((stp.toString().split(" "))[0].split("-"))[1]+
		    		   ((stp.toString().split(" "))[0].split("-"))[2];
			  
			  if(date.equals(today)){
				  newID=name+date+String.format("%05d", idNo);
			  }else{
				  newID=name+today+"00001";
			  }
				
			//-------------------------------------------------------------------
			  
						
			
			//ex:E00001----------------------------------------------------------
			
//			int nextval =( Integer.parseInt((rs.getString("nextval").split(name))[1]))+1;
//			newID=name+String.format("%05d", nextval);
						
			//-------------------------------------------------------------------

			  
//				System.out.println(newID);

				con.close();
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		return newID;
	}
}