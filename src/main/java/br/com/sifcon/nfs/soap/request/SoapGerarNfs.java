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
import br.com.sifcon.nfs.services.request.GerarNfseEnvio;
import br.com.sifcon.nfs.services.response.ConsultarNfseRpsResposta;
import br.com.sifcon.nfs.services.response.GerarNfseResposta;
import br.com.sifcon.nfs.services.response.ServiceResponse;
import br.com.sifcon.nfs.util.TransformXMLUtils;
import br.com.sifcon.soap.base.NfsBase;

public class SoapGerarNfs extends SoapNfsService {

	private static final long serialVersionUID = 1L;

	public SoapGerarNfs() throws SOAPException {
		super();
	}

	@Override
	public ServiceResponse processarResposta(SOAPMessage soapResponse) throws SOAPException,IOException,JAXBException, SAXException, ParserConfigurationException{
		String node = soapResponse.getSOAPBody().getFirstChild().getFirstChild().getFirstChild().getNodeValue();
		JAXBContext jaxbContext = JAXBContext.newInstance(GerarNfseResposta.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Document document = TransformXMLUtils.fromXMLToDOM(node);
		Node firstChild = document.getFirstChild();
		GerarNfseResposta resposta = (GerarNfseResposta) jaxbUnmarshaller.unmarshal(firstChild);
		resposta.setResponse(super.getSOAPResponseString(soapResponse));
		return resposta.convert();
	}

	@Override
	public String generateEnvelope(NfsBase envelopeSoapRequest) throws NfsSignException {
		GerarNfseEnvio gerarNfsEnvio = (GerarNfseEnvio) envelopeSoapRequest;
		return gerarNfsEnvio.gerarXMLEnvio();
	}

}
