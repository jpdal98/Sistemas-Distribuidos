package chatnaobloqueante;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver implements Runnable{
	private DataInputStream in;
	private Object receiver;

	public Receiver(Socket socket, Object receiver) throws IOException {
		this.in = new DataInputStream(socket.getInputStream());
		this.receiver = receiver;
	}
	
	@Override
	public void run() {
		String mensagem = null;
		String agente = null;
		try {
			while(true) {	
				if(receiver instanceof Connection) 
					agente = "CLIENTE";
				else
					agente = "SERVIDOR";
				mensagem = in.readUTF();
				if(mensagem != null & mensagem.equals("sair")) {
					System.out.println(" ");
					System.out.println(agente + " ENCERROU A CONEXAO");
					this.finalize();
					break;
				}
				System.out.println(" ");
				System.out.println(agente + ": " + mensagem);
			}
		} catch (IOException e) {
			System.out.println("Receiver IOException: " + e.getMessage());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
