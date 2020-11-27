package Domain;

import java.util.Date;

public class EmpVO {
	int lentno;
	String custname;
	String phone;
	String gender;
	int bookno;
	String outdate;
	String indate_str;
	Date indate_date;
	String bookName;
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getLentno() {
		return lentno;
	}
	public void setLentno(int lentno) {
		this.lentno = lentno;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getBookno() {
		return bookno;
	}
	public void setBookno(int bookno) {
		this.bookno = bookno;
	}
	public String getOutdate() {
		return outdate;
	}
	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}
	public String getIndate_str() {
		return indate_str;
	}
	public void setIndate_str(String indate_str) {
		this.indate_str = indate_str;
	}
	public Date getIndate_date() {
		return indate_date;
	}
	public void setIndate_date(Date indate_date) {
		this.indate_date = indate_date;
	}
	
	
}
