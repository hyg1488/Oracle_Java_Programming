package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.EmpDAO;
import EmpVO.MemberVO;
import EmpVO.MoneyVO;

public class exam_03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		EmpDAO dao = EmpDAO.getInstance();
		int count = 0;
		List<MemberVO> ll = new ArrayList<MemberVO>();
		String custno_check;
		int custno;
		while(true) {
			System.out.print("회원 번호를 입력 : ");
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
