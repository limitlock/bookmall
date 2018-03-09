package com.cafe24.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cafe24.bookmall.vo.OrderVo;

public class OrderDao {

	public List<OrderVo> getList(Long no) {

		List<OrderVo> list = new ArrayList<OrderVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select b.order_no, concat(a.name, '/', a.email), b.price, b.location from member a, order_list b where a.no = b.member_no and b.member_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				String orderNo = rs.getString(1);
				String orderName = rs.getString(2);
				Long price = rs.getLong(3);
				String location = rs.getString(4);

				OrderVo vo = new OrderVo();
				vo.setOrderNo(orderNo);
				vo.setOrderName(orderName);
				vo.setPrice(price);
				vo.setLocation(location);

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

	public boolean insert(OrderVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Long curPrice = null;

		try {
			conn = getConnection();

			String curPriceUrl = "select sum((b.price*c.amount)) from member a, book b, cart c where a.no = c.member_no and b.no = c.book_no and a.no = ?";
			pstmt = conn.prepareStatement(curPriceUrl);
			pstmt.setLong(1, vo.getMemberNo());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				curPrice = rs.getLong(1);
			}

			String sql = "insert into order_list values(null,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			// 주문번호 ( 주문시간 + 회원번호 )
			SimpleDateFormat format = new SimpleDateFormat("YYYYMMDDHHmmss");
			String data = format.format(new Date());
			data += vo.getMemberNo();

			// 바인딩
			pstmt.setString(1, data);
			pstmt.setLong(2, curPrice); // vo.getPrice()
			pstmt.setString(3, vo.getLocation());
			pstmt.setLong(4, vo.getMemberNo());

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
