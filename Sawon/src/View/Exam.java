package View;

import Mapper.ViewDAO;

public class Exam {
	public static void main(String[] args) {
		
		ViewDAO vd = new ViewDAO();
		vd.checkSawon();
		
		vd.setMemberData();
	}
}
