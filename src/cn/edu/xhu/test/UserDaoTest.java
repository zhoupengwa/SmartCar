package cn.edu.xhu.test;

import org.junit.Test;

import com.google.gson.Gson;

import cn.edu.xhu.dao.UserDao;
import cn.edu.xhu.domain.User;

public class UserDaoTest {

	// ͨ���ֻ������������û��Ƿ����
	public void test1() {
		String phone = "18483638748";
		String password = "123456";
		try {
			if (new UserDao().checkUser(phone, password)) {
				System.out.println("�û�����");
			} else {
				System.out.println("�û�������");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ͨ���ֻ��Ų�ѯ�û�ID
	public void test2() {
		try {
			System.out.println(new UserDao().queryUserId("18483638748"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ����û�
	public void test3() {
		User user = new User();
		user.setName("����");// null
		user.setPassword("123456");
		user.setPhone("18483638748");
		user.setSex("��");
		try {
			if (new UserDao().addUser(user)) {
				System.out.println("ע��ɹ�");
			} else {
				System.out.println("ע��ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	// ͨ��ID��ѯ�û���Ϣ
	public void test4() {
		User user;
		try {
			user = new UserDao().queryUserById(0);
			System.out.println(new Gson().toJson(user));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// �����û���Ϣ
	public void test5() {
		User user = new User();
		user.setId(8);
		user.setName("����");// null
		user.setSex("��");
		try {
			if (new UserDao().updateUser(user)) {
				System.out.println("�޸ĳɹ�");
			} else {
				System.out.println("�޸�ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �û��޸�����
	public void test6() {
		try {
			if (new UserDao().updatePassword("12345", "123456", 8)) {
				System.out.println("�����޸ĳɹ�");
			} else {
				System.out.println("�����޸�ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
