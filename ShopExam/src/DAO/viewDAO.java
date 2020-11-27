package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import EmpVO.MemberVO;
import EmpVO.MoneyVO;

public class viewDAO {
	Scanner sc = new Scanner(System.in);
	EmpDAO dao = EmpDAO.getInstance();
	int count = 0;
	List<MemberVO> lm = new ArrayList<MemberVO>();
	List<MemberVO> ll = new ArrayList<MemberVO>();
	List <MoneyVO> ml = new ArrayList<MoneyVO>();
	String custno_check;
	int custno;
	
	public void moneySumData() {
		ll = dao.sumData();
		
		System.out.println();
		System.out.println(" ▶  회원별 총 매출 조회");
		System.out.println("회원번호\t회원이름\t전화번호\t\t등급\t총 매출");
		for (int i = 0; i < ll.size(); i++) {
			System.out.print(ll.get(i).getCustno()+"\t");
			System.out.print(ll.get(i).getCustname()+"\t");
			System.out.print(ll.get(i).getPhone()+"\t");
			if(ll.get(i).getGrade().equals("A")) {
				System.out.print("VIP\t");
			}else if(ll.get(i).getGrade().equals("B")) {
				System.out.print("일반\t");
			}else if(ll.get(i).getGrade().equals("C")) {
				System.out.print("직원\t");
			}
			System.out.print(ll.get(i).getCount()+"\t");
	
			System.out.println();
		}
		
	}
	
	public void checkDataMember() {
		while(true) {
			System.out.println();
			System.out.println(" ▶  개인 회원 정보 조회");
			System.out.print("검색 하려는 회원 번호를 입력 (END 입력시 종료) : ");
			custno_check = sc.next().toUpperCase();
			if(custno_check.equals("END")) {
				break;
			}
			custno = Integer.parseInt(custno_check);
			
			ll = dao.checkNum();
			for (int i = 0; i < ll.size(); i++) {
				if(ll.get(i).getCustno() == custno) {
					count = 1;
				}
			}
			
			if(count == 1) {
				
				lm = dao.checkM(custno);
				System.out.println("회원 데이터");
				System.out.println();
				System.out.println(" 고객정보 ");
				System.out.println("회원번호\t회원성명\t연락처\t\t성별\t가입일자\t\t고객등급\t도시코드");
				for (int i = 0; i < lm.size(); i++) {
					System.out.print(lm.get(i).getCustno()+"\t");
					System.out.print(lm.get(i).getCustname()+"\t");
					System.out.print(lm.get(i).getPhone()+"\t");
					System.out.print(lm.get(i).getGender()+"\t");
					System.out.print(lm.get(i).getJoindate()+"\t");
					if(lm.get(i).getGrade().equals("A")) {
						System.out.print("VIP\t");
					}else if(lm.get(i).getGrade().equals("B")) {
						System.out.print("일반\t");
					}else if(lm.get(i).getGrade().equals("C")) {
						System.out.print("직원\t");
					}
					System.out.print(lm.get(i).getCity());
					System.out.println();
				}
			}else {
				System.out.println("등록된 회원 정보가 없습니다.");
			}
		}
	}
	
	public void setMemberData() {
		String answer;
		String city;
		int city_sub;
		count = dao.meberList();
		while(true) {
			
			MemberVO vo = new MemberVO();
			System.out.println();
			System.out.println(" ▶  회원 정보 등록");
			
			System.out.println("고객번호 : "+dao.max_cno());
			vo.setCustno(dao.max_cno());
			
			System.out.print("회원 성명 등록 : ");
			vo.setCustname(sc.next());
			
			System.out.print("연락처 등록 (- 를 붙여서 입력하세요.) :");
			vo.setPhone(sc.next());
			
			System.out.print("성별 등록 : ");
			vo.setGender(sc.next());
			
			System.out.print("가입 일자 등록 : ");
			vo.setJoindate(sc.next());
			
			System.out.print("고객 등급 등록 : ");
			vo.setGrade(sc.next());
		
			while(true) {
				System.out.print("도시코드 (1 ~ "+count+") : ");
				city = sc.next();
				city_sub = Integer.parseInt(city);
				if(city_sub<=count) {
					vo.setCity(city);
					break;
				}else {
					System.out.println("(1 ~"+count+") 숫자를 입력하세요.");
				}
			}
			while(true) {
				System.out.println("입력정보");
				System.out.println("회원번호\t회원성명\t연락처\t\t성별\t가입일자\t\t고객등급\t도시코드");
				System.out.println(vo.getCustno()+"\t"+vo.getCustname()+"\t"+vo.getPhone()+"\t"
						+vo.getGender()+"\t"+vo.getJoindate()+"\t"+vo.getGrade()+"\t"+vo.getCity());
				
				System.out.print("해당 데이터로 등록을 진행하시겠습니까? (Y/N) : ");
				answer = sc.next().toUpperCase();
				if(answer.equals("Y") || answer.equals("YES")) {
					dao.setMember(vo);
					System.out.println("데이터가 등록되었습니다.");
					memberData(1);
					break;
				}else if(answer.equals("N") || answer.equals("NO")) {
					System.out.println("데이터를 등록하지 않습니다.");
					break;
				}
			}
			break;
		}
	}
	
	
	public void moneyData() {
		ml = dao.getMoney();
		
		System.out.println();
		System.out.println(" ▶  모든 회원 매출 조회");
		System.out.println("회원번호\t판매번호\t\t단가\t수량\t가격\t상품코드\t판매일자");
		for (int i = 0; i < ml.size(); i++) {
			System.out.print(ml.get(i).getCustno()+"\t");
			System.out.print(ml.get(i).getSaleno()+"\t");
			System.out.print(ml.get(i).getPcost()+"\t");
			System.out.print(ml.get(i).getAmoint()+"\t");
			System.out.print(ml.get(i).getPrice()+"\t");
			System.out.print(ml.get(i).getPcode()+"\t");
			System.out.print(ml.get(i).getSdate());
			System.out.println();
		}
		
	}
	
