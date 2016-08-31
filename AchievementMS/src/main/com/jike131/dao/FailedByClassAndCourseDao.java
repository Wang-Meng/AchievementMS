package main.com.jike131.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.com.jike131.util.FailedByClassAndCourse;
import main.com.jike131.util.FailedPerosnByCourse;

public class FailedByClassAndCourseDao {
	public static ArrayList<FailedByClassAndCourse> classCourseFailed(int class_id, int course_id) throws SQLException {
		ArrayList<FailedByClassAndCourse> list = new ArrayList<>();
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		String search = "select stu.stu_num, stu.stu_name,si.score "
				+ "from score_info si inner join stu_info stu on si.stu_num = stu.stu_num "
				+ "where si.score < 60  and si.course_id = ? and  stu.stu_to_class = ?" ;
//				+ "order by stu.stu_num;";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setInt(1, course_id);
		ps.setInt(2, class_id);
		ResultSet result = ps.executeQuery();
		con.commit();
		while (result.next()) {
			FailedByClassAndCourse fcc = new FailedByClassAndCourse();
			fcc.setStuNum(result.getString("stu_num"));
			fcc.setName(result.getString("stu_name"));
			fcc.setScore(result.getInt("score"));
			list.add(fcc);
			System.out.print(result.getString("stu_num") + "---");
			System.out.print(result.getString("stu_name") + "---");
			System.out.println(result.getString("score") + "---");
		}
		JDBCtools.release(result, ps, con);
		return list;
	}
	
	public static ArrayList<FailedByClassAndCourse> classFailed(int class_id) throws SQLException {
		ArrayList<FailedByClassAndCourse> list = new ArrayList<>();
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		String search = "select stu.stu_num, stu.stu_name,si.score "
				+ "from score_info si inner join stu_info stu on si.stu_num = stu.stu_num "
				+ "where si.score < 60 and  stu.stu_to_class = ?" ;
//				+ "order by stu.stu_num;";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setInt(1, class_id);
		ResultSet result = ps.executeQuery();
		con.commit();
		while (result.next()) {
			FailedByClassAndCourse fcc = new FailedByClassAndCourse();
			fcc.setStuNum(result.getString("stu_num"));
			fcc.setName(result.getString("stu_name"));
			fcc.setScore(result.getInt("score"));
			list.add(fcc);
			System.out.print(result.getString("stu_num") + "---");
			System.out.print(result.getString("stu_name") + "---");
			System.out.println(result.getString("score") + "---");
		}
		JDBCtools.release(result, ps, con);
		return list;
	}
}
