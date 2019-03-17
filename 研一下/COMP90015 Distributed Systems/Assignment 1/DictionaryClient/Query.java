import java.awt.EventQueue;
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Query {
	private BufferedReader input = null;
	private PrintWriter output = null;
	private Socket socket = null;
	private StringTokenizer token = null;
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
	
	public Query(Socket sock) {
		socket = sock;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 660, 486);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterWord = new JLabel("Enter word:");
		lblEnterWord.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblEnterWord.setBounds(56, 64, 73, 40);
		frame.getContentPane().add(lblEnterWord);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField.setBounds(139, 68, 214, 33);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnQuery = new JButton("Query");
		btnQuery.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnQuery.setBounds(387, 63, 104, 45);
		frame.getContentPane().add(btnQuery);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 134, 506, 282);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
		try {
			InputStreamReader in = new InputStreamReader(socket.getInputStream());
			input = new BufferedReader(in);
			output = new PrintWriter(socket.getOutputStream());
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String word = textField.getText();
					if ("".equals(word)) {
						JFrame frame_1 = new JFrame();
						frame_1.setBounds(400, 400, 330, 200);
						frame_1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame_1.getContentPane().setLayout(null);
						frame_1.setVisible(true);
						JLabel lblNewLabel = new JLabel("Insert a word.");
						lblNewLabel.setBounds(83, 48, 230, 50);
						frame_1.getContentPane().add(lblNewLabel);
						JButton btnNewButton = new JButton("Yes");
						btnNewButton.setBounds(175, 100, 100, 50);
						frame_1.getContentPane().add(btnNewButton);
						btnNewButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								frame_1.dispose();
							}
						});
					} else {
						output.println("Query嘦" + textField.getText());
						output.flush();
						
						String temp = "";
						StringTokenizer token2 = new StringTokenizer(input.readLine(), "眚", true);
						while (token2.hasMoreTokens()) {
							String temp1 = token2.nextToken();
							if (temp1.equals("眚")) {
								temp = temp + "\n";
							} else {
								temp = temp + temp1;
							}
						}
						
						
						token = new StringTokenizer(temp, "嘦");
						String next = token.nextToken();
						if (next.equals("False123@")) {
							textArea.setText(token.nextToken());
						} else {
							
							int count = token.countTokens();
							String meaning = "1.\n" + next + "\n";
							for (int i = 2; i <= count + 1; i++) {
								meaning = meaning + i + ".\n" + token.nextToken() + "\n";
							}
							textArea.setText(meaning);
						}
					}
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
				} catch (NullPointerException e4) {
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
