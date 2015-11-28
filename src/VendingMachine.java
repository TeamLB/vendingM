import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static javax.swing.JOptionPane.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



public class VendingMachine extends JFrame implements ActionListener
{
    //DataBase Handler 객체 생성
    static VendingMachineDBHandler vdh = new VendingMachineDBHandler();

    //날짜와 시간 출력을 위한 포맷 설정 및 객체 생성
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();
    Date time = new Date();


    //상품, 권액별 버튼과 텍스트 필드 생성
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


    //내부에서 사용할 각 상품의 이름 및 재고, 판매량 ArrayList 생성
    static ArrayList<String> nameP = new ArrayList<String>();
    static ArrayList<String> nameB = new ArrayList<String>();
    static ArrayList<Integer> stockP = new ArrayList<Integer>();
    static ArrayList<Integer> stockB = new ArrayList<Integer>();
    static ArrayList<Integer> priceP = new ArrayList<Integer>();

    static ArrayList<Integer> sales = new ArrayList<Integer>();
    static ArrayList<Integer> stockPTemp = new ArrayList<Integer>();
    static ArrayList<Integer> stockBTemp = new ArrayList<Integer>();
    int[] tempTotal = new int[2];



    //이미지 파일을 위한 경로를 변수로 저장
    String path = this.getClass().getResource("").getPath();



    //거스름돈 프레임 생성
    JFrame changeFrame = new JFrame("거스름돈");

    //배너 버튼 생성
    ImageIcon bannerImage = new ImageIcon(path+"images\\title.jpg");
    JButton bannerButton = new JButton("", bannerImage);

    //레이아웃용 버튼 생성
    JButton ruleH = new JButton("");
    JButton ruleV = new JButton("");
    JButton ruleV2 = new JButton("");


    //선택된 제품을 표시할 라벨 및 텍스트 필드 생성
    JLabel selectedLabel = new JLabel("선택한 호로요이들:");
    JTextArea selectedItems = new JTextArea(8,250);

    //총 금액 텍스트 필드 생성
    JLabel selectedItemTotalLabel = new JLabel("호로요이 가격:");
    JTextField selectedItemTotal = new JTextField("0", 30);




    //입금 금액 표시 라벨 및 텍스트 필드 생성
    JLabel moneyInputLabel = new JLabel("금액선택:");
    JLabel moneyInputLabel2 = new JLabel("현재 금액:");
    JTextField moneyInput = new JTextField("0", 30);


    //입금, 각종 버튼 및 메시지 출력 텍스트 필드 생성
    ImageIcon enterCoinsImage = new ImageIcon(path+"\\images\\s1.jpg");
    JButton enterCoins = new JButton("", enterCoinsImage);
    ImageIcon returnCoinsImage = new ImageIcon(path+"\\images\\s2.jpg");
    JButton returnCoins = new JButton ("", returnCoinsImage);

    JLabel cancelOrderLabel = new JLabel("취소 / 주문완료");
    ImageIcon cancelOrderImage = new ImageIcon(path+"\\images\\exit.jpg");
    JButton cancelOrder = new JButton("", cancelOrderImage);

    JLabel initChangeLabel = new JLabel("취소 / 주문완료");
    ImageIcon initChangeImage = new ImageIcon(path+"\\images\\exit.jpg");
    JButton initChange = new JButton("", cancelOrderImage);


    JTextArea outputMessage = new JTextArea(2,30);

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



    //총 주문, 입금 금액 변수
    int productTotal = 0;
    int totalInserted = 0;



    public static void main(String[] args) {
        VendingMachine jf = new VendingMachine();
    }

    //생성자 선언
    public VendingMachine()

