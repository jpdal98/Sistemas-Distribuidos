package mensagem;


public class Mensagem_requisicao implements Mensagem {
	private int menssegeType;
	private int requestId;
	private String objectRef;
	private String method;
	private String args;
 
	
	
	public Mensagem_requisicao(int menssegeType, int requestId, String objectRef, String method, String args) {
		super();
		this.menssegeType = menssegeType;
		this.requestId = requestId;
		this.objectRef = objectRef;
		this.method = method;
		this.args = args;
		
	}

	public int getMenssegeType() {
		return menssegeType;
	}
	public void setMenssegeType(int menssegeType) {
		this.menssegeType = menssegeType;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getObjectRef() {
		return objectRef;
	}
	public void setObjectRef(String objectRef) {
		this.objectRef = objectRef;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}

	
}
