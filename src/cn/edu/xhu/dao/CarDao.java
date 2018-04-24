package cn.edu.xhu.dao;

import java.util.List;

import cn.edu.xhu.domain.Car;
import cn.edu.xhu.util.CommUtils;

public class CarDao extends BaseDao {
	// 添加汽车
	public boolean addCar(Car car) throws Exception {
		String sql = "insert into tb_car values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0)";
		Object[] params = { car.getUserid(), car.getBrand(), car.getMark(), car.getType(), car.getNo(), car.getEngine(),
				car.getLevel(), car.getMileage(), car.getGasoline(), car.getCapability(), car.getDerailleur(),
				car.getLamp(), CommUtils.formatDate(), CommUtils.formatDate() };
		int count = super.update(sql, params);

		return count == 1;
	}

	// 检查车辆是否存在
	public boolean checkCarById(int carId) throws Exception {
		String sql = "select id from tb_car where id=?";
		int count = super.query(sql, new Object[] { carId }, Car.class).size();
		return count == 1;
	}

	// 删除汽车
	public boolean deleteCar(int carId, int userId) throws Exception {
		String sql = "update tb_car set is_deleted=1 where id=? and userid=? and is_deleted=0";
		int count = super.update(sql, new Object[] { carId, userId });
		return count == 1;
	}

	// 修改汽车信息
	public boolean updateCar(Car car) throws Exception {
		String sql = "update tb_car set brand=?,mark=?,type=?,no=?,engine=?,level=?,mileage=?,gasoline=?,capability=?,derailleur=?,lamp=?,gmt_motified=? where id=? and userid=? and is_deleted=0";
		Object[] params = { car.getBrand(), car.getMark(), car.getType(), car.getNo(), car.getEngine(), car.getLevel(),
				car.getMileage(), car.getGasoline(), car.getCapability(), car.getDerailleur(), car.getLamp(),
				CommUtils.formatDate(), car.getId(), car.getUserid() };
		int count = super.update(sql, params);
		return count == 1;
	}

	// 查询所有车辆
	public List<Car> getAllCars(int userId) throws Exception {
		String sql = "select id,userid,brand,mark,type,no,engine,level,mileage,gasoline,capability,derailleur,lamp from tb_car where userid=? and is_deleted=0";
		List<Car> cars = super.query(sql, new Object[] { userId }, Car.class);
		return cars;
	}
}
