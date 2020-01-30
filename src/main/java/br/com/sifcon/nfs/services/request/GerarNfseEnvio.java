package br.com.sifcon.nfs.services.request;

import br.com.sifcon.nfs.exception.NfsSignException;
import br.com.sifcon.nfs.services.response.type.TCDadosServico;
import br.com.sifcon.nfs.services.response.type.TCInfRps;
import br.com.sifcon.nfs.services.response.type.TCTomador;
import br.com.sifcon.nfs.sign.Signer;
import br.com.sifcon.nfs.types.complex.TCPrestador;
import br.com.sifcon.nfs.types.complex.TSRegimeEspecialTributacao;
import br.com.sifcon.soap.base.MetodoNfse;
import br.com.sifcon.soap.base.NfsBase;
import lombok.Getter;

public class GerarNfseEnvio extends NfsBase {

	private TCInfRps rps;
	private TCPrestador prestador;
	private TCDadosServico servico;
	private String competencia;
	private TSRegimeEspecialTributacao regimeEspecialTributacao;
	private Integer optanteSimplesNacional;
	private Integer incentivoFiscal;
	private TCTomador tomadorServico;
	private Signer signer;
	@Getter
	private String id;

	public GerarNfseEnvio( String versao, String id, Signer signer) {
		super(MetodoNfse.GERAR_NFSE.getMetodo(), versao);
		this.id  = id;
		this.signer = signer;

	}
	
	public GerarNfseEnvio addRegimeEspecialTributacao(TSRegimeEspecialTributacao regimeEspecialTributacao) {
		this.regimeEspecialTributacao = regimeEspecialTributacao;
		return this;
	}

	public GerarNfseEnvio addParametros(TCInfRps rps, String competencia, Integer optanteSimplesNacional,
			Integer incentivoFiscal, TCPrestador prestador, TCTomador tomadorServicos, TCDadosServico servico) {
		this.competencia = competencia;
		this.optanteSimplesNacional = optanteSimplesNacional;
		this.incentivoFiscal = incentivoFiscal;
		this.prestador = prestador;
		this.tomadorServico = tomadorServicos;
		this.servico = servico;
		this.rps = rps;
		return this;
	}
		

	private String gerarDeclaracaoPrestacaoServico() throws NfsSignException {
		return "<GerarNfseEnvio "+NAMESPACE_ABRASF_XSD+">"
				+ "<Rps><InfDeclaracaoPrestacaoServico Id=\""+id+"\">"
				+rps.gerarRps()+ "<Competencia>" + competencia + "</Competencia>" + servico.gerarDadosServico()
				+ prestador.gerarPrestador() + tomadorServico.gerarTomador()
				+(regimeEspecialTributacao!=null ? ("<RegimeEspecialTributacao>"+regimeEspecialTributacao.getCodigo()+"</RegimeEspecialTributacao>"):"")
				+ "<OptanteSimplesNacional>"
				+ optanteSimplesNacional + "</OptanteSimplesNacional>" + "<IncentivoFiscal>" + incentivoFiscal
				+ "</IncentivoFiscal></InfDeclaracaoPrestacaoServico></Rps>"
				+ "</GerarNfseEnvio>";
	}
	
	public String gerarXMLEnvio() throws NfsSignException {
		return super.createSOAPRequest(signer.signRequest(gerarDeclaracaoPrestacaoServico(),id, "InfDeclaracaoPrestacaoServico", false));
	}

}
