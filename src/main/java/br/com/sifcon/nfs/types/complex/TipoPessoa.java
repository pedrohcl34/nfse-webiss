package br.com.sifcon.nfs.types.complex;

public enum TipoPessoa {
	PESSOA_FISICA("Cpf"), PESSOA_JURIDICA("Cnpj");
	
	private String label;
	private TipoPessoa(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
