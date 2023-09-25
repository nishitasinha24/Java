package chat;


import javax.swing.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class ChatServer extends JFrame implements Runnable {

	private static int WIDTH = 400;
	private static int HEIGHT = 300;
	private static JTextArea outputTextArea;
	
	private ArrayList<HandleClient> conn;
	private ServerSocket server;
	private boolean flag;
	private ExecutorService pool;
	
	public ChatServer() {
		super("Chat Server");
		this.setSize(ChatServer.WIDTH, ChatServer.HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createMenu();
		//this.setVisible(true);
		flag = false;
		conn = new ArrayList<>(); 
		
	}
	
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener((e) -> System.exit(0));
		menu.add(exitItem);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}
	

	public static void main(String[] args) {
		ChatServer chatServer = new ChatServer();
		JPanel mainPanel = new JPanel();
		chatServer.add(mainPanel);
		
		outputTextArea = new JTextArea(14, 34);
		outputTextArea.setWrapStyleWord(true);
		outputTextArea.setEditable(false);
		
		mainPanel.add(outputTextArea);
		chatServer.add(mainPanel);
		chatServer.setVisible(true);
		chatServer.run();
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(9898);
			pool = Executors.newCachedThreadPool();
			while (!flag) {
				Socket client = server.accept();
				HandleClient handler = new HandleClient(client);
				conn.add(handler);
				pool.execute(handler);
				LocalDateTime localDateTime = LocalDateTime.now();
				outputTextArea.append("Client" + conn.size() + " joined at " + localDateTime + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void broadcast(String message) {
		for (HandleClient hc: conn) {
			if (hc != null ) {
				hc.sendMessage(message);
			}
			
		}
	}
	
	public void shutDown() throws IOException {
		flag = true;
		if (!server.isClosed()) {
			server.close();
		}
		for (HandleClient hc: conn) {
			hc.shutdown();
		}
	}
	
	class HandleClient implements Runnable {
		private Socket client;
		private BufferedReader in;
		private PrintWriter out;
		
		public HandleClient(Socket client) {
			this.client = client;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				out = new PrintWriter(client.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String clientName = "Client" + conn.size();
				String message;
				while((message = in.readLine()) != null) {
					broadcast(clientName + ":" + message);
				}
			} catch (IOException e) {
				shutdown();
			}
			
		}
		
		public void sendMessage(String message) {
			//outputTextArea.append(message + "\n");
			out.println(message);
		}
		
		public void shutdown() {
			try {
				in.close();
				out.close();
				if (!client.isClosed()) {
					client.close();
				}
			} catch (IOException e) {
				shutdown();
			}
		}
		
	}
}


