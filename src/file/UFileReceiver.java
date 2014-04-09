package file;

import java.io.File;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UFileReceiver {
	private DatagramSocket socketReceive;
	private DatagramPacket packetReceive; 
	
	public UFileReceiver() {
		FileOutputStream fo=null;
		byte[] receiveBytes=new byte[1024];
		try {
			socketReceive=new DatagramSocket(8888);
			packetReceive=new DatagramPacket(receiveBytes, 1024);
			socketReceive.receive(packetReceive);
			String temps=new String(packetReceive.getData(),0,packetReceive.getLength());
			String temp[]=temps.split("  ");
			String fName=temp[0];
			long L=Long.parseLong(temp[1]);	//file size
			String str =fName+ " size: "+ L + " from " + packetReceive.getAddress().getHostAddress() + " : " + packetReceive.getPort() ;
			System.out.println(str);
			
			//set file path
			String filepath="D:"+File.separator+"temp";
			File f=new File(filepath);
			if(!f.exists()){
				f.mkdir();
			}
			//if file exist rename the file
			f=new File(filepath+File.separator+fName);
			while(f.exists()){
				String name[]=f.getName().split("\\.");	//注意"\\."不能为"."
				String filename=name[0];
				for (int i=1;i<name.length-1;i++){
					filename+="."+name[i];
				}
				filename+="_rec."+name[name.length-1];
				f=new File(f.getParent()+File.separator+filename);
				System.out.println(filename);
			}
			
			//receive file
			fo=new FileOutputStream(f);
			long sumL=0;
			while(true){
				packetReceive=new DatagramPacket(receiveBytes, receiveBytes.length);
				socketReceive.receive(packetReceive);
				int len=packetReceive.getLength();
				fo.write(receiveBytes, 0, len);
				fo.flush();
				System.out.println(len);
				sumL+=len;
				if(sumL==L){
					break;
				}
			}
			System.out.println("receive complete!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String args[]){
		new UFileReceiver();
	}

}
