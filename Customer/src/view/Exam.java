package view;

import java.util.Scanner;

import model.ViewDAO;

public class Exam {
	public static void main(String[] args) {
		ViewDAO vd = new ViewDAO();
		Scanner sc= new Scanner(System.in);
		while(true) {
			String check;

			System.out.println("=================================");
			System.out.println("=\t고객관리프로그램 ver 1.0 \t=");
			System.out.println("=================================");

			System.out.println("	[1] 고객 전체 목록 ");
			System.out.println("	[2] 신규 고객 등록");
			System.out.println("	[3] 고객 검색");
			System.out.println("	[4] 고객 정보 수정");
			System.out.println("	[5] 고객 정보 삭제");
			System.out.println(" 	[6] 프로그램 종료");
			System.out.println("----------------------------------");
			System.out.print("해당 항목을 선택하세요 ? "); check = sc.next();
			System.out.println("===================================");
			if(check.contains("1") || check.contains("전체")) {
				System.out.println();
				System.out.println("▶  고객 전체 목록");
				System.out.println();
				vd.selectAll();
			}
			
			else if(check.contains("2") || check.contains("등록")) {
				System.out.println();
				System.out.println("▶  신규 고객등록");
				System.out.println();
				vd.addData();
				System.out.println();
			}
			else if(check.contains("3") || check.contains("검색")) {
				System.out.println();
				System.out.println("▶  고객 검색");
				System.out.println();
				vd.selectCustomer();
				System.out.println();
			}
			else if(check.contains("4")  || check.contains("수정")) {
				System.out.println();
				System.out.println("▶  고객 정보 수정");
				System.out.println();
				System.out.println("* 수정은 '연락처' , '직장명' 만 수정 가능합니다.");
				System.out.println();
				vd.update();
				System.out.println();
			}else if(check.contains("5")  || check.contains("삭제")) {
				System.out.println();
				System.out.println("▶  고객 정보 삭제");
				System.out.println();
				vd.selectCustomerNum();
				
			}
			else if(check.contains("6")) {
				System.out.println();
				System.out.println("▶  프로그램 종료");
				System.out.println();
				System.out.print("프로그램을 종료하시겠습니까 (Y/N) ?");
				String answer = sc.next().toUpperCase();
				if(answer.equals("Y") || answer.equals("YES")) {
					break;
				}else if(answer.equals("N") || answer.equals("NO")) {
					System.out.println();
					continue;
				}
			}
		}
	}
}
