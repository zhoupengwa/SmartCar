package cn.edu.xhu.test;

import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;

import cn.edu.xhu.dao.CarDao;
import cn.edu.xhu.domain.Car;

public class CarDaoTest {

	// 添加汽车
	public void testAddCar() {
		Car car = new Car();
		car.setUserid(8);
		car.setBrand("奔驰");
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
			new CarDao().addCar(car);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除汽车
	public void testDeleteCar() {
		try {
			new CarDao().deleteCar(2, 8);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 修改汽车信息
	public void testUpdateCar() {
		Car car = new Car();
		car.setId(2);
		car.setUserid(1);
		car.setBrand("宝马");
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
			if (new CarDao().updateCar(car)) {
				System.out.println("修改成功");
			} else {
				System.out.println("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	@Test
	// 查询所有车辆
	public void testGetAllCars() {
		try {
			List<Car> cars = new CarDao().getAllCars(1);
			System.out.println(new Gson().toJson(cars));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
