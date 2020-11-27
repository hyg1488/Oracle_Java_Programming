package view;

import java.util.*;

import DAO.viewDAO;

public class Check {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		viewDAO vd = new viewDAO();
		String checkString;
		int checkInt;
		
		while(true) {
			System.out.println("---- 쇼핑몰 데이터 관리 프로그램 ----");
			System.out.println("1. 회원 데이터 등록");
			System.out.println("2. 회원 테이블 조회");
			System.out.println("3. 회원 데이터 수정");
			System.out.println("4. 회원 정보 삭제");
			System.out.println(" ▶  원하는 메뉴를 선택하세요. (END 를 입력시 프로그램은 종료됩니다.)");
			System.out.print("입력 : ");
			checkString = sc.nextLine().toUpperCase();
			
			if(checkString.equals("END")) {
				break;
			}
			if(checkString.length() == 1) {
				
				if(checkString.contains("1")) {
					System.out.println();
					System.out.println(" ▶  회원 테이블 조회");
					System.out.println("   1. 회원 정보 등록");
					System.out.println("   2. 회원 매출 등록");
					System.out.print("입력 : ");
					checkString = sc.nextLine().toUpperCase();
					
					if(checkString.contains("1")) {
						vd.setMemberData();
						System.out.println();
					}else if(checkString.contains("2")){
						vd.setData_Money();
					}
					
				}
				
				else if(checkString.contains("2")) {
					System.out.println();
					System.out.println(" ▶  회원 테이블 조회");
					System.out.println("   1. 개인 회원 정보 조회");
					System.out.println("   2. 모든 회원 정보 조회");
					System.out.println("   3. 개인 회원 매출 조회");
					System.out.println("   4. 모든 회원 매출 조회");
					System.out.println("   5. 도시 정보 조회");
					System.out.print("입력 : ");
					checkString = sc.nextLine().toUpperCase();
					
					if(checkString.length() == 1) {
						if(checkString.contains("1")) {
							vd.checkDataMember();
							System.out.println();
						}else if(checkString.contains("2")){
							vd.memberData(0);
							System.out.println();
						}else if(checkString.contains("3")) {
							vd.checkData_Member();
							System.out.println();
						}else if(checkString.contains("4")) {
							vd.moneyData();
							System.out.println();
							vd.moneySumData();
							System.out.println();
						}else if(checkString.contains("5")) {
							vd.cityData();
							System.out.println();
						}
						else System.out.println("정확한 데이터를 입력해주십시요.");
						
					}
				}
				
				else if(checkString.contains("3")){
					System.out.println("회원 데이터 수정");
				}
				
				else if(checkString.contains("4")){
					System.out.println("회원 데이터 삭제");
				}
			}
			
			if(checkString.length()> 1){
				if(checkString.contains("1") && checkString.contains("정보")) {
					System.out.println("포함");
				}else {
					System.out.println("no");
				}
			}
			

		
		}
	}
}