    {


        //DB로 부터 재고 값 전달 받음
        ArrayList<Object> stock = vdh.setStock();

        //받아온 값을 물품과 권액별 배열에 저장
        Object[][] Pstock = (Object[][]) stock.get(0);
        Object[][] Bstock = (Object[][]) stock.get(1);

        //제품별 버튼 생성 및 재고 세팅
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
        stockPTemp = (ArrayList<Integer>) stockP.clone();





        //권액 및 거스름돈 별 버튼 생성 및 재고 세팅
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
        stockBTemp = (ArrayList<Integer>) stockB.clone();


        System.out.println();
        System.out.println();
        for(int i = 0 ; i < Pstock.length ; i++) {
            System.out.println(stockPTemp.get(i));
        }
        System.out.println();
        for(int i = 0 ; i < Bstock.length ; i++) {
            System.out.println(stockBTemp.get(i));
        }

        //주문 내역 초기화
        for (int i = 0; i < Pstock.length; i++) {
            sales.add(0);
        }


        //레이아웃 설정
        setLayout(null);
        setSize(830, 705);
        setBackground(Color.red);
        setTitle("호로호로호로요이 자판기![named by SY_L... Not ME]");
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
                productStock[i].setBounds(161+(160*i), 75, 17, 20);

            }
            else{
                product[i].setBounds(20+(160*(i-5)),270,140,150);
                productName[i].setBounds(20+(160*(i-5)),420,140,15);
                productPrice[i].setBounds(20+(160*(i-5)),445,140,15);
                productStock[i].setBounds(161+(160*(i-5)), 270, 17, 20);
            }
            product[i].addActionListener(this);
            add(product[i]);
            add(productName[i]);
            add(productPrice[i]);
            productPrice[i].setForeground(Color.red);

