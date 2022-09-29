package calculator_test;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame {
	
	private JTextField inputSpace;
	
	private String num = ""; //계산식에 사용될 숫자 num
	
	private ArrayList<String> equation = new ArrayList<String>(); //기능 구현: 숫자와 연산 기호 구분
	
	public Calculator() {	
		
		setLayout(null);	// 기본 레이아웃 설정
		
		//JTextField 생성
		inputSpace = new JTextField(); //빈 공간 생성
		inputSpace.setEditable(false); //편집 불가(버튼만 사용가능)
		inputSpace.setBackground(Color.WHITE); //배경: 흰색
		inputSpace.setHorizontalAlignment(JTextField.RIGHT); //정렬: 오른쪽
		inputSpace.setFont(new Font("Arial", Font.BOLD, 50)); //글씨체 설정
		inputSpace.setBounds(8, 10, 270, 70); //위치, 크기 지정(위치:x, y / 크기:270, 70) 
		
		
		//버튼 생성
		JPanel buttonPanel = new JPanel();
		
		//레이아웃 지정 - GridLayout 사용
		buttonPanel.setLayout(new GridLayout(4, 5, 10, 10)); //GridLayout(가로 칸, 세로 칸, 좌우간격, 상하간격)
		
		//버튼 위치, 크기 지정
		buttonPanel.setBounds(8, 90, 270, 235);
		
		//버튼 내용 배열에 저장
		String button_names[] = { "C", "√", "%", "7", "8", "9", "×", "4", "5", "6", "-", "1", "2", "3", "+", "0", "00", ".", "=" };
		
		JButton buttons[] = new JButton[button_names.length]; //버튼 배열
		
		//배열을 통한 버튼 생성
		for (int i = 0; i < button_names.length; i++) {
			
			buttons[i] = new JButton(button_names[i]);
			
			buttons[i].setFont(new Font("Arial", Font.BOLD, 20)); //글씨체
			
			
			if (button_names[i] == "C") buttons[i].setBackground(Color.RED); //버튼 색 지정
			
			else if ((i >= 4 && i <= 6) || (i >= 8 && i <= 10) || (i >= 12 && i <= 14)) buttons[i].setBackground(Color.BLACK);
			
			else buttons[i].setBackground(Color.GRAY);
			
			
			buttons[i].setForeground(Color.WHITE); //글자색 지정
			
			
			buttons[i].setBorderPainted(false); //테두리 없애기
			
			
			
		}
		
		
		add(inputSpace);
		add(buttonPanel);
		
		
		//계산기 창의 제목, 크기 등 요소 지정
		setTitle("Calculator");	//제목 지정
		setVisible(true); //화면에 보이기
		setSize(300,400); //계산기 창 크기
		setLocationRelativeTo(null); //화면 가운데에 창 띄우기
		setResizable(false);	//사이즈 조절 불가
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창 종료 시 프로그램도 종료
	}
	
	abstract //버튼에 액션 리스너 추가하기
	class PadActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	
	//계산 기능
	public double calculate(String inputText) {
		fullTextParsing(inputText);
		
		double prev = 0;
		double current = 0;
		
		String mode = ""; //연산 기호 처리 변수
		
		
		//+일경우 add, -일경우 sub, x일경우 mul, /일경우 div
		for (String s : equation) {
			if (s.equals("+")) {
				mode = "add";
			} else if (s.equals("×")) {
				mode = "sub";
			}  
			else if (s.equals("×")) {
				mode = "mul";
			}  
			else if (s.equals("÷")) {
				mode = "div";
			}  else {
			
				//숫자일 때 문자열을 Double로 형변환
				current = Double.parseDouble(s);
				
				//mode값에 따라 처리, prev는 계산값 계속 갱신
				if (mode.equals("add")) {
					prev += current;
				} else if (mode.equals("sub")) {
					prev -= current;
				} 
				else if (mode.equals("mul")) {
					prev *= current;
				} 
				else if (mode.equals("div")) {
					prev /= current;
				} else {
					prev = current;
				}
				
			}
		}
				//계산값 prev 반환	
				return prev;
		
	}
	
	
	
	private void fullTextParsing(String inputText) {
		
		
	}



	public static void main(String[] args) {
		new Calculator();
		
	}
	
}
