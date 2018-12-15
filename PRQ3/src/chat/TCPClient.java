package chat;

import java.net.*;
import java.util.Scanner;
import java.io.*;
public class TCPClient {
	public static void main (String args[]) {
		// arguments supply message and hostname
		Socket s = null;
		try{
			int serverPort = 7896;
			s = new Socket("localhost", serverPort);    
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out = new DataOutputStream( s.getOutputStream());
			
			Scanner teclado = new Scanner(System.in);
			while(true) {
				System.out.println("DIGITE UMA MENSAGEM PARA SER ENVIADA AO SERVIDOR: ");
				String mensagem = teclado.nextLine();
				out.writeUTF(mensagem);      	// UTF is a string encoding see Sn. 4.4
				String resposta = in.readUTF();	    // read a line of data from the stream
				if(resposta.equals("sair"))
					break;
				System.out.println("RESPOSTA DO SERVIDOR: " + resposta);
			}
			teclado.close();
			
		}catch (UnknownHostException e){System.out.println("Socket: "+e.getMessage());
		}catch (EOFException e){System.out.println("EOF: "+e.getMessage());
		}catch (IOException e){System.out.println("readline: "+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}