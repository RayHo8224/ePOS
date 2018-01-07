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

public class RtnList_Generator implements IdentifierGenerator{
	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
		
		
		//修改下面三行即可
		//-------------------------------------------
		String tableName="RETURNLIST";    //table名稱
		String fieldName="ret_id";		//"編號"欄位名稱
		String name="RE";				//編號專屬開頭代號
		//------------------------------------------------
		
		String newID;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT top 1 "+fieldName+" as nextval from "
			+tableName+" ORDER BY "+ fieldName+" DESC");
			rs.next();
			

			String id=rs.getString("nextval");
			String date=id.substring(2, 10);
			Integer idNo=Integer.parseInt(id.substring(10))+1;
			
			
			java.util.Date utilDate = new Date(); 
			  java.sql.Timestamp stp = new java.sql.Timestamp(utilDate.getTime());
			  String today = ((stp.toString().split(" "))[0].split("-"))[0]+((stp.toString().split(" "))[0].split("-"))[1]+
		    		   ((stp.toString().split(" "))[0].split("-"))[2];
			  
			  if(date.equals(today)){
				  newID=name+date+String.format("%05d", idNo);
			  }else{
				  newID=name+today+"00001";
			  }			

//			System.out.println(newID);
			con.close();
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		return newID;
	}
}
