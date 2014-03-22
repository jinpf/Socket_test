import java.io.*;
import java.net.*;


public class EchoClient {
	public static void main(String arg[]) throws Exception{
		DataInputStream in=null;
		DataOutputStream out=null;
		Socket client=null;
		Boolean flag=true;
		
		client=new Socket("localhost",8888);
		in =new DataInputStream(client.getInputStream());
		out=new DataOutputStream(client.getOutputStream());
		while(flag){
			System.out.print("Client input:");
			BufferedReader input = null ;	// receive from keyboard
			input = new BufferedReader(new InputStreamReader(System.in)) ;
			String str = input.readLine() ;		// receive data from keyboard
			out.writeUTF(str);	//send data to server
			System.out.println("Server:"+in.readUTF());
			if (str==null||"".equals(str)||"bye".equals(str)||"close".equals(str))
				//when type "" "bye" "close" quit
				flag=false;
					
		}
		in.close();
		out.close();
		client.close();
	}
}
