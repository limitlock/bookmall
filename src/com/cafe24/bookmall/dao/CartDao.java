package com.cafe24.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.bookmall.vo.BookVo;
import com.cafe24.bookmall.vo.CartVo;

public class CartDao {

	public List<CartVo> getList(Long no) {

		List<CartVo> list = new ArrayList<CartVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // 결과값이 여러개일 경우

		try {
			conn = getConnection();
			String sql = "select b.name, c.amount, (b.price*c.amount) from member a, book b, cart c where a.no = c.member_no and b.no = c.book_no and a.no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// a.name,
				// String memberName = rs.getString(1);
				String bookName = rs.getString(1);
				Long amount = rs.getLong(2);
				Long price = rs.getLong(3);

				CartVo vo = new CartVo();
				vo.setBookName(bookName);
				vo.setAmount(amount);
				vo.setPrice(price);

				list.add(vo); // DB에서 가져온 결과(Vo)를 list에 삽입
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}

	public boolean insert(CartVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "insert into cart values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			// 바인딩
			pstmt.setLong(1, vo.getMemberNo());
			pstmt.setLong(2, vo.getBookNo());
			pstmt.setLong(3, vo.getAmount());

			// 실행
			int count = pstmt.executeUpdate();

			result = (count == 1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2.연결하기
			String url = "jdbc:mysql://localhost/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 " + e);
		}
		return conn;
	}
}
