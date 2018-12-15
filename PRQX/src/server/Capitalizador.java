package server;

public class Capitalizador {

	private static Capitalizador capitalizador = new Capitalizador();
	
	private Capitalizador() {

	}
	
	public String capitalizar(String mensagem) {
		return mensagem.toUpperCase();
	}
	
	public static Capitalizador getInstance() {
		return capitalizador;
	}
	
}
