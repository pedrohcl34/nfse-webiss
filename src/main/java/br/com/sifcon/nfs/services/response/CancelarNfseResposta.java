package br.com.sifcon.nfs.services.response;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;

import br.com.sifcon.nfs.services.response.message.type.ListaMensagemAlertaRetorno;
import br.com.sifcon.nfs.services.response.type.ListaNfse;
import br.com.sifcon.nfs.services.response.type.TCInfPedidoCancelamento;
import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CancelarNfseResposta", namespace = "http://www.abrasf.org.br/nfse.xsd")
public class CancelarNfseResposta extends ComplexTypeBase implements ServiceResponse {

	private String response;

	private TCInfPedidoCancelamento infPedidoCancelamento;

	private ListaMensagemAlertaRetorno listaMensagemRetorno;

	@XmlAnyElement(lax = true)
	private Object element;

	public CancelarNfseResposta convert() throws JAXBException {
		Element element = (Element) this.element;
		if (element.getNodeName().equalsIgnoreCase("RetCancelamento")) {
			this.infPedidoCancelamento = new TCInfPedidoCancelamento(
					(Element) super.extractNodeByTag(element.getElementsByTagName("InfPedidoCancelamento")));
		} else {
			this.listaMensagemRetorno = new ListaMensagemAlertaRetorno(element);
		}
		return this;
	}

	@Override
	public void setResponse(String response) {
		this.response = response;

	}

	@Override
	public String getResponse() {
		return this.getResponse();
	}

}
