import java.sql.*;
import java.util.ArrayList;


class VendingMachineDBHandler
{
    //Connection order;
    static Statement myStatement;
    static ResultSet rs;
    //public Vector<Object> stock = new Vector<Object>();
    static int Pcount;
    static int Bcount;
    public VendingMachineDBHandler()
    {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection order = DriverManager.getConnection("jdbc:mysql://localhost:3306/VendingMachine", "root","DB!");
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

            System.out.println("Pcount = " + Pcount + ", Bcount = " + Bcount);
        }
// The following exceptions must be caught
        catch (ClassNotFoundException cnfe)
        {
            System.out.println(cnfe);
        }
        catch (SQLException sqle)
        {
            System.out.println(sqle);
        }
    }


    //public ArrayList<Object> setStock(Integer Pcount, Integer Bcount){
    public ArrayList<Object> setStock(){


        Object[][] Pstock = new Object[Pcount][3];
        Object[][] Bstock = new Object[Bcount][3];


        ArrayList<Object> stock = new ArrayList<Object>();

        String writeString = "select * from pstock";

        int cnt = 0;
        try{
            rs = myStatement.executeQuery(writeString);
            while(rs.next()){
                /*System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));*/

                Pstock[cnt][0] = rs.getString(1);
                Pstock[cnt][1] = Integer.parseInt(String.valueOf(rs.getInt(2)));
                Pstock[cnt][2] = Integer.parseInt(String.valueOf(rs.getInt(3)));
                cnt++;
            }
        }
        catch (SQLException sqle){
            System.out.println(sqle);
        }

        writeString = "select * from bstock";


        cnt = 0;
        try{
            rs = myStatement.executeQuery(writeString);
            while(rs.next()){
                Bstock[cnt][0] = rs.getString(1);
                Bstock[cnt][1] = Integer.parseInt(String.valueOf(rs.getInt(2)));
                cnt++;
            }
        }
        catch (SQLException sqle){
            System.out.println(sqle);
        }


        stock.add(0, Pstock);
        stock.add(1, Bstock);

        return stock;
    }

    /*public void madeChoice(String choice)
    {
        String writeString = "INSERT INTO VendingMachine(Date/Time, Products, Price) VALUES('" + new Date() + "', '" + choice + "', '" + choice + "')";
        try{
            myStatement.executeUpdate(writeString);
        }
        catch (SQLException sqle)
        {
            System.out.println(sqle);
        }
    }*/


}


