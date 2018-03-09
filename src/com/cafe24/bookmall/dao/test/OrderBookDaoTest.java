package com.cafe24.bookmall.dao.test;

import java.util.List;

import com.cafe24.bookmall.dao.OrderBookDao;
import com.cafe24.bookmall.vo.OrderBookVo;

public class OrderBookDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 도서번호, 주문번호, 수량
		insertTest(1L, 1L, 2L);
		insertTest(2L, 1L, 4L);
		getListTest(1L);

		insertTest(2L, 2L, 3L);
		insertTest(3L, 2L, 1L);
		getListTest(2L);

	}

	public static void getListTest(Long no) {
		OrderBookDao dao = new OrderBookDao();
		List<OrderBookVo> list = dao.getList(no);
		for (OrderBookVo vo : list) {
			System.out.println(vo);
		}

	}

	public static void insertTest(Long bookNo, Long orderNo, Long amount) {
		OrderBookDao dao = new OrderBookDao();
		OrderBookVo vo = new OrderBookVo();

		vo.setBookNo(bookNo);
		vo.setOrderNo(orderNo);
		vo.setAmount(amount);
		dao.insert(vo);
	}

}
