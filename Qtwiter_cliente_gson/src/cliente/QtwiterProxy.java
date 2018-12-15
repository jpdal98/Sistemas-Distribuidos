package cliente;

import com.google.gson.Gson;

import mensagem.Mensagem_requisicao;
import mensagem.Mensagem_resposta;
import modelo.*;

public class QtwiterProxy {
	int requestiId = 0;
	// 7896
	TCPClient tcpClient = new TCPClient("localhost", 7896);

	public boolean addPerson(Usuario usuario) {
		Gson gson = new Gson();
		String userGson = gson.toJson(usuario);
		Mensagem_requisicao messengeOut = new Mensagem_requisicao(0, 1, "UsuarioDao", "AddUser", userGson);
		String userMessengeOut = gson.toJson(messengeOut);
		tcpClient.sendRequest(userMessengeOut);

		String res = tcpClient.getReply();
		Mensagem_resposta messegeIn = gson.fromJson(res, Mensagem_resposta.class);
		boolean resposta = messegeIn.isResultado();

		return resposta;
		// (1) Empacota argumentos de entrada
		// (2) Chama doOperation
		// (3) Desempacota argumento de resposta (retorno de doOperation)
		// (4) Retorna reposta desempacotada
	}

	public boolean addPostagem(Postagem post) {
		Gson gson = new Gson();
		String postGson = gson.toJson(post);
		Mensagem_requisicao messengeOut = new Mensagem_requisicao(0, 1, "PostagemDao", "addPostagem", postGson);
		String userMessengeOut = gson.toJson(messengeOut);
		tcpClient.sendRequest(userMessengeOut);
		String resposta = tcpClient.getReply();
		Mensagem_resposta messegeIn = gson.fromJson(resposta, Mensagem_resposta.class);
		boolean result = messegeIn.isResultado();
		return result;

		// (1) Empacota argumentos de entrada
		// (2) Chama doOperation
		// (3) Desempacota argumento de resposta (retorno de doOperation)
		// (4) Retorna reposta desempacotada
	}

	public boolean excluirPostagem(Postagem post) {
		Gson gson = new Gson();
		String postGson = gson.toJson(post);
		Mensagem_requisicao messengeOut = new Mensagem_requisicao(0, 1, "PostagemDao", "excluirPostagem", postGson);
		String userMessengeOut = gson.toJson(messengeOut);
		tcpClient.sendRequest(userMessengeOut);
		String resposta = tcpClient.getReply();
		Mensagem_resposta messegeIn = gson.fromJson(resposta, Mensagem_resposta.class);
		boolean result = messegeIn.isResultado();
		return result;
		// (1) Empacota argumentos de entrada
		// (2) Chama doOperation
		// (3) Desempacota argumento de resposta (retorno de doOperation)
		// (4) Retorna reposta desempacotada
	}

	public void finaliza() {
		tcpClient.finaliza();
	}

}