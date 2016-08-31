package main.com.jike131.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.com.jike131.util.ClassTotalScore;

public class ClassScoreDao {
	public static ArrayList<ClassTotalScore> queryScoreByClass(String class_id) throws SQLException {
		ArrayList<ClassTotalScore> list = new ArrayList();
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		String search = "select stu.stu_num, stu.stu_name,SUM(sc.score) as total, AVG(sc.score) as avges "
				+ "from stu_info stu, score_info sc " + "where stu.stu_num = sc.stu_num and stu.stu_to_class = ?"
				+ "GROUP BY stu.stu_num";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setString(1, class_id);
		ResultSet result = ps.executeQuery();
		con.commit();
		while (result.next()) {
			ClassTotalScore pts = new ClassTotalScore();
			pts.setStuNum(result.getString("stu_num"));
			pts.setStuName(result.getString("stu_name"));
			pts.setTotal(result.getDouble("total"));
			pts.setAverage(result.getDouble("avges"));
			list.add(pts);
			System.out.print(result.getString("stu_num") + "---");
			System.out.print(result.getString("stu_name") + "---");
			System.out.print(result.getString("total") + "---");
			System.out.println(result.getString("avges") + "---");
		}
		JDBCtools.release(result, ps, con);
		return list;
	}
}
