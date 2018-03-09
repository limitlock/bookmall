package com.cafe24.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.bookmall.vo.OrderBookVo;
import com.cafe24.bookmall.vo.OrderVo;

public class OrderBookDao {

	public List<OrderBookVo> getList(Long no) {

		List<OrderBookVo> list = new ArrayList<OrderBookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select b.no, b.name, c.amount from member a, book b, order_book c, order_list d where a.no = d.member_no and d.no = c.order_no and c.book_no = b.no and a.no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Long bookNo = rs.getLong(1);
				String bookName = rs.getString(2);
				Long amount = rs.getLong(3);

				OrderBookVo vo = new OrderBookVo();
				vo.setBookNo(bookNo);
				vo.setBookName(bookName);
				vo.setAmount(amount);

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

	public boolean insert(OrderBookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into order_book values(?,?,?)";

			pstmt = conn.prepareStatement(sql);

			// 바인딩
			pstmt.setLong(1, vo.getBookNo());
			pstmt.setLong(2, vo.getOrderNo());
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
