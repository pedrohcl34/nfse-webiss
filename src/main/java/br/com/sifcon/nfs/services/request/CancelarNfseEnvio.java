package br.com.sifcon.nfs.services.request;

import br.com.sifcon.nfs.exception.NfsSignException;
import br.com.sifcon.nfs.services.response.type.TCInfPedidoCancelamento;
import br.com.sifcon.nfs.sign.Signer;
import br.com.sifcon.soap.base.MetodoNfse;
import br.com.sifcon.soap.base.NfsBase;

public class CancelarNfseEnvio extends NfsBase {

	private TCInfPedidoCancelamento infPedidoCancelamento;
	private Signer signer;
	public CancelarNfseEnvio(String versao, TCInfPedidoCancelamento infPedidoCancelamento, Signer signer) {
		super(MetodoNfse.CANCELAR_NFSE.getMetodo(), versao);
		this.signer = signer;
		this.infPedidoCancelamento = infPedidoCancelamento;
	}
	
	private String gerarCancelamento(){
		return "<CancelarNfseEnvio "+NAMESPACE_ABRASF_XSD+">"
				+ (infPedidoCancelamento!=null?"<Pedido>"+infPedidoCancelamento.generateElement()+"</Pedido>":"")
				+ "</CancelarNfseEnvio>";
	}
	
	public String gerarXMLEnvio() throws NfsSignException {
		return super.createSOAPRequest(signer.signRequest(gerarCancelamento(),infPedidoCancelamento.getId(), "InfPedidoCancelamento", false));
	}
	
	

}
