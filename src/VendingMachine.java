import java.text.DecimalFormat;
import java.util.Date;

import static javax.swing.JOptionPane.*;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*; 
import java.sql.*;
public class VendingMachine extends JFrame implements ActionListener
{
    static DataHandler dh;
    
    Date now = new Date();
    
    int year = now.getYear() + 1900;	// 년도 표시
    int month = now.getMonth() + 1;
    int day = now.getDate();
    
    int hours = now.getHours();
    int minutes = now.getMinutes();
    int seconds = now.getSeconds();
    
    JFrame changeFrame = new JFrame("거스름돈 코너");
    
    ImageIcon bannerImage = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\title.jpg");
    JButton bannerButton = new JButton("", bannerImage);
    //All objects for Product 1:
    ImageIcon productImage1 = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\1.jpg");
    JButton product1 = new JButton("", productImage1);
    JLabel product1des = new JLabel("밀키스", JLabel.CENTER);  //Mars Bar
    JLabel product1price = new JLabel("500원", JLabel.CENTER);
    //All objects for Product 2:
    ImageIcon productImage2 = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\2.jpg");
    JButton product2 = new JButton("", productImage2);
    JLabel product2des = new JLabel("복숭아", JLabel.CENTER); //Bounty Bar Original
    JLabel product2price = new JLabel("500원", JLabel.CENTER);
    //All objects for Product 3:
    ImageIcon productImage3 = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\3.jpg");
    JButton product3 = new JButton("", productImage3);
    JLabel product3des = new JLabel("사과", JLabel.CENTER); //Twix Double Bar
    JLabel product3price = new JLabel("1000원", JLabel.CENTER);
    //All objects for Product 4:
    ImageIcon productImage4 = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\4.jpg");
    JButton product4 = new JButton("", productImage4);
    JLabel product4des = new JLabel("레몬샤워", JLabel.CENTER); //Walker's Ready Salted
    JLabel product4price = new JLabel("1000원", JLabel.CENTER);
    //All objects for product 5:
    ImageIcon productImage5 = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\5.jpg");
    JButton product5 = new JButton("", productImage5);
    JLabel product5des = new JLabel("벌꿀레몬", JLabel.CENTER);  //Monster Munch -
    JLabel product5price = new JLabel("2000원", JLabel.CENTER);
    //All objects for product 6:
    ImageIcon productImage6 = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\6.jpg");
    JButton product6 = new JButton("", productImage6);
    JLabel product6des = new JLabel("포도", JLabel.CENTER); //Walkers Sensations -
    JLabel product6price = new JLabel("3000원", JLabel.CENTER);
    //All objects for product 7:
    ImageIcon productImage7 = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\7.jpg");
    JButton product7 = new JButton("", productImage7);
    JLabel product7des = new JLabel("매실술소다", JLabel.CENTER); //Coca-Cola (330ml)
    JLabel product7price = new JLabel("4000원", JLabel.CENTER);
    //All objects for product 8:
    ImageIcon productImage8 = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\8.jpg");
    JButton product8 = new JButton("", productImage8);
    JLabel product8des = new JLabel("아이스티", JLabel.CENTER); //Dr Pepper (330ml)
    JLabel product8price = new JLabel("4000원", JLabel.CENTER);
    //All objects for product 9:
    ImageIcon productImage9 = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\9.jpg");
    JButton product9 = new JButton("", productImage9);
    JLabel product9des = new JLabel("카시스오렌지", JLabel.CENTER); //Fruit Pastels
    JLabel product9price = new JLabel("4000원", JLabel.CENTER);
    //All objects for product 10:
    ImageIcon productImage10 = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\10.jpg");
    JButton product10 = new JButton("", productImage10);
    JLabel product10des = new JLabel("와인", JLabel.CENTER); //Wine Gums
    JLabel product10price = new JLabel("5000원", JLabel.CENTER);
    //Horizontal/Vertical Rules as buttons for simplicity and layout
    JButton ruleH = new JButton("");
    JButton ruleV = new JButton("");
    JButton ruleV2 = new JButton("");
    //selected items section
    JLabel selectedLabel = new JLabel("선택한 호로요이들:");
    JTextArea selectedItems = new JTextArea(8,250);
        //Total price:
    JLabel selectedItemTotalLabel = new JLabel("호로요이 가격:");
    JTextField selectedItemTotal = new JTextField("?0.00", 30);
    //Money section:
//Label:
    JLabel moneyInputLabel = new JLabel("금액선택:");
    //Money buttons with imageIcons:
    ImageIcon FTH_Image = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\b1.jpg");
    JButton fiveTButton = new JButton("", FTH_Image);

    ImageIcon OTH_Image = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\b2.jpg");
    JButton oneTButton = new JButton("", OTH_Image);

    ImageIcon FH_Image = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\b3.jpg");
    JButton fiveHButton = new JButton("", FH_Image);

    ImageIcon OH_Image = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\b4.jpg");
    JButton fiftyPenceButton = new JButton("", OH_Image);

    ImageIcon FT_Image = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\b5.jpg");
    JButton onePoundButton = new JButton("", FT_Image);

    ImageIcon OT_Image = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\b6.jpg");
    JButton twoPoundButton = new JButton("", OT_Image);

