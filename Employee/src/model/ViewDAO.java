package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewDAO {
	Scanner sc = new Scanner(System.in);
	EmployeeDAO dao = EmployeeDAO.getInstance();
	EmployeeVO vo = new EmployeeVO();
	List <EmployeeVO> ll = new ArrayList<EmployeeVO>();
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
			String name = dao.checkNameReturn(num);
			if(name == null) {
				System.out.println("일치하는 번호가 없습니다. 다시 입력하세요.");
				continue;
			}
			ll = dao.selectSawonStr(name);
			System.out.print("사원이름("+name+") :");
			String ename = sc.nextLine();
			sc.nextLine();
			if(ename.equals("")) {
				ename = name;
			}
			
			
			System.out.print("업무명("+ll.get(0).getJob()+") :");
			String job = sc.nextLine();
			if(job.equals("")) {
				job = ll.get(0).getJob();
			}
			
			
			System.out.print("상사번호("+ll.get(0).getManager()+") :");
			String managerS = sc.nextLine();
			int manager = 0;
			if(managerS.equals("")) {
				manager = ll.get(0).getManager();
			}else {
				manager = Integer.parseInt(managerS);
			}
			
			String s = ll.get(0).getHireDateStr();
			s = s.substring(0,10);
			System.out.print("입사일자("+s+") :");
			String hiredate = sc.nextLine();
			if(hiredate.equals("")) {
				hiredate = s;
			}
			
			
			System.out.print("급 여("+ll.get(0).getSalary()+") :");
			String salaryStr = sc.nextLine();
			int salary;
			if(salaryStr.equals("")) {
				salary = ll.get(0).getSalary();
			}else {
				salary = Integer.parseInt(salaryStr);
			}
			
			System.out.print("커미션("+ll.get(0).getCommission()+") :");
			String commissionS = sc.nextLine();
			int commission;
			if(commissionS.equals("")) {
				commission = ll.get(0).getCommission();
			}else {
				commission = Integer.parseInt(commissionS);
			}
			
			System.out.print("부서번호("+ll.get(0).getDno()+") :");
			String DnoS = sc.nextLine();
			int Dno;
			if(DnoS.equals("")) {
				Dno = ll.get(0).getDno();
			}else {
				Dno = Integer.parseInt(DnoS);
			}
			
//			System.out.println(num+", "+name+", "+job+", "+hiredate+", "+salary+", "+commission+", "+Dno+", "+", ");
//			데이터 확인용 print
			
			System.out.print("수정하시겠습니까? (Y/N) ");
			int checkPoint = 0;
			while(true) {
				
				check = sc.next().toUpperCase();
				if(check.equals("YES") ||check.equals("Y") ) {
					dao.update(num, name, job, manager, hiredate, salary, commission, Dno);
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
		System.out.println("사원번호\t이름\t업무명\t\t상사번호\t입사일자\t\t급여\t커미션\t부서명");
		for(EmployeeVO vo : ll) {
			System.out.print(vo.getNum()+"\t");
			System.out.print(vo.getEname()+"\t");
			if(vo.getJob().length() > 7) {
				System.out.print(vo.getJob()+"\t");
			}else System.out.print(vo.getJob()+"\t\t");
			System.out.print(vo.getManager()+"\t");
			System.out.print(vo.getHiredate()+"\t");
			System.out.print(vo.getSalary()+"\t");
			if(vo.getCommission() == 0) {
				System.out.print(" "+"\t");
			}else System.out.print(vo.getCommission()+"\t");
			System.out.print(vo.getDname());
			System.out.println();
		}
		System.out.println();
		enter();
	}
		
	
	public void deleteSawon() {
		System.out.print("삭제를 원하는 고객의 번호를 입력하세요 ? "); 
		int num = sc.nextInt();
		System.out.println();
		if(dao.checkNum(num) == 1) {
			EmployeeVO vo = dao.selectSawonNum(num);
			
			System.out.print("사원번호 : "+vo.getNum()+"\t\t");
			System.out.println("사원이름 : "+vo.getEname()+"\t");
			if(vo.getJob().length() > 7) {
				System.out.print("업 무 명 : "+vo.getJob()+"\t");
			}else System.out.print("업 무 명 : "+vo.getJob()+"\t\t");
			System.out.println("상사이름 : "+vo.getManager());
			// 상사이름은 매니저 이름을 찾는 매서드를 사용
			
			System.out.print("입사일자 : "+vo.getHiredate()+"\t");
			System.out.println("급     여 : "+vo.getSalary()+"\t");
			if(vo.getCommission() == 0) {
				System.out.print("커 미 션 : "+"\t\t");
			}else System.out.print("커 미 션 : "+vo.getCommission()+"\t\t");
			System.out.print("부 서 명 : "+vo.getDname());
			System.out.println();
			
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
	
	public void selectSawon() {
		while(true) {
			System.out.print("검색할 사원 이름을 입력하세요 ? ");
			
			String name = sc.next().toUpperCase();
			System.out.println();
			if(dao.checkName(name) == 1) {
				ll = dao.selectSawon(name);
				for(EmployeeVO vo : ll) {
					System.out.print("사원번호 : "+vo.getNum()+"\t\t");
					System.out.println("사원이름 : "+vo.getEname()+"\t");
					if(vo.getJob().length() > 7) {
						System.out.print("업 무 명 : "+vo.getJob()+"\t");
					}else System.out.print("업 무 명 : "+vo.getJob()+"\t\t");
					System.out.println("상사이름 : "+dao.selectManger(name)+"\t");
					// 상사이름은 매니저 이름을 찾는 매서드를 사용
					
					System.out.print("입사일자 : "+vo.getHiredate()+"\t");
					System.out.println("급     여 : "+vo.getSalary()+"\t");
					if(vo.getCommission() == 0) {
						System.out.print("커 미 션 : "+"\t\t");
					}else System.out.print("커 미 션 : "+vo.getCommission()+"\t\t");
					System.out.print("부 서 명 : "+vo.getDname());
					System.out.println();
					sc.nextLine();
				}
				
			}else {
				System.out.println("등록된 고객이 없습니다.");
				System.out.println();
			}
			int ch = 0;
			while(true) {
				System.out.print("검색을 계속 할까요 (Y/N) ? ");
				String answer = sc.next().toUpperCase();
				if(answer.equals("Y") || answer.equals("YES")) {
					ch = 1;
					break;
				}else if(answer.equals("N") || answer.equals("NO")) {
					break;
				}else {
					System.out.println("제대로 된 값을 입력하세요.");
				}
			}
			if(ch == 1) {
				continue;
			}else {
				break;
			}
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
			String name = null;
			while(true) {
				System.out.print("사원이름 : ");
				name = sc.nextLine().toUpperCase();
				if(name.length() < 1) {
					System.out.println("이름 입력은 필수입니다.");
					continue;
				}else {
					break;
				}
			}
			vo.setEname(name);
			System.out.print("업  무 명 : ");
			vo.setJob(sc.next().toUpperCase());
			while(true) {
				System.out.print("상사번호 : ");
				int ch = sc.nextInt();
				if(dao.checkNum(ch) == 1) {
					vo.setManager(ch);
					break;
				}else {
					System.out.println("존재하지 않는 사원 번호입니다.");
					continue;
				}
			}
			sc.nextLine();
			String check;
			while(true) {
				
				System.out.print("입사일자 :");
				check = sc.nextLine().toUpperCase();
				if(check.length() == 8) {
					vo.setHireDateStr(check);
					check = "in";
					break;
				}else if(check.length() <1 ) {
					System.out.println("자동으로 현재 날짜가 등록일자로 지정됩니다.");
					check = "sys";
					vo.setHireDateStr("sysdate");
					break;
				}else {
					System.out.println("날짜를 정확하게 입력하여 주세요.");
				}
			}
			
			System.out.print("급   여 : ");
			vo.setSalary(sc.nextInt());
			sc.nextLine();
			
			System.out.print("커미션 : ");
			String k = sc.nextLine();
			if(k.length() <1) {
				if(check.equals("in")) {
					check = "null";
				}else if(check.equals("sys")) check = "nullsys";
			}else {
				int i = Integer.parseInt(k);
				vo.setCommission(i);
			}
			
			System.out.print("부서정보 ");
			List <EmployeeVO> vo2 = dao.selectDepartment();
			System.out.print("( ");
			for(EmployeeVO v2 : vo2) {
				System.out.print(v2.getDno()+"  ");
				System.out.print(v2.getDname()+"  ");
			}
			System.out.println(")");
			while(true) {
				System.out.print("부서번호 : ");
				int ch = sc.nextInt();
				if(dao.checkDepartment(ch) == 0) {
					System.out.println("부서 번호를 다시 입력하세요.");
					continue;
				}else if(dao.checkDepartment(ch) == 1) {
					vo.setDno(ch);
					break;
				}
			}
			int ch = 0;
			while(true) {
				System.out.print("설정한 데이터로 등록 하시겠습니까? (Y/N) :");
				String answer = sc.next().toUpperCase();
				if(answer.equals("Y") || answer.equals("YES")) {
					dao.setMember(vo, check);
					ch = 1;
					break;
				}else if(answer.equals("N") || answer.equals("NO")) {
					System.out.print("데이터를 다시 입력하시겠습니까? (Y/N)");
					System.out.println("- N 입력시 데이터는 등록됩니다.");
					System.out.print("입력 :");
					answer = sc.next().toUpperCase();
					if(answer.equals("Y") || answer.equals("YES")) {
						break;
					}else if(answer.equals("N") || answer.equals("NO")) {
						System.out.println("데이터를 등록합니다.");
						dao.setMember(vo, check);
						ch = 1;
						break;
					}
				}else {
					System.out.println("입력값을 제대로 입력해주세요.");
				}
				
			}	System.out.println();
			if(ch == 1) {
				break;
			}
		}
	}
}
