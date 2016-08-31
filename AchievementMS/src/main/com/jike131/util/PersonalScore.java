package main.com.jike131.util;

public class PersonalScore {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getcourseName() {
		return courseName;
	}
	public void setcourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	private String name;
	private String courseName;
	private String score;
	private String teacher;
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
}
