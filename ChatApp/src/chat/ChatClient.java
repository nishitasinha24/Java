package chat;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient extends JFrame implements Runnable {

	private static int WIDTH = 400;
	private static int HEIGHT = 300;
	private static JTextArea outputTextArea;
	private static JTextField inputTextField;
	
	private Socket client;
	private BufferedReader input;
	private static PrintWriter output;
	private static boolean done = false;
	
	public ChatClient() {
		super("Chat Client");
		this.setSize(ChatClient.WIDTH, ChatClient.HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setVisible(true);
		
	}
	
	public void run() {
		try {
			Socket client = new Socket("localhost", 9898);
			output = new PrintWriter(client.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			InputHandler inHandler = new InputHandler();
			Thread t = new Thread(inHandler);
			t.start();
			
			String inMessage;
			while((inMessage = input.readLine()) != null) {
				outputTextArea.append(inMessage + "\n");
				//System.out.println(inMessage);
			}
		} catch (IOException e) {
			shutdown();
		}

	}
	
	public void shutdown() {
		done = true;
		try {
			input.close();
			output.close();
			if(!client.isClosed()) {
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class InputHandler implements Runnable {

		@Override
		public void run() {
			//BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
			while (done) {
				//String message = inReader.readLine();
				String message = inputTextField.getText();
				//System.out.println("Message is " + message);
				inputTextField.setText("");
				outputTextArea.append(message);
				output.println(message);
				done = false;
			}
		}
		
	}
	
	public static void main(String[] args) {
		ChatClient chatClient = new ChatClient();
		JPanel mainPanel = new JPanel();
		//chatClient.add(mainPanel);
		
		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("File");
		menubar.add(file);
		chatClient.setJMenuBar(menubar);
		
		outputTextArea = new JTextArea(13, 34);
		outputTextArea.setWrapStyleWord(true);
		outputTextArea.setEditable(false);
		
		inputTextField = new JTextField(34);
		mainPanel.add(outputTextArea);
		mainPanel.add(inputTextField);
		
		class handleKeyPress implements KeyListener {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				    // Do something
					done = true;
					String message = inputTextField.getText();
					//System.out.println("Message is " + message);
					inputTextField.setText("");
					//outputTextArea.append(message);
					output.println(message);
				}
			}
			
		}
		inputTextField.addKeyListener(new handleKeyPress());
		chatClient.add(mainPanel);
		chatClient.setVisible(true);
		
		chatClient.run();
	}
	
}
