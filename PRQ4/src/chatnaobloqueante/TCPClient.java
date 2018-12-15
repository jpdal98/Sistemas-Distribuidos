package chatnaobloqueante;

import java.net.*;
import java.io.*;

public class TCPClient {
	public static void main (String args[]) {
		Socket s = null;
		try{
			int serverPort = 7896;
			s = new Socket("localhost", serverPort);    
			String mensagem = "cliente conectado";
			
			Receiver recebedor = null;
			Sender saida = new Sender(s, mensagem);
			recebedor = new Receiver(s, null);	
			
			new Thread(saida).start();
			new Thread(recebedor).start();
			
		}catch (UnknownHostException e){System.out.println("Socket: " + e.getMessage());
		}catch (EOFException e){System.out.println("EOF: " + e.getMessage());
		}catch (IOException e){System.out.println("readline: " + e.getMessage());
		}
     }
}