package br.com.sifcon.nfs.services.response;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;

import br.com.sifcon.nfs.services.response.message.type.ListaMensagemAlertaRetorno;
import br.com.sifcon.nfs.services.response.type.CompNfse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ConsultarNfseRpsResposta", namespace = "http://www.abrasf.org.br/nfse.xsd")
public class ConsultarNfseRpsResposta implements ServiceResponse {

	private CompNfse compNfse;
	private String response;

	@XmlAnyElement(lax = true)
	private Object element;

	private ListaMensagemAlertaRetorno listaMensagemRetorno;

	public ConsultarNfseRpsResposta convert() throws JAXBException {
		Element element = (Element) this.element;
		if (element.getNodeName().equalsIgnoreCase("CompNfse")) {
			this.compNfse = new CompNfse(element);
		} else {
			this.listaMensagemRetorno = new ListaMensagemAlertaRetorno(element);
		}
		
		return this;
	}

	public ConsultarNfseRpsResposta() {

	}

	@Override
	public void setResponse(String response) {
		this.response = response;
		
	}

	@Override
	public String getResponse() {
		return this.response;
	}
}
