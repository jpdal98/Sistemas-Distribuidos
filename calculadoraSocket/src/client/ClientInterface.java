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
					System.out.println("DIGITE UMA OPERAÇÃO NA FORMA: a operador b");
				}
			} else if(opSplited.length == 2) {
				System.out.println("DIGITE UMA OPERAÇÃO NA FORMA: a operador b");
			} else {
				String resultado = client.sendResponse(operacao);
				System.out.println("RESULTADO: " + resultado);
			}
			
		}while(!sair);
		
		entrada.close();

	}

}
