package main.com.jike131.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.com.jike131.util.LoginInfo;

public class LoginDao {
	public static LoginInfo login(String username, String password) throws SQLException {
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		// select * from student,teacher,project where student.id=teacher.sid
		// and student.id=project.sid;
		String search = "select * from login l where l.login_id = ? and l.password = ?;";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet result = ps.executeQuery();
		con.commit();
		LoginInfo info = new LoginInfo();
		while (result.next()) {
			info.setStuNum(result.getString("login_id"));
			info.setType(result.getInt("status"));
		}
		JDBCtools.release(result, ps, con);
		return info;

	}

}
