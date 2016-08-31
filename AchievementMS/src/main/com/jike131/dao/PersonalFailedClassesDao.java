package main.com.jike131.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.com.jike131.util.PersonalFailedClasses;

public class PersonalFailedClassesDao {
	public static ArrayList<PersonalFailedClasses> failedPass(String stuNum) throws SQLException {
		ArrayList<PersonalFailedClasses> list = new ArrayList();
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		String search = "select stu.stu_name,c.course_name,si.score  "
				+ "from score_info si inner join stu_info stu on si.stu_num =stu.stu_num  "
				+ "inner join course c on si.course_id= c.course_id " + "where stu.stu_num = ? and si.score < 60;";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setString(1, stuNum);
		ResultSet result = ps.executeQuery();
		con.commit();
		while (result.next()) {
			PersonalFailedClasses pfc = new PersonalFailedClasses();
			pfc.setName(result.getString("stu_name"));
			pfc.setClassName(result.getString("course_name"));
			pfc.setFailedScore(result.getDouble("score"));
			list.add(pfc);
			System.out.print(result.getString("stu_name") + "---");
			System.out.print(result.getString("course_name") + "---");
			System.out.println(result.getString("score") + "---");
		}
		JDBCtools.release(result, ps, con);
		return list;
	}

}
