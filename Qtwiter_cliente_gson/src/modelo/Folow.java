package modelo;

public class Folow {
	private String email_segue;
	private String email_seguidor;
	
	public Folow(String email_segue, String email_seguidor) {
		super();
		this.email_segue = email_segue;
		this.email_seguidor = email_seguidor;
	}

	public String getEmail_segue() {
		return email_segue;
	}

	public void setEmail_segue(String email_segue) {
		this.email_segue = email_segue;
	}

	public String getEmail_seguidor() {
		return email_seguidor;
	}

	public void setEmail_seguidor(String email_seguidor) {
		this.email_seguidor = email_seguidor;
	}

	@Override
	public String toString() {
		return "Folow [email_segue=" + email_segue + ", email_seguidor=" + email_seguidor + "]";
	}
	
	
	

}
