package br.com.sifcon.soap.base;

public enum MetodoNfse {

	GERAR_NFSE("GerarNfseRequest"), CONSULTAR_NFSE("ConsultarNfsePorRpsRequest"), CANCELAR_NFSE("CancelarNfseRequest");
	private String metodo;
	
	private MetodoNfse(String metodo) {
		this.metodo = metodo;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	
	
	
}
