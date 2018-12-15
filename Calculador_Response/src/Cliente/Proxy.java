package Cliente;

public class Proxy {

	Cliente cliente;

	public Proxy() {
		cliente = new Cliente("localhost", 6789);
	}
	
	public double add(double a, double b) {
		cliente.sendRequest("add "+ a + " " + b);
		String result = cliente.getResponse();
		return Double.parseDouble(result);
	}
	
	public double sub(double a, double b) {
		cliente.sendRequest("sub "+ a + " " + b);
		String result = cliente.getResponse();
		return Double.parseDouble(result);
	}
	
	public double mult(double a, double b) {
		cliente.sendRequest("mult "+ a + " " + b);
		String result = cliente.getResponse();
		return Double.parseDouble(result);
	}
	
	public double div(double a, double b) {
		cliente.sendRequest("div "+ a + " " + b);
		String result = cliente.getResponse();
		return Double.parseDouble(result);
	}
	
	public void close() {
		cliente.close();
	}
}
