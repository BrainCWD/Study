import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Job extends DictionaryServer implements Runnable {
	
	private PrintWriter output;
	private InputStreamReader in = null;
	private BufferedReader input;
	private Socket sock = null;
	private String path;
	private StringTokenizer processing = null;
	
	
	public Job(Socket socket, String dic_path){
			this.sock = socket;
			this.path = dic_path;
			
	}
	
	public void run() {
		
		try {
			output = new PrintWriter(sock.getOutputStream());
			in = new InputStreamReader(sock.getInputStream());
			input = new BufferedReader(in);
			String request = null;
			
			while ((request = input.readLine()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				list = fileInput(path);
				processing = new StringTokenizer(request, "嘦");
				String command = processing.nextToken();
				String word = processing.nextToken();
				switch (command) {
				case "Query":
					t.append("one client wants to query the word: " + word + "\n");
					if (list.contains(word)) {
						int index = list.indexOf(word) + 1;
						output.println(list.get(index));
						output.flush();
					} else {
						output.println("False123@嘦" + "word does not exist!");
						output.flush();
					}
					break;
					
				case "Deletion":
					t.append("one client wants to delete the word: " + word + "\n");
					if (list.contains(word)) {
						int index = list.indexOf(word);
						list.remove(index + 1);
						list.remove(index);
						output.println("Deletion Sucess!");
						output.flush();
						fileOutput(list, path);
					} else {
						output.println("word does not exist!");
						output.flush();
					}
					break;
					
				case "Check":
					t.append("one client wants to add the word: " + word + "\n");
					if (list.contains(word)) {
						output.println("Word exists!");
						output.flush();
					} else {
						output.println("ok");
						output.flush();
					}
					break;
					
				case "Addition":
					list.add(word);
					String meaning = "";
					while (processing.hasMoreTokens()) {
						meaning = meaning + processing.nextToken() + "嘦";
					}
					list.add(meaning);
					output.println("Addition Sucess!");
					output.flush();
					fileOutput(list, path);
					t.append("one client successfully adds the word: " + word + "\n");
					break;
				
				case "Disconnection":
					number = number - 1;
					t.append("One client disconnected.\n");
					t.append("Total number of connected clients: " + number + "\n");
					break;
					
				default:
					break;
				}
			}
		} catch (SocketException e) {
			number = number - 1;
			t.append("One client disconnected.\n");
			t.append("Total number of connected clients: " + number + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public synchronized ArrayList<String> fileInput(String path) {
		ArrayList<String> dict = new ArrayList<String>();
		
		try {
			Scanner in = new Scanner(new FileInputStream(path));
			while (in.hasNext()) {
				if (in.hasNext()) {
					dict.add(in.nextLine());
				}
			}
			in.close();
			
		} catch(FileNotFoundException e) {
			System.out.println("Warning: File does not exist!");
		}
		return dict;
	}
	
	public synchronized void fileOutput(ArrayList<String> dict, String path) {
		int length = dict.size();
		
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(path));
			for (int i = 0; i < length; i++) {
				out.println(dict.get(i));
			}
			out.close();		
			
		} catch (FileNotFoundException e) {
			System.out.println("Warning: File does not exist!");
		}
	}
}