	public void cityData() {
		lm = dao.exam_01();
		System.out.println();
		System.out.println(" ▶   도시 정보 조회");
		for (int i = 0; i < lm.size(); i++) {
			System.out.print(lm.get(i).getCity()+"\t");
			System.out.println(lm.get(i).getCityname());
		}
	}
	
	public void memberData(int a) {	// a가 1이면 desc으로 출력
		lm = dao.exam_02(a);
		
		System.out.println();
		System.out.println(" ▶  모든 회원 정보 조회");
		System.out.println("회원번호\t회원성명\t연락처\t\t성별\t가입일자\t\t고객등급\t도시코드");
		for (int i = 0; i < lm.size(); i++) {
			System.out.print(lm.get(i).getCustno()+"\t");
			System.out.print(lm.get(i).getCustname()+"\t");
			System.out.print(lm.get(i).getPhone()+"\t");
			System.out.print(lm.get(i).getGender()+"\t");
			System.out.print(lm.get(i).getJoindate()+"\t");
			if(lm.get(i).getGrade().equals("A")) {
				System.out.print("VIP\t");
			}else if(lm.get(i).getGrade().equals("B")) {
				System.out.print("일반\t");
			}else if(lm.get(i).getGrade().equals("C")) {
				System.out.print("직원\t");
			}
			System.out.print(lm.get(i).getCity());
			System.out.println();
		}
		
	}
	
	public void checkData_Member() {
		while(true) {
			System.out.println();
			System.out.println(" ▶  개인 회원 매출 조회");
			System.out.print("검색 하려는 회원 번호를 입력 : ");
			custno_check = sc.next().toUpperCase();
			if(custno_check.equals("END")) {
				break;
			}
			custno = Integer.parseInt(custno_check);
			
			ll = dao.checkNum();
			for (int i = 0; i < ll.size(); i++) {
				if(ll.get(i).getCustno() == custno) {
					count = 1;
				}
			}
			
			if(count == 1) {
				List<MoneyVO> lm = new ArrayList<MoneyVO>();
				lm = dao.checkMoney(custno);
				System.out.println("회원 데이터");
				System.out.println();
				System.out.println(" 고객정보 ");
				System.out.println("회원번호\t판매번호\t\t단가\t수량\t가격\t상품코드\t판매일자");
				for (int i = 0; i < lm.size(); i++) {
					System.out.print(lm.get(i).getCustno()+"\t");
					System.out.print(lm.get(i).getSaleno()+"\t");
					System.out.print(lm.get(i).getPcost()+"\t");
					System.out.print(lm.get(i).getAmoint()+"\t");
					System.out.print(lm.get(i).getPrice()+"\t");
					System.out.print(lm.get(i).getPcode()+"\t");
					System.out.print(lm.get(i).getSdate());
					System.out.println();
				}
			}else {
				System.out.println("등록된 회원 정보가 없습니다.");
			}
		}
	}

	public void setData_Money() {
		
		while(true) {
			System.out.println();
			System.out.println(" ▶  회원 매출 등록    (END 입력시 등록 종료)");
			System.out.print("등록하려는 회원 번호를 입력 : ");
			custno_check = sc.next().toUpperCase();
			if(custno_check.equals("END")) {
				break;
			}
			
			custno = Integer.parseInt(custno_check);
			ll = dao.checkNum();
			
			for (int i = 0; i < ll.size(); i++) {
				if(ll.get(i).getCustno() == custno) {
					count = 1;
				}
			}
			
			if(count == 0) {
				System.out.println("등록된 회원번호가 없습니다.");
				continue;
			}
			
			int saleno;
			int pcost;
			int amoint;
			int price;
			String pcode;
			String sdate;
			
			
			System.out.print("판매 번호 : ");
			saleno = sc.nextInt();
			
			System.out.print("단가 : ");
			pcost = sc.nextInt();
			
			System.out.print("수량 : ");
			amoint = sc.nextInt();
			
			System.out.print("가격 : ");
			price = sc.nextInt();
			
			System.out.print("상품 코드 : ");
			pcode = sc.next();
			
			System.out.print("판매 일자 : ");
			sdate = sc.next();
			
			MoneyVO money = new MoneyVO();
			money.setCustno(custno);
			money.setSaleno(saleno);
			money.setPcost(pcost);
			money.setAmoint(amoint);
			money.setPrice(price);
			money.setPcode(pcode);
			money.setSdate(sdate);
			
			dao.makeMoney(money);
			
		}
	}
}
