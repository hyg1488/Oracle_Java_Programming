package Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DBUtil.DBUtil;
import Domain.PersonVO;



public class PersonDAO {
	public PersonDAO() {}
	private static PersonDAO instance = new PersonDAO();
	public static PersonDAO getinstatnce() {
		return instance;
	}
	
	public List<PersonVO> check() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PersonVO> ll = new ArrayList<PersonVO>();
		String query = "SELECT * FROM tbl_person_201116";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PersonVO vo = new PersonVO();
				vo.setSawon(rs.getInt("sawon"));
				vo.setName(rs.getString("name"));
				vo.setDuty(rs.getString("duty"));
				vo.setPhone(rs.getString("phone"));
				vo.setIndateDate(rs.getDate("indate"));
				vo.setOutdateDate(rs.getDate("outdate"));
				vo.setDcode(rs.getString("dcode"));
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
	
	public int max_cno() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT MAX(sawon) as max FROM tbl_person_201116";
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
	
	public int meberList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT count(*) FROM tbl_person_201116";
		List <PersonVO> ll = new ArrayList<PersonVO>();
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
	
	public void setMember(PersonVO vo, int a) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query;
		if(a==1) query = "INSERT INTO tbl_person_201116 (SAWON, NAME, DUTY, PHONE, INDATE,OUTDATE, DCODE) VALUES(?, ?, ?, ?, ?,? ,? )";
		else query = "INSERT INTO tbl_person_201116 (SAWON, NAME, DUTY, PHONE, INDATE, DCODE) VALUES(?, ?, ?, ?, ?,? )";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, vo.getSawon());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getDuty());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getIndateStr());
			if(a == 1) pstmt.setString(6, vo.getOutdateStr());
			
			if(a==1) pstmt.setString(7, vo.getDcode());
			else pstmt.setString(6, vo.getDcode());
			
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

}
