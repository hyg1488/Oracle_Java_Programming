package view;	// 3번 매니저 구현

import java.util.Scanner;

import model.ViewDAO;

public class exam {
	public static void main(String[] args) {
		ViewDAO vd = new ViewDAO();
		Scanner sc= new Scanner(System.in);
		while(true) {
			String check;

			System.out.println("=================================");
			System.out.println("=\t사원&부서 관리 프로그램 ver 1.0 \t=");
			System.out.println("=================================");

			System.out.println("	[1] 사원 전체 목록 ");
			System.out.println("	[2] 신규 사원 등록");
			System.out.println("	[3] 사원 검색");
			System.out.println("	[4] 사원 정보 수정");
			System.out.println("	[5] 사원 정보 삭제");
			System.out.println(" 	[6] 프로그램 종료");
			System.out.println("----------------------------------");
			System.out.print("해당 항목을 선택하세요 ? "); check = sc.next();
			System.out.println("===================================");
			if(check.contains("1") || check.contains("전체")) {
				System.out.println();
				System.out.println("▶  사원 전체 목록");
				System.out.println();
				vd.selectAll();
				
			}
			
			else if(check.contains("2") || check.contains("등록")) {
				System.out.println();
				System.out.println("▶  신규 사원등록");
				System.out.println();
				vd.addData();
				System.out.println();
				
			}
			else if(check.contains("3") || check.contains("검색")) {
				System.out.println();
				System.out.println("▶  사원 검색");
				System.out.println();
				vd.selectSawon();
				System.out.println();
				
			}
			else if(check.contains("4")  || check.contains("수정")) {
				System.out.println();
				System.out.println("▶  사원 정보 수정");
				System.out.println();
				vd.update();
				System.out.println();
				
			}else if(check.contains("5")  || check.contains("삭제")) {
				System.out.println();
				System.out.println("▶  고객 정보 삭제");
				System.out.println();
				vd.deleteSawon();
				
			}
			else if(check.contains("6") || check.contains("종료")) {
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

