package br.com.sifcon.nfs.types.complex;

import br.com.sifcon.soap.base.ComplexTypeBase;

public class TCPrestador extends ComplexTypeBase {

	protected String cpfCnpj;
	protected String inscricaoMunicipal;
	protected TipoPessoa tipo;
	
	public TCPrestador(String cpfCnpj, String inscricaoMunicipal, TipoPessoa tipo) {
		this.cpfCnpj = cpfCnpj;
		this.inscricaoMunicipal = inscricaoMunicipal;
		this.tipo = tipo;
	}
	
	
	public String gerarPrestador() {
		return "<Prestador><CpfCnpj><"+tipo.getLabel()+">"+cpfCnpj+"</"+tipo.getLabel()+"></CpfCnpj>"
				+(inscricaoMunicipal!=null?("<InscricaoMunicipal>"+inscricaoMunicipal+"</InscricaoMunicipal>"):"")
				+ "</Prestador>";
	}
	
}
