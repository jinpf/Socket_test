import java.io.*;
import java.net.*;

public class HelloServer {
	public static void main(String arg[]) throws Exception{
		
		DataInputStream in=null;
		DataOutputStream out=null;
		ServerSocket server=null;
		Socket client=null;
		
		server=new ServerSocket(8888);		//listen on port 8888
		System.out.println("Wait for Client:");
		
		client=server.accept();
		in=new DataInputStream(client.getInputStream());	//bind receive data
		out=new DataOutputStream(client.getOutputStream());		//bind send data
		
		out.writeUTF("Hello,I am Server from:"+server.getInetAddress());	//send to client
		System.out.println("Client:"+in.readUTF());		//receive from client
		
		in.close();
		out.close();
		client.close();
		server.close();
		
	}

}
