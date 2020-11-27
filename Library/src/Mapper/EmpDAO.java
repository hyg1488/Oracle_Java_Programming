package Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Domain.EmpVO;
import Util.DBUtil;



public class EmpDAO {
	
	public EmpDAO() {}
	private static EmpDAO instance = new EmpDAO();
	public static EmpDAO getInstance() {
		return instance;
	}
	
	public int checkNo(int x) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int a = 0;
		String query = "select lentno from tbl_booklen_002 where lentno = ?";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, x);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				a = rs.getInt("lentno");
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

	
	public void modify(int c, String s, int a) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = null;
		switch(c) {
		case 0: query = "update tbl_booklen_002 set custname = ? where lentno = ?";
				break;
		case 1: query = "update tbl_booklen_002 set phone = ? where lentno = ?";
				break;
		case 2: query = "update tbl_booklen_002 set gender = ? where lentno = ?";
		break;
		case 3: query = "update tbl_booklen_002 set outdate = ? where lentno = ?";
		break;
		case 4: query = "update tbl_booklen_002 set indate = ? where lentno = ?";
		break;
		case 5: query = "update tbl_booklen_002 set indate = ? where lentno =?";
		break;
		}
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, s);
			pstmt.setInt(2, a);
			
			pstmt.executeUpdate();
			
			System.out.println("데이터가 정상적으로 저장되었습니다.");
			System.out.println();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int CheckAmount(int a) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int k = 0;
		
//		String query = "select * from tbl_booklen_002";
		String query ="select c.bookno ,count(c.bookno)as \"check\", amount, bookname\r\n" + 
				"from tbl_booklen_002 L , tbl_bookcode_002 C\r\n" + 
				"where L.bookno = C.bookno\r\n" + 
				"and indate is null\r\n" + 
				"group by c.bookno, amount, bookname";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				if(a == rs.getInt("bookno")) {
					if(rs.getInt("amount") == rs.getInt("check")) {
						System.out.println(rs.getInt("bookno")+"번 "+rs.getString("bookname")+" 책은 이미 모두 대출 중입니다.");
						k = 1;
					}
					
				}
			}
			
			
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
		
		return k;
		
	}
	
	public List <EmpVO> CheckMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List <EmpVO> ll = new ArrayList<EmpVO>();
//		String query = "select * from tbl_booklen_002";
		String query ="select lentno, custname, phone, decode(gender,'M','남자','F','여자','기타') gender2, bookname, \r\n" + 
				"outdate,indate\r\n" + 
				" from tbl_booklen_002 L, tbl_bookcode_002 C\r\n" + 
				" where L.bookno = c.bookno order by lentno asc";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setLentno(rs.getInt("lentno"));
				vo.setCustname(rs.getNString("custname"));
				vo.setPhone(rs.getString("phone"));
				vo.setGender(rs.getString("gender2"));
				vo.setBookName(rs.getString("bookname"));
				vo.setOutdate(rs.getString("outdate"));
				vo.setIndate_date(rs.getDate("indate"));
				
				ll.add(vo);
			}
			
			
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
		
		return ll;
		
	}
	
	public void setMember(EmpVO vo, int a) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query;
		if(a==1) query = "INSERT INTO tbl_booklen_002 (lentno, custname, phone, gender, bookno, outdate, indate) values(?,?,?,?,?,?,?)";
		else query = "INSERT INTO tbl_booklen_002 (lentno, custname, phone, gender, bookno, outdate ) values(?,?,?,?,?,?)";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, vo.getLentno());
			pstmt.setString(2, vo.getCustname());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getGender());
			pstmt.setInt(5, vo.getBookno());
			pstmt.setString(6, vo.getOutdate());

			if(a==1) pstmt.setString(7, vo.getIndate_str());
			
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
		String query = "select max(lentno) as mb from tbl_booklen_002";
		try {
			conn = DBUtil.getConnection();
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
	
	public int checkBook(int x) {
		List <EmpVO> ll = new ArrayList<EmpVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int a = 0;
		String query = "select bookno from tbl_bookcode_002 where bookno = ?";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, x);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				a = rs.getInt("bookno");
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
