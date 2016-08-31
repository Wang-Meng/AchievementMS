package main.com.jike131.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.com.jike131.util.FailedPerosnByCourse;

public class FailedPersonByCourseDao {
	public static ArrayList<FailedPerosnByCourse> personsByCourse(int course_id) throws SQLException {
		ArrayList<FailedPerosnByCourse> list = new ArrayList<>();
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		String search = "select stu.stu_num, stu.stu_name, c.class_name ,si.score from stu_info stu inner join score_info si on si.stu_num = stu.stu_num inner join class c on stu.stu_to_class =  c.class_num where si.course_id = ? and si.score < 60 order by stu.stu_num;";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setInt(1, course_id);
		ResultSet result = ps.executeQuery();
		con.commit();
		while (result.next()) {
			FailedPerosnByCourse fpb = new FailedPerosnByCourse();
			fpb.setStuNum(result.getString("stu_num"));
			fpb.setName(result.getString("stu_name"));
			fpb.setClassName(result.getString("class_name"));
			fpb.setScore(result.getInt("score"));
			list.add(fpb);
			System.out.print(result.getString("stu_num") + "---");
			System.out.print(result.getString("stu_name") + "---");
			System.out.print(result.getString("class_name") + "---");
			System.out.println(result.getString("score") + "---");
		}
		JDBCtools.release(result, ps, con);
		return list;
	}
}
