package View;

import java.util.Scanner;

import Mapper.ViewDTO;

/*
 *  추가 항목
 *  1. 정보 수정 기능 추가
 *  2. 학생 순위 추가
 *  3. 데이터 삭제 기능 추가
 *  4. 학과 및 교수 테이블 생성후, 담당 교수 - 교수 정보 join 추가
 *  
 *  오류 항목
 *  
 * 
 */
public class Exam {
	public static void main(String[] args) {
		ViewDTO view = new ViewDTO();
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("*** 학생 관리 프로그램 ****");
			System.out.println("1. 학생 정보 테이블");
			System.out.println("2. 학생 성적 테이블");
			System.out.println("3. 프로그램 종료");
			System.out.println("*********************");
			System.out.print("선택 :");
			String check = sc.next();
			
			System.out.println();
			
			if(check.contains("1")) {
				while(true) {
					System.out.println("*** 학생 정보 테이블 ****");
					System.out.println("   1. 학생 정보 등록");
					System.out.println("   2. 학생 정보 조회");
					System.out.println("   3. 테이블 나가기");
					System.out.println("*********************");
					System.out.print("선택 :");
					check = sc.next();
					
					System.out.println();
					
					if(check.contains("1")) {
						System.out.println(" ▶   학생 정보 등록 ");
						view.InsertData();
						System.out.println();
						break;
					}else if(check.contains("2")) {
						System.out.println(" ▶   학생 정보 조회 ");
						view.selectStudent();	
						System.out.println();
						break;
						
					}else if(check.contains("3")) {
						break;
					}
					else {
						System.out.println("입력값을 제대로 입력해주세요. (메뉴내의 숫자를 입력하세요.)");
						System.out.println();
					}
				}
			}else if(check.contains("2")){
				while(true) {
					System.out.println("*** 학생 성적 테이블 ****");
					System.out.println("   1. 학생 성적 등록");
					System.out.println("   2. 학생 성적 조회");
					System.out.println("   3. 테이블 나가기");
					System.out.println("*********************");
					System.out.print("선택 :");
					check = sc.next();
					
					System.out.println();
					
					if(check.contains("1")) {
						System.out.println(" ▶   학생 성적 등록 ");
						view.setGrade();
						System.out.println();
						break;
					}
					else if(check.contains("2")) {
						System.out.println(" ▶   학생 성적 조회 ");
						System.out.println("1. 개인 성적 조회");
						System.out.println("2. 학년별 성적 조회");
						System.out.println("3. 학급별 성적 조회 ");
						System.out.print("선택 :");
						check = sc.next();
						System.out.println();
						if(check.contains("1")) {
							System.out.println("   ▶   개인 성적 조회");
							view.selectGrade();
							System.out.println();
							break;
							
						}else if(check.contains("2")){
							System.out.println("   ▶   학년별 성적 조회");
							view.selectHagnyeonGrade();
							System.out.println();
							break;
						}else if(check.contains("3")) {
							System.out.println("   ▶   학급별 성적 조회");
							view.selectClassGrade();
							System.out.println();
							break;
						}
						else {
							System.out.println("입력값이 잘못되어 초기 화면으로 돌아갑니다. (메뉴내의 숫자를 입력하세요.)");
						}
						
					}else if(check.contains("3")) {
						break;
					}else {
						System.out.println("입력값을 제대로 입력해주세요. (메뉴내의 숫자를 입력하세요.)");
						System.out.println();
					}
				}
				
			}else if(check.contains("3")) {
				System.out.println(" ▶  프로그램 종료");
				break;
			}
		}
	}
}
