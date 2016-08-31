package main.com.jike131.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import org.omg.CORBA.INTERNAL;

import main.com.jike131.util.PersonalScore;
import main.com.jike131.util.Student;

/*查询班级成员信息*/
public class StudentInfoDao {
	public static ArrayList<Student> query(String class_id) throws SQLException {
		ArrayList<Student> list = new ArrayList();
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		// select * from student,teacher,project where student.id=teacher.sid
		// and student.id=project.sid;
		String search = "select * from stu_info s,faculty f,class c where s.stu_to_faculty=f.faculty_id and s.stu_to_class=c.class_num;";
		PreparedStatement ps = con.prepareStatement(search);
		ResultSet result = ps.executeQuery();
		con.commit();
		String sex;
		while (result.next()) {
			Student student = new Student();
			student.setStu_num(result.getString("stu_num"));
			student.setStu_name(result.getString("stu_name"));
			student.setFaculty_name(result.getString("faculty_name"));
			student.setClass_name(result.getString("class_name"));
//			try {
//				student.setStu_birthday(dateFormat(result.getString("stu_birthday")));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			if (result.getInt("stu_sex") == 1) {
				sex = "男";
			}else{
				sex = "女";
			}
			student.setStu_sex(sex);
			list.add(student);
		}
		JDBCtools.release(result, ps, con);
		return list;

	}
	private static String dateFormat(String date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sd = sdf.format(new Date(Long.parseLong(date)));
		return sd;
	}
	
	public static ArrayList<Student> findStudentInfo(int classid,int intFrint,int intpages) throws SQLException{

		ArrayList<Student> list = new ArrayList();
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		// select * from student,teacher,project where student.id=teacher.sid
		// and student.id=project.sid;
		String search = null;
		PreparedStatement ps = null;
		if(classid==0)
		{
			search = "select * from stu_info s,faculty f,class c where s.stu_to_faculty=f.faculty_id and s.stu_to_class=c.class_num limit ?,?;";
			System.out.println("好的"+search);
			ps = con.prepareStatement(search);
			ps.setInt(1, intFrint);
			ps.setInt(2, intpages);
		}else
		{
			
			search = "select * from stu_info s,faculty f,class c where s.stu_to_faculty=f.faculty_id and s.stu_to_class=c.class_num and c.class_num='"+classid+"'"+"limit ?,?;";
			
			ps = con.prepareStatement(search);
			
			
			ps.setInt(1, intFrint);
			ps.setInt(2, intpages);
			System.out.println("我们"+search);
			System.out.println(ps);
		}
		
		
		ResultSet result = ps.executeQuery();
		con.commit();
		String sex;
		while (result.next()) {
			Student student = new Student();
			student.setStu_num(result.getString("stu_num"));
			student.setStu_name(result.getString("stu_name"));
			student.setFaculty_name(result.getString("faculty_name"));
			student.setClass_name(result.getString("class_name"));
//			student.setStu_birthday(dateFormat(result.getString("stu_birthday")));
			if (result.getInt("stu_sex") == 1) {
				sex = "男";
			}else{
				sex = "女";
			}
			student.setStu_sex(sex);
			list.add(student);
		}
		JDBCtools.release(result, ps, con);
		return list;
	}

	//得到学生的总记录数量
	public static int findStudentCount(int classId){
		Connection con = JDBCtools.getConnection();
		
		String sql = null;
		if (classId == 0) {
			sql = "select count(*) from stu_info ";
		}else
		{
			sql =  "select count(*) from stu_info where stu_to_class="+classId;
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		
		try {
			preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				count = resultSet.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return count;
	}
}
