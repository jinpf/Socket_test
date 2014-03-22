import java.io.*;
import java.net.Socket;


public class HelloClient {
	public static void main(String arg[]) throws Exception{
		DataInputStream in=null;
		DataOutputStream out=null;
		
		Socket client=null;
		client=new Socket("localhost",8888);
		in=new DataInputStream(client.getInputStream());
		out=new DataOutputStream(client.getOutputStream());
		
		System.out.println("Server:"+in.readUTF());
		out.writeUTF("Hello,I am client!,I from:"+client.getInetAddress()+":"+client.getLocalPort());
		
		in.close();
		out.close();
		client.close();
	}
}
