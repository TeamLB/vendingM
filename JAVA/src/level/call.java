package APP;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.after_Boot;


public class call extends JFrame implements ActionListener{
	after_Boot afB = new after_Boot();
	Font fontT = new Font("Gulim",Font.PLAIN,40);
	private ImageIcon[] calIm = new ImageIcon[2];
	CardLayout cardO;
	CardLayout cardM;
	
	JFrame calloF = new JFrame();
	JPanel dial;
	JPanel[] calling = new JPanel[3];
	JButton callB;
	JButton[] endB = new JButton[2];
	JButton contactB[] = new JButton[3];
	JTextField pnumb = new JTextField(11);
	
	JFrame callmF = new JFrame();
	JPanel lock;
	JPanel[] answer = new JPanel[4];
	JButton answerB;
	JButton[] ignoreB = new JButton[4];
	JButton calB[] = new JButton[3];;
	
	boolean swP = true;
	boolean swC = true;
	ImageIcon icon = new ImageIcon();
	int ct = 0;
	

	public void Call (String model){

		
		System.out.println("calling call");
		System.out.println("model = "+model);
		
		cardO = new CardLayout();
		cardM= new CardLayout();
		calloF.setLayout(cardO);
		callmF.setLayout(cardM);
		
		
		calloF.setTitle("Other Phone");																							//set mainF
        calloF.setSize(800,1000);
		Toolkit t = Toolkit.getDefaultToolkit();																			//화면 중앙에 프레임 출력
        int x = (int)((t.getScreenSize().getWidth() - calloF.getWidth()) / 2)-450;
        int y = (int)((t.getScreenSize().getHeight() - calloF.getHeight()) / 2);
        calloF.setLocation(x, y);
        //calloF.setAlwaysOnTop(true);
        calloF.setResizable(false);
        
        dial = new JPanel()																									//add homw into mainF
		{ImageIcon i = new ImageIcon("img\\dial.png");	//로고 패널에 이미지 삽입
			public void paintComponent(Graphics g) {
			g.drawImage(i.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);							//프레임 크기에 따라 배경화면 해상도 조절
			}
		};
						
		for(ct=0 ; ct<4 ; ct++)
		{
			answer[ct] = new JPanel()																									//add homw into mainF
			{ImageIcon i = new ImageIcon("img\\answer["+ct+"].png");	//로고 패널에 이미지 삽입
			public void paintComponent(Graphics g) {
				g.drawImage(i.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);							//프레임 크기에 따라 배경화면 해상도 조절
				}
			};
			answer[ct].setLayout(null);
			
			icon= new ImageIcon("img\\ignoreB.png");
			ignoreB[ct] = new JButton(icon);
			ignoreB[ct].addActionListener(this);
			ignoreB[ct].setText("ignoreB");
			ignoreB[ct].setBorder(null);
			ignoreB[ct].setBorderPainted(false);
			ignoreB[ct].setContentAreaFilled(false);
			ignoreB[ct].setOpaque(false);
			ignoreB[ct].setFont(afB.transparent);
			ignoreB[ct].setVisible(true);
			answer[ct].add(ignoreB[ct]);
			ignoreB[ct].setVisible(true);
			ignoreB[ct].setBounds(330,680,145,146);
			
			if(ct<3)
			{
				icon = new ImageIcon("img\\contactB["+ct+"].png");
				contactB[ct] = new JButton(icon);
				contactB[ct].addActionListener(this);
				contactB[ct].setText("contactB");
				contactB[ct].setBorder(null);
				contactB[ct].setBorderPainted(false);
				contactB[ct].setContentAreaFilled(false);
				contactB[ct].setOpaque(false);
				contactB[ct].setBounds(680,120,62,62);
				contactB[ct].setFont(afB.transparent);
				
				calling[ct] = new JPanel ()																									//add homw into mainF
				{ImageIcon i = new ImageIcon("img\\calling["+ct+"].png");	//로고 패널에 이미지 삽입
				public void paintComponent(Graphics g) {
					g.drawImage(i.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);							//프레임 크기에 따라 배경화면 해상도 조절
					}
				};
				calling[ct].setLayout(null);
				
				if(ct<2)
				{
					
					icon= new ImageIcon("img\\calB.png");
					calB[ct] = new JButton(icon);
					calB[ct].addActionListener(this);
					calB[ct].setText("calB");
					calB[ct].setBorder(null);
					calB[ct].setBorderPainted(false);
					calB[ct].setContentAreaFilled(false);
					calB[ct].setOpaque(false);
					calB[ct].setBounds(630,330,62,62);
					calB[ct].setFont(afB.transparent);
					calB[ct].setVisible(true);
					
					icon = new ImageIcon("img\\endB.png");
					endB[ct] = new JButton(icon);
					endB[ct].addActionListener(this);
					endB[ct].setText("endB");
					endB[ct].setBorder(null);
					endB[ct].setBorderPainted(false);
					endB[ct].setContentAreaFilled(false);
					endB[ct].setOpaque(false);
					endB[ct].setBounds(330,680,145,146);
					endB[ct].setFont(afB.transparent);
					calling[ct].add(endB[ct]);
					endB[ct].setVisible(true);
					
				}
				
				calloF.add("calling["+ct+"]", calling[ct]);
			}
			
			
			if(ct>=1)
			{
				answer[ct].add(contactB[ct-1]);
				contactB[ct-1].setVisible(true);
				
			}
			callmF.add("answer["+ct+"]", answer[ct]);
			
			
		}
		
		
		ignoreB[0].setBounds(130,680,145,146);
		answer[2].add(calB[0]);
		answer[3].add(calB[1]);
		
		icon= new ImageIcon("img\\answerB.png");
		answerB = new JButton(icon);
		answerB.addActionListener(this);
		answerB.setText("answerB");
		answerB.setBorder(null);
		answerB.setBorderPainted(false);
		answerB.setContentAreaFilled(false);
		answerB.setOpaque(false);
		answerB.setBounds(530,680,148,146);
		answerB.setFont(afB.transparent);
		answer[0].add(answerB);
		answerB.setVisible(true);
		
		dial.setLayout(null);
		pnumb.setFont(fontT);
		pnumb.setBorder(null);
		pnumb.setOpaque(false);
		pnumb.setEditable(false);
		pnumb.setBounds(100,155,600,60);
		pnumb.setHorizontalAlignment(JTextField.CENTER);
		dial.add(pnumb);
		pnumb.setVisible(true);
		
		ImageIcon icon = new ImageIcon("img\\callB.png");
		callB = new JButton(icon);
		callB.addActionListener(this);
		callB.setText("callB");
		callB.setBorder(null);
		callB.setBorderPainted(false);
		callB.setContentAreaFilled(false);
		callB.setOpaque(false);
		callB.setBounds(350 ,690,102,102);
		callB.setFont(afB.transparent);
		dial.add(callB);
		callB.setVisible(true);
		calloF.add("dial", dial);
		
		
		        
        callmF.setTitle("Your Phone");																							//set mainF
        callmF.setSize(800,1000);
		Toolkit ty = Toolkit.getDefaultToolkit();																			//화면 중앙에 프레임 출력
        x = (int)((t.getScreenSize().getWidth() - callmF.getWidth()) / 2)+450;
        y = (int)((t.getScreenSize().getHeight() - callmF.getHeight()) / 2);
        callmF.setLocation(x, y);
        //callmF.setAlwaysOnTop(true);
        callmF.setResizable(false);
        
        lock = new JPanel()																									//add homw into mainF
		{ImageIcon i = new ImageIcon("img\\lock.png");	
			public void paintComponent(Graphics g) {
			g.drawImage(i.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);							//프레임 크기에 따라 배경화면 해상도 조절
			}
		};
		callmF.add("lock", lock);

		calloF.setVisible(true);
		callmF.setVisible(true);
						
		cardO.show(calloF.getContentPane(), "dial");
		cardM.show(callmF.getContentPane(), "lock");
		
		}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Pushed Button : "+e.getActionCommand());
		if(e.getActionCommand().equals("homeB"))
		{
			calloF.disable();
			callmF.disable();
			//afB.home();
		}
		
