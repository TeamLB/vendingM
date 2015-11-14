import java.io.*;
import java.sql.*;
import java.util.Date;
class VendingMachineDBHandler
{
    //Connection order;
    static Statement myStatement;
    public VendingMachineDBHandler()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //String sourceURL = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=VendingMachineDB.mdb;";

            Connection order = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendtest", "root","DB!");
            //String sourceURL = "jdbc:mariadb:"
            //order = DriverManager.getConnection(sourceURL, "admin", "");
            myStatement = order.createStatement();
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
    public void madeChoice(String Product, String Stock, String Total)
    {
        /*System.out.println(Product+"\n"+Stock+"\n"+Total);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //String sourceURL = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=VendingMachineDB.mdb;";

            Connection order = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendtest", "root","DB!");
            //String sourceURL = "jdbc:mariadb:"
            //order = DriverManager.getConnection(sourceURL, "admin", "");
            myStatement = order.createStatement();
        }
// The following exceptions must be caught
        catch (ClassNotFoundException cnfe)
        {
            System.out.println(cnfe);
        }
        catch (SQLException sqle)
        {
            System.out.println(sqle);
        }*/

        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(dt);

        System.out.println(Product+"\n"+Stock+"\n"+Total);
        String writeString = "INSERT INTO VendingMachine(Date, Product, Stock, Total) VALUES('" + currentTime + "', '" + Product + "', '" + Stock + "','"+Total+"')";
        //System.out.println(currentTime);
        try{
            myStatement.executeUpdate(writeString);
        }
        catch (SQLException sqle)
        {
            System.out.println(sqle);
        }
    }
/*
    public static void main(String[] args){

    }
*/

    public void main(String productsFW, String productsST, String totalFW) {
        VendingMachineDBHandler VDB = new VendingMachineDBHandler();
        VDB.madeChoice(productsFW, productsST, totalFW);

    }
}


