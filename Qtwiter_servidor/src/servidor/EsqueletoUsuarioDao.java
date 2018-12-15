package servidor;

import com.google.gson.Gson;

import mensagem.Mensagem_requisicao;
import usuario.*;

public class EsqueletoUsuarioDao {
	Usuario_dao usdao;
	public EsqueletoUsuarioDao() {
		usdao = new Usuario_dao();
	}

	public boolean AddUser(Mensagem_requisicao messenge) {
		String args = messenge.getArgs();;
		boolean resultado;
		Gson gson = new Gson();
		Usuario user = gson.fromJson(args, Usuario.class);
		resultado = usdao.addUser(user);
		return resultado;
	}

}