    JLabel moneyInputLabel2 = new JLabel("현재 금액:");
    JTextField moneyInput = new JTextField("0.00", 30);

    ImageIcon enterCoinsImage = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\s1.jpg");
    JButton enterCoins = new JButton("", enterCoinsImage);
    ImageIcon returnCoinsImage = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\s2.jpg");
    JButton returnCoins = new JButton ("", returnCoinsImage);
    
    JTextArea outputMessage = new JTextArea(2,30);
    
    JLabel cancelOrderLabel = new JLabel("취소 / 주문완료");
    ImageIcon cancelOrderImage = new ImageIcon("C:\\Users\\LEESIYOUNG\\workspace\\vending\\src\\images\\exit.jpg");
    JButton cancelOrder = new JButton("", cancelOrderImage);
    JTextField product1stock = new JTextField("10", 2);
    JTextField product2stock = new JTextField("10", 2);
    JTextField product3stock = new JTextField("10", 2);
    JTextField product4stock = new JTextField("10", 2);
    JTextField product5stock = new JTextField("10", 2);
    JTextField product6stock = new JTextField("10", 2);
    JTextField product7stock = new JTextField("10", 2);
    JTextField product8stock = new JTextField("10", 2);
    JTextField product9stock = new JTextField("10", 2);
    JTextField product10stock = new JTextField("10", 2);
    
    JTextField fivepStock = new JTextField("20", 2);
    JTextField tenpStock = new JTextField("20", 2);
    JTextField twentypStock = new JTextField("20", 2);
    JTextField fiftypStock = new JTextField("20", 2);
    JTextField onepStock = new JTextField("20", 2);
    JTextField twopStock = new JTextField("20", 2);
    
    JButton takeChange = new JButton("거스름돈 반환");
    
    JLabel dateLabel = new JLabel("날짜:");
    JLabel timeLabel = new JLabel("시간:");
    JTextField currentDate = new JTextField("", 10);
    JTextField currentTime = new JTextField ("", 10);
    
    ImageIcon cfLabelicon = new ImageIcon("images/CFLabel1icon.jpg");
    JLabel cfLabelTakeChange = new JLabel("", cfLabelicon, JLabel.CENTER);
 
    JButton CFfiveTButton = new JButton("", FTH_Image);
    
    JButton CFoneTButton = new JButton("", OTH_Image);

    JButton CFfiveHButton = new JButton("", FH_Image);

    JButton CFfiftyPenceButton = new JButton("", OH_Image);

    JButton CFonePoundButton = new JButton("", FT_Image);

    JButton CFtwoPoundButton = new JButton("", OT_Image);

    JTextField CFfivepStock = new JTextField("20", 2);
    JTextField CFtenpStock = new JTextField("20", 2);
    JTextField CFtwentypStock = new JTextField("20", 2);
    JTextField CFfiftypStock = new JTextField("20", 2);
    JTextField CFonepStock = new JTextField("20", 2);
    JTextField CFtwopStock = new JTextField("20", 2);
    JLabel CFpleaseTakeChange = new JLabel("권액별 거스름돈 :");
    JLabel CFchangeRemainingLabel = new JLabel("거스름돈 잔액 : ");
    JTextField CFchangeRemaining = new JTextField("",10);
    JButton CFruleH1 = new JButton("");
    JTextArea CFoutputMessage = new JTextArea("", 2, 30);
    JButton CFfinished = new JButton("교환 완료");

    JButton CFclose = new JButton("거래 종료");

    Connection order;
    Statement myStatement;
    String writeString = "";

    int productTotal = 0;
    int totalInserted = 0;
    
    double product1stockNum = 10;
    double product2stockNum = 10;
    double product3stockNum = 10;
    double product4stockNum = 10;
    double product5stockNum = 10;
    double product6stockNum = 10;
    double product7stockNum = 10;
    double product8stockNum = 10;
    double product9stockNum = 10;
    double product10stockNum = 10;

    double fivepStockNum = 20;
    double tenpStockNum = 20;
    double twentypStockNum = 20;
    double fiftypStockNum = 20;
    double onepStockNum = 20;
    double twopStockNum = 20;

    public static void main(String[] args)
    {
        VendingMachine jf = new VendingMachine();
        dh = new FileHandler(); 
    }

    public VendingMachine()

