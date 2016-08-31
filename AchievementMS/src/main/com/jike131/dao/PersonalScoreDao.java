package main.com.jike131.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.jike131.util.*;
public class PersonalScoreDao {
	/*
	 * ͨ��ѧ�Ų�ѯĳ�˳ɼ� �����ʾ�� �˺�---Ӣ���﷨---80---
	 * 个人成绩查询
	 */
	public static ArrayList<PersonalScore> queryByNum(String stuNum) throws SQLException {
		ArrayList<PersonalScore> list = new ArrayList();
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		String search = "select stu.stu_name,c.course_name,c.teacher, si.score  "
				+ "from score_info si inner join stu_info stu on si.stu_num =stu.stu_num  "
				+ "inner join course c on si.course_id= c.course_id " + "where stu.stu_num = ?;";
		PreparedStatement ps = con.prepareStatement(search);
		ps.setString(1, stuNum);
		ResultSet result = ps.executeQuery();
		con.commit();
		while (result.next()) {
			PersonalScore score = new PersonalScore();
			score.setName(result.getString("stu_name"));
			score.setScore(result.getString("score"));
			score.setcourseName(result.getString("course_name"));
			score.setTeacher(result.getString("teacher"));
			list.add(score);
		}
		JDBCtools.release(result, ps, con);
		return list;
	}
}
