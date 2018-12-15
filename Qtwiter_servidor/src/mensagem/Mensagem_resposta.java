package mensagem;

public class Mensagem_resposta implements Mensagem {
	private boolean resultado;
	
	public Mensagem_resposta(boolean resultado) {
		super();
		this.resultado = resultado;
	}

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}
	
}
