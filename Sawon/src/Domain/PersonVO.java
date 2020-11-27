package Domain;

import java.util.Date;

public class PersonVO {
	
	int sawon;
	String name;
	String duty;
	String phone;
	Date indateDate;
	String indateStr;
	String outdateStr;
	Date outdateDate;
	String dcode;
	
	
	
	public Date getIndateDate() {
		return indateDate;
	}
	public void setIndateDate(Date indateDate) {
		this.indateDate = indateDate;
	}
	public String getIndateStr() {
		return indateStr;
	}
	public void setIndateStr(String indateStr) {
		this.indateStr = indateStr;
	}
	public String getOutdateStr() {
		return outdateStr;
	}
	public void setOutdateStr(String outdateStr) {
		this.outdateStr = outdateStr;
	}
	public Date getOutdateDate() {
		return outdateDate;
	}
	public void setOutdateDate(Date outdateDate) {
		this.outdateDate = outdateDate;
	}
	public int getSawon() {
		return sawon;
	}
	public void setSawon(int sawon) {
		this.sawon = sawon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDcode() {
		return dcode;
	}
	public void setDcode(String dcode) {
		this.dcode = dcode;
	}
	
	
}
