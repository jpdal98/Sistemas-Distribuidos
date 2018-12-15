package cliente;

//import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.*;
import java.net.*;

public class TCPClient {

	Socket socket = null;
	// DataInputStream in = null;
	// DataOutputStream out = null;

	public TCPClient(String serverIP, int port) {
		try {

			socket = new Socket(serverIP, port);
			// in = new DataInputStream(socket.getInputStream());
			// out = new DataOutputStream(socket.getOutputStream());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendRequest(String requisicao) {
		try {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(requisicao);

		} catch (UnknownHostException e) {
			System.out.println("TCPCliente UnknownHostException: " + e.getMessage());
		} catch (EOFException e) {
			System.out.println("TCPCliente EOFException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("TCPCliente IOException: " + e.getMessage());
		}

	}

	public String getReply() {
		String response = null;
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			response = in.readUTF();
		} catch (UnknownHostException e) {
			System.out.println("TCPCliente UnknownHostException: " + e.getMessage());
		} catch (EOFException e) {
			System.out.println("TCPCliente EOFException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("TCPCliente IOException: " + e.getMessage());
		}

		return response;
		
	}

	public void finaliza() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}