package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	static Socket connection;

	public static String getResponse() {
		DataInputStream in;
		Despachante des;
		String result = null;
		
		try {
			in = new DataInputStream(connection.getInputStream());
			des = new Despachante();
			result = des.invoke(in.readUTF());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	public static void sendResponse(String response) {
		try {
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeUTF(response);
			
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		ServerSocket servSocket = new ServerSocket(6789);
		
		while(true) {
			connection = servSocket.accept();

			System.out.println("Conexao aceita. \n"
					+ "Porta Cliente Remoto: " + connection.getPort() + 
					"\nPorta Local: " + connection.getLocalPort());

			String result = getResponse();
			sendResponse(result);
		}
	}

}
