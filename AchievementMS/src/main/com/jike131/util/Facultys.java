package main.com.jike131.util;

public class Facultys {
	private int id;
	private String name;
	private String num;
	
	public Facultys (int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Facultys (String num, String name) {
		this.num = num;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
}
