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
			
			//밑에서 만든 액션리스너를 버튼에 추가
			buttons[i].addActionListener(new PadActionListener());
			
			//버튼들을 버튼패널에 추가
			buttonPanel.add(buttons[i]);
			
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
	
	
	//버튼에 액션 리스너 추가하기
	class PadActionListener implements ActionListener { 
	
		public void actionPerformed(ActionEvent e) {
			
			//어떤 버튼이 눌렸는지를 알아냄
			String operation = e.getActionCommand();
			
			//C가 눌렸을 경우 위의 계산식 내용을 지워줌
			if (operation.equals("C")) {
				inputSpace.setText("");
				
			//=이 눌렸을 경우 위에 입력된 식을 계산, 계산값이 나오도록 함
			} else if (operation.equals("=")) {
				
				//밑의 메소드들을 이용하여 계산, 계산식 화면에 값을 띄워줌
				String result = Double.toString(calculate(inputSpace.getText()));
				inputSpace.setText("" + result);
				num = "";
				
			//나버지 버튼은 눌렀을 때 계산식에 추가됨
			} else {
				inputSpace.setText(inputSpace.getText() + e.getActionCommand());
			}
			
		}
		
	}
	
	private void fullTextParsing(String inputText) {
		
		equation.clear();
		
		//계산식의 글자를 하나하나 거쳐감
		for (int i = 0; i < inputText.length(); i++) {
			char ch = inputText.charAt(i);
			
			//연산기호가 나오면 ArrayList에 추가되고 초기화
			if (ch == '-' || ch == '+' || ch == '×' || ch == '÷') {
				
				//연산기호를 만났다 : 앞은 숫자라는 것을 의미
				//숫자를 ArrayList에 추가
				equation.add(num);
				
				//num 초기화
				num = "";
				
				//연산기호를 ArrayList에 추가
				equation.add(ch + "");
			} else {
				
				//나머지는 그냥 숫자 처리
				num = num + ch;
			}
		}
		
		//반복문 끝나고 남아있는 숫자값 추가
		equation.add(num);
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
	
	
	public static void main(String[] args) {
		new Calculator();
		
	}
	
}
