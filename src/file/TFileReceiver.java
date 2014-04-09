package file;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * file transfer server with no GUI
 * @author jinpf
 *
 */
public class TFileReceiver {

	public static void main(String[] args) {
		try{
			final ServerSocket server=new ServerSocket(8888);
			System.out.println("Server start,wait for Client...");
			//using multiThreading to handle multiClient
			Thread t=new Thread(
					
					new Runnable(){
						public void run(){
							while(true){
								try{
									Socket client=server.accept();
									Receivefile(client);
								}catch(Exception e){
									System.out.println("Server exception!");
									e.printStackTrace();
								}	
							}				
						}						
					}
					
					);
			
			//start thread
			t.run();
			server.close();
		}catch(Exception e){
			e.printStackTrace();		
		}

	}
	
	/**
	 * receive file from client,file path at D:/temp/
	 * @param client
	 * @author jinpf
	 */
	protected static void Receivefile(Socket client) {
		DataInputStream di=null;
		FileOutputStream fo=null;
		byte[] inputByte=null;
		int len=0;	//receive data length at one time
		
		try{
			
			try{
				di=new DataInputStream(client.getInputStream());
				
				String ID=client.getInetAddress()+":"+client.getPort();
				String filename=di.readUTF();
				System.out.println("start receive "+ID+"--"+filename);
				
				//set file path
				String filepath="D:"+File.separator+"temp";
				File f=new File(filepath);
				if(!f.exists()){
					f.mkdir();
				}
				//if file exist rename the file
				f=new File(filepath+File.separator+filename);
				while(f.exists()){
					String name[]=f.getName().split("\\.");	//注意"\\."不能为"."
					String fname=name[0];
					for (int i=1;i<name.length-1;i++){
						fname+="."+name[i];
					}
					fname+="_rec."+name[name.length-1];
					f=new File(f.getParent()+File.separator+fname);
//					System.out.println(fname);
				}
				
				//receive file
				fo=new FileOutputStream(f);
				inputByte=new byte[1024];
				while( (len=di.read(inputByte, 0, inputByte.length)) >0){
					fo.write(inputByte,0,len);
					fo.flush();
				}
				System.out.println(ID+"--"+filename+" receive complete!");	
			}finally{
				if(fo!=null){
					fo.close();
				}
				if(di!=null){
					di.close();
				}
				if(client!=null){
					client.close();
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
