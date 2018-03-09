package com.cafe24.bookmall.dao.test;

import java.util.List;

import com.cafe24.bookmall.dao.OrderDao;
import com.cafe24.bookmall.vo.OrderVo;

public class OrderDaoTest {
	public static void main(String[] args) {

		insertTest("관악구 신림동 위너스 402", 1L);
		getListTest(1L);

		insertTest("울산광역시 중구 태화동", 2L);
		getListTest(2L);
	}

	public static void insertTest(String location, Long memberNo) {
		OrderDao dao = new OrderDao();
		OrderVo vo = new OrderVo();

		vo.setLocation(location);
		vo.setMemberNo(memberNo);

		dao.insert(vo);
	}

	public static void getListTest(Long no) {
		OrderDao dao = new OrderDao();
		List<OrderVo> list = dao.getList(no);
		for (OrderVo vo : list) {
			System.out.println(vo);
		}

	}

}
