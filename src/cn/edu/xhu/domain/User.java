package cn.edu.xhu.domain;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;// 编号
	private String name;// 姓名
	private String sex;// 性别
	private String phone;// 电话
	private String password;// 密码

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
