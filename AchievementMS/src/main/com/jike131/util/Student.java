package main.com.jike131.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Student {
	public String getStu_num() {
		return stu_num;
	}
	public void setStu_num(String stu_num) {
		this.stu_num = stu_num;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getFaculty_name() {
		return faculty_name;
	}
	public void setFaculty_name(String faculty_name) {
		this.faculty_name = faculty_name;
	}
	public String getStu_sex() {
		return stu_sex;
	}
	public void setStu_sex(String stu_sex) {
		if (stu_sex.equals("1")) this.stu_sex = "男";
		else this.stu_sex = "女";
	}
	public String getStu_birthday() {
		return stu_birthday;
	}
	public void setStu_birthday(String stu_birthday) {
//		this.stu_birthday = dateFormat(stu_birthday);
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	private String stu_num;
	private String stu_name;
	private String faculty_name;
	private String stu_sex;
	private String stu_birthday;
	private String class_name;
	
	private static String dateFormat(String date) throws ParseException{
		Long long1 = Long.parseLong(date);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		Date date2 = sdf.parse(sdf.format(long1));
		String sd  = sdf.format(date2);
		
		
//		String sd = sdf.format(new Date(Integer.valueOf(date)));
//		String sd = sdf.format(new Date(date));
		return sd;
	}
}
