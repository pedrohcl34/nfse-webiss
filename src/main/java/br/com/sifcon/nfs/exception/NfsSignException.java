package br.com.sifcon.nfs.exception;

public class NfsSignException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String subMessage;
	
	public NfsSignException(String message) {
		super(message);
	}
	
	public NfsSignException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NfsSignException(String message, String subMessage) {
		super(message);
		this.subMessage = subMessage;
	}
	
	public NfsSignException(String message, String subMessage, Throwable cause) {
		super(message, cause);
		this.subMessage = subMessage;
	}

	public String getSubMessage() {
		return subMessage;
	}

	public void setSubMessage(String subMessage) {
		this.subMessage = subMessage;
	}
	
	

}