            productStock[i].setEditable(false);
            add(productStock[i]);
        }


        int pointX = 0;
        int pointY;


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



        cancelOrderLabel.setBounds(676,635,122,25);
        add(cancelOrderLabel);
        cancelOrder.setBounds(794,635,25,25);
        cancelOrder.addActionListener(this);
        add(cancelOrder);


        enterCoins.setBounds(568,475,250,40);
        enterCoins.addActionListener(this);
        add(enterCoins);
        returnCoins.setBounds(568,525,250,40);
        returnCoins.addActionListener(this);
        add(returnCoins);
        outputMessage.setBounds(568,575,250,50);
        outputMessage.setEditable(false);
        add(outputMessage);

        takeChange.setBounds(565,635,109,25);
        takeChange.setForeground(Color.red);

        takeChange.setVisible(false);
        takeChange.addActionListener(this);
        add(takeChange);


        setVisible(true);
        setResizable(false);
    }


    //거스름돈 레이아웃 설정 함수
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


        initChangeLabel.setBounds(160,320,100,25);
        initChange.setBounds(180,320,100,25);
        initChange.addActionListener(this);
        changeFrame.add(initChangeLabel);
        changeFrame.add(initChange);

        for(int i = 0 ; i < stockB.size()
                ; i++){
            change[i].setBounds(10 + (82 * i), 80, 60, 60);
            changeStock[i].setText(String.valueOf(stockBTemp.get(i)));
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

    //거스름돈 프레임 닫기 함수
    private void closeChangeFrame()
    {
        changeFrame.setVisible(false);
    }

    //액션 관련 이벤트 설정
    public void actionPerformed(ActionEvent e)
    {


        //표시 할 항목에 대한 포멧 설정
        DecimalFormat wonFormat = new DecimalFormat ("0");
        DecimalFormat numFormat = new DecimalFormat ("0");
        DecimalFormat decFormat = new DecimalFormat("0");

        //선택된 상품 정보 문자열
        String addToSelection;

        //현재 날짜 및 시간 세팅
        currentDate.setText(dateFormat.format(date));
        currentTime.setText(timeFormat.format(time));

        //거스름돈 계산&에러 판별을 위한 변수 (총 입금 금액 - 총 주문 금액)
        int errorGiveChange = totalInserted - productTotal;




        //상품 버튼별 선택 판별
        for(int i = 0 ; i < nameP.size() ; i++){
            if(e.getSource() == product[i]){
                /*
                  주문 내역 추가
                  선택된 상품 추가
                  총 판매액 증가
                  상품 재고 수정
                */
                sales.set(i, sales.get(i) + 1);
                addToSelection = selectedItems.getText() + ("\n"+nameP.get(i)+" - "+priceP.get(i));
                selectedItems.setText(addToSelection);


                productTotal += priceP.get(i);
                selectedItemTotal.setText(wonFormat.format(productTotal));

                /*stockP.add(i, stockP.get(i)-1);
                productStock[i].setText(numFormat.format(stockP.get(i)));*/

                stockPTemp.add(i, stockPTemp.get(i)-1);
                productStock[i].setText(numFormat.format(stockPTemp.get(i)));

                //상품 재고 부족 시 메시지 표시 및 버튼 비 활성화
                if (stockP.get(i) == 0)
                {
                    product[i].setEnabled(false);
                    showMessageDialog(null, "ごめんなさい "+ nameP.get(i)+"맛이 매진입니다", nameP.get(i)+" 매진", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        //권액별 버튼 선택 판별
        for (int i = 0 ; i < nameB.size() ; i++){

            if(e.getSource() == bill[i]){

                //선택된 권액의 수량 재 설정
                totalInserted += Integer.parseInt(nameB.get(i).substring(0,nameB.get(i).indexOf("원")));;
                moneyInput.setText(wonFormat.format(totalInserted));

                stockBTemp.set(i, stockBTemp.get(i) + 1);
                billStock[i].setText(numFormat.format(stockBTemp.get(i)));
            }

            //권액별 수량에 따른 버튼 활성/비활성화
            if (stockBTemp.get(i) == 0) {
                change[i].setEnabled(false);
            }
            else{
                change[i].setEnabled(true);
            }
        }

        //취소버튼 선택시
        if (e.getSource() == cancelOrder)
        {
            selectedItems.setText("");
            moneyInput.setText(wonFormat.format(productTotal - productTotal));
            selectedItemTotal.setText("0.00");
            outputMessage.setText("");
            outputMessage.setBackground(Color.white);

            takeChange.setVisible(false);

            totalInserted = 0;
            productTotal = 0;


            stockPTemp = (ArrayList<Integer>) stockP.clone();
            stockBTemp = (ArrayList<Integer>) stockB.clone();
            sales.clear();
            for (int i = 0; i < nameP.size(); i++) {
                sales.add(0);
            }

        }

        //거스름돈 초기화 버튼 선택시
        if (e.getSource() == initChange)
        {
            System.out.println("!!!!");
            totalInserted = tempTotal[0];
            productTotal = tempTotal[1];
            errorGiveChange = totalInserted - productTotal;
            double calcChange = errorGiveChange;
            System.out.println(calcChange);
            CFchangeRemaining.setText(decFormat.format(calcChange));
            CFoutputMessage.setText("잔액이 초기화 되었습니다");
            CFoutputMessage.setBackground(Color.green);
            stockBTemp = (ArrayList<Integer>) stockB.clone();

            for(int i = 0 ; i < nameB.size() ; i ++){
                changeStock[i].setText(numFormat.format(stockBTemp.get(i)));
                if (stockBTemp.get(i) == 0) {
                    change[i].setEnabled(false);
                }
                else{
                    change[i].setEnabled(true);
                }
            }

        }


        //계산 버튼 선택 시
        if (e.getSource() == enterCoins)
        {
            tempTotal[0] = totalInserted;
            tempTotal[1] = productTotal;
            //남은 금액 세팅
            double changePH = errorGiveChange;
            CFchangeRemaining.setText(decFormat.format(changePH));


            //입금 금액 == 0
            if (totalInserted == 0)
            {
                outputMessage.setText("우선 돈부터 넣어주세요!!!");
                outputMessage.setBackground(Color.red);
            }

            //주문한 상품이 없을 시
            else if (productTotal == 0)
            {

                outputMessage.setText("최소 한가지의 상품을 선택 해 주세요");
                outputMessage.setBackground(Color.red);
            }

            //총 주문 금액과 총 입금 금액이 같을 경우
            else if (productTotal == totalInserted)
            {
                /*
                  메시지 출력
                  주문 내역 및 재고 DB로 전달
                  주문 내역 및 초기화 필요 항목 초기화
                */
                outputMessage.setText(wonFormat.format(totalInserted) + "\n\n감사합니다!");
                outputMessage.setBackground(Color.green);

                selectedItems.setText("");
                selectedItemTotal.setText("0");
                moneyInput.setText("0");

                totalInserted = 0;
                productTotal = 0;

                stockP = (ArrayList<Integer>) stockPTemp.clone();
                stockB = (ArrayList<Integer>) stockBTemp.clone();
                vdh.madeChoice(sales, nameP, currentDate.getText()+ " " + currentTime.getText());
                vdh.setStockDB(nameP, stockP, nameB, stockB, currentDate.getText());
                sales.clear();
                for (int i = 0; i < nameP.size(); i++) {
                    sales.add(0);
                }
            }

            //입금 금액 부족시
            else if (totalInserted < productTotal)
            {

                //부족한 금액 계산 후 변수 대입
                double errorInputShort = productTotal - totalInserted;

                //메시지 표시
                outputMessage.setText("에너지가 ㅂ.. 아니 돈이 부족합니다!\n\n " + wonFormat.format(errorInputShort) + " 을 더 넣어 주세요");
                outputMessage.setBackground(Color.red);
            }

            //입금 금액이 주문 금액 보다 많을 시
            else if (totalInserted > productTotal)
            {

                //거스름돈 버튼 활성화
                takeChange.setVisible(true);


                //재품 및 입금, 반납 버튼 비활성화
                for(int i = 0 ; i < nameP.size() ; i++){
                    product[i].setEnabled(false);
                }
                for(int i = 0 ; i < nameB.size() ; i++){
                    bill[i].setEnabled(false);
                }
                enterCoins.setEnabled(false);
                returnCoins.setEnabled(false);

                //메시지 출력
                outputMessage.setText(wonFormat.format(productTotal) + " 원만큼 사셨네요 감사합니다!\n거스름돈은 총 " + wonFormat.format(errorGiveChange) + "입니다!\n 거스름돈 반환 버튼을 눌러주세요.");
                outputMessage.setBackground(Color.green);

                //입금, 선택 상품 필드, 변수 초기화
                selectedItems.setText("");
                selectedItemTotal.setText("0");
                moneyInput.setText("0");
                totalInserted = 0;
                productTotal = 0;

            }
        }


        //잔액 반납 버튼 선택 시
        if (e.getSource() == takeChange)
        {

            takeChange.setVisible(false);

            //거스름돈 프레임 함수 호출
            changeFrameInit();
            changeFrame.setVisible(true);

        }

        //거스름돈 버튼 선택 판별
        for(int i = 0 ; i < nameB.size() ; i++){

            if (e.getSource() == change[i]) {

                //거스름돈 수량 변경
                stockBTemp.set(i, stockBTemp.get(i) - 1);
                changeStock[i].setText(numFormat.format(stockBTemp.get(i)));


                //남은 금액 계산
                double calcChange = Double.parseDouble(CFchangeRemaining.getText());
                calcChange -= Integer.parseInt(nameB.get(i).substring(0, nameB.get(i).indexOf("원")));
                CFchangeRemaining.setText(decFormat.format(calcChange));

                //남은 금액이 요청된 권액 보다 작다면
                if (calcChange < 0) {

                    //선택된 권액 만큼 다시 더한 후 수량 복구, 메시지 출력
                    calcChange += Integer.parseInt(nameB.get(i).substring(0, nameB.get(i).indexOf("원")));
                    CFchangeRemaining.setText(decFormat.format(calcChange));

                    stockBTemp.set(i, stockBTemp.get(i) + 1);
                    changeStock[i].setText(numFormat.format(stockBTemp.get(i)));

                    CFoutputMessage.setText(" 잔액 확인!\n현재 반납금액보다 더 많은 거스름돈을\n선택하셨습니다.");
                    CFoutputMessage.setBackground(Color.red);
                }

                //선택한 권액의 수량이 0이면 해당 권 비활성화 후 메시지 출력
                if (stockBTemp.get(i) == 0) {
                    change[i].setEnabled(false);
                    showMessageDialog(null, nameB.get(i) + "권이 모자랍니다. 다른 권액을 이용해주세요", nameB.get(i) + "권 부족", JOptionPane.WARNING_MESSAGE);
                }

            }
        }


        //거스름돈 선택 완료 버튼 선택 시
        if (e.getSource() == CFfinished)
        {

            //남은 거스름돈
            double calcChange = Double.parseDouble(CFchangeRemaining.getText());

            //거스름돈이 남았을 경우
            if (calcChange > 0)
            {
                //메시지 출력
                CFoutputMessage.setText("확인!\n\n거스름돈 총" + decFormat.format(calcChange) + "을 바꿔 주시기 바랍니다!");
                CFoutputMessage.setBackground(Color.red);
            }

            //남은 거스름돈이 0이면
            if (calcChange == 0)
            {
                //메시지 출력 후 버튼 비 활성화
                CFoutputMessage.setText(" 감사합니다!\n거래 종료 버튼을 눌러서\n자판기로 돌아가시기 바랍니다.");
                CFoutputMessage.setBackground(Color.green);

                CFclose.setVisible(true);

                for (int i = 0 ; i < nameB.size() ; i++){
                    change[i].setEnabled(false);
                }


                CFfinished.setEnabled(false);
            }
        }

        //거스름돈 프레임을 닫을 시
        if (e.getSource() == CFclose)
        {

            //거스름돈 닫기 프레임 함수 호출
            closeChangeFrame();


            //상품과 입금 및 각종 버튼 재 활성화, 상품 및 거스름돈 재고 재 설정
            for(int i = 0 ; i < nameP.size() ; i++){
                product[i].setEnabled(true);
                productStock[i].setText(String.valueOf(stockPTemp.get(i)));
            }

            for(int i = 0 ; i < nameB.size() ; i++){
                bill[i].setEnabled(true);
                change[i].setEnabled(true);
                billStock[i].setText(String.valueOf(stockBTemp.get(i)));
                changeStock[i].setText(String.valueOf(stockBTemp.get(i)));
            }
            enterCoins.setEnabled(true);
            returnCoins.setEnabled(true);
            CFfinished.setEnabled(true);

            outputMessage.setText("");
            outputMessage.setBackground(Color.white);

            stockP = (ArrayList<Integer>) stockPTemp.clone();
            stockB = (ArrayList<Integer>) stockBTemp.clone();

            //주문내역과 재고 DB로 전달
            vdh.setStockDB(nameP, stockP, nameB, stockB, currentDate.getText());
            vdh.madeChoice(sales, nameP, currentDate.getText()+ " " + currentTime.getText());



            //주문내역 초기화
            sales.clear();
            for (int i = 0; i < nameP.size(); i++) {
                sales.add(0);
            }

        }


        //반환 버튼 선택시
        if (e.getSource() == returnCoins)
        {

            //입금 금액이 없을 시
            if (totalInserted == 0)
            {
                //메시지 출력
                outputMessage.setText("돈을 아직 넣지 않았습니다.\n\n돈을 넣어주세요.");
                outputMessage.setBackground(Color.red);

                moneyInput.setText("0");
            }


            //입금 금액이 있을 시
            else
            {
                //메시지 출력
                outputMessage.setText(wonFormat.format(totalInserted) + "을 반납합니다.\n\n다시 돈을 넣어주세요");
                outputMessage.setBackground(Color.red);

                //입금 금액 초기화
                stockBTemp = (ArrayList<Integer>) stockB.clone();
                moneyInput.setText("0");
                totalInserted = 0;
            }
        }

    }
}