package com.appdynamics.labs.isdk.apps.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient  {

	private String hostname;
	private int port;
	
	public TcpClient(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public String getHostname() {
		return hostname;
	}

	public int getPort() {
		return port;
	}

	public void startClient() throws UnknownHostException, IOException, ClassNotFoundException {
        // Create the socket
        Socket clientSocket = new Socket(hostname, port);

        // Create the input & output streams to the server
        ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());

        /* Create The Message Object to send */
        Message msg = new Message();
        msg.setMsg("Test");
        msg.getParameters().put("key1", "value1");

        /* Send the Message Object to the server */
        sendMessage(outToServer,msg);

        /* Retrieve the Message Object from server */
        Message msgFrmServer = (Message)inFromServer.readObject();

        /* Print out the recived Message */
        System.out.println("Message: " + msgFrmServer);

        clientSocket.close();
	}

	public void sendMessage(ObjectOutputStream outToServer, Message message) throws IOException {
		outToServer.writeObject(message);
        outToServer.flush();		
	}
	
	public static void main(String[] args) throws InterruptedException, UnknownHostException, ClassNotFoundException, IOException {
		while(true) {
			new TcpClient("localhost", 8088).startClient();
			Thread.sleep(1000);
		}
	}
}
