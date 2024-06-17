package _02_Chat_Application;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
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

	public void start() {
		try {
			server = new ServerSocket(8080, 100);

			connection = server.accept();
			Scanner scan = new Scanner(System.in);
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();
			// System.out.println("Serverpreconnect");
			while (connection.isConnected()) {
				//if (is.available() != 0) {
				
				//}
				// System.out.println("Serverconnect");
				// try {
				// JOptionPane.showMessageDialog(null, is.readObject());
				// System.out.println(is.readObject());
				
				if(is.available()==0&&bf.ready()) {
					System.out.println("Server: ");
				String message = "";
				
				message = bf.readLine();

				sendMessage(message);
				}//else if(is.available()!=0){
				try {
					System.out.println(is.readObject());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				//}
				// }catch(Exception e) {
				// JOptionPane.showMessageDialog(null, "Connection Lost");
				// System.exit(0);
				// }
			}
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
		// System.out.println("Server: "+message);

		try {
			if (os != null) {
				os.writeObject("Server: " + message);

				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
