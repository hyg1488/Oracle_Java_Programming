package Domain;

import java.util.Date;

public class EmpVO {
	String hakbun;
	String name;
	String phone1;
	String phone2;
	String phone3;
	String birth;
	String gender;
	Date regdate;
	String regdate_str;
	
	int kor;
	int mat;
	int eng;
	
	double sum;
	double avg;
	
	double allKor;
	double allMat;
	double allEng;
	
	double allKorAvg;
	double allMatAvg;
	double allEngAvg;
	
	
	
	public double getAllKor() {
		return allKor;
	}


	public void setAllKor(double allKor) {
		this.allKor = allKor;
	}


	public double getAllMat() {
		return allMat;
	}


	public void setAllMat(double allMat) {
		this.allMat = allMat;
	}


	public double getAllEng() {
		return allEng;
	}


	public void setAllEng(double allEng) {
		this.allEng = allEng;
	}


	public double getAllKorAvg() {
		return allKorAvg;
	}


	public void setAllKorAvg(double allKorAvg) {
		this.allKorAvg = allKorAvg;
	}


	public double getAllMatAvg() {
		return allMatAvg;
	}


	public void setAllMatAvg(double allMatAvg) {
		this.allMatAvg = allMatAvg;
	}


	public double getAllEngAvg() {
		return allEngAvg;
	}


	public void setAllEngAvg(double allEngAvg) {
		this.allEngAvg = allEngAvg;
	}


	public void setSum(double sum) {
		this.sum = sum;
	}


	public void setAvg(double avg) {
		this.avg = avg;
	}


	public double getSum() {
		return sum;
	}


	public double getAvg() {
		return avg;
	}



	public void avg() {
		this.sum = kor+mat+eng;
		this.avg = sum/3;
	}
	
	
	
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public String getRegdate_str() {
		return regdate_str;
	}
	public void setRegdate_str(String regdate_str) {
		this.regdate_str = regdate_str;
	}
	public String getHakbun() {
		return hakbun;
	}
	public void setHakbun(String hakbun) {
		this.hakbun = hakbun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
	
	
}