		else if(e.getActionCommand().equals("callB"))
		{
			
			pnumb.setText("010-3269-1891");
			cardO.show(calloF.getContentPane(), "calling[0]");
			cardM.show(callmF.getContentPane(), "answer[0]");
		}
		
		else if(e.getActionCommand().equals("answerB"))
		{
			cardO.show(calloF.getContentPane(), "calling[1]");
			cardM.show(callmF.getContentPane(), "answer[1]");
		}
		
		else if(e.getActionCommand().equals("ignoreB") || e.getActionCommand().equals("endB"))
		{
			cardO.show(calloF.getContentPane(), "calling[2]");
			cardM.show(callmF.getContentPane(), "lock");
			
			cardO.show(calloF.getContentPane(), "dial");
		}
		
		else if(e.getActionCommand().equals("contactB"))
		{
				
				if(swP==true)
				{
					cardM.show(callmF.getContentPane(), "answer[2]");
					contactB[0].setVisible(true);
					swP=false;
				}
				else
				{
					cardM.show(callmF.getContentPane(), "answer[1]");
					contactB[1].setVisible(true);
					swP=true;
				}
				
		}
		
		else if(e.getActionCommand().equals("calB"))
		{
				if(swC==true)
				{
					cardM.show(callmF.getContentPane(), "answer[3]");
					swC=false;
				}
				else
				{
					cardM.show(callmF.getContentPane(), "answer[2]");
					swC=true;
				}
		}
		
		
	}


	
	
}
