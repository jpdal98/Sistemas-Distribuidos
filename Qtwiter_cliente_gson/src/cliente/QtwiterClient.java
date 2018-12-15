package cliente;

import modelo.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Scanner;

public class QtwiterClient {

	QtwiterProxy proxy;

	public QtwiterClient() {
		proxy = new QtwiterProxy();
	}

	public int selecionaOperacao() throws IOException, ParseException {
		Scanner input = new Scanner(System.in);
		int operacao = 0;
		String nome, senha, email;

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String opt = null;
		do {
			opt = stdin.readLine();
		} while (opt.equals("\n") || opt.equals("") || opt.isEmpty());
		operacao = Integer.parseInt(opt);

		switch (operacao) {
		case 1:
			System.out.println("Digite seu nome:");
			nome = stdin.readLine();
			System.out.println("Digite sua senha:");
			senha = stdin.readLine();
			System.out.println("Digite seu email:");
			email = stdin.readLine();
			Usuario user = new Usuario(nome, email, senha);
			
			Validacoes valid = new Validacoes();
			valid.addEntrada(user.getEmail());
			valid.addEntrada(user.getNome());
			valid.addEntrada(user.getSenha());
			boolean Nvazio = valid.Entrada();
			
			if(!Nvazio) {
				System.out.println("Usuario vazio");
				break;
			}
			
			boolean result = proxy.addPerson(user);

			if (result)
				System.out.println("Inserido com sucesso!");
			System.out.println("Erro ao inserir!");

			break;

		case 2:
			System.out.println("Digite seu email:");
			String emailp = input.next();
			System.out.println("O que está acontecendo?");
			String texto = input.next();
			Postagem post = new Postagem(texto, emailp);
			result = proxy.addPostagem(post);
			if (result)
				System.out.println("Adicionada!");
			System.out.println("Usuario não cadastrado");
			break;

		case 3:
			System.out.println("Digite seu email:");
			emailp = input.next();
			System.out.println("O que está acontecendo?");
			texto = input.next();
			post = new Postagem(texto, emailp);
			result = proxy.excluirPostagem(post);
			
			if (result)
				System.out.println("Apagada");
			System.out.println("Erro, usuario não cadastrado ou postagem inexistente!");
			break;

		case 0:
			input.close();
			proxy.finaliza();
			break;

		default:
			System.out.println("Operação invalida, tente outra.");
			break;
		}
		return operacao;
	}

	public void printMenu() {
		System.out.println("\nDigite o numero da operação que deseja executar: ");
		System.out.println("1 - Adicionar Pessoa");
		System.out.println("2 - Adicionar Postagem");
		System.out.println("3 - Excluir Postagem");
		System.out.println("0 - Sair \n");
	}

	public static void main(String[] args) throws ParseException {
		QtwiterClient bookClient = new QtwiterClient();
		int operacao = -1;
		do {
			bookClient.printMenu();
			try {
				operacao = bookClient.selecionaOperacao();
			} catch (IOException ex) {
				System.out.println("Escolha uma das operaÃ§Ãµes pelo nÃºmero");
			}
		} while (operacao != 0);
	}
}
