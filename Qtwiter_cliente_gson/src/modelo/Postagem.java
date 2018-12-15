package modelo;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.Date;
//import java.sql.Date;

public class Postagem {

	private String texto;
	private String email_usuario;

	public Postagem(String texto, String email_usuario) throws ParseException {
		this.texto = texto;
		this.email_usuario = email_usuario;
	}

	public Postagem(String email, String texto, int x) {
		this.texto = texto;
		this.email_usuario = email;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getEmail_usuario() {
		return email_usuario;
	}

	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}

}
