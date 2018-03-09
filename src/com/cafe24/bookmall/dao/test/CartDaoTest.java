package com.cafe24.bookmall.dao.test;

import java.util.List;

import com.cafe24.bookmall.dao.CartDao;
import com.cafe24.bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		//고객번호, 도서번호, 수량
		
		//1. 아이유
		insertTest(1L, 1L, 2L);
		insertTest(1L, 2L, 4L);
		getListTest(1L);
		
		//2. 마이콜
		insertTest(2L, 2L, 3L);
		insertTest(2L, 3L, 1L);
		getListTest(2L);
	
	}

	public static void insertTest(Long memberNo, Long bookNo, Long amount) {
		CartDao dao = new CartDao();
		CartVo vo = new CartVo();

		vo.setMemberNo(memberNo);
		vo.setBookNo(bookNo);
		vo.setAmount(amount);

		dao.insert(vo);
	}

	public static void getListTest(Long no) {
		CartDao dao = new CartDao();
		List<CartVo> list = dao.getList(no);
		for (CartVo vo : list) {
			System.out.println(vo);
		}

	}
}
