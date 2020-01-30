package br.com.sifcon.nfs.types.complex;

public enum TSExigibilidadeISS {
	EXIGIVEL(1, "Exigível"), NAO_INCIDENCIA(2, "Não incidência"),
	ISENCAO(3, "Isenção"), EXPORTACAO(4, "Exportação"), IMUNIDADE(5, "Imunidade"),
	EXIGIBILIDADE_SUSPENSA_JUDICIAL(6, "Exigibilidade Suspensa por Decisão Judicial"),
	EXIGIBILIDADE_SUSPENSA_ADMINISTRATIVO(7, "Exigibilidade Suspensa por Processo Administrativo");
	
	private String label;
	private Integer codigo;
	
	private TSExigibilidadeISS(Integer codigo, String label) {
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
