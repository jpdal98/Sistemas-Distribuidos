package Servidor;

public class Esqueleto {
	Calculadora calc = null;
	
	public Esqueleto() {
		this.calc = new Calculadora();
	}
	
	public String add( String par ) {
		String[] operandos = par.split(" ");
		return String.valueOf( calc.add(Double.parseDouble(operandos[0]),Double.parseDouble(operandos[1])) );
	}
	
	public String sub(String par) {
		String[] operandos = par.split(" ");
		return String.valueOf( calc.sub(Double.parseDouble(operandos[0]),Double.parseDouble(operandos[1])) );
	}
	
	public String mult(String par) {
		String[] operandos = par.split(" ");
		return String.valueOf( calc.mult(Double.parseDouble(operandos[0]),Double.parseDouble(operandos[1])) );
	}
	
	public String div(String par) {
		String[] operandos = par.split(" ");
		return String.valueOf( calc.div(Double.parseDouble(operandos[0]),Double.parseDouble(operandos[1])) );
	}
	
}
