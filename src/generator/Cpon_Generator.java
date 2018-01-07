package generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class Cpon_Generator implements IdentifierGenerator{
	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
		
		
		//修改下面三行即可
		//-------------------------------------------
		String tableName="COUPON";    //table名稱
		String fieldName="cpon_id";		//"編號"欄位名稱
		String name="CPa";				//編號專屬開頭代號
		//------------------------------------------------
		
		String newID;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT top 1 "+fieldName+" as nextval from "
			+tableName+" ORDER BY "+ fieldName+" DESC");
			rs.next();
			

			int nextval =( Integer.parseInt((rs.getString("nextval").split(name))[1]))+1;
			newID=name+String.format("%05d", nextval);			

//			System.out.println(newID);
			con.close();
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		return newID;
	}
}
