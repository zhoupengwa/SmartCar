package cn.edu.xhu.test;

import org.junit.Test;

import com.google.gson.Gson;

import cn.edu.xhu.dao.UserDao;
import cn.edu.xhu.domain.User;

public class UserDaoTest {

	// 通过手机号与密码检查用户是否存在
	public void test1() {
		String phone = "18483638748";
		String password = "123456";
		try {
			if (new UserDao().checkUser(phone, password)) {
				System.out.println("用户存在");
			} else {
				System.out.println("用户不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 通过手机号查询用户ID
	public void test2() {
		try {
			System.out.println(new UserDao().queryUserId("18483638748"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 添加用户
	public void test3() {
		User user = new User();
		user.setName("周鹏");// null
		user.setPassword("123456");
		user.setPhone("18483638748");
		user.setSex("男");
		try {
			if (new UserDao().addUser(user)) {
				System.out.println("注册成功");
			} else {
				System.out.println("注册失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	// 通过ID查询用户信息
	public void test4() {
		User user;
		try {
			user = new UserDao().queryUserById(0);
			System.out.println(new Gson().toJson(user));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 更新用户信息
	public void test5() {
		User user = new User();
		user.setId(8);
		user.setName("周鹏");// null
		user.setSex("男");
		try {
			if (new UserDao().updateUser(user)) {
				System.out.println("修改成功");
			} else {
				System.out.println("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 用户修改密码
	public void test6() {
		try {
			if (new UserDao().updatePassword("12345", "123456", 8)) {
				System.out.println("密码修改成功");
			} else {
				System.out.println("密码修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
