package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import Calc.Calculadora;

public class Servidor {

	static Socket connection;

	public static String getResponse() {
		DataInputStream in;
		Calculadora calc;		
		String result = null;
		try {
			calc = new Calculadora();
			in = new DataInputStream(connection.getInputStream());
			String[] operador = in.readUTF().split(" ");

			switch (operador[1]) {
			case "+":
				result = String.valueOf(calc.Adicao(Double.parseDouble(operador[0]),Double.parseDouble(operador[2])));
				break;
			case "-":
				result = String.valueOf(calc.Subtracao(Double.parseDouble(operador[1]),Double.parseDouble(operador[2])));
				break;
			case "*":
				result = String.valueOf(calc.Multiplicacao(Double.parseDouble(operador[1]),Double.parseDouble(operador[2])));
				break;
			case "/":
				result = String.valueOf(calc.Divisao(Double.parseDouble(operador[1]),Double.parseDouble(operador[2])));
				break;
			default:
				result = "Operação Invalida!";
				break;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public static void sendResponse(String response) {
		try {
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeUTF(response);
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		ServerSocket servSocket = new ServerSocket(6789);
		while(true) {
			connection = servSocket.accept();

			System.out.println("Conexao aceita. \n"
					+ "Porta Cliente Remoto: " + connection.getPort() + 
					"\nPorta Local: " + connection.getLocalPort());

			String result = getResponse();
			sendResponse(result);
		}
	}
}
