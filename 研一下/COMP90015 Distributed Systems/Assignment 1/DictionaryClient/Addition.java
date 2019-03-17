import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import java.net.*;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class Addition {
	private BufferedReader input = null;
	private PrintWriter output = null;
	private Socket socket = null;
	private String meaning = "";
	private JFrame frame;
	private JTextField textField;
	private StringTokenizer token = null;
	private String word = "";

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
	
	public Addition(Socket sock) {
		socket = sock;
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {
			InputStreamReader in = new InputStreamReader(socket.getInputStream());
			input = new BufferedReader(in);
			output = new PrintWriter(socket.getOutputStream());
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		
		frame = new JFrame();
		frame.setBounds(200, 200, 425, 239);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterTheWord = new JLabel("Enter the word:");
		lblEnterTheWord.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblEnterTheWord.setBounds(31, 38, 109, 41);
		frame.getContentPane().add(lblEnterTheWord);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField.setBounds(84, 80, 190, 41);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					word = textField.getText();
					meaning = "";
					if (word.equals("")) {
						JFrame frame_6 = new JFrame();
						frame_6.setBounds(400, 400, 330, 200);
						frame_6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame_6.getContentPane().setLayout(null);
						frame_6.setVisible(true);
						JLabel lblNewLabel = new JLabel("Insert a word.");
						lblNewLabel.setBounds(83, 48, 230, 50);
						frame_6.getContentPane().add(lblNewLabel);
						JButton btnNewButton = new JButton("Yes");
						btnNewButton.setBounds(175, 100, 100, 50);
						frame_6.getContentPane().add(btnNewButton);
						btnNewButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								frame_6.dispose();
							}
						});
					} else {
						output.println("Check嘦" + word);
						output.flush();
						String answer = input.readLine();
						if (answer.equals("ok")) {
							JFrame frame_1 = new JFrame();
							frame_1.setBounds(300, 300, 730, 567);
							frame_1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							frame_1.getContentPane().setLayout(null);
							frame_1.setVisible(true);
							
							JLabel lblYourWordIs = new JLabel("Your word is:");
							lblYourWordIs.setFont(new Font("Times New Roman", Font.PLAIN, 15));
							lblYourWordIs.setBounds(32, 33, 144, 28);
							frame_1.getContentPane().add(lblYourWordIs);
							
							
							
							
							JScrollPane scrollPane_1 = new JScrollPane();
							scrollPane_1.setBounds(277, 60, 429, 447);
							frame_1.getContentPane().add(scrollPane_1);
							
							JTextArea textArea = new JTextArea();
							textArea.setEditable(false);
							textArea.setLineWrap(true);
							textArea.setFont(new Font("Times New Roman", Font.PLAIN, 15));
							scrollPane_1.setViewportView(textArea);
							
							JButton btnAddMeaning = new JButton("Add meaning");
							btnAddMeaning.setFont(new Font("Times New Roman", Font.PLAIN, 15));
							btnAddMeaning.setBounds(62, 153, 144, 49);
							frame_1.getContentPane().add(btnAddMeaning);
							
							JButton btnFinish = new JButton("Finish");
							btnFinish.setFont(new Font("Times New Roman", Font.PLAIN, 15));
							btnFinish.setBounds(62, 430, 144, 49);
							frame_1.getContentPane().add(btnFinish);
							
							
							JTextField textField_1 = new JTextField();
							textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
							textField_1.setBounds(42, 62, 206, 40);
							frame_1.getContentPane().add(textField_1);
							textField_1.setEditable(false);
							textField_1.setColumns(10);
							textField_1.setText(word);
							
							btnAddMeaning.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									JFrame frame_3 = new JFrame();
									frame_3.setBounds(400, 400, 475, 328);
									frame_3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
									frame_3.getContentPane().setLayout(null);
									frame_3.setVisible(true);
									
									JButton btnFinish = new JButton("Finish");
									btnFinish.setFont(new Font("Times New Roman", Font.PLAIN, 15));
									btnFinish.setBounds(313, 250, 119, 35);
									frame_3.getContentPane().add(btnFinish);
									
									JScrollPane scrollPane_3 = new JScrollPane();
									scrollPane_3.setBounds(39, 31, 397, 197);
									frame_3.getContentPane().add(scrollPane_3);
									
									JTextArea textArea1 = new JTextArea();
									scrollPane_3.setViewportView(textArea1);
									textArea1.setLineWrap(true);
									textArea1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
									textArea1.append("");
									
									btnFinish.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											if ("".equals(textArea1.getText())) {
												JFrame frame_4 = new JFrame();
												frame_4.setBounds(450, 450, 330, 200);
												frame_4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
												frame_4.getContentPane().setLayout(null);
												frame_4.setVisible(true);
												JLabel lblNewLabel = new JLabel("Meaning is empty!");
												lblNewLabel.setBounds(83, 48, 230, 50);
												frame_4.getContentPane().add(lblNewLabel);
												JButton btnNewButton = new JButton("Yes");
												btnNewButton.setBounds(175, 100, 100, 50);
												frame_4.getContentPane().add(btnNewButton);
												btnNewButton.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														frame_4.dispose();
													}
												});
											} else {
												meaning = meaning + textArea1.getText() + "嘦";
												token = new StringTokenizer(meaning, "嘦");
												int count = token.countTokens();
												textArea.setText("");
												for (int i = 1; i <= count; i++) {
													textArea.append(i + ".\n" + token.nextToken() + "\n");
												}
												frame_3.dispose();
											}
										}
									});
									
								}
							});
							
							btnFinish.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									frame_1.dispose();
									if ("".equals(meaning)) {
										JFrame frame_5 = new JFrame();
										frame_5.setBounds(400, 400, 330, 200);
										frame_5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
										frame_5.getContentPane().setLayout(null);
										frame_5.setVisible(true);
										JLabel lblNewLabel = new JLabel("Addition failed! Add meaning, please.");
										lblNewLabel.setBounds(53, 48, 330, 50);
										frame_5.getContentPane().add(lblNewLabel);
										JButton btnNewButton = new JButton("Yes");
										btnNewButton.setBounds(175, 100, 100, 50);
										frame_5.getContentPane().add(btnNewButton);
										btnNewButton.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												frame_5.dispose();
											}
										});
									} else {
										try {
											String temp = "";
											StringTokenizer token2 = new StringTokenizer(meaning, "\n", true);
											while (token2.hasMoreTokens()) {
												String temp1 = token2.nextToken();
												if (temp1.equals("\n")) {
													temp = temp + "眚";
												} else {
													temp = temp + temp1;
												}
											}
											
											String out = "Addition嘦" + word + "嘦" + temp;
											output.println(out);
											output.flush();
											String answer = input.readLine();
											JFrame frame_6 = new JFrame();
											frame_6.setBounds(400, 400, 330, 200);
											frame_6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
											frame_6.getContentPane().setLayout(null);
											frame_6.setVisible(true);
											JLabel lblNewLabel = new JLabel(answer);
											lblNewLabel.setBounds(83, 48, 230, 50);
											frame_6.getContentPane().add(lblNewLabel);
											JButton btnNewButton = new JButton("Yes");
											btnNewButton.setBounds(175, 100, 100, 50);
											frame_6.getContentPane().add(btnNewButton);
											btnNewButton.addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent e) {
													frame_6.dispose();
												}
											});
										} catch (IOException e5) {
											e5.printStackTrace();
										}
										
										
									}
									
								}
							});
							
						} else {
							JFrame frame_2 = new JFrame();
							frame_2.setBounds(400, 400, 330, 200);
							frame_2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							frame_2.getContentPane().setLayout(null);
							frame_2.setVisible(true);
							JLabel lblAnswer = new JLabel(answer);
							lblAnswer.setBounds(83, 48, 230, 50);
							frame_2.getContentPane().add(lblAnswer);
							JButton btnYes = new JButton("Yes");
							btnYes.setBounds(175, 100, 100, 50);
							frame_2.getContentPane().add(btnYes);
							btnYes.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									frame_2.dispose();
								}
							});
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
				} catch (IOException e3) {
					e3.printStackTrace();
				}
			}
		});
		btnEnter.setBounds(286, 81, 117, 41);
		frame.getContentPane().add(btnEnter);	
	}
}
