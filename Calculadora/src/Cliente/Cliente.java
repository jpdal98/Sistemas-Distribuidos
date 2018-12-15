package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	Socket sock = null;

	public Cliente(String ip, int port) {
		try {
			this.sock = new Socket(ip, port);
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void sendRequest(String request) {
		DataOutputStream out;
		try {
			out = new DataOutputStream(sock.getOutputStream());
			out.writeUTF(request);;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public String getResponse() {
		DataInputStream in;
		String res = null;
		try {
			in = new DataInputStream(sock.getInputStream());
			res = in.readUTF();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return res;
	}

	public void close() {
		if(sock!=null) { 
			try { 
				sock.close(); 
			} catch (IOException e){
				System.out.println("close:"+e.getMessage()); 
			} 
		}
	}
}
