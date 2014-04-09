package file;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * file transfer client with no GUI
 * @author jinpf
 * 
 */
public class TFileSender {

	public static void main(String[] args) {
		Socket client=null;
		DataOutputStream dout=null;
		FileInputStream fi=null;
		byte[] sendBytes=null;
		int len=0;	//read file data length at one time
		Boolean Sflag=false; //send successful or not 
		
		try {
			
			File f=null;
			Boolean tag=false;
			do{
				if (tag){
					System.out.println("please input legal file path!!!");
				}
				System.out.print("Please input file Path:");
				BufferedReader input = null ;	// receive from keyboard
				input = new BufferedReader(new InputStreamReader(System.in)) ;
				String str = input.readLine() ;		// receive data from keyboard
				f=new File(str);
				tag=true;
			}while(!f.exists());
			
			//send file
			try{
				client=new Socket("localhost",8888);
				dout=new DataOutputStream(client.getOutputStream());
				fi=new FileInputStream(f);
				sendBytes=new byte[1024];
				long L=f.length();
				long sumL=0;
				
				//send file name
				dout.writeUTF(f.getName());
				//send file
				while( (len=fi.read(sendBytes, 0, sendBytes.length))>0 ){
					dout.write(sendBytes, 0, len);
					dout.flush();
					sumL+=len;
					System.out.println("send "+(sumL/L*100)+"%");
				}
				if(sumL==L)
					Sflag=true;
			}catch(Exception e){
				System.out.println("client send failure...");
				Sflag=false;
				e.printStackTrace();
			}finally{
				if(fi!=null){
					fi.close();
				}
				if(dout!=null){
					dout.close();
				}
				if(client!=null){
					client.close();
				}
			}
	
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		System.out.println("File send "+(Sflag?"success!":"failure..."));
	}

}
