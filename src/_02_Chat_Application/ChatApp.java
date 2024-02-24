package _02_Chat_Application;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import _02_Chat_Application.ClientApp;
import _02_Chat_Application.ServerApp;

public class ChatApp extends JFrame {
	JButton button = new JButton("CLICK");
	
	ServerApp server;
	ClientApp client;
	
	
	public static void main(String[] args) {
		new ChatApp();
	}
	
	public ChatApp(){
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			
			server = new ServerApp(8080);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			String message = "";
			message =JOptionPane.showInputDialog("Input message here:");
			
			server.sendMessage(message);
			
			server.start();
			
		}else{
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new ClientApp(ipStr, port);
			String message = "";
			message =JOptionPane.showInputDialog("Input message here:");
			
			client.sendMessage(message);
			
			client.start();
			
		}
	}
}