import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.io.*;
import java.net.*;
import java.awt.Font;

public class Deletion {
	private Socket socket = null;
	private BufferedReader input = null;
	private PrintWriter output = null;
	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Deletion(Socket sock) {
		this.socket = sock;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterTheWord = new JLabel("Enter the word:");
		lblEnterTheWord.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblEnterTheWord.setBounds(53, 48, 111, 32);
		frame.getContentPane().add(lblEnterTheWord);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnDelete.setBounds(305, 113, 117, 29);
		frame.getContentPane().add(btnDelete);
		
		textField = new JTextField();
		textField.setBounds(97, 106, 191, 41);
		frame.getContentPane().add(textField);
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField.setColumns(10);
		
		try {
			InputStreamReader in = new InputStreamReader(socket.getInputStream());
			input = new BufferedReader(in);
			output = new PrintWriter(socket.getOutputStream());
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("".equals(textField.getText())) {
					JFrame frame_2 = new JFrame();
					frame_2.setBounds(400, 400, 330, 200);
					frame_2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame_2.getContentPane().setLayout(null);
					frame_2.setVisible(true);
					JLabel lblNewLabel = new JLabel("Insert a word.");
					lblNewLabel.setBounds(83, 48, 230, 50);
					frame_2.getContentPane().add(lblNewLabel);
					JButton btnNewButton = new JButton("Yes");
					btnNewButton.setBounds(175, 100, 100, 50);
					frame_2.getContentPane().add(btnNewButton);
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame_2.dispose();
						}
					});
				} else {
					JFrame frame_1 = new JFrame();
					frame_1.setBounds(400, 400, 330, 200);
					frame_1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame_1.getContentPane().setLayout(null);
					frame_1.setVisible(true);
					
					JLabel lblToDelete = new JLabel("Are you sure to delete?");
					lblToDelete.setBounds(83, 35, 230, 50);
					frame_1.getContentPane().add(lblToDelete);
					
					JButton btnYes = new JButton("Yes");
					btnYes.setBounds(65,100,80,50);
					frame_1.getContentPane().add(btnYes);
					
					JButton btnNo = new JButton("No");
					btnNo.setBounds(175,100,80,50);
					frame_1.getContentPane().add(btnNo);
					
					btnNo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame_1.dispose();
						}
					});
					
					
					btnYes.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame_1.dispose();
							output.println("Deletion嘦" + textField.getText());
							output.flush();
							String answer;
							try {
								answer = input.readLine();
								JFrame frame_2 = new JFrame();
								frame_2.setBounds(400, 400, 330, 200);
								frame_2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								frame_2.getContentPane().setLayout(null);
								frame_2.setVisible(true);
								JLabel lblNewLabel = new JLabel(answer);
								lblNewLabel.setBounds(83, 48, 230, 50);
								frame_2.getContentPane().add(lblNewLabel);
								JButton btnNewButton = new JButton("Yes");
								btnNewButton.setBounds(175, 100, 100, 50);
								frame_2.getContentPane().add(btnNewButton);
								btnNewButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										frame_2.dispose();
									}
								});
							} catch (SocketException e4) {
								JFrame frame = new JFrame();
								frame.setBounds(400, 400, 450, 250);
								frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								frame.getContentPane().setLayout(null);
								frame.setVisible(true);
								JLabel lblNewLabel;
								lblNewLabel = new JLabel("Oops, your connection was interrupted!");
								lblNewLabel.setBounds(60, 50, 400, 50);
								frame.getContentPane().add(lblNewLabel);
								JButton btnNewButton = new JButton("Yes");
								btnNewButton.setBounds(175, 100, 70, 50);
								frame.getContentPane().add(btnNewButton);
								btnNewButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										System.exit(0);  
									}
								});
							} catch (IOException e3) {
								e3.printStackTrace();
							}
						}
					});
				}	
			}
		});
	}

}
