import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import static javax.swing.JOptionPane.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.html.ObjectView;

import java.awt.event.*; 
import java.sql.*;
public class VendingMachine extends JFrame implements ActionListener
{
    static VendingMachineDBHandler vdh = new VendingMachineDBHandler();
    Date now = new Date();

    int year = now.getYear() + 1900;	// 년도 표시
    int month = now.getMonth() + 1;
    int day = now.getDate();

    int hours = now.getHours();
    int minutes = now.getMinutes();
    int seconds = now.getSeconds();

    static ImageIcon[] productImg = new ImageIcon[10];
    static JButton[] product = new JButton[10];
    static JLabel[] productName = new JLabel[10];
    static JLabel[] productPrice = new JLabel[10];
    static JTextField[] productStock = new JTextField[10];

    static ImageIcon[] billImg = new ImageIcon[6];
    static JButton[] bill = new JButton[6];
    static JTextField[] billStock = new JTextField[6];

    static ImageIcon[] changeImg = new ImageIcon[6];
    static JButton[] change = new JButton[6];
    static JTextField[] changeStock = new JTextField[6];

    static ArrayList<String> nameP = new ArrayList<String>();
    static ArrayList<String> nameB = new ArrayList<String>();
    static ArrayList<Integer> stockP = new ArrayList<Integer>();
    static ArrayList<Integer> stockB = new ArrayList<Integer>();
    static ArrayList<Integer> priceP = new ArrayList<Integer>();
    static ArrayList<Integer> sales = new ArrayList<Integer>();
    String path = this.getClass().getResource("").getPath();


    JFrame changeFrame = new JFrame("거스름돈 코너");

    ImageIcon bannerImage = new ImageIcon(path+"images\\title.jpg");
    JButton bannerButton = new JButton("", bannerImage);

    //Horizontal/Vertical Rules as buttons for simplicity and layout
    JButton ruleH = new JButton("");
    JButton ruleV = new JButton("");
    JButton ruleV2 = new JButton("");
    //selected items section
    JLabel selectedLabel = new JLabel("선택한 호로요이들:");
    JTextArea selectedItems = new JTextArea(8,250);
        //Total price:
    JLabel selectedItemTotalLabel = new JLabel("호로요이 가격:");
    JTextField selectedItemTotal = new JTextField("0.00", 30);
    //Money section:
//Label:
    JLabel moneyInputLabel = new JLabel("금액선택:");


    JLabel moneyInputLabel2 = new JLabel("현재 금액:");
    JTextField moneyInput = new JTextField("0.00", 30);

    ImageIcon enterCoinsImage = new ImageIcon(path+"\\images\\s1.jpg");
    JButton enterCoins = new JButton("", enterCoinsImage);
    ImageIcon returnCoinsImage = new ImageIcon(path+"\\images\\s2.jpg");
    JButton returnCoins = new JButton ("", returnCoinsImage);

    JTextArea outputMessage = new JTextArea(2,30);

    JLabel cancelOrderLabel = new JLabel("취소 / 주문완료");
    ImageIcon cancelOrderImage = new ImageIcon(path+"\\images\\exit.jpg");
    JButton cancelOrder = new JButton("", cancelOrderImage);

    JButton takeChange = new JButton("거스름돈 반환");

    JLabel dateLabel = new JLabel("날짜:");
    JLabel timeLabel = new JLabel("시간:");
    JTextField currentDate = new JTextField("", 10);
    JTextField currentTime = new JTextField ("", 10);

    ImageIcon cfLabelicon = new ImageIcon(path+"images/CFLabel1icon.jpg");
    JLabel cfLabelTakeChange = new JLabel("", cfLabelicon, JLabel.CENTER);

    JLabel CFpleaseTakeChange = new JLabel("권액별 거스름돈 :");
    JLabel CFchangeRemainingLabel = new JLabel("거스름돈 잔액 : ");
    JTextField CFchangeRemaining = new JTextField("",10);
    JButton CFruleH1 = new JButton("");
    JTextArea CFoutputMessage = new JTextArea("", 2, 30);
    JButton CFfinished = new JButton("교환 완료");

    JButton CFclose = new JButton("거래 종료");


    int productTotal = 0;
    int totalInserted = 0;



    public static void main(String[] args) {
        VendingMachine jf = new VendingMachine();
    }

    public VendingMachine()

