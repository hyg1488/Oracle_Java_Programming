package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.EmpDAO;
import EmpVO.MemberVO;
import EmpVO.MoneyVO;

public class exam_02 {
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
}
