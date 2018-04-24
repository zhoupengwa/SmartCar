package cn.edu.xhu.dao;

import cn.edu.xhu.domain.User;
import cn.edu.xhu.util.CommUtils;

public class UserDao extends BaseDao {
	// 通过手机号与密码检查用户是否存在
	public boolean checkUser(String phone, String password) throws Exception {
		String sql = "select id from tb_user where phone=? and password=?";
		int count = super.query(sql, new Object[] { phone, password }, User.class).size();
		return count == 1;
	}

	// 通过ID检查用户是否存在
	public boolean checkUserById(int userId) throws Exception {
		String sql = "select id from tb_user where id=?";
		int count = super.query(sql, new Object[] { userId }, User.class).size();
		return count == 1;
	}

	// 通过手机号检查用户是否存在
	public boolean checkUserByPhone(String phone) throws Exception {
		String sql = "select id from tb_user where phone=?";
		int count = super.query(sql, new Object[] { phone }, User.class).size();
		return count == 1;
	}

	// 通过手机号查询用户ID
	public int queryUserId(String phone) throws Exception {
		String sql = "select id from tb_user where phone=?";
		int userId = super.query(sql, new Object[] { phone }, User.class).get(0).getId();
		return userId;
	}

	// 添加用户
	public boolean addUser(User user) throws Exception {
		String sql = "insert into tb_user values (null,?,?,?,?,?,?,0)";
		Object[] params = { user.getName(), user.getSex(), user.getPhone(), user.getPassword(), CommUtils.formatDate(),
				CommUtils.formatDate() };
		int count = super.update(sql, params);
		return count == 1;
	}

	// 通过ID查询用户信息
	public User queryUserById(int id) throws Exception {
		String sql = "select id,name,sex,phone from tb_user where id=?";
		return super.query(sql, new Object[] { id }, User.class).get(0);
	}

	// 更新用户信息
	public boolean updateUser(User user) throws Exception {
		String sql = "update tb_user set name=?,sex=?,gmt_motified=? where id=?";
		Object[] params = { user.getName(), user.getSex(), CommUtils.formatDate(), user.getId() };
		int count = super.update(sql, params);
		return count == 1;
	}

	// 用户修改密码
	public boolean updatePassword(String password, String newPassword, int userId) throws Exception {
		String sql = "update tb_user set password=?,gmt_motified=? where password=? and id=?";
		Object[] params = { newPassword, CommUtils.formatDate(), password, userId };
		int count = super.update(sql, params);
		return count == 1;
	}
}
