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

	// ע��
	public void test1() {
		User user = new User();
		user.setName("����");// null
		user.setPassword("123456");
		user.setPhone("18483638749");
		user.setSex("��");
		try {
			if (new UserService().register(user)) {
				System.out.println("ע��ɹ�");
			} else {
				System.out.println("ע��ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��¼
	public void test2() {
		try {
			if (new UserService().login("18483638748", "123456") > 0) {
				System.out.println("��¼�ɹ�");
			} else {
				System.out.println("��¼ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �޸�����
	public void test3() {
		try {
			if (new UserService().updatePassword("12345", "12345", 2)) {
				System.out.println("�޸ĳɹ�");
			} else {
				System.out.println("�޸�ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �鿴������Ϣ
	public void test4() {
		try {
			User user = new UserService().lookinfo(2);
			System.out.println(new Gson().toJson(user));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// �޸ĸ�����Ϣ
	public void test5() {
		User user = new User();
		user.setId(2);
		user.setName("�뷲");// null
		user.setPassword("123456");
		user.setPhone("18483638749");
		user.setSex("��");
		try {
			if (new UserService().updateUserInfo(user)) {
				System.out.println("�޸ĳɹ�");
			} else {
				System.out.println("�޸�ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �������
	public void test6() {
		Car car = new Car();
		car.setUserid(1);
		car.setBrand("����1");
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
			if (new UserService().addCar(car)) {
				System.out.println("��ӳɹ�");
			} else {
				System.out.println("���ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ɾ������
	public void test7() {
		try {
			if (new UserService().deleteCar(4, 1)) {
				System.out.println("ɾ���ɹ�");
			} else {
				System.out.println("ɾ��ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �޸�����
	public void test8() {
		Car car = new Car();
		car.setUserid(1);
		car.setId(5);
		car.setBrand("����111");
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
			if (new UserService().updateCar(car)) {
				System.out.println("�޸ĳɹ�");
			} else {
				System.out.println("�޸�ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��ѯ¼������
	public void test9() {
		List<Car> cars;
		try {
			cars = new UserService().getAllCars(1);
			System.out.println(new Gson().toJson(cars));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ԤԼ����
	public void test10() {
		RefuelInfo refuelInfo = new RefuelInfo();
		refuelInfo.setUserid(100);
		refuelInfo.setCarid(1);
		refuelInfo.setName("����");
		refuelInfo.setStation("˫�Ӽ���վ");
		refuelInfo.setType("56#����");
		refuelInfo.setTime(CommUtils.formatDate());
		refuelInfo.setAmount(20.12);
		refuelInfo.setPrice(56.42);
		try {
			if (new RefuelInfoDao().addRefuelInfo(refuelInfo)) {
				System.out.println("ԤԼ�ɹ�");
			} else {
				System.out.println("ԤԼʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	// ��ѯ����
	public void test11() {
		try {
			List<RefuelInfo> refuelinfos = new UserService().getAllRefuelInfos(1);
			System.out.println(new Gson().toJson(refuelinfos));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
