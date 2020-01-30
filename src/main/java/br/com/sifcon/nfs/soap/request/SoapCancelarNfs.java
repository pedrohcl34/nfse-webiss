package br.com.sifcon.nfs.soap.request;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import br.com.sifcon.nfs.exception.NfsSignException;
import br.com.sifcon.nfs.services.request.CancelarNfseEnvio;
import br.com.sifcon.nfs.services.response.CancelarNfseResposta;
import br.com.sifcon.nfs.services.response.ConsultarNfseRpsResposta;
import br.com.sifcon.nfs.services.response.ServiceResponse;
import br.com.sifcon.nfs.util.TransformXMLUtils;
import br.com.sifcon.soap.base.NfsBase;

public class SoapCancelarNfs extends SoapNfsService {
	
	private static final long serialVersionUID = 1L;

	public SoapCancelarNfs() throws SOAPException {
		super();
	}

	@Override
	public String generateEnvelope(NfsBase envelopeSoapRequest) throws NfsSignException {
		CancelarNfseEnvio cancelarNfs = (CancelarNfseEnvio) envelopeSoapRequest;
		return cancelarNfs.gerarXMLEnvio();
	}

	@Override
	public ServiceResponse processarResposta(SOAPMessage soapResponse)
			throws SOAPException, IOException, JAXBException, SAXException, ParserConfigurationException {
		String node = soapResponse.getSOAPBody().getFirstChild().getFirstChild().getFirstChild().getNodeValue();
		JAXBContext jaxbContext = JAXBContext.newInstance(CancelarNfseResposta.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Document document = TransformXMLUtils.fromXMLToDOM(node);
		Node firstChild = document.getFirstChild();
		CancelarNfseResposta cancelar = (CancelarNfseResposta) jaxbUnmarshaller.unmarshal(firstChild);
		cancelar.setResponse(super.getSOAPResponseString(soapResponse));
		return cancelar.convert();
	}

}
