package Servidor;

public class Despachante {

	Esqueleto esqueleto;

	public Despachante() {
		esqueleto = new Esqueleto();
	}

	public String invoke(String mensage) {

		String[] mens = mensage.split(" ");
		String mensagem = null;

		switch (mens[0]) {
		case "add": 
			mensagem = esqueleto.add(mens[1]+" "+mens[2]);
			break;
		
		case "mult":
			mensagem = esqueleto.mult(mens[1]+" "+mens[2]);
			break;
		
		case "div":
			mensagem = esqueleto.div(mens[1]+" "+mens[2]);
			break;
		
		case "sub":
			mensagem = esqueleto.sub(mens[1]+" "+mens[2]);
			break;
			
		default:
			mensagem = "ERRO";
			break;
		}
		
		return mensagem;
	}


}
