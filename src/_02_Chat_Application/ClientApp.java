package _02_Chat_Application;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ClientApp {
	private String ip;
	private int port;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;
	
	public ClientApp(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void start(){
		try {

			connection = new Socket(ip, port);
			Scanner scan = new Scanner(System.in);
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			os.flush();
			
			
		
			
		while (connection.isConnected()) {
			//if(is.available()!=0) {
			
			//}
				
				//JOptionPane.showMessageDialog(null, is.readObject());
				//System.out.println(is.readObject());
			
			if(is.available()==0&&bf.ready()) {
				System.out.println("Client: ");
					String message ="";
				
				
				message = bf.readLine();
				sendMessage(message);
			}else if(is.available()!=0) {
				try {
					System.out.println(is.readObject());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				}
			
		}
		}
			 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	public void sendMessage(String message) {
		
		//System.out.println("Client: "+message);
		try {
			if (os != null) {
				os.writeObject("Client: "+message);
//				try {
//	//				System.out.println(is.readObject());
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
