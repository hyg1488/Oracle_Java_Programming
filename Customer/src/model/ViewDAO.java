package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ViewDAO {
		Scanner sc = new Scanner(System.in);
		CustomerDAO dao = CustomerDAO.getInstance();
		CustomerVO vo = new CustomerVO();
		List <CustomerVO> ll = new ArrayList<CustomerVO>();
		int a = 0;
		int checkNo = 0;
		String check;
		
	public void enter() {
		System.out.print(" 메인 메뉴로 이동합니다 (Enter) ?");
		
		sc.nextLine();	
		
		System.out.println();
	}	
		
	public void update() {
		
		while(true) {
			System.out.print("수정을 원하는 고객의 번호를 입력하세요 ? ");
			int num = sc.nextInt();
			
			System.out.print("고 객 명 : ");
			String name = sc.next();
			
			String tel ="";
			while(true) {
				System.out.print("연 락 처 : ");
				check = sc.next();
				if(check.contains("-")) {
					tel = check;
					break;
				}
				else if(check.length() == 11) {
					String phone = "";
					phone += check.substring(0,3)+"-";
					phone += check.substring(3,7)+"-";
					phone += check.substring(7,11);
					tel = phone;
					break;
				}else if(check.length() == 10) {
					String phone = "";
					phone += check.substring(0,3)+"-";
					phone += check.substring(3,6)+"-";
					phone += check.substring(6,10);
					tel = phone;
					break;
				}
				else {
					System.out.println("전화 번호 값을 다시 확인해 주세요.");
					continue;
				}
			}
			System.out.print("직 장 명 : ");
			String office = sc.next();
			
			System.out.print("수정하시겠습니까? (Y/N) ");
			int checkPoint = 0;
			while(true) {
				
				check = sc.next().toUpperCase();
				if(check.equals("YES") ||check.equals("Y") ) {
					dao.update(num, name, tel, office);
					break;
				}else if(check.equals("NO") ||check.equals("N") ) {
					System.out.println("수정 작업을 계속 진행 하시겠습니까? (Y/N)");
					check = sc.next().toUpperCase();
					if(check.equals("YES") ||check.equals("Y") ) {
						checkPoint = 1;
						break;
					}else if(check.equals("NO") ||check.equals("N") ) {
						System.out.println();
						break;
					}else {
						System.out.println("Y / N 정확하게 입력하여 주세요.");
					}
				}else {
					System.out.println("Y / N 정확하게 입력하여 주세요.");
				}
			}
			
			if(checkPoint == 1) {
				continue;
			}else {
				break;
			}
		}
		
	}
	
	public void selectAll() {
		ll = dao.selectAll();
		System.out.println("고객번호\t이름\t연락처\t\t직장명\t\t생년월일\t\t성별\t등록일자");
		for(CustomerVO vo : ll) {
			System.out.print(vo.getNum()+"\t");
			System.out.print(vo.getName()+"\t");
			System.out.print(vo.getTel()+"\t");
			System.out.print(vo.getOffice());
			System.out.print(vo.getBirthday()+"\t");
			if(vo.getSex().equals("M"))
				System.out.print("남\t");
			else
				System.out.print("여\t");
			System.out.print(vo.getIndate()+"\t");
			System.out.println();
		}
		System.out.println();
		enter();
	}
		
	
	public void selectCustomerNum() {
		System.out.print("삭제를 원하는 고객의 번호를 입력하세요 ? "); 
		int num = sc.nextInt();
		System.out.println();
		if(dao.checkNum(num) == 1) {
			CustomerVO vo = dao.selectCustomer(num);
			System.out.println("고 객 명 : "+vo.getName());
			System.out.println("주     소 : "+vo.getTel());
			System.out.println("직 장 명 : "+vo.getOffice());
			System.out.println("생년월일 : "+vo.getBirthday());
			System.out.println("성     별 : "+vo.getSex());
			System.out.println("등록일자 : "+vo.getIndate());
			
			sc.nextLine();
			System.out.print("삭제 하시겠습니까 (Y/N) ? ");
			String answer = sc.next().toUpperCase();
			if(answer.equals("Y") || answer.equals("YES")) {
				dao.deleteData(num);
				System.out.println();
			}else if(answer.equals("N") || answer.equals("NO")) {
				System.out.println("삭제를 취소합니다.");
				System.out.println();
			}
		}else {
			System.out.println("등록된 고객이 없습니다.");
			System.out.println();
		}
	}
	
	public void selectCustomer() {
		System.out.print("원하는 고객의 이름을 입력하세요 ? ");
		
		String name = sc.next();
		System.out.println();
		if(dao.checkName(name) == 1) {
			CustomerVO vo = dao.selectCustomer(name);
			System.out.println("고 객 명 : "+vo.getName());
			System.out.println("주     소 : "+vo.getTel());
			System.out.println("직 장 명 : "+vo.getOffice());
			System.out.println("생년월일 : "+vo.getBirthday());
			System.out.println("성     별 : "+vo.getSex());
			System.out.println("등록일자 : "+vo.getIndate());
			
			sc.nextLine();
			enter();
			
		}else {
			System.out.println("등록된 고객이 없습니다.");
			System.out.println();
		}
	}
	
	public void addData() {
		if(dao.checkNo() == 0 ) {
			checkNo = 1;
		}else {
			checkNo = dao.checkNo() + 1;
		}
		vo.setNum(checkNo);
		while(true) {
			System.out.print("고 객 명 : ");
			vo.setName(sc.next());
			
			
			while(true) {
				System.out.print("연 락 처 : ");
				check = sc.next();
				if(check.contains("-")) {
					vo.setTel(check);
					break;
				}
				else if(check.length() == 11) {
					String phone = "";
					phone += check.substring(0,3)+"-";
					phone += check.substring(3,7)+"-";
					phone += check.substring(7,11);
					vo.setTel(phone);
					break;
				}else if(check.length() == 10) {
					String phone = "";
					phone += check.substring(0,3)+"-";
					phone += check.substring(3,6)+"-";
					phone += check.substring(6,10);
					vo.setTel(phone);
					break;
				}
				else {
					System.out.println("전화 번호 값을 다시 확인해 주세요.");
					continue;
				}
			}
			
			sc.nextLine();
			System.out.print("주     소 : ");
			String addr = sc.nextLine();
//			String[] addrArr = addr.split(" ");
//			String addrR = "";
//			for (int i = 0; i < addrArr.length; i++) {
//				addrR += addrArr[i];
//			}
			
			vo.setAddr(addr);
			System.out.print("직 장 명 : ");
			vo.setOffice(sc.next());
			
			while(true) {
				System.out.print("생년월일 : ");
				check = sc.next();
				
				if(check.contains("-")) {
					String [] checkPhone = check.split("-");
					String b = checkPhone[0]+checkPhone[1]+checkPhone[2];
					vo.setBirthdayStr(b);
					break;
				}
				else if(check.length() == 8) {
					
					try {
						int a = Integer.parseInt(check);
						check = Integer.toString(a);
					}
					catch(Exception e){
						System.out.println("제대로 된 날짜를 입력하세요. (문자 x)");
						continue;
					}
					vo.setBirthdayStr(check);
					break;
				}else {
					System.out.println("생년월일 값을 다시 확인해 주세요.( 19950516 or 1995-05-16)");
					continue;
				}
			}
			
			
			
			while(true) {
				System.out.print("성    별 : ");
				check = sc.next().toUpperCase();
				if(check.equals("남자") ||check.equals("남") || check.equals("M") ) {
					System.out.println("선택된 성별은 남자입니다.");
					vo.setSex("M");
					break;
				}else if(check.equals("여자") ||check.equals("여") || check.equals("F") ) {
					System.out.println("선택된 성별은 여자입니다.");
					vo.setSex("F");
					break;
				}else {
					System.out.println("성별 값을 정확하게 입력해주세요. (남자/남/M)");
				}
			}
			
			String check;
			while(true) {
				
				System.out.print("등록 일자를 입력하시겠습니까? (Y/N) :");
				check = sc.next().toUpperCase();
				if(check.equals("YES") ||check.equals("Y") ) {
					System.out.println("등록 일자를 입력하세요.");
					vo.setIndateStr(sc.next());
					check = "in";
					break;
				}else if(check.equals("NO") ||check.equals("N") ) {
					System.out.println("자동으로 현재 날짜가 등록일자로 지정됩니다.");
					vo.setIndateStr("sysdate");
					break;
				}else {
					System.out.println("Y / N 정확하게 입력하여 주세요.");
				}
			}
			
			System.out.print("설정한 데이터로 등록 하시겠습니까? (Y/N) :");
			String answer = sc.next().toUpperCase();
			if(answer.equals("Y") || answer.equals("YES")) {
				dao.setMember(vo, check);
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
					dao.setMember(vo, check);
					break;
				}
			}System.out.println();
		}
		
		
	}
}
