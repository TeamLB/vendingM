package level;

import java.io.*;
import java.net.*;

public class MyJavaServer{
	public static int M1;
	public static int M2;
	public static int M3;
	public static int M4;
	public static int Money;	
public static void main(String[] args){
}
class serverStart{
	void S_Start(){
	int port = 4441;
	ServerSocket listenSock = null;
	Socket sock = null;
	
	try {
		listenSock = new ServerSocket(port);
		
		/*while(true){
		
		}*/
		
		sock = listenSock.accept();
		
		BufferedReader br = 
			new BufferedReader(new InputStreamReader(sock.getInputStream()));
		BufferedWriter bw = 
			new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		String line = "";
		while((line = br.readLine()) != null) {
			System.out.println(line);
			bw.write("PHP said: " + line + "\n");
			bw.flush();
		}
		
		bw.close();
		br.close();
		sock.close();
	}catch(IOException ex){
		ex.printStackTrace();
	}
	
	}
}

public void getSend(int m1, int m2, int m3, int m4, int c_Total) {
	serverStart a = new serverStart();
	M1 = m1;
	M2 = m2;
	M3 = m3;
	M4 = m4;
	Money = c_Total;
	a.S_Start();
}

}