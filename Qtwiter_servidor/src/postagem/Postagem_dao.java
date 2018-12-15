package postagem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;

import jdbc.Connection_factory;

public class Postagem_dao {
	private Connection connection;

	// crud
	public Postagem_dao() {
	}

	public boolean insercaoPostagem(Postagem post) {
		// Postagem postagem = new Postagem(texto, email);
		String sql = "INSERT INTO postagens (texto, email_usuario) VALUES(?,?);";
		this.connection = new Connection_factory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			//stmt.setInt(1, post.getId());
			stmt.setString(1, post.getTexto());
			stmt.setString(2, post.getEmail_usuario());

			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if (qtdRowsAffected > 0)
				return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}

	public boolean deletarPostagem(String texto, String email) {
		String sql = "DELETE FROM postagens WHERE texto = ? and email_usuario = ?";
		this.connection = new Connection_factory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, texto);
			stmt.setString(2, email);
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if (qtdRowsAffected > 0)
				return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}


	public boolean altPostagem() {
		return false;
	}

	public boolean verPostagem() {
		return false;
	}

	// specifc
	public boolean list_my_Posts() {
		return false;
	}

	public boolean list_Postagem_seguido() {
		return false;
	}

	public boolean feed() {
		return false;
	}
}
