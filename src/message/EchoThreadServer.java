package message;
import java.net.*;


public class EchoThreadServer {

	public static void main(String[] args) throws Exception {
		ServerSocket server=null;
		Socket client=null;
		Boolean flag=true;
		
		server=new ServerSocket(8888);
		
		while(flag){
			System.out.println("Server start,wait for client:");
			client=server.accept();
			new Thread(new EchoThread(client)).start();
		}
		server.close();
	}

}
