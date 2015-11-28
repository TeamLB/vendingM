import java.sql.*;
import java.util.ArrayList;


class VendingMachineDBHandler
{

    static Statement myStatement;
    static ResultSet rs;

    //상품, 권액별 항목 갯수
    static int Pcount;
    static int Bcount;

    /*
      생성자
      객체 생성시 DB연결
    */
    public VendingMachineDBHandler()
    {
        //DB연결 후 각 항목별 갯수를 전역 변수에 대입
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection order = DriverManager.getConnection("jdbc:mysql://jycom.asuscomm.com:3306/VendingMachine", "vending","vending");
            myStatement = order.createStatement();
            String writeString = "select count(*) from pstock";
            rs = myStatement.executeQuery(writeString);
            while(rs.next()) {
                Pcount = rs.getInt(1);
            }

            writeString = "select count(*) from bstock";
            rs = myStatement.executeQuery(writeString);
            while(rs.next()) {
                Bcount = rs.getInt(1);
            }
        }
        //에러 메시지 출력
        catch (ClassNotFoundException cnfe)
        {
            System.out.println(cnfe);
        }
        catch (SQLException sqle)
        {
            System.out.println(sqle);
        }
    }


    //초기 재고 설정
    public ArrayList<Object> setStock(){


        //상품, 권액 별 재고 배열
        Object[][] Pstock = new Object[Pcount][4];
        Object[][] Bstock = new Object[Bcount][4];


        //재고값을 리턴 해줄 ArrayList
        ArrayList<Object> stock = new ArrayList<Object>();

        //상품 정보 쿼리문
        String writeString = "select * from pstock";

        //배열의 인덱스 값 선택을 위한 변수
        int cnt = 0;
        try{
            //쿼리 실행
            rs = myStatement.executeQuery(writeString);
            while(rs.next()){
                //리턴 받은 상품의 이름과 가격, 재고를 배열에 저장
                Pstock[cnt][0] = rs.getString(1);
                Pstock[cnt][1] = Integer.parseInt(String.valueOf(rs.getInt(3)));
                Pstock[cnt][2] = Integer.parseInt(String.valueOf(rs.getInt(4)));
                Pstock[cnt][3] = Integer.parseInt(String.valueOf(rs.getInt(2)));
                cnt++;
            }
        }
        catch (SQLException sqle){
            System.out.println(sqle);
        }

        //권액 정보 쿼리문
        writeString = "select * from bstock";

        //인덱스 값 초기화
        cnt = 0;
        try{

            //쿼리 실행
            rs = myStatement.executeQuery(writeString);
            while(rs.next()){
                //리턴 받은 권액별 이름과 수량 배열에 저장
                Bstock[cnt][0] = rs.getString(1);
                Bstock[cnt][1] = Integer.parseInt(String.valueOf(rs.getInt(3)));
                Bstock[cnt][2] = Integer.parseInt(String.valueOf(rs.getInt(2)));
                cnt++;
            }
        }
        catch (SQLException sqle){
            System.out.println(sqle);
        }

        //ArrayList에 상품과 권액별 배열을 더함
        stock.add(0, Pstock);
        stock.add(1, Bstock);

        return stock;
    }


    //DB에 재고 및 수량 저장
    public void setStockDB(ArrayList<String> product, ArrayList<Integer> stockP, ArrayList<String> bills, ArrayList<Integer> stockB, String date){
        String writeString = "";

        //상품별 재고 저장
        for(int i = 0 ; i < product.size() ; i++) {
            writeString = "update pstock set Pstock = '" + stockP.get(i) + "' where Pname = '"+product.get(i) + "'";
            try{
                System.out.println(writeString);
                myStatement.executeUpdate(writeString);
            }catch (SQLException sqle){
                System.out.println(sqle);
            }
        }


        //권액별 수량 저장
        for (int i = 0 ; i < bills.size() ; i++){
            writeString = "update bstock set Bstock = '" + stockB.get(i) + "' where Bname = '"+bills.get(i) + "'";
            try{
                myStatement.executeUpdate(writeString);
                System.out.println(writeString);
            }catch (SQLException sqle){
                System.out.println(sqle);
            }
        }
    }


    //DB에 주문내역 저장
    public void madeChoice(ArrayList<Integer> sold, ArrayList<String> product, String date)
    {
        String writeString;
        for(int i = 0 ; i < sold.size() ; i++){
            if(sold.get(i) != 0) {
                writeString = "INSERT INTO salesvolume VALUES('" + product.get(i) + "', '" + sold.get(i) + "', '" + date + "')";
                try{
                    myStatement.executeUpdate(writeString);
                    System.out.println(writeString);
                }
                catch (SQLException sqle)
                {
                    System.out.println(sqle);
                }
            }
        }

    }


}


