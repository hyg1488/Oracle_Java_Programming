package Mapper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import Domain.EmpVO;

public class ViewDTO {
	EmpDAO dao = EmpDAO.getInstance();
	Scanner sc = new Scanner(System.in);
	EmpVO vo = new EmpVO();
	List<EmpVO> ll = new ArrayList<EmpVO>();
	
	
	
	public void updateStudent() {
		
	}

	public void selectClassGrade() {
		ll = dao.selectClassGrade();
		System.out.println("학년\t반\t국어 총점\t영어 총점\t수학총점\t국어평균\t영어평균\t수학평균");
		for (int i = 0; i < ll.size(); i++) {
			DecimalFormat df = new DecimalFormat("###.00");
			System.out.print(ll.get(i).getHakbun()+"학년\t");
			System.out.print(ll.get(i).getName()+"반\t");
			System.out.print(df.format(ll.get(i).getAllKor())+"\t");
			System.out.print(df.format(ll.get(i).getAllEng())+"\t");
			System.out.print(df.format(ll.get(i).getAllMat())+"\t");
			System.out.print(df.format(ll.get(i).getAllKorAvg())+"\t");
			System.out.print(df.format(ll.get(i).getAllEngAvg())+"\t");
			System.out.print(df.format(ll.get(i).getAllMatAvg())+"\t");
			System.out.println();
		}
	}
	
	
	public void selectHagnyeonGrade() {
		ll = dao.selectHagnyeonGrade();
		System.out.println("학년\t국어 총점\t영어 총점\t수학총점\t국어평균\t영어평균\t수학평균");
		for (int i = 0; i < ll.size(); i++) {
			DecimalFormat df = new DecimalFormat("###.00");
			System.out.print(ll.get(i).getHakbun()+"학년\t");
			System.out.print(df.format(ll.get(i).getAllKor())+"\t");
			System.out.print(df.format(ll.get(i).getAllEng())+"\t");
			System.out.print(df.format(ll.get(i).getAllMat())+"\t");
			System.out.print(df.format(ll.get(i).getAllKorAvg())+"\t");
			System.out.print(df.format(ll.get(i).getAllEngAvg())+"\t");
			System.out.print(df.format(ll.get(i).getAllMatAvg())+"\t");
			System.out.println();
		}
	}
	
	public void selectStudent() {
		ll = dao.selectStudent();
		System.out.println("학번\t이름\t전화번호\t\t생년월일\t\t성별\t등록일자");
		for (int i = 0; i < ll.size(); i++) {
			System.out.print(ll.get(i).getHakbun()+"\t");
			System.out.print(ll.get(i).getName()+"\t");
			System.out.print(ll.get(i).getPhone1()+"-"+ll.get(i).getPhone2()+"-"+ll.get(i).getPhone3()+"\t");
			System.out.print(ll.get(i).getBirth()+"\t");
			if(ll.get(i).getGender().equals("M"))
			System.out.print("남\t");
			else
			System.out.print("여\t");
			System.out.print(ll.get(i).getRegdate()+"\t");
			System.out.println();
		}
	}
	
	public void selectGrade() {
		ll = dao.selectGrade();
		
		
		// String.format("%5.2f", ave1);
		// DecimalFormat df = new DecimalFormat("###.00");
		// DecimalFormat("$###.00"); 이런식으로 많이 사용함.
		// syso(df.format(ave1));
		int allKor = 0;
		int allEng = 0;
		int allMat = 0;
		double allSum = 0;
		double allAvg = 0;
		int i = 0;
		DecimalFormat df01 = new DecimalFormat("###");
		DecimalFormat df02 = new DecimalFormat("###.00");

		
		System.out.println("학번\t이름\t성별\t국어\t수학\t영어\t총점\t평균");
		for (i = 0; i < ll.size(); i++) {
			System.out.print(ll.get(i).getHakbun()+"\t");
			System.out.print(ll.get(i).getName()+"\t");
			if(ll.get(i).getGender().equals("M"))
			System.out.print("남자\t");
			else
			System.out.print("여자\t");
			System.out.print(ll.get(i).getKor()+"\t");
			System.out.print(ll.get(i).getMat()+"\t");
			System.out.print(ll.get(i).getEng()+"\t");
			System.out.print(df01.format(ll.get(i).getSum())+"\t");
			System.out.print(df02.format(ll.get(i).getAvg()));
			System.out.println();
			allKor += ll.get(i).getKor();
			allMat += ll.get(i).getMat();
			allEng += ll.get(i).getEng();
			allSum += ll.get(i).getSum();
			allAvg += ll.get(i).getAvg();
		}
		System.out.println("전체총점\t\t\t"+allKor+"\t"+allMat+"\t"+allEng+"\t"+df01.format(allSum));
		System.out.println("전체평균\t\t\t"+allKor/i+"\t"+allMat/i+"\t"+allEng/i+"\t"+df01.format(allSum/i)+"\t"+df02.format(allAvg/i));
		System.out.println();
	}
	
