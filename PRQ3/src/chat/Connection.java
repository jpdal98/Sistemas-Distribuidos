package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){
		try {			                
			Scanner teclado = new Scanner(System.in);
			while(true) {
				String mensagem = in.readUTF();	                  // read a line of data from the stream
				if(mensagem.equals("sair"))
					break;
				System.out.println("MENSAGEM DO CLIENTE: " + mensagem);
				System.out.println("DIGITE MENSAGEM PARA SER ENVIADA AO CLIENTE: ");
				String resposta = teclado.nextLine();
				out.writeUTF(resposta);
			}
			teclado.close();
		}catch (EOFException e){System.out.println("EOF: "+e.getMessage());
		} catch(IOException e) {System.out.println("readline: "+e.getMessage());
		} catch (Throwable e) { System.out.println("finalize: "+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
}