    {

        ArrayList<Object> stock = vdh.setStock();
        /*((String) stock.get(0))*/
        Object[][] Pstock = (Object[][]) stock.get(0);
        Object[][] Bstock = (Object[][]) stock.get(1);

        for(int i = 0 ; i < Pstock.length ; i++){

            productImg[i] = new ImageIcon(path+"images\\p"+(i+1)+".jpg");
            product[i] = new JButton("", productImg[i]);
            productName[i] = new JLabel(String.valueOf(Pstock[i][0]), JLabel.CENTER);
            productPrice[i] = new JLabel(String.valueOf(Pstock[i][1])+"원", JLabel.CENTER);
            productStock[i] = new JTextField(String.valueOf(Pstock[i][2]),2);

            nameP.add(i, String.valueOf(Pstock[i][0]));
            stockP.add(i, (Integer) Pstock[i][2]);
            priceP.add(i, (Integer) Pstock[i][1]);

        }

        for(int i = 0 ; i < Bstock.length ; i++){
            billImg[i] = new ImageIcon(path+"images\\b"+(i+1)+".jpg");
            bill[i] = new JButton("", billImg[i]);
            billStock[i] = new JTextField(String.valueOf(Bstock[i][1]),2);
            nameB.add(i, String.valueOf(Bstock[i][0]));
            stockB.add(i, (Integer) Bstock[i][1]);
            changeImg[i] = new ImageIcon(path+"images\\b"+(i+1)+".jpg");
            change[i] = new JButton("", billImg[i]);
            changeStock[i] = new JTextField("",2);
        }



        for (int i = 0; i < Pstock.length; i++) {
            sales.add(0);
        }
        setLayout(null); 
        setSize(830, 705); 
        setBackground(Color.red);
        setTitle("호로호로호로요이 자판기!"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bannerButton.setBounds(210, 10, 400, 30);
        add(bannerButton);

        currentDate.setBounds(740, 5, 80, 20);
        currentDate.setEditable(false);
        add(currentDate);
        dateLabel.setBounds(705,7,30,15);
        add(dateLabel);
        currentTime.setBounds(740, 28, 80, 20);
        currentTime.setEditable(false);
        add(currentTime);
        timeLabel.setBounds(702,30,33,15);
        add(timeLabel);


        for(int i = 0 ; i < Pstock.length ; i++){
            if(i < 5) {
                product[i].setBounds(20+(160*i),75,140,150);

                productName[i].setBounds(20+(160*i),225,140,15);
                productPrice[i].setBounds(20+(160*i),250,140,15);

            }
            else{
                product[i].setBounds(20+(160*(i-5)),270,140,150);
                productName[i].setBounds(20+(160*(i-5)),420,140,15);
                productPrice[i].setBounds(20+(160*(i-5)),445,140,15);

            }
            product[i].addActionListener(this);
            add(product[i]);
            add(productName[i]);
            add(productPrice[i]);
            productPrice[i].setForeground(Color.red);
            productStock[i].setBounds(161+(160*i), 75, 17, 20);
            productStock[i].setEditable(false);
            add(productStock[i]);
        }

        int pointX = 0;
        int pointX2 = 0;
        int pointY = 0;


        for(int i = 0 ; i < Bstock.length ; i++){

            switch (i){
                case 0:
                case 3:{
                    pointX = 290;
                    break;
                }

                case 1:
                case 4:{
                    pointX = 379;
                    break;
                }

                case 2:
                case 5:{
                    pointX = 471;
                    break;
                }

            }

            if(i < 3){
                pointY = 495;
            }
            else{
                pointY = 565;
            }
            bill[i].setBounds(pointX, pointY, 68, 68);
            billStock[i].setBounds(pointX+68, pointY, 68, 68);
            bill[i].addActionListener(this);
            add(bill[i]);
        }


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


        for(int i = 0 ; i < stockB.size()-1 ; i++){
            change[i].setBounds(10 + (82 * i), 80, 60, 60);
            changeStock[i].setText(String.valueOf(stockB.get(i)));
            changeStock[i].setBounds(35 + (82 * i), 150, 18, 20);
            change[i].addActionListener(this);
            changeFrame.add(change[i]);
            changeFrame.add(changeStock[i]);
        }


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



        for(int i = 0 ; i < nameP.size() ; i++){
            if(e.getSource() == product[i]){

                sales.add(i, sales.get(i) + 1);
                addToSelection = selectedItems.getText() + ("\n"+nameP.get(i)+" - "+priceP.get(i));
                selectedItems.setText(addToSelection);


                productTotal += priceP.get(i);
                selectedItemTotal.setText(poundsFormat.format(productTotal));

                stockP.add(i, stockP.get(i)-1);
                productStock[i].setText(numFormat.format(stockP.get(i)));

                if (stockP.get(i) == 0)
                {
                    product[i].setEnabled(false);
                    showMessageDialog(null, "ごめんなさい "+ nameP.get(i)+"맛이 매진입니다", nameP.get(i)+" 매진", JOptionPane.WARNING_MESSAGE);
                }
            }
        }


        if (e.getSource() == cancelOrder)
        {
            selectedItems.setText("");
            moneyInput.setText(poundsFormat.format(productTotal - productTotal));
            selectedItemTotal.setText("0.00");
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

                for(int i = 0 ; i < nameP.size() ; i++){
                    product[i].setEnabled(false);
                }
                for(int i = 0 ; i < nameB.size() ; i++){
                    bill[i].setEnabled(false);
                }
                enterCoins.setEnabled(false);
                returnCoins.setEnabled(false);
                cancelOrder.setEnabled(false);


                outputMessage.setText(poundsFormat.format(productTotal) + " 원만큼 사셨네요 감사합니다!\n거스름돈은 총 " + poundsFormat.format(errorGiveChange) + "입니다!\n 거스름돈 반환 버튼을 눌러주세요.");
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

            for(int i = 0 ; i < nameB.size() ; i++){
                change[i].setText(numFormat.format(stockB.get(i)));
            }

        }

        for(int i = 0 ; i < nameB.size() ; i++){
            if(e.getSource() == change[i]){
                stockB.set(i, stockB.get(i) - 1);
                changeStock[i].setText(numFormat.format(stockB.get(i)));

                double calcChange = Double.parseDouble(CFchangeRemaining.getText());
                calcChange -= Integer.parseInt(nameB.get(i).substring(0,nameB.get(i).indexOf("원")));
                CFchangeRemaining.setText(decFormat.format(calcChange));

                if (calcChange < 0)
                {
                    calcChange += Integer.parseInt(nameB.get(i).substring(0, nameB.get(i).indexOf("원")));
                    CFchangeRemaining.setText(decFormat.format(calcChange));

                    stockB.set(i, stockB.get(i) + 1);
                    changeStock[i].setText(numFormat.format(stockB.get(i)));

                    CFoutputMessage.setText(" 잔액 확인!\n현재 반납금액보다 더 많은 거스름돈을\n선택하셨습니다.");
                    CFoutputMessage.setBackground(Color.red);
                }
                if (stockB.get(i) == 0)
                {
                    change[i].setEnabled(false);
                    showMessageDialog(null, nameB.get(i)+"권이 모자랍니다. 다른 권액을 이용해주세요", nameB.get(i)+"권 부족", JOptionPane.WARNING_MESSAGE);
                }

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

                for (int i = 0 ; i < nameB.size() ; i++){
                    change[i].setEnabled(false);
                }

                CFfinished.setEnabled(false);
            }
        }

        if (e.getSource() == CFclose)
        {

            closeChangeFrame();

            for(int i = 0 ; i < nameP.size() ; i++){
                product[i].setEnabled(true);
                productStock[i].setText(String.valueOf(stockP.get(i)));
            }

            for(int i = 0 ; i < nameB.size() ; i++){
                bill[i].setEnabled(true);
                change[i].setEnabled(true);
                billStock[i].setText(String.valueOf(stockB.get(i)));
                changeStock[i].setText(String.valueOf(stockB.get(i)));
            }
            enterCoins.setEnabled(true);
            returnCoins.setEnabled(true);
            cancelOrder.setEnabled(true);
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


        for (int i = 0 ; i < nameB.size() ; i++){
            if(e.getSource() == bill[i]){
                totalInserted += Integer.parseInt(nameB.get(i).substring(0,nameB.get(i).indexOf("원")));;
                moneyInput.setText(poundsFormat.format(totalInserted));
                stockB.set(i, stockB.get(i) +  1);
                billStock[i].setText(numFormat.format(stockB.get(i)));
            }
            for(int j = 0 ; j < nameB.size() ; j++){
            }
        }

    }
}