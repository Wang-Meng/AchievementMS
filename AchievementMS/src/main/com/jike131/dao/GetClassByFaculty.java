package main.com.jike131.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.com.jike131.util.Facultys;

public class GetClassByFaculty {
	public static List<Facultys> getCLass(int facultyId) throws SQLException {
		List<Facultys> map = new ArrayList();
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		String search = "select * from class where class_to_faculty = ?";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setInt(1, facultyId);
		ResultSet resultSet = ps.executeQuery();
		con.commit();
		while (resultSet.next()) {
			map.add(new Facultys(resultSet.getInt("class_num"), resultSet.getString("class_name")));
		}
		JDBCtools.releaseStaAndCon(ps, con);
		return map;
	}
//	public static String[] getCLassByName(String name) throws SQLException {
//		String [] list;
//		Connection con = JDBCtools.getConnection();
//		con.setAutoCommit(false);
//		String search = "select * from class where class_to_faculty = ?";
//		PreparedStatement ps = con.prepareStatement(search);
//		ps.setString(1, facultyId);
//		ResultSet resultSet = ps.executeQuery();
//		con.commit();
//		while (resultSet.next()) {
//			map.put(resultSet.getInt("class_num"), resultSet.getString("class_name"));
//		}
//		JDBCtools.releaseStaAndCon(ps, con);
//		return map;
//	}
}
