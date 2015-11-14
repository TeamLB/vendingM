import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Date;


public class VendingMachinePHPconn
{


    private static Socket socket;

    public VendingMachinePHPconn(String writeString) throws IOException {

        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("127.0.0.1",20222);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        sentence = inFromUser.readLine();
        outToServer.writeBytes(writeString + '\n');
        modifiedSentence = inFromServer.readLine();
        //System.out.println(modifiedSentence);
        System.out.println(sentence);



        System.out.println("서버 주소 : " + socket.getInetAddress().getHostName());

        System.out.println("서버 포트 : " + socket.getPort());

        System.out.println("클라이언트 주소 : " + socket.getLocalAddress().getHostName());

        System.out.println("클라이언트 포트 : " + socket.getLocalPort());

        clientSocket.close();
    }

        /* try
        {

            int port = 20222;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started and listening to the port");

            //Server is running always. This is done using this while(true) loop
            //while(true)
            //{
                //Reading the message from the client
                socket = serverSocket.accept();
            socket.setSoLinger( true , 0 ); // TIME_WAIT 줄이기

            socket.setReuseAddress(true);
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String number = br.readLine();
                System.out.println("Message received from client is "+number);

                //Multiplying the number by 2 and forming the return message
                String returnMessage;
                *//*try
                {
                    int numberInIntFormat = Integer.parseInt(number);
                    int returnValue = numberInIntFormat*2;
                    returnMessage = String.valueOf(returnValue) + "\n";
                }
                catch(NumberFormatException e)
                {
                    //Input was not a number. Sending proper message back to client.
                    returnMessage = "Please send a proper number\n";
                }*//*
                returnMessage = writeString+"\n";
                //Sending the response back to the client.
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(returnMessage);
                System.out.println("Message sent to the client is "+returnMessage);
                bw.flush();
                bw.close();
                br.close();
                socket.close();
            //}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }*/


    public static void main(String[] args)
    {
        /*try
        {

            int port = 20222;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started and listening to the port");

            //Server is running always. This is done using this while(true) loop
            while(true)
            {
                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String number = br.readLine();
                System.out.println("Message received from client is "+number);

                //Multiplying the number by 2 and forming the return message
                String returnMessage;
                *//**//*try
                {
                    int numberInIntFormat = Integer.parseInt(number);
                    int returnValue = numberInIntFormat*2;
                    returnMessage = String.valueOf(returnValue) + "\n";
                }
                catch(NumberFormatException e)
                {
                    //Input was not a number. Sending proper message back to client.
                    returnMessage = "Please send a proper number\n";
                }*//**//*
                returnMessage = "Are You Listening?\n";
                //Sending the response back to the client.
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(returnMessage);
                System.out.println("Message sent to the client is "+returnMessage);
                bw.flush();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }*/
    }
}