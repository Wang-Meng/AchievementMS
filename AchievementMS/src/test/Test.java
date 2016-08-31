package test;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import main.com.jike131.dao.ScoreByClassAndCourse;
import main.com.jike131.dao.StudentDAO;

public class Test {

	public static void main(String[] args) {
		System.out.println("=======");
		// TODO Auto-generated method stub
//		new JDBCtools().getConnection();
		try {
//			new StudentDAO().queryByClass("1");
//			new StudentDAO().queryByNum("201401");
//			new StudentDAO().queryScoreByClass("1");
//			new StudentDAO().failedPass("201401");
//			new StudentDAO().numsByClass(1);
//			StudentDAO.classCourseFailed("1", "1");
//			StudentDAO.login("root", "123456");
			ScoreByClassAndCourse.classCourseFailed("1", "1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
/*//		日期时间转换
		String beginDate="1328007600000";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sd = sdf.format(new java.util.Date(Long.parseLong(beginDate)));
		System.out.println(sd);*/
	}

}
