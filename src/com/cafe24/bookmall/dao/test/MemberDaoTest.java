package com.cafe24.bookmall.dao.test;

import java.util.List;

import com.cafe24.bookmall.dao.MemberDao;
import com.cafe24.bookmall.vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {

		insertTest("아이유","111-1111-1111","iu@gmail.com","1234");
		insertTest("마이콜", "222-2222-2222", "mycall@naver.com", "5678");
		getListTest();
	}

	public static void insertTest(String name, String phone, String email, String password) {
		MemberDao dao = new MemberDao();
		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setPhone(phone);
		vo.setEmail(email);
		vo.setPassword(password);
		dao.insert(vo);
	}

	public static void getListTest() {
		MemberDao dao = new MemberDao();
		List<MemberVo> list = dao.getList();
		for (MemberVo vo : list) {
			System.out.println(vo);
		}

	}
}
