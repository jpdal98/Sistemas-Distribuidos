package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import mensagem.Mensagem;
import mensagem.Mensagem_requisicao;

public class TCPServer {
	private static ServerSocket listenSocket;

	public static void main(String args[]) {
		try {
			int serverPort = 7896;
			listenSocket = new ServerSocket(serverPort);
			while (true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}
		} catch (IOException e) {
			System.out.println("Listen :" + e.getMessage());
		}
	}
}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	QtwiterDespachante despachante;

	public Connection(Socket aClientSocket) {
		try {
			despachante = new QtwiterDespachante();
			clientSocket = aClientSocket;
			System.out.println("conectado a " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	public String getRequest() throws IOException {
		String request = in.readUTF();
		return request;
		// enviado via moodle
	}

	public Mensagem_requisicao desempacotaRequisicao(String request) {
		Gson gson = new Gson();
		Mensagem_requisicao mensagem = gson.fromJson(request, Mensagem_requisicao.class);
		return mensagem;

	}

	public String empacotaResposta(Mensagem resposta) {
		Gson gson = new Gson();
		String msgJson = gson.toJson(resposta);
		return msgJson;
	}

	public void sendReply(String resposta) throws IOException {
		out.writeUTF(resposta);
	}

	public void run() {
		// Loop para intergair com socket client - TCP Permanente
		while (clientSocket.isConnected()) {
			Mensagem_requisicao mensagem;
			try {
				mensagem = desempacotaRequisicao(getRequest());
				System.out.println(mensagem);
				 Mensagem msgResposta = despachante.selecionaEqueleto(mensagem);
				 sendReply(empacotaResposta(msgResposta));

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try {
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}