package main.com.jike131.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*修改成绩*/
public class AlterCourseScore {
	public static void alterScore(String score, String stuNum, String courseId) throws SQLException {
		Connection con = JDBCtools.getConnection();
		String sql = "select * from score_info where stu_num = ? and course_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, stuNum);
		ps.setString(2, courseId);
		ResultSet rs = ps.executeQuery();
		rs.first();
		String search;
		con.setAutoCommit(false);
		if (rs.getRow() > 0) {
			search = "update score_info set score = ? where stu_num = ? and course_id = ?";
			ps = con.prepareStatement(search);
			ps.setString(1, score);
			ps.setString(2, stuNum);
			ps.setString(3, courseId);
		} else {
			search = "insert into score_info(stu_num, course_id, score) values(?,?,?);";
			ps = con.prepareStatement(search);
			ps.setString(3, score);
			ps.setString(1, stuNum);
			ps.setString(2, courseId);
		}
		int result = ps.executeUpdate();
		System.out.println("结果为："+result);
		con.commit();
		JDBCtools.releaseStaAndCon(ps, con);
	}
}
