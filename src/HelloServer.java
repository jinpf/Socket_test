import java.io.*;
import java.net.*;

public class HelloServer {
	public static void main(String arg[]) throws Exception{
		
		DataInputStream in=null;
		DataOutputStream out=null;
		ServerSocket server=null;
		Socket client=null;
		
		server=new ServerSocket(8888);
		System.out.println("等待客户端连接：");
		
		client=server.accept();
		in=new DataInputStream(client.getInputStream());
		out=new DataOutputStream(client.getOutputStream());
		
		out.writeUTF("Hello,I am Server from:"+server.getInetAddress());
		System.out.println("Client:"+in.readUTF());
		
		in.close();
		out.close();
		client.close();
		server.close();
		
	}

}
