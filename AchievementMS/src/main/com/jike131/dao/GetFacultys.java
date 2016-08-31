package main.com.jike131.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.jike131.util.Facultys;

public class GetFacultys {
	public static List<Facultys> getFacultys() throws SQLException {
		List<Facultys> list = new ArrayList<>();
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		String search = "select * from faculty";
		PreparedStatement ps = con.prepareStatement(search);
		ResultSet resultSet = ps.executeQuery();
		con.commit();
		while(resultSet.next()){
			list.add(new Facultys(resultSet.getInt("faculty_id"), resultSet.getString("faculty_name")));
			System.out.println("id="+resultSet.getInt("faculty_id")+"----"+"name="+resultSet.getString("faculty_name"));
		}
		JDBCtools.releaseStaAndCon(ps, con);
		return list;
	}
	public static ArrayList<String> getFaculty() throws SQLException {
		ArrayList<String> list = new ArrayList();
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		String search = "select * from faculty";
		PreparedStatement ps = con.prepareStatement(search);
		ResultSet resultSet = ps.executeQuery();
		con.commit();
		while(resultSet.next()){
			list.add(resultSet.getString("faculty_name"));
			System.out.println("id="+resultSet.getInt("faculty_id")+"----"+"name="+resultSet.getString("faculty_name"));
		}
		JDBCtools.releaseStaAndCon(ps, con);
		return list;
	}
}
