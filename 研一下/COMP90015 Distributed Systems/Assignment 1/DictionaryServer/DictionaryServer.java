import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.regex.*;

public class DictionaryServer {
	private int port = 0;
	private String path = null;
	public ServerSocket serversocket = null;
	public static JTextArea t = null;
	public static int number = 0;
	
	public DictionaryServer() {
		
	}
	
	public DictionaryServer(int portnumber, String pathname) {
		this.port = portnumber;
		this.path = pathname;
	}

	public void connection() {
		try {
			if (port > 65535) {
				JFrame frame = new JFrame();
				frame.setBounds(400, 400, 450, 250);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				frame.setVisible(true);
				JLabel lblNewLabel;
				lblNewLabel = new JLabel("Please enter the port number and dictionary path ^_^");
				lblNewLabel.setBounds(60, 50, 400, 50);
				frame.getContentPane().add(lblNewLabel);
				JButton btnNewButton = new JButton("Yes");
				btnNewButton.setBounds(175, 100, 100, 50);
				frame.getContentPane().add(btnNewButton);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);  
					}
				});
			} else {
				JFrame frame = new JFrame();
				frame.setBounds(100, 100, 500, 390);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				frame.setVisible(true);
				
				JScrollPane scroll = new JScrollPane();
				scroll.setBounds(29,19,443,260);
				frame.getContentPane().add(scroll);
				
				t = new JTextArea();
				t.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				t.setEditable(false);
				scroll.setViewportView(t);
				t.setLineWrap(true);
				
				JButton btnTotal = new JButton("Clients number");
				btnTotal.setBounds(69, 310, 130, 29);
				frame.getContentPane().add(btnTotal);
				btnTotal.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFrame frame1 = new JFrame();
						frame1.setBounds(400, 400, 480, 250);
						frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame1.getContentPane().setLayout(null);
						frame1.setVisible(true);
						JLabel lblNewLabel;
						lblNewLabel = new JLabel("Total number of clients: " + number);
						lblNewLabel.setBounds(60, 50, 400, 50);
						frame1.getContentPane().add(lblNewLabel);
						JButton btnNewButton = new JButton("Yes");
						btnNewButton.setBounds(175, 100, 100, 50);
						frame1.getContentPane().add(btnNewButton);
						btnNewButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								frame1.dispose();;  
							}
						});  
					}
				});
				
				JButton btnFinish = new JButton("Close server");
				btnFinish.setBounds(322, 310, 117, 29);
				frame.getContentPane().add(btnFinish);
				btnFinish.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);  
					}
				});
				
				serversocket = new ServerSocket(port);
				Socket socket = null;
				while (true) {
					socket = serversocket.accept();
					number = number + 1;
					t.append("One client connected.\n");
					t.append("Total number of connected clients: " + number + "\n");
					Job job = new Job(socket, path);
					Thread t = new Thread(job);
					t.start();	
				}
			}
		} catch (BindException e) {
			JFrame frame = new JFrame();
			frame.setBounds(400, 400, 480, 250);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.setVisible(true);
			JLabel lblNewLabel;
			lblNewLabel = new JLabel("Address already in use. Please change another one.");
			lblNewLabel.setBounds(60, 50, 400, 50);
			frame.getContentPane().add(lblNewLabel);
			JButton btnNewButton = new JButton("Yes");
			btnNewButton.setBounds(175, 100, 100, 50);
			frame.getContentPane().add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);  
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
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
			lblNewLabel = new JLabel("Please enter the port number and dictionary path ^_^");
			lblNewLabel.setBounds(60, 50, 400, 50);
			frame.getContentPane().add(lblNewLabel);
			JButton btnNewButton = new JButton("Yes");
			btnNewButton.setBounds(175, 100, 100, 50);
			frame.getContentPane().add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);  
				}
			});
		} else {
			try {
				DictionaryServer server = new DictionaryServer(Integer.parseInt(args[0]), args[1]);
				String content = args[1];
				String pattern = ".*\\.txt$";
				if (!Pattern.matches(pattern, content)) {
					JFrame frame = new JFrame();
					frame.setBounds(400, 400, 450, 250);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().setLayout(null);
					frame.setVisible(true);
					JLabel lblNewLabel;
					lblNewLabel = new JLabel("The dictionary file should be a \".txt\" file!");
					lblNewLabel.setBounds(60, 50, 400, 50);
					frame.getContentPane().add(lblNewLabel);
					JButton btnNewButton = new JButton("Yes");
					btnNewButton.setBounds(175, 100, 100, 50);
					frame.getContentPane().add(btnNewButton);
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(0); 
						}
					});
				} else {
					File file = new File(args[1]);
					if (!file.exists()) {
						JFrame frame = new JFrame();
						frame.setBounds(400, 400, 450, 250);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.getContentPane().setLayout(null);
						frame.setVisible(true);
						JLabel lblNewLabel;
						lblNewLabel = new JLabel("Cannot find the dictionary file!");
						lblNewLabel.setBounds(60, 50, 400, 50);
						frame.getContentPane().add(lblNewLabel);
						JButton btnNewButton = new JButton("Yes");
						btnNewButton.setBounds(175, 100, 100, 50);
						frame.getContentPane().add(btnNewButton);
						btnNewButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								frame.dispose();  
							}
						});
					}
					server.connection();
				}
			} catch (NumberFormatException e) {
				JFrame frame = new JFrame();
				frame.setBounds(400, 400, 450, 250);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				frame.setVisible(true);
				JLabel lblNewLabel;
				lblNewLabel = new JLabel("Please enter the correct port number and dictionary path ^_^");
				lblNewLabel.setBounds(60, 50, 400, 50);
				frame.getContentPane().add(lblNewLabel);
				JButton btnNewButton = new JButton("Yes");
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
