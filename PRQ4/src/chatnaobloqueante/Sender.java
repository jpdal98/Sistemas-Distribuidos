package chatnaobloqueante;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender implements Runnable{
	private DataOutputStream out;
	private String mensagem;
	
	public Sender(Socket socket, String mensagem) throws IOException {
		this.out = new DataOutputStream(socket.getOutputStream());
		this.mensagem = mensagem;
		try {
			out.writeUTF(this.mensagem);
		} catch (IOException e) {
			System.out.println("Sender IOException: " + e.getMessage());
		}
		
	}
	
	@Override
	public void run() {
		try {
			Scanner teclado = null;
			while(true) {
				teclado = new Scanner(System.in);
				System.out.print(">> ");
				mensagem = teclado.nextLine();
				out.writeUTF(mensagem);
				if(mensagem.equals("sair")) {
					System.out.println(" ");
					System.out.println("CONEXÃO ENCERRADA");
					this.finalize();
					break;
				}
			}
			teclado.close();
		} catch (IOException e) {
			System.out.println("Sender IOException: " + e.getMessage());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}             
		
	}

}
