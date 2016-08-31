package main.com.jike131.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.jike131.util.Facultys;
import main.com.jike131.util.FailedByClassAndCourse;

public class AddStuScore {
	public static int addStuScore(String stuNum, int course, int score) throws SQLException {
		Connection con = JDBCtools.getConnection();
		String sql = "insert into score_info(stu_num, course_id, score) values (?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, stuNum);
		ps.setInt(2, course);
		ps.setInt(3, score);
		int result = ps.executeUpdate();
		JDBCtools.releaseStaAndCon(ps, con);
		if (result > 0) return 0;
		else return -1;
	}
	
	public static ArrayList<Facultys> selectNoScore (int cl, int course) throws SQLException{
		ArrayList<Facultys> list = new ArrayList<>();
		Connection con = JDBCtools.getConnection();
		String search = "select * from stu_info where stu_to_class = ? and  stu_num not in (select stu_num from score_info where course_id = ?)";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setInt(1, cl);
		ps.setInt(2, course);
		ResultSet result = ps.executeQuery();
		while (result.next()) {
			list.add(new Facultys(result.getString("stu_num"), result.getString("stu_name")));
		}
		JDBCtools.release(result, ps, con);
		return list;
	}
}
