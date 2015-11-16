import java.io.*;
import java.sql.*;
import java.util.Date;
class VendingMachineDBHandler
{
    Connection order;
    Statement myStatement;
    public VendingMachineDBHandler()
    {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String sourceURL = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=VendingMachineDB.mdb;";
        order = DriverManager.getConnection(sourceURL, "admin", "");
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
    public void madeChoice(String choice)
    {
        String writeString = "INSERT INTO VendingMachine(Date/Time, Products, Price) VALUES('" + new Date() + "', '" + choice + "', '" + choice + "')";
        try{
            myStatement.executeUpdate(writeString);
        }
        catch (SQLException sqle)
        {
            System.out.println(sqle);
        }
    }
}