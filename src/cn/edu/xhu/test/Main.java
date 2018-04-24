package cn.edu.xhu.test;

import com.google.gson.Gson;

import cn.edu.xhu.domain.RefuelInfo;

public class Main {
	public static void main(String[] args) {
		RefuelInfo refuelInfo = new RefuelInfo();
		refuelInfo.setId(1);
		refuelInfo.setAmount(232);
		refuelInfo.setName("º¬Ë°µ¥¼Û");
		System.out.println(new Gson().toJson(refuelInfo));
	}

}
