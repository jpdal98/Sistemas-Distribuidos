package server;

public class Calculadora {
	
	public Calculadora() {
		
	}
	
	public String process(String operation) {
		String response;
		
		String[] opSplit = operation.split(" ");
		
		double a, b;
		try {
			a = Double.parseDouble(opSplit[0]);
			b = Double.parseDouble(opSplit[2]);
		} catch (NumberFormatException e) {
			response = "impossivel converter em número";
			return response;
		}
		
		double dResult;
		
		switch (opSplit[1]) {
		case "+":
			dResult = a + b;
			response = Double.toString(dResult);
			break;
		case "-":
			dResult = a - b;
			response = Double.toString(dResult);
			break;
		case "*":
			dResult = a * b;
			response = Double.toString(dResult);
			break;
		case "/":
			dResult = a / b;
			response = Double.toString(dResult);
			break;
		case "^":
			dResult = Math.pow(a, b);
			response = Double.toString(dResult);
			break;
		default:
			response = "operador desconhecido";
			break;
		}
		return response;
	}
	
}
