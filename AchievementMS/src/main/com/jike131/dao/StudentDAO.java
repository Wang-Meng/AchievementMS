package main.com.jike131.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.jike131.util.Student;

public class StudentDAO {

	/*
	 * ��ѯĳ��ɼ� �����ʾ�� 201301---����---��Ϣ����ѧԺ---�ƿ�131---1---1450837939---
	 * 201302---����---��Ϣ����ѧԺ---�ƿ�131---1---1450837939---
	 * 201303---�䶯---��Ϣ����ѧԺ---�ƿ�131---1---1450837939---
	 * 
	 */
	public static void queryByClass(String class_id) throws SQLException {
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		// select * from student,teacher,project where student.id=teacher.sid
		// and student.id=project.sid;
		String search = "select * from stu_info s,faculty f,class c "
				+ "where s.stu_to_faculty=f.faculty_id and s.stu_to_class=c.class_num " + "and c.class_num = ?;";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setString(1, class_id);
		ResultSet result = ps.executeQuery();
		con.commit();
		while (result.next()) {
			System.out.print(result.getString("stu_num") + "---");
			System.out.print(result.getString("stu_name") + "---");
			System.out.print(result.getString("faculty_name") + "---");
			System.out.print(result.getString("class_name") + "---");
			System.out.print(result.getString("stu_sex") + "---");
//			System.out.println(result.getString("stu_birthday") + "---");
		}
		JDBCtools.release(result, ps, con);

	}

	public static ArrayList<Student> query(String stuNum) throws SQLException {
		ArrayList list = new ArrayList();
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		// select * from student,teacher,project where student.id=teacher.sid
		// and student.id=project.sid;
		String search = "select * from stu_info s,faculty f,class c where s.stu_to_faculty=f.faculty_id and s.stu_to_class=c.class_num and s.stu_num = ?;";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setString(1, stuNum);
		ResultSet result = ps.executeQuery();
		con.commit();
		while (result.next()) {
			Student student = new Student();
			student.setStu_num(result.getString("stu_num"));
			student.setStu_name(result.getString("stu_name"));
			student.setFaculty_name(result.getString("faculty_name"));
			student.setClass_name(result.getString("class_name"));
			student.setStu_sex(result.getString("stu_sex"));
//			student.setStu_birthday(result.getString("stu_birthday"));
			list.add(student);
		}
		JDBCtools.release(result, ps, con);
		return list;

	}

	/*
	 * ͨ��ѧ�Ų�ѯĳ�˳ɼ� �����ʾ�� �˺�---Ӣ���﷨---80---
	 */
	public static void queryByNum(String stuNum) throws SQLException {
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		// select * from student inner join teacher on student.id=teacher.sid
		// inner join project on student.id=project.sid;
		String search = "select stu.stu_name,c.course_name,si.score  "
				+ "from score_info si inner join stu_info stu on si.stu_num =stu.stu_num  "
				+ "inner join course c on si.course_id= c.course_id " + "where stu.stu_num = ?;";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setString(1, stuNum);
		ResultSet result = ps.executeQuery();
		con.commit();
		while (result.next()) {
			System.out.print(result.getString("stu_name") + "---");
			System.out.print(result.getString("course_name") + "---");
			System.out.println(result.getString("score") + "---");
		}
		JDBCtools.release(result, ps, con);
	}

	/*
	 * ��ѯĳ���Ա��ƽ���ּ��ܷ� �����ʾ�� 201301---����---165---82.5--- 201302---����---90---90---
	 * 201303---�䶯---78---78---
	 */
	public static void queryScoreByClass(String class_id) throws SQLException {
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		// select stu.stu_name,c.course_name,si.score from score_info si inner
		// join stu_info stu on si.stu_num =stu.stu_num inner join course c on
		// si.course_id= c.course_id where stu.stu_num = ?;
		String search = "select stu.stu_num, stu.stu_name,SUM(sc.score) as total, AVG(sc.score) as avges "
				+ "from stu_info stu, score_info sc " + "where stu.stu_num = sc.stu_num and stu.stu_to_class = ?"
				+ "GROUP BY stu.stu_num";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setString(1, class_id);
		ResultSet result = ps.executeQuery();
		con.commit();
		while (result.next()) {
			System.out.print(result.getString("stu_num") + "---");
			System.out.print(result.getString("stu_name") + "---");
			System.out.print(result.getString("total") + "---");
			System.out.println(result.getString("avges") + "---");
		}
		JDBCtools.release(result, ps, con);

	}
//	"======="

	// ĳ�˲������Ŀ������
	public static void failedPass(String stuNum) throws SQLException {
		int resultRows = 0;
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		// select * from student inner join teacher on student.id=teacher.sid
		// inner join project on student.id=project.sid;
		String search = "select stu.stu_name,c.course_name,si.score  "
				+ "from score_info si inner join stu_info stu on si.stu_num =stu.stu_num  "
				+ "inner join course c on si.course_id= c.course_id " + "where stu.stu_num = ? and si.score < 60;";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setString(1, stuNum);
		ResultSet result = ps.executeQuery();
		con.commit();
		while (result.next()) {
			System.out.print(result.getString("stu_name") + "---");
			System.out.print(result.getString("course_name") + "---");
			System.out.println(result.getString("score") + "---");
			resultRows++;
		}
		System.out.println("�������Ŀ:" + resultRows);
		resultRows = 0;
		JDBCtools.release(result, ps, con);

	}
//	====
	// ĳ�γ̲���������
	public static void numsByClass(int class_id) throws SQLException {
		int numbers = 0;
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		// select * from student,teacher,project where student.id=teacher.sid
		// and student.id=project.sid;
		String search = "select stu.stu_num, stu.stu_name, c.class_name ,si.score from stu_info stu inner join score_info si on si.stu_num = stu.stu_num inner join class c on stu.stu_to_class =  c.class_num where si.course_id = ? and si.score < 60 order by stu.stu_num;";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setInt(1, class_id);
		ResultSet result = ps.executeQuery();
		con.commit();
		while (result.next()) {
			System.out.print(result.getString("stu_num") + "---");
			System.out.print(result.getString("stu_name") + "---");
			System.out.print(result.getString("class_name") + "---");
			System.out.println(result.getString("score") + "---");
			numbers++;
		}
		System.out.println("������������" + numbers);
		JDBCtools.release(result, ps, con);
	}

//	=====
	// ĳ��ĳ�γ̲�����
	public static void classCourseFailed(String class_id, String course_id) throws SQLException {
		int numbers = 0;
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		// select * from student,teacher,project where student.id=teacher.sid
		// and student.id=project.sid;
		String search = "select stu.stu_num, stu.stu_name,si.score "
				+ "from score_info si inner join stu_info stu on si.stu_num = stu.stu_num "
				+ "where si.score < 60  and si.course_id = ? and  stu.stu_to_class = ?" + "order by stu.stu_num;";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setString(1, course_id);
		ps.setString(2, class_id);
		ResultSet result = ps.executeQuery();
		con.commit();
		while (result.next()) {
			System.out.print(result.getString("stu_num") + "---");
			System.out.print(result.getString("stu_name") + "---");
			System.out.println(result.getString("score") + "---");
			numbers++;
		}
		System.out.println("������������" + numbers);
		JDBCtools.release(result, ps, con);
	}
}
