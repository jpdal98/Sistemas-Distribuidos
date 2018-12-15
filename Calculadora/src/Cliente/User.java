package Cliente;

import java.util.Scanner;

public class User {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String mensagem = null;
		Cliente cliente = new Cliente(args[0], 6789);
		
		mensagem = scan.nextLine();
		
		try {
			cliente.sendRequest(mensagem);
			System.out.println("Resultado: " + cliente.getResponse());
		}finally {
			cliente.close();
		}
	}
}
