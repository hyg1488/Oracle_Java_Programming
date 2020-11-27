package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	String myDriver ="oracle.jdbc.driver.OracleDriver";
	String myURL = "jdbc:oracle:thin:@localhost:1521:xe";
	String myID = "jslhrd46";
	String myPass = "1234";
	
	private EmployeeDAO() {};
	private static EmployeeDAO instance = new EmployeeDAO();
	
	public static EmployeeDAO getInstance() {
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
		
		String query = "DELETE FROM Employee WHERE eno = ?";
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
	
	public void update(int num, String ename, String job, int manager, String hiredate, int salary, int commission, int dno) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "update employee set ename = ?, job = ?, manager = ?, hiredate = ?, salary = ?, commission = ?, dno = ?  where eno = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ename);
			pstmt.setString(2, job);
			pstmt.setInt(3, manager);
			pstmt.setString(4, hiredate);
			pstmt.setInt(5, salary);
			pstmt.setInt(6, commission);
			pstmt.setInt(7, dno);
			pstmt.setInt(8, num);


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
	
	public List<EmployeeVO> selectAll() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List <EmployeeVO> ll = new ArrayList<EmployeeVO>();
		
		String query = "select eno, ename, job, manager, hiredate, salary, commission, dname \r\n" + 
				"from employee E, Department D \r\n" + 
				"where E.dno = D.dno \r\n"
				+ "order by eno asc";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmployeeVO vo = new EmployeeVO();
				vo.setNum(rs.getInt("eno"));
				vo.setEname(rs.getString("ename"));
				vo.setJob(rs.getString("job"));
				vo.setManager(rs.getInt("manager"));
				vo.setHiredate(rs.getDate("hiredate"));
				vo.setSalary(rs.getInt("salary"));
				vo.setCommission(rs.getInt("commission"));
				vo.setDname(rs.getString("dname"));
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
	
	public EmployeeVO selectSawonNum(int num) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeVO vo = new EmployeeVO();
		
		String query = "select eno, ename, job, manager, hiredate, salary, commission, dname\r\n" + 
				"				from employee E, Department D\r\n" + 
				"				where E.dno = D.dno \r\n" + 
				"				and eno = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo.setNum(rs.getInt("eno"));
				vo.setEname(rs.getString("ename"));
				vo.setHiredate(rs.getDate("hiredate"));
				vo.setJob(rs.getString("job"));
				vo.setManager(rs.getInt("manager"));
				vo.setSalary(rs.getInt("salary"));
				vo.setCommission(rs.getInt("commission"));
				vo.setDname(rs.getString("dname"));
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
	
	
public List<EmployeeVO> selectSawonStr(String name) {
		
		List <EmployeeVO> ll = new ArrayList<EmployeeVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select eno, ename, job, manager, hiredate, salary, commission, dname, e.dno \r\n" + 
				"from employee E, Department D \r\n" + 
				"where E.dno = D.dno\r\n" + 
				"and ename = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmployeeVO vo = new EmployeeVO();
				vo.setNum(rs.getInt("eno"));
				vo.setEname(rs.getString("ename"));
				vo.setJob(rs.getString("job"));
				vo.setManager(rs.getInt("manager"));
				vo.setHireDateStr(rs.getString("hiredate"));
				vo.setSalary(rs.getInt("salary"));
				vo.setCommission(rs.getInt("commission"));
				vo.setDname(rs.getString("dname"));
				vo.setDno(rs.getInt("dno"));
				
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
	
	public List<EmployeeVO> selectSawon(String name) {
		
		List <EmployeeVO> ll = new ArrayList<EmployeeVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select eno, ename, job, manager, hiredate, salary, commission, dname \r\n" + 
				"from employee E, Department D \r\n" + 
				"where E.dno = D.dno\r\n" + 
				"and ename = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmployeeVO vo = new EmployeeVO();
				vo.setNum(rs.getInt("eno"));
				vo.setEname(rs.getString("ename"));
				vo.setJob(rs.getString("job"));
				vo.setManager(rs.getInt("manager"));
				vo.setHiredate(rs.getDate("hiredate"));
				vo.setSalary(rs.getInt("salary"));
				vo.setCommission(rs.getInt("commission"));
				vo.setDname(rs.getString("dname"));
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
	
	
	public int checkDepartment(int x) {	// a = 1 이면 이름 존재
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String st = null;
		int a = 0;
		String query = "select dname from department where dno = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, x);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				st = rs.getString("dname");
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

	public String checkNameReturn(int x) {	// a = 1 이면 이름 존재
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String st = null;
		
		String query = "select ename from employee where eno = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, x);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				st = rs.getString("ename");
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
		return st;
	}
			
			
	public int checkName(String x) {	// a = 1 이면 이름 존재
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String st = null;
		int a = 0;
		String query = "select ename from employee where ename = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, x);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				st = rs.getString("ename");
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
		String query = "select eno from Employee where eno = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, x);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				st = rs.getString("eno");
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

	

	public void setMember(EmployeeVO vo, String a) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query =null;
		if(a.equals("null")) query = "INSERT INTO employee (eno, ename, job, manager, salary, dno, hiredate, commission) values(?, ?, ?, ?,?,?,?,null)";
		else if(a.equals("in")) query = "INSERT INTO employee (eno, ename, job, manager, salary, dno, hiredate, commission) values(?, ?, ?, ?,?,?,?,?)";
		else if(a.equals("sys")) query = "INSERT INTO employee (eno, ename, job, manager, salary, dno, hiredate, commission) values(?, ?, ?, ?,?,?,sysdate,?)";
		else if(a.equals("nullsys")) query = "INSERT INTO employee (eno, ename, job, manager, salary, dno, hiredate, commission) values(?, ?, ?, ?,?,?,sysdate,null)";
		
		System.out.println();
		System.out.print("사원번호 : "+vo.getNum()+"\t\t");
		System.out.println("사원이름 : "+vo.getEname()+"\t");
		if(vo.getJob().length() > 7) {
			System.out.print("업 무 명 : "+vo.getJob()+"\t");
		}else System.out.print("업 무 명 : "+vo.getJob()+"\t\t");
		System.out.println("상사이름 : "+vo.getManager());
		// 상사이름은 매니저 이름을 찾는 매서드를 사용
		if(vo.getHireDateStr().equals("sys")) {
			System.out.print("입사일자 : "+"\t\t");
		}else System.out.print("입사일자 : "+vo.getHireDateStr()+"\t");
		System.out.println("급     여 : "+vo.getSalary()+"\t");
		if(vo.getCommission() == 0) {
			System.out.print("커 미 션 : "+"\t\t");
		}else System.out.print("커 미 션 : "+vo.getCommission()+"\t\t");
		System.out.print("부서번호 : "+vo.getDno());
		System.out.println();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, vo.getNum());
			pstmt.setString(2, vo.getEname());
			pstmt.setString(3, vo.getJob());
			pstmt.setInt(4, vo.getManager());
			pstmt.setInt(5, vo.getSalary());
			pstmt.setInt(6, vo.getDno());

			if(a.equals("null")) {
				pstmt.setString(7, vo.getHireDateStr());
			}else if(a.equals("in")) {
				pstmt.setString(7, vo.getHireDateStr());
				pstmt.setInt(8, vo.getCommission());
			}else if(a.equals("sys")){
				pstmt.setInt(7, vo.getCommission());
			}else if(a.equals("nullsys")) {
				
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
		String query = "select max(eno) as mb from employee";
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
	
	public List<EmployeeVO> selectDepartment() {
		
		List <EmployeeVO> List = new ArrayList<EmployeeVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select dno, dname from department";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmployeeVO vo = new EmployeeVO();
				vo.setDno(rs.getInt("dno"));
				vo.setDname(rs.getString("dname"));
				List.add(vo);
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
		return List;
	}
	
	public String selectManger(String name) {
		
		String manager = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select e1.ename from employee e1, employee e2\r\n" + 
				"where e1.eno = e2.manager\r\n" + 
				"and e2.ename = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				 manager = rs.getString("ename");
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
		return manager;
	}
}
