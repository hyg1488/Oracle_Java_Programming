package view;

import java.util.Scanner;

import DAO.EmpDAO;
import DAO.viewDAO;
import EmpVO.MemberVO;

public class TEST {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		viewDAO vd = new viewDAO();
		String checkString;
		int checkInt;
		MemberVO vo = new MemberVO();
		EmpDAO dao = EmpDAO.getInstance();
		
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
	
		
		checkInt = dao.checkSeq();
//		dao.setMemberSeq(dao,checkInt);
		
	}
}
