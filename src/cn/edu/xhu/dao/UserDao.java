package cn.edu.xhu.dao;

import cn.edu.xhu.domain.User;
import cn.edu.xhu.util.CommUtils;

public class UserDao extends BaseDao {
	// ͨ���ֻ������������û��Ƿ����
	public boolean checkUser(String phone, String password) throws Exception {
		String sql = "select id from tb_user where phone=? and password=?";
		int count = super.query(sql, new Object[] { phone, password }, User.class).size();
		return count == 1;
	}

	// ͨ��ID����û��Ƿ����
	public boolean checkUserById(int userId) throws Exception {
		String sql = "select id from tb_user where id=?";
		int count = super.query(sql, new Object[] { userId }, User.class).size();
		return count == 1;
	}

	// ͨ���ֻ��ż���û��Ƿ����
	public boolean checkUserByPhone(String phone) throws Exception {
		String sql = "select id from tb_user where phone=?";
		int count = super.query(sql, new Object[] { phone }, User.class).size();
		return count == 1;
	}

	// ͨ���ֻ��Ų�ѯ�û�ID
	public int queryUserId(String phone) throws Exception {
		String sql = "select id from tb_user where phone=?";
		int userId = super.query(sql, new Object[] { phone }, User.class).get(0).getId();
		return userId;
	}

	// ����û�
	public boolean addUser(User user) throws Exception {
		String sql = "insert into tb_user values (null,?,?,?,?,?,?,0)";
		Object[] params = { user.getName(), user.getSex(), user.getPhone(), user.getPassword(), CommUtils.formatDate(),
				CommUtils.formatDate() };
		int count = super.update(sql, params);
		return count == 1;
	}

	// ͨ��ID��ѯ�û���Ϣ
	public User queryUserById(int id) throws Exception {
		String sql = "select id,name,sex,phone from tb_user where id=?";
		return super.query(sql, new Object[] { id }, User.class).get(0);
	}

	// �����û���Ϣ
	public boolean updateUser(User user) throws Exception {
		String sql = "update tb_user set name=?,sex=?,gmt_motified=? where id=?";
		Object[] params = { user.getName(), user.getSex(), CommUtils.formatDate(), user.getId() };
		int count = super.update(sql, params);
		return count == 1;
	}

	// �û��޸�����
	public boolean updatePassword(String password, String newPassword, int userId) throws Exception {
		String sql = "update tb_user set password=?,gmt_motified=? where password=? and id=?";
		Object[] params = { newPassword, CommUtils.formatDate(), password, userId };
		int count = super.update(sql, params);
		return count == 1;
	}
}
