package Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import Domain.*; 

public class ViewDAO {
	
	Scanner sc = new Scanner(System.in);
	PersonDAO dao = PersonDAO.getinstatnce();
	int count = 0;
	List<PersonVO> lm = new ArrayList<PersonVO>();
	
	String custno_check;
	int custno;
	
	
	public void checkSawon() {
		lm = dao.check();
		for (int i = 0; i < lm.size(); i++){
			PersonVO vo = new PersonVO();
			vo = lm.get(i);
			System.out.println("사원 번호\t사원 이름\t직책\t연락처\t\t입사 일자\t\t퇴사 일자\t\t부서 코드");
			System.out.print(vo.getSawon()+"\t"+vo.getName()+"\t"+vo.getDuty()+"\t"+vo.getPhone()+"\t"
				+vo.getIndateDate()+"\t");
			if(vo.getOutdateDate() == null)	
			System.out.println(vo.getOutdateDate()+"\t\t"+vo.getDcode()+"\t");
			if(vo.getOutdateDate() != null)	
				System.out.println(vo.getOutdateDate()+"\t"+vo.getDcode()+"\t");
		
		}
	}
	
	public void setMemberData() {
		String answer;
		String city;
		int a = 0;
		count = dao.meberList();
		
		while(true) {
			
			PersonVO vo = new PersonVO();
			System.out.println();
			System.out.println(" ▶  회원 정보 등록");
			
			System.out.println("사원 번호 : "+dao.max_cno());
			vo.setSawon(dao.max_cno());
			
			System.out.print("사원 이름 : ");
			vo.setName(sc.next());
			
			System.out.print("직책 : ");
			vo.setDuty(sc.next());
			
			System.out.print("연락처 등록 (- 를 붙여서 입력하세요.) :");
			vo.setPhone(sc.next());
			
			System.out.print("입사 일자 등록 : ");
			vo.setIndateStr(sc.next());
			
			System.out.print("퇴사한 사원 입니까? (Y/N) :");
			answer = sc.next().toUpperCase();
			if(answer.equals("Y") || answer.equals("YES")) {
				System.out.print("퇴사 일자 등록 :");
				vo.setOutdateStr(sc.next());
				a = 1;
			}else if(answer.equals("N") || answer.equals("NO")) {
				System.out.println("퇴사한 사원이 아닙니다.");
			}
			System.out.print("부서 코드 등록 : ");
			vo.setDcode(sc.next());
		
			
			while(true) {
				System.out.println("입력정보");
				if(a == 0) {
					System.out.println("사원 번호\t사원 이름\t직책\t연락처\t입사 일자\t부서 코드");
					System.out.println(vo.getSawon()+"\t"+vo.getName()+"\t"+vo.getDuty()+"\t"+vo.getPhone()+"\t"
							+vo.getIndateStr()+"\t"+vo.getDcode()+"\t");
				}else {
					System.out.println("사원 번호\t사원 이름\t직책\t연락처\t입사 일자\t퇴사 일자\t부서 코드");
					System.out.println(vo.getSawon()+"\t"+vo.getName()+"\t"+vo.getDuty()+"\t"+vo.getPhone()+"\t"
							+vo.getIndateStr()+"\t"+vo.getOutdateStr()+"\t"+vo.getDcode()+"\t");
				}
				
				System.out.print("해당 데이터로 등록을 진행하시겠습니까? (Y/N) : ");
				answer = sc.next().toUpperCase();
				if(answer.equals("Y") || answer.equals("YES")) {
					dao.setMember(vo, a);
					System.out.println("데이터가 등록되었습니다.");
					break;
				}else if(answer.equals("N") || answer.equals("NO")) {
					System.out.println("데이터를 등록하지 않습니다.");
					break;
				}
			}
			break;
		}
			
		}
	}


