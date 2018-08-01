package generator;

import java.io.*;
import java.sql.*;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class test_Generator implements IdentifierGenerator {

	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
		
		
		//�ק�U���T��Y�i
		//-------------------------------------------
		String tableName="EMPLOYEE";    //table�W��
		String fieldName="emp_id";		//"�s��"���W��
		String name="E";				//�s���M�ݶ}�Y�N��
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
//			con.close();
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}
		return newID;
	}
}