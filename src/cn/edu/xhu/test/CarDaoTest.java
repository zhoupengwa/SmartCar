package cn.edu.xhu.test;

import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;

import cn.edu.xhu.dao.CarDao;
import cn.edu.xhu.domain.Car;

public class CarDaoTest {

	// �������
	public void testAddCar() {
		Car car = new Car();
		car.setUserid(8);
		car.setBrand("����");
		car.setMark("�󱼱�־");
		car.setType("ԽҰ��");
		car.setNo("��A-9999");
		car.setEngine("��������001");
		car.setLeverl("��������");
		car.setMileage(2400.3);
		car.setGasoline(100.5);
		car.setCapability("��������");
		car.setDerailleur("���ٲ���");
		car.setLamp("���Ʋ���");
		try {
			new CarDao().addCar(car);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ɾ������
	public void testDeleteCar() {
		try {
			new CarDao().deleteCar(2, 8);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �޸�������Ϣ
	public void testUpdateCar() {
		Car car = new Car();
		car.setId(2);
		car.setUserid(1);
		car.setBrand("����");
		car.setMark("�󱼱�־");
		car.setType("ԽҰ��");
		car.setNo("��A-9999");
		car.setEngine("��������001");
		car.setLeverl("��������");
		car.setMileage(2400.3);
		car.setGasoline(100.5);
		car.setCapability("��������");
		car.setDerailleur("���ٲ���");
		car.setLamp("���Ʋ���");
		try {
			if (new CarDao().updateCar(car)) {
				System.out.println("�޸ĳɹ�");
			} else {
				System.out.println("�޸�ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	@Test
	// ��ѯ���г���
	public void testGetAllCars() {
		try {
			List<Car> cars = new CarDao().getAllCars(1);
			System.out.println(new Gson().toJson(cars));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