	public void setGrade(){
		while(true) {
			
			System.out.print("학번 입력 :");
			String hakbun = sc.next();
			
			int check = dao.checkHakBun(hakbun);
			if(check == 0) {
				System.out.println("학번을 찾을 수 없습니다. 다시 입력해주세요.");
				continue;
			}
			EmpVO ch = dao.checkHakBun02(hakbun);
			if(ch.getHakbun() == null) {
			}else {
				System.out.println();
				System.out.println("이미 성적이 존재합니다.\n원래 성적을 삭제하고 새로운 성적 등록을 진행하겠습니까? (Y/N)");
				System.out.print("입력 :");
				while(true) {
					String check_02 = sc.next().toUpperCase();
					if(check_02.equals("YES") ||check_02.equals("Y") ) {
						System.out.println("입력 되있던 성적을 삭제합니다.");
						dao.deleteGrade(hakbun);
						System.out.println();
						break;
					}else if(check_02.equals("NO") ||check_02.equals("N") ) {
						System.out.println("성적 등록을 진행하지 않습니다.");
						System.out.println();
						check = -1;
						break;
					}else {
						System.out.println("Y / N 정확하게 입력하여 주세요.");
					}
				}
			}
			if(check == -1) {
				break;
			}
			vo.setHakbun(hakbun);
			System.out.println(hakbun +" 학번 학생의 성적을 입력합니다.");
			System.out.print("국어 점수 입력 :");
			int kor = 0;
			while(true) {
				kor = sc.nextInt();
				check = checkGrade(kor);
				if(check == 0) {
					System.out.println("성적은 0 ~ 100 점 사이의 값만 입력 가능합니다.");
					System.out.print("재입력 :");
					continue;
				}else {
					break;
				}
			}
			vo.setKor(kor);
			System.out.print("수학 점수 입력 :");
			int mat = 0;
			while(true) {
				mat = sc.nextInt();
				check = checkGrade(mat);
				if(check == 0) {
					System.out.println("성적은 0 ~ 100 점 사이의 값만 입력 가능합니다.");
					System.out.print("재입력 :");
					continue;
				}else {
					break;
				}
			}
			vo.setMat(mat);
			System.out.print("영어 점수 입력 :");
			int eng = 0;
			while(true) {
				eng = sc.nextInt();
				check = checkGrade(eng);
				if(check == 0) {
					System.out.println("성적은 0 ~ 100 점 사이의 값만 입력 가능합니다.");
					System.out.print("재입력 :");
					continue;
				}else {
					break;
				}
			}
			vo.setEng(eng);
			
			System.out.print("국어 : "+kor +", 수학 : "+mat+", 영어 :  "+eng+"\n이대로 점수를 입력 하시겠습니까? (Y/N) :");
			while(true) {
				String check_02 = sc.next().toUpperCase();
				if(check_02.equals("YES") ||check_02.equals("Y") ) {
					System.out.println("해당 데이터로 성적을 등록합니다.");
					dao.insertGrade(vo);
					break;
				}else if(check_02.equals("NO") ||check_02.equals("N") ) {
					System.out.println("성적 등록을 진행하지 않습니다.");
					break;
				}else {
					System.out.println("Y / N 정확하게 입력하여 주세요.");
				}
			}
			
			System.out.println();
			break;
		}
	}
	public int checkGrade(int a) {
		int c = 0;
		if(a<=100 && a>=0) {
			c = 1;
		}
		return c;
	}
	public void InsertData() {
		String check;
		String[] checkPhone = new String[3];
		int checkDate = 0;
		while(true) {
			
			System.out.print("학번 입력 :");
			String hakbun;
			while(true) {
				String hakpattern = "\\d{4}"; // 정규식
				hakbun = sc.next();
				boolean bool = Pattern.matches(hakpattern, hakbun);
				if(bool) {
					
					dao.checkHackBun();
					
					int checkHakbun = dao.checkHakBun(hakbun);
					if(checkHakbun == 1) {
						System.out.println("이미 존재하는 학번입니다. 다시 입력해주십시요.");
						System.out.print("재입력 :");
					}
					else {
						System.out.println();
						break;
					}
				}else {
					System.out.println("입력 값을 다시 확인하세요. 학번은 4자리 수 입니다.");
					System.out.print("재입력 : ");
				}
			}
			vo.setHakbun(hakbun);
			System.out.print("이름 입력 :");
			sc.nextLine();
			while(true) {
				String name = sc.nextLine();
				
				
				if(name.length() < 1 || name.contains(" ")) {
					System.out.println("이름은 한자리 이상 입력해야 합니다. 또 공백이 포함되지 않게 입력해주세요.");
					System.out.print("재입력 : ");
				}
				else {
					vo.setName(name);
					System.out.println(name+"으로 이름을 등록합니다.");
					System.out.println();
					break;
				}
			}
			
			while(true) {
				System.out.print("전화 번호를 입력 :");
				check = sc.next();
				if(check.contains("-")) {
					checkPhone = check.split("-");
					vo.setPhone1(checkPhone[0]);
					vo.setPhone2(checkPhone[1]);
					vo.setPhone3(checkPhone[2]);
					System.out.println();
					break;
				}
				else if(check.length() == 11) {
					vo.setPhone1(check.substring(0,3));
					vo.setPhone2(check.substring(3,7));
					vo.setPhone3(check.substring(7,11));
					System.out.println();
					break;
				}else {
					System.out.println("전화 번호 값을 다시 확인해 주세요.");
					continue;
				}
			}
			while(true) {
				System.out.print("생년월일 입력 :");
				check = sc.next();
				System.out.println(check);
				if(check.contains("-")) {
					checkPhone = check.split("-");
					String b = checkPhone[0]+checkPhone[1]+checkPhone[2];
					vo.setBirth(b);
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
					vo.setBirth(check);
					break;
				}else {
					System.out.println("생년월일 값을 다시 확인해 주세요.( 19950516 or 1995-05-16)");
					continue;
				}
			}
			
			while(true) {
				System.out.print("성별을 입력 :");
				check = sc.next().toUpperCase();
				if(check.equals("남자") ||check.equals("남") || check.equals("M") ) {
					System.out.println("선택된 성별은 남자입니다.");
					vo.setGender("M");
					break;
				}else if(check.equals("여자") ||check.equals("여") || check.equals("F") ) {
					System.out.println("선택된 성별은 여자입니다.");
					vo.setGender("F");
					break;
				}else {
					System.out.println("성별 값을 정확하게 입력해주세요. (남자/남/M)");
				}
			}
			
			while(true) {
				System.out.print("등록 일자를 입력하시겠습니까? (Y/N) :");
				check = sc.next().toUpperCase();
				if(check.equals("YES") ||check.equals("Y") ) {
					System.out.println("등록 일자를 입력하세요.");
					vo.setRegdate_str(sc.next());
					checkDate = 1;
					break;
				}else if(check.equals("NO") ||check.equals("N") ) {
					System.out.println("자동으로 현재 날짜가 등록일자로 지정됩니다.");
					vo.setRegdate_str("sysdate");
					break;
				}else {
					System.out.println("Y / N 정확하게 입력하여 주세요.");
				}
			}
			
			boolean b = dao.insertData(vo, checkDate);
			
			if(b == true) {
				break;
			}
		}
		
	}
}
