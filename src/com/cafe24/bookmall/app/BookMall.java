package com.cafe24.bookmall.app;

import com.cafe24.bookmall.dao.test.BookDaoTest;
import com.cafe24.bookmall.dao.test.CartDaoTest;
import com.cafe24.bookmall.dao.test.CategoryDaoTest;
import com.cafe24.bookmall.dao.test.MemberDaoTest;
import com.cafe24.bookmall.dao.test.OrderBookDaoTest;
import com.cafe24.bookmall.dao.test.OrderDaoTest;

public class BookMall {

	public static void main(String[] args) {
		System.out.println("===============[1. 회원 리스트 – 2명]================");
		MemberDaoTest.getListTest();
		System.out.println();

		System.out.println("===============[2. 카테고리 리스트 – 3개]==============");
		CategoryDaoTest.getListTest();
		System.out.println();

		System.out.println("===============[3. 상품 리스트 – 3개]=================");
		BookDaoTest.getListTest();
		System.out.println();

		System.out.println("===============[4. 카트 리스트 – 2개]=================");
		CartDaoTest.getListTest(2L); // 회원번호 - 2 - 마이콜
		System.out.println();

		System.out.println("===============[5. 주문 리스트 – 1개]=================");
		OrderDaoTest.getListTest(2L);// 주문번호 - 2 - 마이콜
		System.out.println();

		System.out.println("===============[6. 주문 도서 리스트 – 2개]==============");
		OrderBookDaoTest.getListTest(2L);
	}

}
