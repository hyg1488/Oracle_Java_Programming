package model;

import java.util.Date;

public class EmployeeVO {
	int num;
	String ename;
	String job;
	int manager;
	String HireDateStr;
	Date Hiredate;
	int dno;
	int commission;
	int salary;
	
	int Ddno;
	String dname;
	String loc;
	
	int grade;
	int losal;
	int hisal;
	
	public Date getHiredate() {
		return Hiredate;
	}
	public void setHiredate(Date hiredate) {
		Hiredate = hiredate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getManager() {
		return manager;
	}
	public void setManager(int manager) {
		this.manager = manager;
	}
	public String getHireDateStr() {
		return HireDateStr;
	}
	public void setHireDateStr(String hireDateStr) {
		HireDateStr = hireDateStr;
	}
	public int getDno() {
		return dno;
	}
	public void setDno(int dno) {
		this.dno = dno;
	}
	public int getCommission() {
		return commission;
	}
	public void setCommission(int commission) {
		this.commission = commission;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getDdno() {
		return Ddno;
	}
	public void setDdno(int ddno) {
		Ddno = ddno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getLosal() {
		return losal;
	}
	public void setLosal(int losal) {
		this.losal = losal;
	}
	public int getHisal() {
		return hisal;
	}
	public void setHisal(int hisal) {
		this.hisal = hisal;
	}
	
	
}
