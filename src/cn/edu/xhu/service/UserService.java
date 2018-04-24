package cn.edu.xhu.service;

import java.util.List;

import com.google.gson.Gson;

import cn.edu.xhu.dao.CarDao;
import cn.edu.xhu.dao.RefuelInfoDao;
import cn.edu.xhu.dao.UserDao;
import cn.edu.xhu.domain.Car;
import cn.edu.xhu.domain.RefuelInfo;
import cn.edu.xhu.domain.User;
import cn.edu.xhu.exception.CarNotExistException;
import cn.edu.xhu.exception.UserExistException;
import cn.edu.xhu.exception.UserNotExistException;
import cn.edu.xhu.util.QRCodeUtil;

public class UserService {
	// ע��
	public boolean register(User user) throws Exception {
		UserDao dao = new UserDao();
		if (dao.checkUserByPhone(user.getPhone())) {
			throw new UserExistException();
		}
		return new UserDao().addUser(user);
	}

	// ��¼
	public int login(String phone, String password) throws Exception {
		UserDao dao = new UserDao();
		if (dao.checkUser(phone, password)) {
			return dao.queryUserId(phone);
		} else {
			return 0;
		}
	}

	// �޸�����
	public boolean updatePassword(String password, String newPassword, int userId) throws Exception {
		if (new UserDao().updatePassword(password, newPassword, userId)) {
			return true;
		}
		return false;
	}

	// �鿴������Ϣ
	public User lookinfo(int userId) throws Exception {
		User user = new UserDao().queryUserById(userId);
		return user;
	}

	// �޸ĸ�����Ϣ
	public boolean updateUserInfo(User user) throws Exception {
		return new UserDao().updateUser(user);
	}

	// �������
	public boolean addCar(Car car) throws Exception {
		if (new UserDao().checkUserById(car.getUserid())) {
			if (new CarDao().addCar(car)) {
				return true;
			}
		} else {
			throw new UserNotExistException();
		}
		return false;
	}

	// ɾ������
	public boolean deleteCar(int carId, int userId) throws Exception {
		CarDao dao = new CarDao();
		if (dao.checkCarById(carId)) {
			if (dao.deleteCar(carId, userId)) {
				return true;
			}
		} else {
			throw new CarNotExistException();
		}
		return false;
	}

	// �޸�����
	public boolean updateCar(Car car) throws Exception {
		CarDao dao = new CarDao();
		if (dao.checkCarById(car.getId())) {
			if (new CarDao().updateCar(car)) {
				return true;
			}
		} else {
			throw new CarNotExistException();
		}
		return false;
	}

	// ��ѯ¼������
	public List<Car> getAllCars(int userId) throws Exception {
		return new CarDao().getAllCars(userId);
	}

	// ԤԼ����
	public boolean addRefuelInfo(RefuelInfo refuelInfo, String savePath) throws Exception {
		if (new CarDao().checkCarById(refuelInfo.getCarid())) {
			QRCodeUtil.createQRCode(new Gson().toJson(refuelInfo), savePath);
			if (new RefuelInfoDao().addRefuelInfo(refuelInfo)) {
				return true;
			}
		} else {
			throw new CarNotExistException();
		}
		return false;
	}

	// ��ѯ����
	public List<RefuelInfo> getAllRefuelInfos(int userId) throws Exception {
		return new RefuelInfoDao().getAllRefulInfos(userId);
	}
}
