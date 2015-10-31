package level;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MyVendingMachine1 extends JFrame implements ActionListener{

	private	JPanel 	M_Menu;			// 물건 목록, 지불 및 거스름돈
	private JPanel 	M_Coin;			// 권액별 선택 창
	private JPanel  M_Item;			// 고른 물건 보여줌
	private JButton M_Buttons[];	// 물건 버튼 배열
	private JButton C_Buttons[];	// 권액 버튼 배열
	private JButton E_Button;		// 종료 버튼
	private JLabel	C_Show;			// 현재 금액 표시 
	private int		C_Total = 0;		// 금액
	private int		M1 = 0;
	private int		M2 = 0;
	private int		M3 = 0;
	private int		M4 = 0;
	private ImageIcon	M_Image;	//매뉴 이미지선택
	private JLabel	M_Show;			//매뉴 이미지 표시
	MyVendingMachine1(){
		setTitle("Vending Machine");
		setSize(1000,1000);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);	//윈도우 중앙 배치

		M_Menu 		= 	new	JPanel(); 
		M_Menu.setBackground(Color.green);
		M_Menu.setSize(1000,750);
		M_Menu.setLocation(0,0);
		M_Menu.setLayout(null);

		
		M_Coin	 	= 	new	JPanel();
		M_Coin.setBackground(Color.blue);
		M_Coin.setSize(1000,250);
		M_Coin.setLocation(0,750);
		M_Coin.setLayout(null);
		
		C_Show 		= 	new JLabel("0",JLabel.RIGHT);
		C_Show.setFont(new Font("Dialog.bold",1,70));
		C_Show.setOpaque(true);
        C_Show.setBackground(Color.white);


		
	
		C_Buttons 	= 	new JButton[5];
		M_Buttons	= 	new JButton[4];
		

		E_Button	 = new JButton("change");
		M_Buttons[0] = new JButton("Menu1");
		M_Buttons[1] = new JButton("Menu2");
		M_Buttons[2] = new JButton("Menu3");
		M_Buttons[3] = new JButton("Menu4");
		C_Buttons[0] = new JButton("1000");
		C_Buttons[1] = new JButton("500");
		C_Buttons[2] = new JButton("100");
		C_Buttons[3] = new JButton("50");
		C_Buttons[4] = new JButton("10"); 

		M_Menu.add(C_Show);
		M_Menu.add(E_Button);
		C_Show.setBounds(560,352,250,108);
		E_Button.setBounds(810,350,110,110);
		M_Buttons[0].setBounds(80,150,150,80);
		M_Buttons[1].setBounds(310,150,150,80);
		M_Buttons[2].setBounds(540,150,150,80);
		M_Buttons[3].setBounds(770,150,150,80);

		for(int i=0;i<=3;i++){
			M_Menu.add(M_Buttons[i]);
			M_Buttons[i].addActionListener(this);
		}
		E_Button.addActionListener(this);
				
		C_Buttons[0].setBounds(25,30,150,150);
		C_Buttons[1].setBounds(225,30,150,150);
		C_Buttons[2].setBounds(425,30,150,150);
		C_Buttons[3].setBounds(625,30,150,150);
		C_Buttons[4].setBounds(825,30,150,150);
		for(int i=0;i<=4;i++){
			M_Coin.add(C_Buttons[i]);
			C_Buttons[i].addActionListener(this);
		}
		this.add(M_Menu);
		this.add(M_Coin);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		Image M = new Image();
		MyJavaServer MS = new MyJavaServer();
		if(e.getSource() == M_Buttons[0] && C_Total >= 300){
			C_Total -= 300;
			M1		+=	1;
			M.SelectImage("1");
			C_Show.setText(String.valueOf(C_Total));
		}		
		else if(e.getSource() == M_Buttons[1] && C_Total >= 500){
			C_Total -= 500;
			M2		+=	1;
			M.SelectImage("2");
			C_Show.setText(String.valueOf(C_Total));	
		}
		else if(e.getSource() == M_Buttons[2] && C_Total >= 700){
			C_Total -= 700;
			M3		+=	1;
			M.SelectImage("3");
			C_Show.setText(String.valueOf(C_Total));	
		}
		else if(e.getSource() == M_Buttons[3] && C_Total >= 900){
			C_Total -= 900;
			M4		+= 	1;
			M.SelectImage("4");
			C_Show.setText(String.valueOf(C_Total));	
		}
		else if(e.getSource() == C_Buttons[0]){
			C_Total += 1000;
			C_Show.setText(String.valueOf(C_Total));	
		}
		else if(e.getSource() == C_Buttons[1]){
			C_Total += 500;
			C_Show.setText(String.valueOf(C_Total));	
		}
		else if(e.getSource() == C_Buttons[2]){
			C_Total += 100;
			C_Show.setText(String.valueOf(C_Total));	
		}
		else if(e.getSource() == C_Buttons[3]){
			C_Total += 50;
			C_Show.setText(String.valueOf(C_Total));	
		}
		else if(e.getSource() == C_Buttons[4]){
			C_Total += 10;
			C_Show.setText(String.valueOf(C_Total));	
		}
		else if(e.getSource() == E_Button){
			C_Show.setText(String.valueOf(0));
			MS.getSend(M1, M2, M3, M4, C_Total);
		}
		
	}
	class Image{
		void SelectImage(String M){
			
		String P1 = "C:\\Users\\LEESIYOUNG\\workspace\\level\\src\\level\\";
		String P2 = ".jpg";
		String P_Root = P1 + M + P2;
		JFrame F = new JFrame();
		F.setSize(100, 300);
		F.setLocationRelativeTo(null);
		F.setResizable(false);
		M_Image = new ImageIcon(P_Root);
		
		M_Show = new JLabel(M_Image);
		F.add(M_Show);
		F.setVisible(true);
		}
		}
	
	public static void main(String args[]){
		MyVendingMachine1	m1	=	new	MyVendingMachine1();

	}
	
}



