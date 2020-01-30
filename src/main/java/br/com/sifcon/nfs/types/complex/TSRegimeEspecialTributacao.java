package br.com.sifcon.nfs.types.complex;

public enum TSRegimeEspecialTributacao {
	MICROEMPRESA_MUNICIPAL("Microempresa Municipal", 1),
	ESTIMATIVA("Estimativa", 2),
	SOCIEDADE_DE_PROFISSIONAIS("Sociedade de Profissionais", 3),
	COOPERATIVA("Cooperativa", 4),
	MEI("Microempresário Individual", 5),
	ME_EPP("Microempresário e Empresa de Pequeno Porte", 6);
	
	private String label;
	private Integer codigo;

	private TSRegimeEspecialTributacao(String label, Integer codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	
	
}