    {
        setLayout(null); 
        setSize(830, 705); 
        setBackground(Color.red);
        setTitle("호로호로호로요이 자판기!"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bannerButton.setBounds(210,10,400,30);
        add(bannerButton);

        currentDate.setBounds(740,5,80,20);
        currentDate.setEditable(false);
        add(currentDate);
        dateLabel.setBounds(705,7,30,15);
        add(dateLabel);
        currentTime.setBounds(740,28,80,20);
        currentTime.setEditable(false);
        add(currentTime);
        timeLabel.setBounds(702,30,33,15);
        add(timeLabel);

        product1.setBounds(20,75,140,150);
        product1.addActionListener(this);
        add(product1);
        product2.setBounds(180,75,140,150);
        product2.addActionListener(this);
        add(product2);
        product3.setBounds(340,75,140,150);
        product3.addActionListener(this);
        add(product3);
        product4.setBounds(500,75,140,150);
        product4.addActionListener(this);
        add(product4);
        product5.setBounds(660,75,140,150);
        product5.addActionListener(this);
        add(product5);

        product1des.setBounds(20,225,140,15);
        add(product1des);
        product1price.setBounds(20,250,140,15);
        product1price.setForeground(Color.red);
        add(product1price);
        product2des.setBounds(180,225,140,15);
        add(product2des);
        product2price.setBounds(180,250,140,15);
        product2price.setForeground(Color.red);
        add(product2price);
        product3des.setBounds(340,225,140,15);
        add(product3des);
        product3price.setBounds(340,250,140,15);
        product3price.setForeground(Color.red);
        add(product3price);
        product4des.setBounds(500,225,140,15);
        add(product4des);
        product4price.setBounds(500,250,140,15);
        product4price.setForeground(Color.red);
        add(product4price);
        product5des.setBounds(660,225,140,15);
        add(product5des);
        product5price.setBounds(660,250,140,15);
        product5price.setForeground(Color.red);
        add(product5price);

        product6.setBounds(20,270,140,150);
        product6.addActionListener(this);
        add(product6);
        product7.setBounds(180,270,140,150);
        product7.addActionListener(this);
        add(product7);
        product8.setBounds(340,270,140,150);
        product8.addActionListener(this);
        add(product8);
        product9.setBounds(500,270,140,150);
        product9.addActionListener(this);
        add(product9);
        product10.setBounds(660,270,140,150);
        product10.addActionListener(this);
        add(product10);

        product6des.setBounds(20,420,140,15);
        add(product6des);
        product6price.setBounds(20,445,140,15);
        product6price.setForeground(Color.red);
        add(product6price);
        product7des.setBounds(180,420,140,15);
        add(product7des);
        product7price.setBounds(180,445,140,15);
        product7price.setForeground(Color.red);
        add(product7price);
        product8des.setBounds(340,420,140,15);
        add(product8des);
        product8price.setBounds(340,445,140,15);
        product8price.setForeground(Color.red);
        add(product8price);
        product9des.setBounds(500,420,150,15);
        add(product9des);
        product9price.setBounds(500,445,150,15);
        product9price.setForeground(Color.red);
        add(product9price);
        product10des.setBounds(660,420,150,15);
        add(product10des);
        product10price.setBounds(660,445,150,15);
        product10price.setForeground(Color.red);
        add(product10price);

        product1stock.setBounds(161,75,17,20);
        product1stock.setEditable(false);
        add(product1stock);
        product2stock.setBounds(321,75,17,20);
        product2stock.setEditable(false);
        add(product2stock);
        product3stock.setBounds(481,75,17,20);
        product3stock.setEditable(false);
        add(product3stock);
        product4stock.setBounds(641,75,17,20);
        product4stock.setEditable(false);
        add(product4stock);
        product5stock.setBounds(801,75,17,20);
        product5stock.setEditable(false);
        add(product5stock);
        product6stock.setBounds(161,270,17,20);
        product6stock.setEditable(false);
        add(product6stock);
        product7stock.setBounds(321,270,17,20);
        product7stock.setEditable(false);
        add(product7stock);
        product8stock.setBounds(481,270,17,20);
        product8stock.setEditable(false);
        add(product8stock);
        product9stock.setBounds(641,270,17,20);
        product9stock.setEditable(false);
        add(product9stock);
        product10stock.setBounds(801,270,17,20);
        product10stock.setEditable(false);
        add(product10stock);

        ruleH.setBounds(15,465,800,2);
        add(ruleH);

        selectedLabel.setBounds(15,475,220,15);
        add(selectedLabel);
        selectedItems.setBounds(15,495,265,140);
        selectedItems.setEditable(false);
        add(selectedItems);

        selectedItemTotalLabel.setBounds(15,645,120,20);
        add(selectedItemTotalLabel);
        selectedItemTotal.setBounds(140,645,125,20);
        selectedItemTotal.setEditable(false);
        add(selectedItemTotal);

        ruleV.setBounds(283,475,2,185);
        add(ruleV);

        moneyInputLabel.setBounds(290,470,150,20);
        add(moneyInputLabel);

        fiveTButton.setBounds(290,495,68,68);
        fiveTButton.addActionListener(this);
        add(fiveTButton);

        oneTButton.setBounds(379,495,68,68);
        oneTButton.addActionListener(this);
        add(oneTButton);

        fiveHButton.setBounds(471,495,68,68);
        fiveHButton.addActionListener(this);
        add(fiveHButton);

        fiftyPenceButton.setBounds(290,565,68,68);
        fiftyPenceButton.addActionListener(this);
        add(fiftyPenceButton);

        onePoundButton.setBounds(379,565,68,68);
        onePoundButton.addActionListener(this);
        add(onePoundButton);

        twoPoundButton.setBounds(471,565,68,68);
        twoPoundButton.addActionListener(this);
        add(twoPoundButton);

        fivepStock.setBounds(358,495,18,20);
        fivepStock.setEditable(false);
        add(fivepStock);
        tenpStock.setBounds(447,495,18,20);
        tenpStock.setEditable(false);
        add(tenpStock);
        twentypStock.setBounds(539,495,18,20);
        twentypStock.setEditable(false);
        add(twentypStock);
        fiftypStock.setBounds(358,565,18,20);
        fiftypStock.setEditable(false);
        add(fiftypStock);
        onepStock.setBounds(447,565,18,20);
        onepStock.setEditable(false);
        add(onepStock);
        twopStock.setBounds(539,565,18,20);
        twopStock.setEditable(false);
        add(twopStock);

        moneyInputLabel2.setBounds(290,638,100,20);
        add(moneyInputLabel2);
        moneyInput.setBounds(380,638,127,20);
        moneyInput.setEditable(false);
        add(moneyInput);

        ruleV2.setBounds(560,475,2,185);
        add(ruleV2);

        enterCoins.setBounds(568,475,250,40);
        enterCoins.addActionListener(this);
        add(enterCoins);
        returnCoins.setBounds(568,525,250,40);
        returnCoins.addActionListener(this);
        add(returnCoins);
        outputMessage.setBounds(568,575,250,50);
        outputMessage.setEditable(false);
        add(outputMessage);

        cancelOrderLabel.setBounds(676,635,122,25);
        add(cancelOrderLabel);
        cancelOrder.setBounds(794,635,25,25);
        cancelOrder.addActionListener(this);
        add(cancelOrder);

        takeChange.setBounds(565,635,109,25);
        takeChange.setForeground(Color.red);

        takeChange.setVisible(false);
        takeChange.addActionListener(this);
        add(takeChange);

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String sourceURL = new String("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=VendingMachineDB.mdb;");
            order = DriverManager.getConnection(sourceURL, "admin", "");
            myStatement = order.createStatement();
        }

        catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
        }
        catch (SQLException sqle) {
            System.out.println(sqle);
        }
        setVisible(true); 
        setResizable(false); 
    }

    public void changeFrameInit()
    {

        changeFrame.setSize(504, 390); 
        changeFrame.setLayout(null); 
        changeFrame.setVisible(false);
        changeFrame.setResizable(false);
        changeFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        cfLabelTakeChange.setBounds(22,2,450,40);
        changeFrame.add(cfLabelTakeChange);
        CFpleaseTakeChange.setBounds(6,50,300,20);
        changeFrame.add(CFpleaseTakeChange);

        CFfiveTButton.setBounds(10,80,68,68);
        CFfiveTButton.addActionListener(this);
        changeFrame.add(CFfiveTButton);

        CFoneTButton.setBounds(92,80,68,68);
        CFoneTButton.addActionListener(this);
        changeFrame.add(CFoneTButton);

        CFfiveHButton.setBounds(174,80,68,68);
        CFfiveHButton.addActionListener(this);
        changeFrame.add(CFfiveHButton);

        CFfiftyPenceButton.setBounds(256,80,68,68);
        CFfiftyPenceButton.addActionListener(this);
        changeFrame.add(CFfiftyPenceButton);

        CFonePoundButton.setBounds(338,80,68,68);
        CFonePoundButton.addActionListener(this);
        changeFrame.add(CFonePoundButton);

        CFtwoPoundButton.setBounds(420,80,68,68);
        CFtwoPoundButton.addActionListener(this);
        changeFrame.add(CFtwoPoundButton);

        CFfivepStock.setBounds(35,150,18,20);
        CFfivepStock.setEditable(false);
        changeFrame.add(CFfivepStock);
        CFtenpStock.setBounds(117,150,18,20);
        CFtenpStock.setEditable(false);
        changeFrame.add(CFtenpStock);
        CFtwentypStock.setBounds(199,150,18,20);
        CFtwentypStock.setEditable(false);
        changeFrame.add(CFtwentypStock);
        CFfiftypStock.setBounds(281,150,18,20);
        CFfiftypStock.setEditable(false);
        changeFrame.add(CFfiftypStock);
        CFonepStock.setBounds(364,150,18,20);
        CFonepStock.setEditable(false);
        changeFrame.add(CFonepStock);
        CFtwopStock.setBounds(445,150,18,20);
        CFtwopStock.setEditable(false);
        changeFrame.add(CFtwopStock);

        CFruleH1.setBounds(4,180,490,2);
        changeFrame.add(CFruleH1);

        CFchangeRemainingLabel.setBounds(6,190,120,20);
        changeFrame.add(CFchangeRemainingLabel);
        CFchangeRemaining.setBounds(125,190,60,20);
        CFchangeRemaining.setEditable(false);
        changeFrame.add(CFchangeRemaining);

        CFoutputMessage.setBounds(120,220,250,60);
        CFoutputMessage.setEditable(false);
        changeFrame.add(CFoutputMessage);

        CFfinished.setBounds(192,290,100,20);
        CFfinished.addActionListener(this);
        changeFrame.add(CFfinished);

        CFclose.setBounds(145,320,100,25);
        CFclose.addActionListener(this);
        CFclose.setVisible(false);
        changeFrame.add(CFclose);
    }
    private void closeChangeFrame()
    {
        changeFrame.setVisible(false);
    }
    public void actionPerformed(ActionEvent e)
    {

        DecimalFormat poundsFormat = new DecimalFormat ("0.00");

        DecimalFormat numFormat = new DecimalFormat ("0");

        DecimalFormat twoFormat = new DecimalFormat("00");
        DecimalFormat decFormat = new DecimalFormat("0.00");
        String addToSelection;

        currentDate.setText(twoFormat.format(day) + "/" + twoFormat.format(month) + "/" + year);

        currentTime.setText(twoFormat.format(hours) + ":" + twoFormat.format(minutes) + ":" +
                twoFormat.format(seconds));
        int errorGiveChange = totalInserted - productTotal;

        if (e.getSource() == product1)
        {

            writeString = "INSERT INTO VendingMachine(Products, Price) VALUES('Mars Bar', '?0.50')";

            addToSelection = selectedItems.getText() + "\n 밀키스 - 500원";
            selectedItems.setText(addToSelection);

            productTotal += 500;
            selectedItemTotal.setText(poundsFormat.format(productTotal));

            product1stockNum -= 1;
            product1stock.setText(numFormat.format(product1stockNum));
            
            if (product1stockNum == 0)
            {
                product1.setEnabled(false);
                showMessageDialog(null, "죄송합니다 ㅠㅠ 밀키스맛이 없어요", "밀키스 매진", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == product2)
        {

            writeString = "INSERT INTO VendingMachine(Products, Price) VALUES('Bounty Bar', '?0.50')";
 
            addToSelection = selectedItems.getText() + "\n 복숭아 - 500원";
            selectedItems.setText(addToSelection);
 
            productTotal += 500;
            selectedItemTotal.setText(poundsFormat.format(productTotal));
 
            product2stockNum -= 1;
            product2stock.setText(numFormat.format(product2stockNum));

            if (product2stockNum == 0)
            {
                product2.setEnabled(false);
                        showMessageDialog(null, "죄송합니다 ㅠㅠ 복숭아맛이 없어요", "복숭아 매진", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == product3)
        {

            writeString =
                    "INSERT INTO VendingMachine(Products, Price) VALUES('Twix Double Bar', '?0.65')";

            addToSelection = selectedItems.getText() + "\n 사과 - 1000원";
            selectedItems.setText(addToSelection);
            
            productTotal += 1000;
            selectedItemTotal.setText(poundsFormat.format(productTotal));
 
            product3stockNum -= 1;
            product3stock.setText(numFormat.format(product3stockNum));
 
            if (product3stockNum == 0)
            {
                product3.setEnabled(false);
                showMessageDialog(null, "죄송합니다 ㅠㅠ 사과맛이 없어요","사과 매진", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == product4)
        {

            writeString =
                    "INSERT INTO VendingMachine(Products, Price) VALUES('Walker's Ready Salted Crisps', '?0.55')";
 
            addToSelection = selectedItems.getText() + "\n 레몬샤워 - 1000원";
            selectedItems.setText(addToSelection);
 
            productTotal += 1000;
            selectedItemTotal.setText(poundsFormat.format(productTotal));
 
            product4stockNum -= 1;
            product4stock.setText(numFormat.format(product4stockNum));

            if (product4stockNum == 0)
            {
                product4.setEnabled(false);
                showMessageDialog(null, "죄송합니다 ㅠㅠ 레몬샤워맛이 없어요", "레몬샤워 매진", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == product5)
        {

            writeString =
                    "INSERT INTO VendingMachine(Products, Price) VALUES('Monster Munch - Pickled Onion', '?0.70')";

            addToSelection = selectedItems.getText() + "\n 벌꿀레몬 - 2000원";
            selectedItems.setText(addToSelection);

            productTotal += 2000;
            selectedItemTotal.setText(poundsFormat.format(productTotal));

            product5stockNum -= 1;
            product5stock.setText(numFormat.format(product5stockNum));

            if (product5stockNum == 0)
            {
                product5.setEnabled(false);
                showMessageDialog(null, "죄송합니다 ㅠㅠ 벌꿀레몬맛이 없어요", "벌꿀레몬 매진", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == product6)
        {

            writeString = "INSERT INTO VendingMachine(Products, Price) VALUES('Walkers Sensations - Thai Sweet Chilli', '?1.10')";
 
            addToSelection = selectedItems.getText() + "\n 포도 - 3000원";
            selectedItems.setText(addToSelection);
 
            productTotal += 3000;
            selectedItemTotal.setText(poundsFormat.format(productTotal));
 
            product6stockNum -= 1;
            product6stock.setText(numFormat.format(product6stockNum));

            if (product6stockNum == 0)

            {
                product6.setEnabled(false);
                showMessageDialog(null, "죄송합니다 ㅠㅠ 포도맛이 없어요", "포도 매진", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == product7)
        {

            writeString =
                    "INSERT INTO VendingMachine(Products, Price) VALUES('Coca-Cola (330ml)', '?0.75')";
 
            addToSelection = selectedItems.getText() + "\n 매실술소다 - 4000원";
            selectedItems.setText(addToSelection);

            productTotal += 3000;
            selectedItemTotal.setText(poundsFormat.format(productTotal));

            product7stockNum -= 1;
            product7stock.setText(numFormat.format(product7stockNum));

            if (product7stockNum == 0)
            {
                product7.setEnabled(false);
                showMessageDialog(null, "죄송합니다 ㅠㅠ 매실술소다맛이 없어요","매실술소다 매진", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == product8)
        {

            writeString =
                    "INSERT INTO VendingMachine(Products, Price) VALUES('Dr Pepper (330ml)', '?0.75')";
 
            addToSelection = selectedItems.getText() + "\n 아이스티 - 4000원";
            selectedItems.setText(addToSelection);

            productTotal += 4000;
            selectedItemTotal.setText(poundsFormat.format(productTotal));

            product8stockNum -= 1;
            product8stock.setText(numFormat.format(product8stockNum));

            if (product8stockNum == 0)
            {
                product8.setEnabled(false);
                showMessageDialog(null, "죄송합니다 ㅠㅠ 아이스티맛이 없어요","아이스티 매진", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == product9)
        {

            writeString =
                    "INSERT INTO VendingMachine(Products, Price) VALUES('Fruit Pastels', '?0.60')";
 
            addToSelection = selectedItems.getText() + "\n 카시스오렌지 - 4000원";
            selectedItems.setText(addToSelection);

            productTotal += 4000;
            selectedItemTotal.setText(poundsFormat.format(productTotal));

            product9stockNum -= 1;
            product9stock.setText(numFormat.format(product9stockNum));

            if (product9stockNum == 0)
            {
                product9.setEnabled(false);
                showMessageDialog(null, "죄송합니다 ㅠㅠ 카시스오렌지맛이 없어요","카시스오렌지 매진", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == product10)
        {

            writeString =
                    "INSERT INTO VendingMachine(Products, Price) VALUES('Wine Gums', '?0.65')";
 
            addToSelection = selectedItems.getText() + "\n 와인 - 5000원";
            selectedItems.setText(addToSelection);

            productTotal += 5000;
            selectedItemTotal.setText(poundsFormat.format(productTotal));

            product10stockNum -= 1;
            product10stock.setText(numFormat.format(product10stockNum));

            if (product10stockNum == 0)
            {
                product10.setEnabled(false);
                showMessageDialog(null, "죄송합니다 ㅠㅠ 와인맛이 없어요","와인 매진", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == cancelOrder)
        {
            selectedItems.setText("");
            moneyInput.setText(poundsFormat.format(productTotal - productTotal));
            selectedItemTotal.setText("?0.00");
            outputMessage.setText("");
            outputMessage.setBackground(Color.white);

            takeChange.setVisible(false);

            totalInserted = 0;
            productTotal = 0;
        }

        if (e.getSource() == enterCoins) 
        {
            String productsFW = selectedItems.getText();
            String totalFW = selectedItemTotal.getText();
            String inputFW = moneyInput.getText();
            dh.write(productsFW, totalFW, inputFW);
            double changePH = errorGiveChange;
            CFchangeRemaining.setText(decFormat.format(changePH));

            if (totalInserted == 0)
            {
                outputMessage.setText("우선 돈부터 넣어주세요!!!");
                outputMessage.setBackground(Color.red);
            }

            else if (productTotal == 0)
            {

                outputMessage.setText("Error! Please select at least one product!");
                outputMessage.setBackground(Color.red);
            }
            else if (productTotal == totalInserted) 
            {
                outputMessage.setText(poundsFormat.format(totalInserted) + " Accepted! \n\nThank you for your purchase!");
                outputMessage.setBackground(Color.green);

                selectedItems.setText("");
                selectedItemTotal.setText("0.00");
                moneyInput.setText("0.00");

                totalInserted = 0;
                productTotal = 0;
            }
            else if (totalInserted < productTotal) 
            {

                double errorInputShort = productTotal - totalInserted;

                outputMessage.setText("Error! Please input the correct amount!\n\n You still have " + poundsFormat.format(errorInputShort) + " to pay!");
                outputMessage.setBackground(Color.red);
            }
            else if (totalInserted > productTotal) 
            {

                takeChange.setVisible(true);

                product1.setEnabled(false);
                product2.setEnabled(false);
                product3.setEnabled(false);
                product4.setEnabled(false);
                product5.setEnabled(false);
                product6.setEnabled(false);
                product7.setEnabled(false);
                product8.setEnabled(false);
                product9.setEnabled(false);
                product10.setEnabled(false);
                enterCoins.setEnabled(false);
                returnCoins.setEnabled(false);
                cancelOrder.setEnabled(false);
                fiveTButton.setEnabled(false);
                oneTButton.setEnabled(false);
                fiveHButton.setEnabled(false);
                fiftyPenceButton.setEnabled(false);
                onePoundButton.setEnabled(false);
                twoPoundButton.setEnabled(false);

                outputMessage.setText(poundsFormat.format(totalInserted) + " 원만큼 사셨네요 감사합니다!\n거스름돈은 총 " + poundsFormat.format(errorGiveChange) + "입니다!\n 거스름돈 반환 버튼을 눌러주세요.");
                outputMessage.setBackground(Color.green);

                selectedItems.setText("");
                selectedItemTotal.setText("0.00");
                moneyInput.setText("0.00");

                totalInserted = 0;
                productTotal = 0;
            }
        }

        if (e.getSource() == takeChange)
        {

            takeChange.setVisible(false);
            changeFrameInit();
            changeFrame.setVisible(true);

            CFfivepStock.setText(numFormat.format(fivepStockNum));
            CFtenpStock.setText(numFormat.format(tenpStockNum));
            CFtwentypStock.setText(numFormat.format(twentypStockNum));
            CFfiftypStock.setText(numFormat.format(fiftypStockNum));
            CFonepStock.setText(numFormat.format(onepStockNum));
            CFtwopStock.setText(numFormat.format(twopStockNum));
        }

        if (e.getSource() == CFfiveTButton)
        {

            fivepStockNum -=1;
            CFfivepStock.setText(numFormat.format(fivepStockNum));

            double calcChange = Double.parseDouble(CFchangeRemaining.getText());
            calcChange -=5000;
            CFchangeRemaining.setText(decFormat.format(calcChange));

            if (calcChange < 0)
            {
                calcChange +=5000;  
                CFchangeRemaining.setText(decFormat.format(calcChange));

                fivepStockNum +=1;
                CFfivepStock.setText(numFormat.format(fivepStockNum));

                CFoutputMessage.setText(" 잔액 확인!\n현재 반납금액보다 더 많은 거스름돈을\n선택하셨습니다.");
                CFoutputMessage.setBackground(Color.red);
            }
            if (fivepStockNum == 0)
            {
                CFfiveTButton.setEnabled(false);
                showMessageDialog(null, "5000원권이 모자랍니다. 다른 권액을 이용해주세요", "5000원권 부족", JOptionPane.WARNING_MESSAGE);
            }
        } 
        
        if (e.getSource() == CFoneTButton)
        {

            tenpStockNum -=1;
            CFtenpStock.setText(numFormat.format(tenpStockNum));

            double calcChange = Double.parseDouble(CFchangeRemaining.getText());
            calcChange -=1000;
            CFchangeRemaining.setText(decFormat.format(calcChange));

            if (calcChange < 0)
            {
                calcChange +=1000;  
                CFchangeRemaining.setText(decFormat.format(calcChange));

                tenpStockNum +=1;
                CFtenpStock.setText(numFormat.format(tenpStockNum));

                CFoutputMessage.setText(" 잔액 확인!\n현재 반납금액보다 더 많은 거스름돈을\n선택하셨습니다.");
                CFoutputMessage.setBackground(Color.red);
            }
            if (tenpStockNum == 0)
            {
                CFoneTButton.setEnabled(false);
                showMessageDialog(null, "1000원권이 모자랍니다. 다른 권액을 이용해주세요", "1000원권 부족", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == CFfiveHButton)
        {

            twentypStockNum -=1;

            CFtwentypStock.setText(numFormat.format(twentypStockNum));

            double calcChange = Double.parseDouble(CFchangeRemaining.getText());
            calcChange -=500;
            CFchangeRemaining.setText(decFormat.format(calcChange));

            if (calcChange < 0)
            {
                calcChange +=500; 
                CFchangeRemaining.setText(decFormat.format(calcChange));

                twentypStockNum +=1;
                CFtwentypStock.setText(numFormat.format(twentypStockNum));

                CFoutputMessage.setText(" 잔액 확인!\n현재 반납금액보다 더 많은 거스름돈을\n선택하셨습니다.");
                CFoutputMessage.setBackground(Color.red);
            }
            if (twentypStockNum == 0)
            {
                CFfiveHButton.setEnabled(false);
                showMessageDialog(null, "500원이 모자랍니다. 다른 권액을 이용해주세요", "500원 부족", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == CFfiftyPenceButton)
        {

            fiftypStockNum -=1;
            CFfiftypStock.setText(numFormat.format(fiftypStockNum));

            double calcChange = Double.parseDouble(CFchangeRemaining.getText());
            calcChange -=100;
            CFchangeRemaining.setText(decFormat.format(calcChange));

            if (calcChange < 0)
            {
                calcChange +=100; 
                CFchangeRemaining.setText(decFormat.format(calcChange));

                fiftypStockNum +=1;
                CFfiftypStock.setText(numFormat.format(fiftypStockNum));

                CFoutputMessage.setText(" 잔액 확인!\n현재 반납금액보다 더 많은 거스름돈을\n선택하셨습니다.");

                CFoutputMessage.setBackground(Color.red);
            }
            if (fiftypStockNum == 0)
            {
                CFfiftyPenceButton.setEnabled(false);
                showMessageDialog(null, "100원이 모자랍니다. 다른 권액을 이용해주세요", "100원 부족", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource()== CFonePoundButton)
        {

            onepStockNum -=1;
            CFonepStock.setText(numFormat.format(onepStockNum));

            double calcChange = Double.parseDouble(CFchangeRemaining.getText());
            calcChange -=50;
            CFchangeRemaining.setText(decFormat.format(calcChange));

            if (calcChange < 0)
            {
                calcChange +=50; 
                CFchangeRemaining.setText(decFormat.format(calcChange));

                onepStockNum +=1;
                CFonepStock.setText(numFormat.format(onepStockNum));

                CFoutputMessage.setText(" 잔액 확인!\n현재 반납금액보다 더 많은 거스름돈을\n선택하셨습니다.");
                CFoutputMessage.setBackground(Color.red);
            }
            if (onepStockNum == 0)
            {
                CFonePoundButton.setEnabled(false);
                showMessageDialog(null, "50원이 모자랍니다. 다른 권액을 이용해주세요", "50원 부족", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == CFtwoPoundButton)
        {

            twopStockNum -=1;
            CFtwopStock.setText(numFormat.format(twopStockNum));

            double calcChange = Double.parseDouble(CFchangeRemaining.getText());

            calcChange -=10;
            CFchangeRemaining.setText(decFormat.format(calcChange));

            if (calcChange < 0)
            {
                calcChange +=10;  
                CFchangeRemaining.setText(decFormat.format(calcChange));

                twopStockNum +=1;
                CFtwopStock.setText(numFormat.format(twopStockNum));

                CFoutputMessage.setText(" 잔액 확인!\n현재 반납금액보다 더 많은 거스름돈을\n선택하셨습니다.");
                CFoutputMessage.setBackground(Color.red);
            }
            if (twopStockNum == 0)
            {
                CFtwoPoundButton.setEnabled(false);
                showMessageDialog(null, "10원이 모자랍니다. 다른 권액을 이용해주세요", "10원 부족", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == CFfinished)
        {

            double calcChange = Double.parseDouble(CFchangeRemaining.getText());
            if (calcChange > 0)
            {
                CFoutputMessage.setText("확인!\n\n거스름돈 총"
                        + decFormat.format(calcChange) + "을 바꿔 주시기 바랍니다!");
                CFoutputMessage.setBackground(Color.red);
            }
            if (calcChange == 0)
            {
                CFoutputMessage.setText(" 감사합니다!\n거래 종료 버튼을 눌러서\n자판기로 돌아가시기 바랍니다.");
                        CFoutputMessage.setBackground(Color.green);

                CFclose.setVisible(true);

                CFfiveTButton.setEnabled(false);
                CFoneTButton.setEnabled(false);
                CFfiveHButton.setEnabled(false);
                CFfiftyPenceButton.setEnabled(false);
                CFonePoundButton.setEnabled(false);

                CFtwoPoundButton.setEnabled(false);
                CFfinished.setEnabled(false);
            }
        }

        if (e.getSource() == CFclose)
        {

            closeChangeFrame();

            product1.setEnabled(true);
            product2.setEnabled(true);
            product3.setEnabled(true);
            product4.setEnabled(true);
            product5.setEnabled(true);
            product6.setEnabled(true);
            product7.setEnabled(true);
            product8.setEnabled(true);
            product9.setEnabled(true);
            product10.setEnabled(true);
            enterCoins.setEnabled(true);
            returnCoins.setEnabled(true);
            cancelOrder.setEnabled(true);
            fiveTButton.setEnabled(true);
            oneTButton.setEnabled(true);
            fiveHButton.setEnabled(true);
            fiftyPenceButton.setEnabled(true);
            onePoundButton.setEnabled(true);
            twoPoundButton.setEnabled(true);
            CFfiveTButton.setEnabled(true);
            CFoneTButton.setEnabled(true);
            CFfiveHButton.setEnabled(true);
            CFfiftyPenceButton.setEnabled(true);
            CFonePoundButton.setEnabled(true);
            CFtwoPoundButton.setEnabled(true);
            CFfinished.setEnabled(true);

            outputMessage.setText("");
            outputMessage.setBackground(Color.white);
        }

        if (e.getSource() == returnCoins)
        {
            if (totalInserted == 0) 
            {
                outputMessage.setText("돈을 아직 넣지 않았습니다.\n\n돈을 넣어주세요.");
                outputMessage.setBackground(Color.red);

                moneyInput.setText("0.00");

            }

            else 
            {
                outputMessage.setText(poundsFormat.format(totalInserted) + "을 반납합니다.\n\n다시 돈을 넣어주세요");
                        outputMessage.setBackground(Color.red);
                moneyInput.setText("0.00");
                totalInserted = 0;
            }
        }

        if(e.getSource() == fiveTButton)
        {
            totalInserted += 5000;
            moneyInput.setText(poundsFormat.format(totalInserted));
            fivepStockNum += 1;
            fivepStock.setText(numFormat.format(fivepStockNum));
        }
        if(e.getSource() == oneTButton)
        {
            totalInserted += 1000;
            moneyInput.setText(poundsFormat.format(totalInserted));
            tenpStockNum += 1;
            tenpStock.setText(numFormat.format(tenpStockNum));
        }
        if(e.getSource() == fiveHButton)
        {
            totalInserted += 500;
            moneyInput.setText(poundsFormat.format(totalInserted));
            twentypStockNum += 1;
            twentypStock.setText(numFormat.format(twentypStockNum));
        }
        if(e.getSource() == fiftyPenceButton)
        {
            totalInserted += 100;
            moneyInput.setText(poundsFormat.format(totalInserted));
            fiftypStockNum += 1;
            fiftypStock.setText(numFormat.format(fiftypStockNum));
        }
        if(e.getSource() == onePoundButton)
        {
            totalInserted += 50;
            moneyInput.setText(poundsFormat.format(totalInserted));
            onepStockNum += 1;
            onepStock.setText(numFormat.format(onepStockNum));
        }
        if(e.getSource() == twoPoundButton)
        {
            totalInserted += 10;
            moneyInput.setText(poundsFormat.format(totalInserted)); 
            twopStockNum += 1;
            twopStock.setText(numFormat.format(twopStockNum));
        }
        try {
            myStatement.executeUpdate(writeString);
        }
        catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }
}