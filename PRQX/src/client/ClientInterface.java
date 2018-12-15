package client;

import java.util.Scanner;

public class ClientInterface {
	
	public static void main(String[] args) {
		TCPClient client = new TCPClient("localhost", 6789);
		boolean sair = false;
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("DIGITE UMA OPERAÇÃO OU ESCREVA 'sair' PARA ENCERRAR");
		
		do {
			System.out.print(">> ");
			String operacao = entrada.nextLine();
			String[] opSplited = operacao.split(" ");
			if(opSplited.length == 1) {
				if(opSplited[0].equals("sair")) {
					sair = true;
				} else {
					msgOpInvalida();
				}
			} else if(opSplited.length == 2) {
				if(opSplited[0].equals("cap")) {
					String capitalizada = client.sendResponse(operacao);
					System.out.println("MENSAGEM : " + capitalizada);
				} else {
					msgOpInvalida();
				}
				
			} else {
				String resultado = client.sendResponse(operacao);
				if(opSplited[0].equals("cap"))
					System.out.println("MENSAGEM: " + resultado);
				else 
					System.out.println("RESULTADO: " + resultado);
			}
			
		}while(!sair);
		
		entrada.close();

	}

	public static void msgOpInvalida() {
		System.out.println("DIGITE UMA OPERAÇÃO NAS FORMAS:");
		System.out.println("a operador b | para calcular ou");
		System.out.println("cap mensagem | para capitalizar a string 'mensagem'");
	}

}
