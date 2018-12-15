package chatnaobloqueante;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
		clientSocket = aClientSocket;
		this.start();
	}
	public void run(){
		try {	
			String mensagem = "servidor conectado";
			
			Receiver recebedor = null;
			Sender saida = new Sender(clientSocket, mensagem);
			recebedor = new Receiver(clientSocket, this);	
			
			new Thread(recebedor).start();
			new Thread(saida).start();
			
		} catch (EOFException e){System.out.println("EOF: " + e.getMessage());
		} catch(IOException e) {System.out.println("readline: "+e.getMessage());
		}
		

	}
}
