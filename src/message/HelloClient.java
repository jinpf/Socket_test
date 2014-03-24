package message;
import java.io.*;
import java.net.Socket;


public class HelloClient {
	public static void main(String arg[]) throws Exception{
		DataInputStream in=null;
		DataOutputStream out=null;
		
		Socket client=null;
		client=new Socket("localhost",8888);	//send to 127.0.0.1:8888
		in=new DataInputStream(client.getInputStream());	//bind receive data
		out=new DataOutputStream(client.getOutputStream());		//bind send data
		
		System.out.println("Server:"+in.readUTF());		//receive from server 
		out.writeUTF("Hello,I am client!,I from:"+client.getInetAddress()+":"+client.getLocalPort());	//send to server
		
		in.close();
		out.close();
		client.close();
	}
}
