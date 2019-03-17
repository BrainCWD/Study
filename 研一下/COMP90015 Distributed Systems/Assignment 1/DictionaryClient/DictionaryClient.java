import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DictionaryClient {
	private String serveraddress = null;
	private int serverport = 0;
	private ClientGui gui = null;
	
	public DictionaryClient(String serverip, int serverportnumber) {
		serveraddress = serverip;
		serverport = serverportnumber;
	}
	
	private void go() {
		try {
			Socket socket = new Socket(serveraddress, serverport);
			gui = new ClientGui(socket);
			gui.run();
			
		} catch (IOException e) {
			JFrame frame = new JFrame();
			frame.setBounds(400, 400, 505, 250);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.setVisible(true);
			JLabel lblNewLabel;
			lblNewLabel = new JLabel("Connection failed. Please enter the correct server address and port number.");
			lblNewLabel.setBounds(18, 50, 490, 50);
			frame.getContentPane().add(lblNewLabel);
			JButton btnNewButton = new JButton("Yes");
			btnNewButton.setBounds(202, 100, 100, 50);
			frame.getContentPane().add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);  
				}
			});
		}
	}
	
	public static void main(String[] args) {
		
		if(args.length != 2) {
			JFrame frame = new JFrame();
			frame.setBounds(400, 400, 450, 250);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.setVisible(true);
			JLabel lblNewLabel;
			lblNewLabel = new JLabel("Please enter the correct server address and server port ^_^");
			lblNewLabel.setBounds(60, 50, 400, 50);
			frame.getContentPane().add(lblNewLabel);
			JButton btnNewButton = new JButton("Click me!");
			btnNewButton.setBounds(175, 100, 100, 50);
			frame.getContentPane().add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);  
				}
			});
		} else {
			try {
				DictionaryClient client = new DictionaryClient(args[0], Integer.parseInt(args[1]));
				client.go();
			} catch (NumberFormatException e) {
				JFrame frame = new JFrame();
				frame.setBounds(400, 400, 450, 250);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				frame.setVisible(true);
				JLabel lblNewLabel;
				lblNewLabel = new JLabel("Please enter the correct server address and server port ^_^");
				lblNewLabel.setBounds(60, 50, 400, 50);
				frame.getContentPane().add(lblNewLabel);
				JButton btnNewButton = new JButton("Click me!");
				btnNewButton.setBounds(175, 100, 100, 50);
				frame.getContentPane().add(btnNewButton);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);  
					}
				});
			}
			
		}
	}
	
}
