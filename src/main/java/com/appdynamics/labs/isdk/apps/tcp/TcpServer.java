package com.appdynamics.labs.isdk.apps.tcp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

	public static void main(String[] args) throws Exception {
		new TcpServer().startServer();
	}

	public void startServer() throws Exception {

		ServerSocket welcomeSocket = null;

		try {
			System.out.println("Starting Server.");

			welcomeSocket = new ServerSocket(8088);

			while (true) {
				// Create the Client Socket
				Socket clientSocket = welcomeSocket.accept();
				System.out.println("Socket Extablished...");

				// Create input and output streams to client
				ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());
				ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());

				/* Read Message object */
				Message inMsg = (Message) inFromClient.readObject();
				processMessage(inMsg);

				/* Modify the message object */
				inMsg.setMsg("Server Ack");
				inMsg.getParameters().put("ServerKey1", "Value2");

				/* Send the modified Message object back */
				outToClient.writeObject(inMsg);
				outToClient.flush();
			}
		} catch (Exception e) {
			if (welcomeSocket != null)
				welcomeSocket.close();
		}
	}

	public static void processMessage(Message message) {
		System.out.println("Processing message : " + message);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
