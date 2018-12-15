package principal;

import java.util.Scanner;

import usuario.Usuario;
import usuario.Usuario_dao;

public class Teste_login {
	public static void main(String[] args) {
		boolean run = true, logado = false;
		Usuario usuario = null;
		Usuario_dao usuario_dao = new Usuario_dao();
		String nome, email, senha;
		while (run) {
			System.out.println("| 1 | Cadastrar usuário");
			System.out.println("| 2 | Logar");
			System.out.println("| 0 | Sair");
			
			
			Scanner input = new Scanner(System.in);
			String comando = input.nextLine();
			//String[] comandos = comando.split(" ");
			switch (comando) {
			case "0":
				System.out.println("Programa encerrado!");
				input.close();
				run = false;
				break;
				
			case "1":
				System.out.println("Digite seu nome:");
				nome = input.nextLine();
				System.out.println("Digite sua senha:");
				senha = input.nextLine();
				System.out.println("Digite seu email:");
				email = input.nextLine();
				usuario = new Usuario(nome, email, senha);
				usuario_dao.addUser(usuario);
				break;
				
			case "2":
				break;
				
			default:
				System.out.println("comando inválido");
				break;
			}
		}
	}
	public void validacao(Usuario user) {
		if(user == null) return;
		else {
			this.logado(user);
		}
	}
	
	public void logado(Usuario user) {
		
	}
}
