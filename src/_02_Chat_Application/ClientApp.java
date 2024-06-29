package _02_Chat_Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientApp {
	private String ip;
	private int port;
	private ChatApp chatApp;

	Socket connection;
	ObjectOutputStream os;
	ObjectInputStream is;

	public ClientApp(String ip, int port, ChatApp chatApp) {
		this.ip = ip;
		this.port = port;
		this.chatApp = chatApp;
	}

	public void start() {
		try {
			connection = new Socket(ip, port);
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());
			os.flush();

			new Thread(this::listenForMessages).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String message) {
		try {
			if (os != null) {
				os.writeObject("Client: " + message);
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
