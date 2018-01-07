package generator;

import java.io.*;
import java.sql.*;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class Com_Generator implements IdentifierGenerator {

	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
		
		//將本檔名改成   "table名_Generator" 後修改分隔線內三行程式 
		//新創一個 package 取名 "generator" 將本檔放置其內
		//修改 xxxVO.java下的映射檔      <generator class="generator.table名_Generator" /> 
		
		
		//------------------------------------------------
		String tableName="COMPANY"; //table名稱
		String fieldName="com_id";      //"編號"欄位名稱
		String name="C";                //編號專屬開頭代號
		//------------------------------------------------------
		
		String newID;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT top 1 "+fieldName+" as nextval from "
			+tableName+" ORDER BY "+ fieldName+" DESC");
			rs.next();
			
			
			//ex:R2016092900001-------------------------------------------------
			
//			String id=rs.getString("nextval");
//			String date=id.substring(1, 9);
//			Integer idNo=Integer.parseInt(id.substring(9))+1;
//			
//			
//			java.util.Date utilDate = new Date(); 
//			  java.sql.Timestamp stp = new java.sql.Timestamp(utilDate.getTime());
//			  String today = ((stp.toString().split(" "))[0].split("-"))[0]+((stp.toString().split(" "))[0].split("-"))[1]+
//		    		   ((stp.toString().split(" "))[0].split("-"))[2];
//			  
//			  if(date.equals(today)){
//				  newID=name+date+String.format("%05d", idNo);
//			  }else{
//				  newID=name+today+"00001";
//			  }
				
			//-------------------------------------------------------------------
			  
						
			
			//ex:E00001----------------------------------------------------------
			
			int nextval =( Integer.parseInt((rs.getString("nextval").split(name))[1]))+1;
			newID=name+String.format("%05d", nextval);
						
			//-------------------------------------------------------------------

			  
//				System.out.println(newID);

				con.close();
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		return newID;
	}
}