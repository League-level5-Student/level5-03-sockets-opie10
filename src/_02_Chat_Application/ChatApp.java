package _02_Chat_Application;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatApp extends JFrame {
	JButton button = new JButton("SEND");
	JFrame sjf = new JFrame();
	JPanel sjp = new JPanel();
	JTextArea sjta = new JTextArea();
	JTextField sjtf = new JTextField(20);

	ServerApp server;
	ClientApp client;

	public static void main(String[] args) {
		new ChatApp();
	}

	public ChatApp() {
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!",
				JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			server = new ServerApp(8080, this);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null,
					"Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			setupUI();
			server.start();
		} else {
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new ClientApp(ipStr, port, this);
			setupUI();
			client.start();
		}
	}

	private void setupUI() {
		sjp.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		sjtf.setSize(300, 300);

		sjp.add(sjtf, c);
		c.gridwidth = 3;

		sjta.setSize(300, 100);
		sjta.setLineWrap(true);
		sjta.setWrapStyleWord(true);
		button.addActionListener((e) -> {
			if (server != null) {
				server.sendMessage(sjtf.getText());
			} else {
				client.sendMessage(sjtf.getText());
			}
			sjtf.setText("");
		});

		sjf.setSize(500, 500);
		c.gridx = 3;
		sjp.add(button, c);
		c.gridy = 1;
		c.gridx = 0;

		JScrollPane sjsp = new JScrollPane(sjta);
		sjsp.setPreferredSize(new Dimension(300, 200));
		sjsp.setMinimumSize(new Dimension(300, 200));
		sjsp.setMaximumSize(new Dimension(400, 300));
		sjta.setColumns(10);
		sjsp.setMinimumSize(sjta.getMaximumSize());
		sjsp.setAutoscrolls(true);
		sjp.add(sjsp, c);

		sjf.setTitle("Chat");
		sjf.add(sjp);
		sjf.pack();
		sjf.setVisible(true);

		sjf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		sjf.addWindowListener(new WindowAdapter() {

		});
	}

	public void displayMessage(String message) {
		sjta.append(message + "\n");
	}
}
