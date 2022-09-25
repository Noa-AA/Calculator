package calculator_test;

import javax.swing.JFrame;

public class Calculator extends JFrame {
	
	public Calculator() {	//계산기 창의 제목, 크기 등 요소 지정
		
		setTitle("Calculator");	//제목
		setVisible(true); //화면에 보이기
		setSize(300,400); //계산기 창 크기
		setLocationRelativeTo(null); //화면 가운데에 창 띄우기
		setResizable(false);	//사이즈 조절 불가
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창 종료 시 프로그램도 종료
	}
	
	public static void main(String[] args) {
		new Calculator();
		
	}
	
}
