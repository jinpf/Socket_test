package message;
import java.io.*;
import java.net.*;


public class EchoServer {
	public static void main(String arg[]) throws Exception{
		DataInputStream in=null;
		DataOutputStream out=null;
		ServerSocket server=null;
		Socket client=null;		
			
		server=new ServerSocket(8888);
		
		Boolean Flags=true;		//server listen socket close or not
		while(Flags){
			System.out.println("**************************");
			System.out.println("Wait for Client:");
			
			client=server.accept();
			System.out.println("Client from"+client.getInetAddress()+":"+client.getPort());
			in=new DataInputStream(client.getInputStream());
			out=new DataOutputStream(client.getOutputStream());
			
			Boolean Flag=true;
			while(Flag){
				while(Flag){
					String str=in.readUTF();
					System.out.println("Client:"+str);
					if (str==null||"".equals(str)||"bye".equals(str)){
						//when receive "" or "bye" client quit
						out.writeUTF("bye bye!");
						Flag=false;
					}else if("close".equals(str)){
						//when receive "close" server close
						out.writeUTF("Server Close!");
						Flag=false;
						Flags=false;
					}else{
						out.writeUTF("Server receive:"+str);
					}
				}
			}
			
			in.close();
			out.close();
			client.close();
			System.out.println("client quit!");
		}
		
		server.close();
		
		System.out.println("Server shutdown!");
	}
}
