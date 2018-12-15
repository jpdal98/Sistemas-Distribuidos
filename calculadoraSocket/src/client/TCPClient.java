package client;

import java.net.*;
import java.io.*;

public class TCPClient {
	private Socket s = null;
	private String host;
	private int serverPort;
	
	public TCPClient(String host, int serverPort) {
		this.host = host;
		this.serverPort = serverPort;
		
	}
	
	public String sendResponse(String data) {
		String response = null;
		try{
		   	s = new Socket(host, serverPort);
		   	
		   	DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			
			out.writeUTF(data);        	
			response = in.readUTF();	         
			
		} catch (UnknownHostException e) {
			System.out.println("TCPCliente UnknownHostException: " + e.getMessage());
		} catch (EOFException e) { 
			System.out.println("TCPCliente EOFException: " + e.getMessage());
		} catch (IOException e){ 
			System.out.println("TCPCliente IOException: " + e.getMessage());
		} finally { 
			if(s!=null) { 
				try { 
					s.close(); 
				} catch (IOException e){
					System.out.println("IOException: " +e.getMessage()); 
				} 
			}
		}
		return response;
	}
		
}
