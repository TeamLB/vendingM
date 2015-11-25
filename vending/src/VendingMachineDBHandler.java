import java.sql.*;


class VendingMachineDBHandler
{
    //Connection order;
    static Statement myStatement;
    ResultSet rs;
    //public Vector<Object> stock = new Vector<Object>();
    public Object[][] Pstock;
    public Object[][] Mstock;
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
    public Object[][] setPstock(){

        String writeString = "select * from pstock";
        System.out.println(writeString);
        int cnt = 0;
        try{
            rs = myStatement.executeQuery(writeString);
            while(rs.next()){
                Pstock[cnt][0] = rs.getString(0);
                Pstock[cnt][1] = rs.getInt(1);
                cnt++;
            }
        }
        catch (SQLException sqle){
            System.out.println(sqle);
        }

        return Pstock;
    }

    public Object[][] setMstock(){

        String writeString = "select * from mstock";
        System.out.println(writeString);
        int cnt = 0;
        try{
            rs = myStatement.executeQuery(writeString);
            while(rs.next()){
                Pstock[cnt][0] = rs.getString(0);
                Pstock[cnt][1] = rs.getInt(1);
                cnt++;
            }
        }
        catch (SQLException sqle){
            System.out.println(sqle);
        }

        return Mstock;
    }

    public void madeChoice(String Product, String Total)
    {

        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(dt);

        System.out.println(Product+"\n"+Total);
        String writeString = "INSERT INTO VendingMachine(Date, Product, Total) VALUES('" + currentTime + "','" + Product + "','"+Total+"')";

        System.out.println(writeString);
        //System.out.println(currentTime);
        try{
            myStatement.executeUpdate(writeString);
        }
        catch (SQLException sqle)
        {
            System.out.println(sqle);
        }
    }


    /*public void main(String productsFW, String totalFW) {
        VendingMachineDBHandler VDB = new VendingMachineDBHandler();
        VDB.madeChoice(productsFW, totalFW);

    }*/
}


