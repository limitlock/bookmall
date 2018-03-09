package com.cafe24.bookmall.dao.test;

import java.util.List;

import com.cafe24.bookmall.dao.CategoryDao;
import com.cafe24.bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		
		insertTest("소설");
		insertTest("수필");
		insertTest("컴퓨터/IT");
		getListTest();
	}

	public static void insertTest(String genre) {
		CategoryDao dao = new CategoryDao();
		CategoryVo vo = new CategoryVo();
		vo.setGenre(genre);
		dao.insert(vo);
	}
	
	public static void getListTest() {
		CategoryDao dao = new CategoryDao();
		List<CategoryVo> list = dao.getList();
		for (CategoryVo vo : list) {
			System.out.println(vo);
		}

	}

}
