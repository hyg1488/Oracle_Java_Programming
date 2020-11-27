package Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Domain.EmpVO;

public class ViewDAO {
	Scanner sc = new Scanner(System.in);
	EmpDAO dao = EmpDAO.getInstance();
	EmpVO vo = new EmpVO();
	List <EmpVO> ll = new ArrayList<EmpVO>();
	int a = 0;
	int checkNo = 0;
	
	public void modify() {
		while(true) {
			while(true) {
				System.out.println("수정하려는 대출 번호를 입력하세요.");
				System.out.print("입력  (-999 입력시 종료) :");
				checkNo = sc.nextInt();
				if(checkNo == -999) {
					break;
				}
				int c = dao.checkNo(checkNo);
				if(c==0) {
					System.out.println();
					System.out.println("해당하는 대출 번호를 찾을 수 없습니다.");
					continue;
				}else {System.out.println();
					System.out.println("수정할 대출 번호는 "+c+"번 입니다.");
					break;
				}
			}
			if(checkNo == -999) {
				break;
			}
			System.out.println("수정할 정보를 선택하세요.");
			System.out.println("( 고객성명, 연락처, 성별, 도서명, 대출일자, 반납일자 )");
			System.out.print("입력 :");
			String s = sc.next();
			int check = 0;
			if(s.contains("성명")) {check = 0;
				s="성명";
			}
			else if(s.contains("연락처")) {check = 1;
				s="연락처";
			}
			else if(s.contains("성별")) {check = 2;
				s="성별";
			}
			else if(s.contains("대출")) {check = 3;
				s="대출 일자";
			}
			else if(s.contains("반납")) {check = 4;
				s="반납 일자";
			}else {
				System.out.println("제대로된 값을 입력해주세요.");
				continue;
			}
			
			int in;
			String st;
			if(check == 4) {
				System.out.println("반납일자 입력 (반납 취소인 경우 취소라고 입력하세요.):");
			}
			else {
				System.out.print("변경할 "+s+" 입력 :");
			}
			st = sc.next().toUpperCase();
			if(st.contains("취소")) {
				check = 5;
				st = null;
			}
			
			
			dao.modify(check, st, checkNo);
			
			
			break;
		}
			checkNo = 0;
		
	}

	
	
	public void checkData() {
		ll = dao.CheckMember();
		System.out.println("대출번호\t고객성명\t연락처\t\t성별\t도서명\t\t대출일자\t\t반납일자");
		for (int i = 0; i < ll.size(); i++) {
			System.out.print(ll.get(i).getLentno()+"\t");
			System.out.print(ll.get(i).getCustname()+"\t");
			System.out.print(ll.get(i).getPhone()+"\t");
			System.out.print(ll.get(i).getGender()+"\t");
			System.out.print(ll.get(i).getBookName()+"\t\t");
			System.out.print(ll.get(i).getOutdate()+"\t");
			if(ll.get(i).getIndate_date() == null) 
				System.out.println("대출중");
			else	
				System.out.println(ll.get(i).getIndate_date());
		}
		System.out.println();
	}
	
	public void addData() {
		if(dao.checkNo() == 0 ) {
			checkNo = 1;
		}else {
			checkNo = dao.checkNo() + 1;
		}
		while(true) {
			System.out.println("대출 번호 : "+ checkNo);
			vo.setLentno(checkNo);
			System.out.print("고객 성명 : ");
			vo.setCustname(sc.next());
			System.out.print("연락처 : ");
			vo.setPhone(sc.next());
			System.out.print("성별 : ");
			vo.setGender(sc.next().toUpperCase());
			while(true) {
				System.out.print("도서 코드 : ");
				checkNo = sc.nextInt();
				int ch = dao.checkBook(checkNo);
				
				if(ch == 0) {
					System.out.println("존재하지 않는 도서 코드 입니다.");
				}else {
					int k = dao.CheckAmount(ch);
					if(k == 1) {
						continue;
					}else { 
						vo.setBookno(ch);
						break;
					}
				}
			}
			System.out.print("대출 일자 : ");
			vo.setOutdate(sc.next());
			
			System.out.print("반납 일자가 존재하나요? (Y/N) : ");
			String answer = sc.next().toUpperCase();
			if(answer.equals("Y") || answer.equals("YES")) {
				System.out.print("반납 일자 등록 :");
				vo.setIndate_str(sc.next());
				a = 1;
			}else if(answer.equals("N") || answer.equals("NO")) {
				System.out.println("반납 일자가 존재하지 않습니다.");
			}
			
			System.out.print("설정한 데이터로 등록 하시겠습니까? (Y/N) :");
			answer = sc.next().toUpperCase();
			if(answer.equals("Y") || answer.equals("YES")) {
				dao.setMember(vo, a);
				break;
			}else if(answer.equals("N") || answer.equals("NO")) {
				System.out.print("데이터를 다시 입력하시겠습니까? (Y/N)");
				System.out.println("- N 입력시 데이터는 등록됩니다.");
				System.out.print("입력 :");
				answer = sc.next().toUpperCase();
				if(answer.equals("Y") || answer.equals("YES")) {
					continue;
				}else if(answer.equals("N") || answer.equals("NO")) {
					System.out.println("데이터를 등록합니다.");
					dao.setMember(vo, a);
					break;
				}
			}System.out.println();
		}
		
		
	}

}
