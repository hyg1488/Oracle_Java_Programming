package model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;





public class CustomerDAO {
	
	String myDriver ="oracle.jdbc.driver.OracleDriver";
	String myURL = "jdbc:oracle:thin:@localhost:1521:xe";
	String myID = "jslhrd46";
	String myPass = "1234";
	
	private CustomerDAO() {};
	private static CustomerDAO instance = new CustomerDAO();
	
	public static CustomerDAO getInstance() {
		return instance;
	}

	
	// 커넥션 설정
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(myDriver);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(myURL, myID, myPass);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// 메소드 정의

			
	public void deleteData(int x) {	// a = 1 이면 이름 존재
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "DELETE FROM tbl_customer_2020 WHERE num = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, x);
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}		
	
	public void update(int num, String name, String tel, String office) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "update tbl_customer_2020 set name = ?, tel = ?, office = ? where num = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, tel);
			pstmt.setString(3, office);
			pstmt.setInt(4, num);
			rs = pstmt.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<CustomerVO> selectAll() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List <CustomerVO> ll = new ArrayList<CustomerVO>();
		
		String query = "select * from tbl_customer_2020 order by num asc";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CustomerVO vo = new CustomerVO();
				vo.setNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setTel(rs.getString("TEL"));
				vo.setAddr(rs.getString("addr"));
				vo.setOffice(rs.getString("office"));
				vo.setBirthday(rs.getDate("birthday"));
				vo.setSex(rs.getString("sex"));
				vo.setIndate(rs.getDate("indate"));
				ll.add(vo);
			}
	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return ll;
	}
	
	public CustomerVO selectCustomer(int num) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO vo = new CustomerVO();
		
		String query = "select * from tbl_customer_2020 where num = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				vo.setName(rs.getString("name"));
				vo.setTel(rs.getString("TEL"));
				vo.setAddr(rs.getString("addr"));
				vo.setOffice(rs.getString("office"));
				vo.setBirthday(rs.getDate("birthday"));
				vo.setSex(rs.getString("sex"));
				vo.setIndate(rs.getDate("indate"));
			}
	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	
	public CustomerVO selectCustomer(String name) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO vo = new CustomerVO();
		
		String query = "select * from tbl_customer_2020 where name = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				vo.setName(rs.getString("name"));
				vo.setTel(rs.getString("TEL"));
				vo.setAddr(rs.getString("addr"));
				vo.setOffice(rs.getString("office"));
				vo.setBirthday(rs.getDate("birthday"));
				vo.setSex(rs.getString("sex"));
				vo.setIndate(rs.getDate("indate"));
			}
	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	public int checkName(String x) {	// a = 1 이면 이름 존재
		List <CustomerVO> ll = new ArrayList<CustomerVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String st = null;
		int a = 0;
		String query = "select name from tbl_customer_2020 where name = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, x);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				st = rs.getString("name");
				if(st.equals(null)) {
					a = 0;
				}else {
					a = 1;
				}
			}
	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return a;
	}

	public int checkNum(int x) {	// a = 1 이면 이름 존재
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String st = null;
		int a = 0;
		String query = "select num from tbl_customer_2020 where num = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, x);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				st = rs.getString("num");
				if(st.equals(null)) {
					a = 0;
				}else {
					a = 1;
				}
			}
	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return a;
	}

	
	
	public void setMember(CustomerVO vo, String a) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query =null;
		if(a.equals("in")) query = "INSERT INTO tbl_customer_2020 (num, name, tel, addr, office, birthday, sex, indate) values(?,?,?,?,?,?,?,?)";
		else query = "INSERT INTO tbl_customer_2020 (num, name, tel, addr, office, birthday, sex, indate) values(?,?,?,?,?,?,?,sysdate)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, vo.getNum());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getTel());
			pstmt.setString(4, vo.getAddr());
			pstmt.setString(5, vo.getOffice());
			pstmt.setString(6, vo.getBirthdayStr());
			pstmt.setString(7, vo.getSex());
			if(a.equals("in")) {
				pstmt.setString(8, vo.getIndateStr());
			}
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public int checkNo() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int a = 0;
		String query = "select max(num) as mb from tbl_customer_2020";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				a = rs.getInt("mb");
			}
	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return a;
	}
	
	
}
