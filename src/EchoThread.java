import java.io.*;
import java.net.Socket;

/**
 * when server accept a client`s request,this thread starts run
 * @author jinpf
 */
public class EchoThread implements Runnable {
	
	private Socket client=null;
	public Boolean server_flag=true;	//stop server socket or not
	
	/**
	 * initialize function
	 * @param client stands for client socket accepted by socket
	 * @author jinpf
	 */
	public EchoThread(Socket client) {
		this.client=client;
	}

	@Override
	public void run() {
		DataInputStream in=null;
		DataOutputStream out=null;
		try{
			in=new DataInputStream(client.getInputStream());
			out=new DataOutputStream(client.getOutputStream());
			String ID=client.getInetAddress()+":"+client.getPort();
			System.out.println("Client from "+ID);
			
			Boolean client_flag=true;
			while(client_flag){
				String str=in.readUTF();
				System.out.println("Client"+"("+ID+")"+str);
				if (str==null||"".equals(str)||"bye".equals(str)){
					out.writeUTF("bye bye!");
					client_flag=false;
				}else if("close".equals(str)){
					out.writeUTF("Server Close!");
					client_flag=false;
					this.server_flag=false;
				}else{
					out.writeUTF("Server receive:"+str);
				}
			}
			
			in.close();
			out.close();
			client.close();
			System.out.println("client"+"("+ID+")"+"quit!");
		}catch(Exception e){
			
		}
		
	}

}
