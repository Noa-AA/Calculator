package calculator_test;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Calculator extends JFrame {
	
	private JTextField inputSpace;
	
	public Calculator() {	
		
		setLayout(null);	// 기본 레이아웃 설정
		
		inputSpace = new JTextField(); //빈 공간 생성
		inputSpace.setEditable(false); //편집 불가(버튼만 사용가능)
		inputSpace.setBackground(Color.WHITE); //배경: 흰색
		inputSpace.setHorizontalAlignment(JTextField.RIGHT); //정렬: 오른쪽
		inputSpace.setFont(new Font("ginger", Font.BOLD, 50)); //글씨체 설정
		inputSpace.setBounds(8, 10, 270, 70); //위치, 크기 지정(위치:x, y / 크기:270, 70) )
		
		add(inputSpace);
		
		//계산기 창의 제목, 크기 등 요소 지정
		setTitle("Calculator");	//제목 지정
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
