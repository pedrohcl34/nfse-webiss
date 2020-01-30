package br.com.sifcon.nfs.services.request;

import br.com.sifcon.nfs.exception.NfsSignException;
import br.com.sifcon.nfs.services.response.type.TCIdentificacaoRps;
import br.com.sifcon.nfs.types.complex.TCPrestador;
import br.com.sifcon.soap.base.MetodoNfse;
import br.com.sifcon.soap.base.NfsBase;

public class ConsultarNfseRpsEnvio extends NfsBase {

	private TCIdentificacaoRps identificaoRps;
	private TCPrestador prestador;
	
	public ConsultarNfseRpsEnvio(String versao, TCIdentificacaoRps identificaoRps, TCPrestador prestador) {
		super(MetodoNfse.CONSULTAR_NFSE.getMetodo(), versao);
		this.identificaoRps  = identificaoRps;
		this.prestador = prestador;
	}
	
	private String gerarConsulta() {
		return "<ConsultarNfseRpsEnvio "+NAMESPACE_ABRASF_XSD+">"
				+ identificaoRps.generateIdentificacaoRps()
				+ prestador.gerarPrestador()
				+"</ConsultarNfseRpsEnvio>";
	}
	
	public String gerarXMLEnvio() throws NfsSignException {
		return super.createSOAPRequest(gerarConsulta());
	}
	
	

}
