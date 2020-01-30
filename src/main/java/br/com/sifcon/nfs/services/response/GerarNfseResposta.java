package br.com.sifcon.nfs.services.response;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;

import br.com.sifcon.nfs.services.response.message.type.ListaMensagemAlertaRetorno;
import br.com.sifcon.nfs.services.response.type.ListaNfse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GerarNfseResposta", namespace = "http://www.abrasf.org.br/nfse.xsd")
public class GerarNfseResposta implements ServiceResponse{
	
	private String response;
	
	private ListaNfse listaNfse;
	
	private ListaMensagemAlertaRetorno listaMensagemRetorno;

	@XmlAnyElement(lax = true)
	private Object element;
	
	public GerarNfseResposta convert() throws JAXBException {
		Element element = (Element) this.element;
		if (element.getNodeName().equalsIgnoreCase("ListaNfse")) {
			this.listaNfse = new ListaNfse(element);
		} else {
			this.listaMensagemRetorno = new ListaMensagemAlertaRetorno(element);
		}
		return this;
	}
	public GerarNfseResposta() {
		
	}
	@Override
	public void setResponse(String response) {
		this.response = response;
		
	}
	@Override
	public String getResponse() {
		return response;
	}
	
}
