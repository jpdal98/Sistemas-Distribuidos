package servidor;

import java.text.ParseException;

import com.google.gson.Gson;

import mensagem.Mensagem_requisicao;
import postagem.*;

public class EsqueletoPostagemDao {
	Postagem_dao postdao;
	public EsqueletoPostagemDao() {
		postdao = new Postagem_dao();
	}

	public boolean addPostagem(Mensagem_requisicao messenge) throws ParseException {
		String args = messenge.getArgs();
		boolean resultado;
		Gson gson = new Gson();
		Postagem post = gson.fromJson(args, Postagem.class);
		post.setData();
		post.setHora();
		System.out.println(post);
		resultado = postdao.insercaoPostagem(post);
		
		
		return resultado;
	}
	public boolean excluirPostagem(Mensagem_requisicao messenge) {
		String args = messenge.getArgs();
		boolean resultado = false;
		Gson gson = new Gson();
		Postagem post = gson.fromJson(args, Postagem.class);
		resultado = postdao.deletarPostagem(post.getTexto(),post.getEmail_usuario());
		return resultado;
	}
}
