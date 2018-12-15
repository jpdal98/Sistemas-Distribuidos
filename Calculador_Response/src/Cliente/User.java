package Cliente;

import java.util.Scanner;

public class User {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String mensagem = null;
		Proxy proxy = new Proxy();
		
		mensagem = scan.nextLine();
		String[] mensagens = mensagem.split(" ");
		
		try {
			
			switch (mensagens[0]) {
			case "add":
				proxy.add(Double.parseDouble(mensagens[1]), Double.parseDouble(mensagens[2]));
				break;
				
			case "sub":
				proxy.sub(Double.parseDouble(mensagens[1]), Double.parseDouble(mensagens[2]));
				break;
				
			case "mult":
				proxy.mult(Double.parseDouble(mensagens[1]), Double.parseDouble(mensagens[2]));
				break;
			
			case "div":
				proxy.div(Double.parseDouble(mensagens[1]), Double.parseDouble(mensagens[2]));
				break;
			
			default:
				System.out.println("Operador Inválido!");
				break;
			}
			
		}finally {
			proxy.close();
		}

	}

}
