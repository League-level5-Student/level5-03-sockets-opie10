package _02_Chat_Application;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

			os.flush();
			
			
		
			System.out.println("Client - PreConnect");
		while (connection.isConnected()) {
			System.out.println("Client - Connect");
				//JOptionPane.showMessageDialog(null, is.readObject());
				//System.out.println(is.readObject());
				String message ="";
				System.out.println("Client: \n");
				message = scan.nextLine();
				sendMessage(message);
		}
		}
			 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void sendMessage(String message) {
		System.out.println(os.equals(null));
		System.out.println("Client: "+message);
		try {
			if (os != null) {
				os.writeObject("Message from Client:\n"+message);
				try {
					System.out.println(is.readObject());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
