import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.net.*;
import java.io.*;

public class ClientGui {
	private Socket socket = null;
	private JFrame frame;
	private PrintWriter output = null;

	/**
	 * Launch the application.
	 */
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public ClientGui(Socket sock) {
		this.socket = sock;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 806, 488);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					output = new PrintWriter(socket.getOutputStream());
					output.println("Disconnection嘦-1");
					output.flush();
					socket.close();
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblWelcomeToOnline = new JLabel("Welcome to Online Dictionary!");
		lblWelcomeToOnline.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		lblWelcomeToOnline.setBounds(160, 59, 453, 63);
		frame.getContentPane().add(lblWelcomeToOnline);
		
		JButton btnQuery = new JButton("Query");
		btnQuery.setBackground(Color.WHITE);
		btnQuery.setForeground(Color.BLACK);
		btnQuery.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnQuery.setBounds(96, 207, 111, 57);
		frame.getContentPane().add(btnQuery);
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Query query = new Query(socket);
				query.run();
			}
		});
		
		JButton btnAdd = new JButton("Insertion");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addition addition = new Addition(socket);
				addition.run();
			}
		});
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setBounds(339, 207, 111, 57);
		frame.getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Deletion");
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(588, 207, 111, 57);
		frame.getContentPane().add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Deletion deletion = new Deletion(socket);
				deletion.run();
			}
		});
		
		JButton btnClickMe = new JButton("Click me ! ^_^");
		btnClickMe.setForeground(Color.BLACK);
		btnClickMe.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnClickMe.setBackground(Color.WHITE);
		btnClickMe.setBounds(603, 348, 155, 30);
		frame.getContentPane().add(btnClickMe);
		btnClickMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame_1 = new JFrame();
				frame_1.setBounds(200, 200, 500, 388);
				frame_1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame_1.getContentPane().setLayout(null);
				frame_1.setVisible(true);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(29, 29, 443, 270);
				frame_1.getContentPane().add(scrollPane);
				
				JTextArea t = new JTextArea();
				t.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				t.setEditable(false);
				scrollPane.setViewportView(t);
				t.setLineWrap(true);
				t.setText("Welcome to Online Dictionary!\n\nYou can click three buttons to query, insert, or delete "
						+ "a word.\nTips:\n1. You cannot query, insert, or delete an empty word.\n"
						+ "2. When you are inserting a new word, the meaning cannot be empty, but you can add "
						+ "more meanings as you wish~\n\nEnjoy your study ^_^");
				
				JButton btnNewButton = new JButton("Yes");
				btnNewButton.setBounds(322, 310, 117, 29);
				frame_1.getContentPane().add(btnNewButton);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame_1.dispose();;  
					}
				});
			}
		});
		
		JLabel lblForMoreInformation = new JLabel("For more information:");
		lblForMoreInformation.setBounds(462, 353, 138, 16);
		frame.getContentPane().add(lblForMoreInformation);
	}
}
