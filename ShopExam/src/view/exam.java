package view;

import java.util.*;

import DAO.EmpDAO;
import EmpVO.MemberVO;

public class exam {
	public static void main(String[] args) {
		EmpDAO manager = EmpDAO.getInstance();
		List <MemberVO> lm = manager.exam_01();
		int count = manager.meberList();
		Scanner sc = new Scanner(System.in);
		System.out.println("도시 코드");
		for (int i = 0; i < lm.size(); i++) {
			System.out.print(lm.get(i).getCity()+"\t");
			System.out.println(lm.get(i).getCityname());
		}
		
		lm = manager.exam_02(1);
		
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
		
		int custno;
		String custname;
		String phone;
		String gender;
		String joindate;
		String grade;
		int city_sub;
		String city;
		String answer;
		
		while(true) {
			MemberVO vo = new MemberVO();
			
			System.out.println("회원 정보 등록");
			
			System.out.print("고객번호 등록 (정수 6자리이내) : ");
			vo.setCustno(sc.nextInt());
			
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
				city_sub = sc.nextInt();
				if(city_sub<=count) {
					vo.setCity(Integer.toString(city_sub));
					break;
				}else {
					System.out.println("(1 ~"+count+") 숫자를 입력하세요.");
				}
			}
			while(true) {
				System.out.println("입력정보");
				System.out.println(vo.getCustno()+"\t"+vo.getCustname()+"\t"+vo.getPhone()+"\t"
						+vo.getGender()+"\t"+vo.getJoindate()+"\t"+vo.getGrade()+"\t"+vo.getCity());
				System.out.print("해당 데이터로 등록을 진행하시겠습니까? (Y/N) : ");
				answer = sc.next().toUpperCase();
				if(answer.equals("Y") || answer.equals("YES")) {
					System.out.println("데이터가 등록되었습니다.");
					break;
				}else if(answer.equals("N") || answer.equals("NO")) {
					System.out.println("데이터를 등록하지 않습니다.");
					break;
				}
			}
			break;
		}
		
		System.out.print("회원 번호 입력 : ");
		
		
	}

}
