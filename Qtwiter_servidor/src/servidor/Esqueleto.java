package servidor;

import java.text.ParseException;

import com.google.gson.Gson;

import mensagem.Mensagem_requisicao;
import postagem.Postagem;
import postagem.Postagem_dao;
import usuario.Usuario;
import usuario.Usuario_dao;

public class Esqueleto {
	Usuario_dao usdao;
	Postagem_dao postdao;
	

	public Esqueleto() {
		usdao = new Usuario_dao();
		postdao = new Postagem_dao();
		
	}

	public boolean AddUser(Mensagem_requisicao messenge) {
		String args = messenge.getArgs();
		boolean resultado;
		Gson gson = new Gson();
		Usuario user = gson.fromJson(args, Usuario.class);
		resultado = usdao.addUser(user);
		return resultado;

	}

	public boolean addPostagem(Mensagem_requisicao messenge) {
		String args = messenge.getArgs();
		boolean resultado;
		Gson gson = new Gson();
		Postagem post = gson.fromJson(args, Postagem.class);
		resultado = postdao.insercaoPostagem(post);
		return resultado;
	}

	public boolean excluirPostagem(Mensagem_requisicao messenge) {
		String args = messenge.getArgs();
		boolean resultado = false;
		Gson gson = new Gson();
		Postagem post = gson.fromJson(args, Postagem.class);
		resultado = postdao.deletarPostagem(post.getTexto(), post.getEmail_usuario());
		return resultado;
	}
}
