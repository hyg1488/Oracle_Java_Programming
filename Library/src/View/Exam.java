package View;

import java.util.Date;
import java.util.Scanner;

import Mapper.ViewDAO;

public class Exam {
	public static void main(String[] args) {
		ViewDAO vd = new ViewDAO();
		Scanner sc= new Scanner(System.in);
		while(true) {
			String check;
			System.out.println("** 도서 관리 프로그램 **");
			System.out.println("1. 대출 정보 등록");
			System.out.println("2. 대출 정보 조회");
			System.out.println("4. 대출 정보 수정");
			System.out.println("5. 대출 정보 삭제");
			System.out.println("6. 종료");
			System.out.print("입력 : ");
			check = sc.next();
			if(check.contains("1") || check.contains("등록")) {
				System.out.println();
				
				System.out.println("▶  대출 정보 등록");
				vd.addData();
			}
			else if(check.contains("2") || check.contains("조회")) {
				System.out.println();
				System.out.println("▶  대출 정보 조회");
				vd.checkData();
			}
			else if(check.contains("3")  || check.contains("수정")) {
				System.out.println();
				System.out.println("▶  대출 정보 삭제");
				
			}else if(check.contains("4")  || check.contains("삭제")) {
				System.out.println();
				System.out.println("▶  대출 정보 수정");
				vd.modify();
			}
			else if(check.contains("4")) {
				System.out.println();
				System.out.println("▶  프로그램 종료");
				break;
			}
		}
	}
}
