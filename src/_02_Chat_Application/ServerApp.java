package _02_Chat_Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
	private int port;
	private ChatApp chatApp;

	private ServerSocket server;
	private Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public ServerApp(int port, ChatApp chatApp) {
		this.port = port;
		this.chatApp = chatApp;
	}

	public void start() {
		try {
			server = new ServerSocket(port, 100);
			connection = server.accept();

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());
			os.flush();

			new Thread(this::listenForMessages).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (IOException e) {
			return "ERROR!";
		}
	}

	public int getPort() {
		return port;
	}

	public void sendMessage(String message) {
		try {
			if (os != null) {
				os.writeObject("Server: " + message);
				os.flush();
				chatApp.displayMessage("You: " + message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void listenForMessages() {
		try {
			while (connection.isConnected()) {
				String message = (String) is.readObject();
				chatApp.displayMessage(message);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
