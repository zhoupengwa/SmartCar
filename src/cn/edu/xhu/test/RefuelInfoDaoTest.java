package cn.edu.xhu.test;

import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;

import cn.edu.xhu.dao.RefuelInfoDao;
import cn.edu.xhu.domain.RefuelInfo;
import cn.edu.xhu.util.CommUtils;

public class RefuelInfoDaoTest {

	// 添加订单
	public void test1() {
		RefuelInfo refuelInfo = new RefuelInfo();
		refuelInfo.setUserid(100);
		refuelInfo.setCarid(1);
		refuelInfo.setName("周鹏");
		refuelInfo.setStation("双河加油站");
		refuelInfo.setType("56#汽油");
		refuelInfo.setTime(CommUtils.formatDate());
		refuelInfo.setAmount(20.12);
		refuelInfo.setPrice(56.42);
		try {
			new RefuelInfoDao().addRefuelInfo(refuelInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	// 查询订单
	public void test2() {
		try {
			List<RefuelInfo> refuelInfos = new RefuelInfoDao().getAllRefulInfos(1);
			System.out.println(new Gson().toJson(refuelInfos));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
