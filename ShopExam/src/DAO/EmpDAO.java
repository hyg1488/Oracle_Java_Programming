package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DBUtil.DBUtil;
import EmpVO.MemberVO;
import EmpVO.MoneyVO;

public class EmpDAO {
	private EmpDAO() {}
	private static EmpDAO instance = new EmpDAO();
	public static EmpDAO getInstance() {
		return instance;
	}
	
	public List<MemberVO> sumData() {
		List <MemberVO> ll = new ArrayList<MemberVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select m.custno, custname, phone, grade, sum(price) as sum \r\n" + 
				"from tbl_member_20200203 m, tbl_money_20200203 v\r\n" + 
				"where m.custno = v.custno\r\n" + 
				"group by m.custno, custname, phone, grade order by sum(price)desc";
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setCustno(rs.getInt("custno"));
				vo.setCustname(rs.getString("custname"));
				vo.setPhone(rs.getString("phone"));
				vo.setGrade(rs.getString("grade"));
				vo.setCount(rs.getInt("sum"));
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
	
	public int checkSeq() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT TBL_MEMBER_SEQ_CUSTNO.nextval as ss FROM DUAL";
		int a = 0;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				a = rs.getInt("ss");
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
		
		return a;
		
	}
	
	
	public void setMemberSeq(MemberVO vo , int a) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO tbl_member_20200203 (custno, custname, phone, gender, joindate, grade, city) values(?,?,?,?,?,?,?)";
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, a);
			pstmt.setString(2, vo.getCustname());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getJoindate());
			pstmt.setString(6, vo.getGrade());
			pstmt.setString(7, vo.getCity());
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
	
	
	public int max_cno() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT MAX(CUSTNO) as max FROM tbl_member_20200203";
		int max_no = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				max_no = rs.getInt("max");	
				max_no ++;
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
		return max_no;
	}
	
	public List<MemberVO> checkM(int a) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM tbl_member_20200203 M, tbl_city_20200203 C WHERE CUSTNO = ? AND M.CITY = C.CITY ORDER BY m.custno ASC";
		List <MemberVO> ll = new ArrayList<MemberVO>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, a);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mv = new MemberVO();
				mv.setCustno(rs.getInt("custno"));
				mv.setCustname(rs.getString("custname"));
				mv.setPhone(rs.getString("phone"));
				mv.setGender(rs.getString("gender"));
				mv.setJoindate(rs.getString("joindate"));
				mv.setGrade(rs.getString("grade"));
				mv.setCity(rs.getString("cityname"));
				ll.add(mv);
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
	
	
	public void setMember(MemberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO tbl_member_20200203 (custno, custname, phone, gender, joindate, grade, city) values(?,?,?,?,?,?,?)";
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, vo.getCustno());
			pstmt.setString(2, vo.getCustname());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getJoindate());
			pstmt.setString(6, vo.getGrade());
			pstmt.setString(7, vo.getCity());
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
	
	public List<MoneyVO> getMoney() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM tbl_money_20200203";
		List <MoneyVO> ll = new ArrayList<MoneyVO>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MoneyVO mv = new MoneyVO();
				mv.setCustno(rs.getInt("custno"));
				mv.setSaleno(rs.getInt("saleno"));
				mv.setPcost(rs.getInt("pcost"));
				mv.setAmoint(rs.getInt("amount"));
				mv.setPrice(rs.getInt("price"));
				mv.setPcode(rs.getString("pcode"));
				mv.setSdate(rs.getString("sdate"));
				ll.add(mv);
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
	
	
	public void makeMoney(MoneyVO mv) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "INSERT INTO tbl_money_20200203 (custno, saleno, pcost, amount, price, pcode, sdate) values(?,?,?,?,?,?,?)";
		List <MoneyVO> ll = new ArrayList<MoneyVO>();
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mv.getCustno());
			pstmt.setInt(2, mv.getSaleno());
			pstmt.setInt(3, mv.getPcost());
			pstmt.setInt(4, mv.getAmoint());
			pstmt.setInt(5, mv.getPrice());
			pstmt.setString(6, mv.getPcode());
			pstmt.setString(7, mv.getSdate());

			
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
	
	
	public List<MoneyVO> checkMoney(int a) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM tbl_money_20200203 WHERE CUSTNO = ?";
		List <MoneyVO> ll = new ArrayList<MoneyVO>();
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, a);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MoneyVO mv = new MoneyVO();
				mv.setCustno(rs.getInt("custno"));
				mv.setSaleno(rs.getInt("saleno"));
				mv.setPcost(rs.getInt("pcost"));
				mv.setAmoint(rs.getInt("amount"));
				mv.setPrice(rs.getInt("price"));
				mv.setPcode(rs.getString("pcode"));
				mv.setSdate(rs.getString("sdate"));
				
				ll.add(mv);
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
	
	public List<MemberVO> checkNum() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT CUSTNO FROM tbl_member_20200203";
		List <MemberVO> ll = new ArrayList<MemberVO>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mv = new MemberVO();
				mv.setCustno(rs.getInt("custno"));
				ll.add(mv);
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
	
	public int meberList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT count(*) FROM TBL_CITY_20200203";
		List <MemberVO> ll = new ArrayList<MemberVO>();
		int result = 0;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) result = rs.getInt(1);
			
			
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
		
		return result;
		
	}
	
	public List<MemberVO> exam_02(int a) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		if(a == 0) {
			query = "SELECT * FROM tbl_member_20200203 M, tbl_city_20200203 C\r\n" + 
					"WHERE M.CITY = C.CITY ORDER BY m.custno ASC";
		}else if(a == 1) {
			query = "SELECT * FROM tbl_member_20200203 M, tbl_city_20200203 C\r\n" + 
					"WHERE M.CITY = C.CITY ORDER BY m.custno DESC";
		}
		List <MemberVO> ll = new ArrayList<MemberVO>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mv = new MemberVO();
				mv.setCustno(rs.getInt("custno"));
				mv.setCustname(rs.getString("custname"));
				mv.setPhone(rs.getString("phone"));
				mv.setGender(rs.getString("gender"));
				mv.setJoindate(rs.getString("joindate"));
				mv.setGrade(rs.getString("grade"));
				mv.setCity(rs.getString("cityname"));
				ll.add(mv);
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
	
	public List<MemberVO> exam_01() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM TBL_CITY_20200203";
		List <MemberVO> ll = new ArrayList<MemberVO>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mv = new MemberVO();
				mv.setCity(rs.getString("city"));
				mv.setCityname(rs.getString("cityname"));
				ll.add(mv);
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
}
