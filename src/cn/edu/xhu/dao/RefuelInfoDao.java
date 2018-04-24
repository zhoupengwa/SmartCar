package cn.edu.xhu.dao;

import java.util.List;

import cn.edu.xhu.domain.RefuelInfo;
import cn.edu.xhu.util.CommUtils;

public class RefuelInfoDao extends BaseDao {
	// 添加订单
	public boolean addRefuelInfo(RefuelInfo refuelInfo) throws Exception {
		String sql = "insert into tb_refuelinfo values(null,?,?,?,?,?,?,?,?,?,?,?,0)";
		Object[] params = { refuelInfo.getUserid(), refuelInfo.getCarid(), refuelInfo.getName(), refuelInfo.getTime(),
				refuelInfo.getStation(), refuelInfo.getType(), refuelInfo.getAmount(), refuelInfo.getPrice(),
				refuelInfo.getPath(), CommUtils.formatDate(), CommUtils.formatDate() };
		int count = super.update(sql, params);
		return count == 1;
	}

	// 查询订单
	public List<RefuelInfo> getAllRefulInfos(int userId) throws Exception {
		String sql = "select id,userid,carid,name,time,station,type,amount,price,path from tb_refuelinfo where userid=?";
		List<RefuelInfo> refuelInfos = super.query(sql, new Object[] { userId }, RefuelInfo.class);
		return refuelInfos;
	}
}
