import javax.crypto.spec.PSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;


class VendingMachineDBHandler
{
    //Connection order;
    static Statement myStatement;
    static ResultSet rs;
    //public Vector<Object> stock = new Vector<Object>();
    static int Pcount;
    static int Mcount;
    public VendingMachineDBHandler()
    {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection order = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendtest", "root","DB!");
            myStatement = order.createStatement();
            String writeString = "select count(*) from pstock";
            rs = myStatement.executeQuery(writeString);
            while(rs.next()) {
                Pcount = rs.getInt(1);
            }

            writeString = "select count(*) from mstock";
            rs = myStatement.executeQuery(writeString);
            while(rs.next()) {
                Mcount = rs.getInt(1);
            }

            System.out.println("Pcount = " + Pcount + ", Mcount = " + Mcount);
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


    //public ArrayList<Object> setStock(Integer Pcount, Integer Mcount){
    public ArrayList<Object> setStock(){


        Object[][] Pstock = new Object[Pcount][2];
        Object[][] Mstock = new Object[Mcount][2];
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
                cnt++;
            }
        }
        catch (SQLException sqle){
            System.out.println(sqle);
        }

        writeString = "select * from mstock";


        cnt = 0;
        try{
            rs = myStatement.executeQuery(writeString);
            while(rs.next()){
                Mstock[cnt][0] = rs.getString(1);
                Mstock[cnt][1] = Integer.parseInt(String.valueOf(rs.getInt(2)));
                cnt++;
            }
        }
        catch (SQLException sqle){
            System.out.println(sqle);
        }


        stock.add(0, Pstock);
        stock.add(1, Mstock);

        return stock;
    }


}


