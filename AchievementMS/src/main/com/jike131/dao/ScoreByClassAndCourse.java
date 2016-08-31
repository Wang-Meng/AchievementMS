package main.com.jike131.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.com.jike131.util.FailedByClassAndCourse;

public class ScoreByClassAndCourse {
	public static ArrayList<FailedByClassAndCourse> classCourseFailed(String class_id, String course_id) throws SQLException {
		ArrayList<FailedByClassAndCourse> list = new ArrayList<>();
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		String search = "select stu.stu_num, stu.stu_name,si.score, c.teacher "
				+ "from score_info si inner join stu_info stu on si.stu_num = stu.stu_num inner join course c on c.course_id = si.course_id "
				+ "where si.course_id = ? and  stu.stu_to_class = ?" ;
//				+ "order by stu.stu_num;";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setString(1, course_id);
		ps.setString(2, class_id);
		ResultSet result = ps.executeQuery();
		con.commit();
		while (result.next()) {
			FailedByClassAndCourse fcc = new FailedByClassAndCourse();
			fcc.setStuNum(result.getString("stu_num"));
			fcc.setName(result.getString("stu_name"));
			fcc.setScore(result.getInt("score"));
			fcc.setTeacher(result.getString("teacher"));
			list.add(fcc);
			System.out.print(result.getString("stu_num") + "---");
			System.out.print(result.getString("stu_name") + "---");
			System.out.println(result.getString("score") + "---");
		}
		JDBCtools.release(result, ps, con);
		return list;
	}
}
