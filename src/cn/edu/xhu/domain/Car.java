package cn.edu.xhu.domain;

import java.io.Serializable;

public class Car implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;// 编号
	private int userid;// 拥有者编号
	private String brand;// 品牌
	private String mark;// 标识
	private String type;// 类型
	private String no;// 车牌号
	private String engine;// 发动机号
	private String level;// 级别（四门六座）
	private double mileage;// 里程
	private double gasoline;// 汽油量
	private String capability;// 发动机性能（好、坏）
	private String derailleur;// 变速器性能
	private String lamp;// 车灯性能

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getLevel() {
		return level;
	}

	public void setLeverl(String level) {
		this.level = level;
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	public double getGasoline() {
		return gasoline;
	}

	public void setGasoline(double gasoline) {
		this.gasoline = gasoline;
	}

	public String getCapability() {
		return capability;
	}

	public void setCapability(String capability) {
		this.capability = capability;
	}

	public String getDerailleur() {
		return derailleur;
	}

	public void setDerailleur(String derailleur) {
		this.derailleur = derailleur;
	}

	public String getLamp() {
		return lamp;
	}

	public void setLamp(String lamp) {
		this.lamp = lamp;
	}

}
