package cn.edu.xhu.test;

import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;

import cn.edu.xhu.dao.RefuelInfoDao;
import cn.edu.xhu.domain.Car;
import cn.edu.xhu.domain.RefuelInfo;
import cn.edu.xhu.domain.User;
import cn.edu.xhu.service.UserService;
import cn.edu.xhu.util.CommUtils;

public class UserServiceTest {

	// 注册
	public void test1() {
		User user = new User();
		user.setName("周鹏");// null
		user.setPassword("123456");
		user.setPhone("18483638749");
		user.setSex("男");
		try {
			if (new UserService().register(user)) {
				System.out.println("注册成功");
			} else {
				System.out.println("注册失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 登录
	public void test2() {
		try {
			if (new UserService().login("18483638748", "123456") > 0) {
				System.out.println("登录成功");
			} else {
				System.out.println("登录失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 修改密码
	public void test3() {
		try {
			if (new UserService().updatePassword("12345", "12345", 2)) {
				System.out.println("修改成功");
			} else {
				System.out.println("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查看个人信息
	public void test4() {
		try {
			User user = new UserService().lookinfo(2);
			System.out.println(new Gson().toJson(user));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 修改个人信息
	public void test5() {
		User user = new User();
		user.setId(2);
		user.setName("冯凡");// null
		user.setPassword("123456");
		user.setPhone("18483638749");
		user.setSex("男");
		try {
			if (new UserService().updateUserInfo(user)) {
				System.out.println("修改成功");
			} else {
				System.out.println("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 添加汽车
	public void test6() {
		Car car = new Car();
		car.setUserid(1);
		car.setBrand("本田1");
		car.setMark("大奔标志");
		car.setType("越野车");
		car.setNo("川A-9999");
		car.setEngine("发动机号001");
		car.setLeverl("四门死做");
		car.setMileage(2400.3);
		car.setGasoline(100.5);
		car.setCapability("发动机好");
		car.setDerailleur("变速不好");
		car.setLamp("车灯不好");
		try {
			if (new UserService().addCar(car)) {
				System.out.println("添加成功");
			} else {
				System.out.println("添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除汽车
	public void test7() {
		try {
			if (new UserService().deleteCar(4, 1)) {
				System.out.println("删除成功");
			} else {
				System.out.println("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 修改汽车
	public void test8() {
		Car car = new Car();
		car.setUserid(1);
		car.setId(5);
		car.setBrand("本田111");
		car.setMark("大奔标志");
		car.setType("越野车");
		car.setNo("川A-9999");
		car.setEngine("发动机号001");
		car.setLeverl("四门死做");
		car.setMileage(2400.3);
		car.setGasoline(100.5);
		car.setCapability("发动机好");
		car.setDerailleur("变速不好");
		car.setLamp("车灯不好");
		try {
			if (new UserService().updateCar(car)) {
				System.out.println("修改成功");
			} else {
				System.out.println("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查询录入汽车
	public void test9() {
		List<Car> cars;
		try {
			cars = new UserService().getAllCars(1);
			System.out.println(new Gson().toJson(cars));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 预约加油
	public void test10() {
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
			if (new RefuelInfoDao().addRefuelInfo(refuelInfo)) {
				System.out.println("预约成功");
			} else {
				System.out.println("预约失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	// 查询订单
	public void test11() {
		try {
			List<RefuelInfo> refuelinfos = new UserService().getAllRefuelInfos(1);
			System.out.println(new Gson().toJson(refuelinfos));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
