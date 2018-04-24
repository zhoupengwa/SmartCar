package cn.edu.xhu.domain;

import java.io.Serializable;

public class Car implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;// ���
	private int userid;// ӵ���߱��
	private String brand;// Ʒ��
	private String mark;// ��ʶ
	private String type;// ����
	private String no;// ���ƺ�
	private String engine;// ��������
	private String level;// ��������������
	private double mileage;// ���
	private double gasoline;// ������
	private String capability;// ���������ܣ��á�����
	private String derailleur;// ����������
	private String lamp;// ��������

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
