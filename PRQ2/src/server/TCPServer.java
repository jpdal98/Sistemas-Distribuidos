package server;

import java.net.*;
import java.io.*;

public class TCPServer {
    public static void main (String args[]) {
    	ServerSocket listenSocket = null;
		try {
			int serverPort = 6789; 
			listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
				c.start();
			}
		} catch(IOException e) {
			System.out.println("TCPServer IOException: " + e.getMessage());
		}
    }
}