package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UFileSender {
	private DatagramSocket socketSend;
	private DatagramPacket packetSend; 
	
	public UFileSender() {
		FileInputStream fi=null;
		byte[] sendBytes=null;
//		Boolean Sflag=false; //send successful or not
		
		//get file
		File f=null;
		Boolean tag=false;
		do{
			if (tag){
				System.out.println("please input legal file path!!!");
			}
			System.out.print("Please input file Path:");
			BufferedReader input = null ;	// receive from keyboard
			input = new BufferedReader(new InputStreamReader(System.in)) ;
			String str;
			try {
				str = input.readLine();
				f=new File(str);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}		// receive data from keyboard
			tag=true;
		}while(!f.exists());
		
		//send file
		try {
			fi=new FileInputStream(f);
			sendBytes=new byte[1024];
			int len=0;	//read file data length at one time
			long L=f.length();
			long sumL=0;
			
			socketSend=new DatagramSocket();
			String str=f.getName()+"  "+f.length();
			packetSend=new DatagramPacket(str.getBytes(), str.length(), InetAddress.getByName("localhost"), 8888);
		
			socketSend.send(packetSend);
			
			while(true){
				len=fi.read(sendBytes, 0, sendBytes.length);
				if(len<0)
					break;
				sumL+=len;
				packetSend=new DatagramPacket(sendBytes, len, InetAddress.getByName("localhost"), 8888);
				socketSend.send(packetSend);
			}
			if(L==sumL)
				System.out.println("send successful!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]){
		new UFileSender();
	}
}
