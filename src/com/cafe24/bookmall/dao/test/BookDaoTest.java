package com.cafe24.bookmall.dao.test;

import java.util.List;

import com.cafe24.bookmall.dao.BookDao;
import com.cafe24.bookmall.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		
		 insertTest("사람의 아들", 12000L, 1L);
		 insertTest("생활인의 철학", 28000L, 2L);
		 insertTest("파이썬 웹프로그래밍", 30000L, 3L);

		getListTest();
	}

	public static void insertTest(String name, Long price, Long categoryNo) {
		BookDao dao = new BookDao();
		BookVo vo = new BookVo();
		vo.setName(name);
		vo.setPrice(price);
		vo.setCategoryNo(categoryNo);
		dao.insert(vo);
	}

	public static void getListTest() {
		BookDao dao = new BookDao();
		List<BookVo> list = dao.getList();
		for (BookVo vo : list) {
			System.out.println(vo);
		}

	}

}
