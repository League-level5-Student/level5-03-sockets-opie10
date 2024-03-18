package _02_Chat_Application;


	

	import java.io.EOFException;
	import java.io.IOException;
	import java.io.ObjectInputStream;
	import java.io.ObjectOutputStream;
	import java.net.InetAddress;
	import java.net.ServerSocket;
	import java.net.Socket;
	import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

	public class ServerApp {
		private int port;

		private ServerSocket server;
		private Socket connection;

		ObjectOutputStream os;
		ObjectInputStream is;

		public ServerApp(int port) {
			this.port = port;
		}

		public void start(){
			try {
				server = new ServerSocket(8080, 100);
				
				connection = server.accept();
				Scanner scan = new Scanner(System.in);
				
				os = new ObjectOutputStream(connection.getOutputStream());
				is = new ObjectInputStream(connection.getInputStream());

				os.flush();
				System.out.println("Serverpreconnect");
				while (connection.isConnected()) {
					System.out.println("Serverconnect");
				//	try {
						//JOptionPane.showMessageDialog(null, is.readObject());
						//System.out.println(is.readObject());
						String message = "";
						System.out.println("Server: \n");
						message = scan.nextLine();
						
						sendMessage(message);
					//}catch(Exception e) {
					//	JOptionPane.showMessageDialog(null, "Connection Lost");
					//	System.exit(0);
					//}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public String getIPAddress() {
			try {
				return InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				return "ERROR!!!!!";
			}
		}

		public int getPort() {
			return port;
		}

		public void sendMessage(String message) {
			//System.out.println("Server: "+message);
			try {
				os = new ObjectOutputStream(connection.getOutputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				is = new ObjectInputStream(connection.getInputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if (os != null) {
					os.writeObject("Message from server:\n"+message);
					System.out.println(message);
					os.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


