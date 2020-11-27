package Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DBUtil.DBUtil;
import Domain.EmpVO;

public class EmpDAO {
	
	public EmpDAO() {}

	private static EmpDAO instance = new EmpDAO();
	public static EmpDAO getInstance() {
		return instance;
	}
	
	
	
	public List<EmpVO> selectClassGrade() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select SUBSTR(hakbun,1,1) as hakN, SUBSTR(hakbun,2,1) as hakG, sum(kor) as sk,\r\n" + 
				"	sum(mat) as sm, sum(eng) as se, round(avg(kor),2) as ak, \r\n" + 
				"	avg(eng) as ae, avg(mat) as am from tbl_score_002\r\n" + 
				"	group by substr(hakbun,1,1),substr(hakbun,2,1) order by hakN asc";
		
		List<EmpVO> ll = new ArrayList<EmpVO>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setHakbun(rs.getString("hakN"));
				vo.setName(rs.getString("hakG"));
				vo.setAllKor(rs.getDouble("sk"));
				vo.setAllMat(rs.getDouble("sm"));
				vo.setAllEng(rs.getDouble("se"));
				vo.setAllKorAvg(rs.getDouble("ak"));
				vo.setAllMatAvg(rs.getDouble("am"));
				vo.setAllEngAvg(rs.getDouble("ae"));
				
				ll.add(vo);
				
			}
		}catch(Exception e){
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
	
	public List<EmpVO> selectHagnyeonGrade() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select SUBSTR(hakbun,1,1) as sh,sum(kor) as sk,\r\n" + 
				"	sum(mat) as sm, sum(eng) as se, round(avg(kor),2) as ak, \r\n" + 
				"	avg(eng) as ae, avg(mat) as am from tbl_score_002\r\n" + 
				"	group by SUBSTR(hakbun,1,1)";
		
		List<EmpVO> ll = new ArrayList<EmpVO>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setHakbun(rs.getString("sh"));
				vo.setAllKor(rs.getDouble("sk"));
				vo.setAllMat(rs.getDouble("sm"));
				vo.setAllEng(rs.getDouble("se"));
				vo.setAllKorAvg(rs.getDouble("ak"));
				vo.setAllMatAvg(rs.getDouble("am"));
				vo.setAllEngAvg(rs.getDouble("ae"));
				
				ll.add(vo);
				
			}
		}catch(Exception e){
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
	
	
	public List<EmpVO> selectStudent() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM tbl_student_002";
		List<EmpVO> ll = new ArrayList<EmpVO>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setHakbun(rs.getString("hakbun"));
				vo.setName(rs.getString("name"));
				vo.setPhone1(rs.getString("phone1"));
				vo.setPhone2(rs.getString("phone2"));
				vo.setPhone3(rs.getString("phone3"));
				vo.setBirth(rs.getString("birth"));
				vo.setGender(rs.getString("gender"));
				vo.setRegdate(rs.getDate("regdate"));
				
				ll.add(vo);
				
			}
		}catch(Exception e){
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
	
	public List<EmpVO> selectGrade() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT c.hakbun, name, gender, kor, eng, mat FROM tbl_score_002 c, tbl_student_002 s where c.hakbun = s.hakbun";
		List<EmpVO> ll = new ArrayList<EmpVO>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setHakbun(rs.getString("hakbun"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setKor(rs.getInt("kor"));
				vo.setMat(rs.getInt("mat"));
				vo.setEng(rs.getInt("eng"));
				vo.avg();
				
				ll.add(vo);
				
			}
		}catch(Exception e){
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
	
	
	public void deleteGrade(String i) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		String query = null;
		query = "DELETE FROM tbl_score_002 WHERE hakbun = ?";
		
	
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, i);
			
			pstmt.executeUpdate();
			System.out.println("데이터가 성공적으로 삭제 되었습니다.");
			
		}catch(Exception k) {
			k.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	
	
	public boolean insertGrade(EmpVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		String query = null;
		query = "INSERT INTO TBL_SCORE_002 (HAKBUN, KOR, ENG, MAT) VALUES (?, ?,?,?)";
		
		
		boolean check = false;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getHakbun());
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			
			pstmt.executeUpdate();
			System.out.println("데이터가 성공적으로 저장 되었습니다.");
			check = true;
		}catch(Exception k) {
			k.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return check;
	}
	
	public EmpVO checkHakBun02(String i) {
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM tbl_score_002 where hakbun = ?";
		EmpVO vo = new EmpVO();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, i);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo.setHakbun(rs.getString("hakbun"));
				vo.setKor(rs.getInt("kor"));
				vo.setMat(rs.getInt("mat"));
				vo.setEng(rs.getInt("eng"));
			}
		}catch(Exception e){
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
	
	public int checkHackBun() {
		int a = 0;
		return a;
	}
	
	
	public int checkHakBun(String i) { // 0이면 널이다. 1이면 널이 아니다.
		int a = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT name FROM tbl_student_002 where hakbun = ?";
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, i);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a=1;
			}
		}catch(Exception e){
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
	
	public boolean insertData(EmpVO vo, int c) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		String query = null;
		if(c==1) {
			query = "INSERT INTO TBL_STUDENT_002 (HAKBUN, NAME, PHONE1, PHONE2, PHONE3, BIRTH, GENDER, REGDATE) VALUES (?, ?, ?,?,?,?,?,?)";
		}else {
			query = "INSERT INTO TBL_STUDENT_002 (HAKBUN, NAME, PHONE1, PHONE2, PHONE3, BIRTH, GENDER, REGDATE) VALUES (?, ?, ?,?,?,?,?,sysdate)";
		}
		
		boolean check = false;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getHakbun());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPhone1());
			pstmt.setString(4, vo.getPhone2());
			pstmt.setString(5, vo.getPhone3());
			pstmt.setString(6, vo.getBirth());
			pstmt.setString(7, vo.getGender());
			if(c==1) {
				pstmt.setString(8, vo.getRegdate_str());
			}
			
			pstmt.executeUpdate();
			System.out.println("데이터가 성공적으로 저장 되었습니다.");
			check = true;
		}catch(Exception k) {
			k.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return check;
	}
}
