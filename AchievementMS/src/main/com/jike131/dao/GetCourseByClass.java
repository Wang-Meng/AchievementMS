package main.com.jike131.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.jike131.util.Facultys;

public class GetCourseByClass {
	public static List<Facultys> getCourse(int facultyId) throws SQLException {
		List<Facultys> map = new ArrayList();
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		String search = "select * from course where course_to_class = ?";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setInt(1, facultyId);
		ResultSet resultSet = ps.executeQuery();
		con.commit();
		while (resultSet.next()) {
			map.add(new Facultys(resultSet.getInt("course_id"), resultSet.getString("course_name")));
		}
		JDBCtools.releaseStaAndCon(ps, con);
		return map;
	}
}
