package br.com.sifcon.nfs.soap.request;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.xml.sax.SAXException;

import br.com.sifcon.nfs.exception.NfsSignException;
import br.com.sifcon.nfs.services.response.ServiceResponse;
import br.com.sifcon.soap.base.NfsBase;
import lombok.Getter;
import lombok.Setter;

public abstract class SoapNfsService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private String baseUrl;

	@Getter
	@Setter
	private String baseUrlAction;

	@Getter
	@Setter
	private SOAPConnectionFactory factory;
	
	@Getter
	@Setter
	private SOAPConnection connection;

	public SoapNfsService() throws SOAPException {
		this.factory = SOAPConnectionFactory.newInstance();
	}

	public SoapNfsService configurarSoapConnection(String baseUrl, String baseUrlAction) {
		this.baseUrl = baseUrl;
		this.baseUrlAction = baseUrlAction;
		return this;
	}
	
	protected SOAPMessage sendRequest(NfsBase rawSoap, String soapAction) throws SOAPException, IOException, NfsSignException {
		SOAPMessage response = createRequest(generateEnvelope(rawSoap), soapAction);
		connection = factory.createConnection();
		return connection.call(response, baseUrl + baseUrlAction);
	}
	
	public ServiceResponse onRequest(NfsBase rawSoap, String soapAction) throws SAXException, SOAPException, IOException, JAXBException, NfsSignException, ParserConfigurationException {
		return processarResposta(sendRequest(rawSoap, soapAction));
	}
	
	public abstract String generateEnvelope(NfsBase envelopeSoapRequest) throws NfsSignException;
	
	public abstract ServiceResponse processarResposta(SOAPMessage soapResponse)  throws SOAPException,IOException,JAXBException, SAXException, ParserConfigurationException;

	private SOAPMessage createRequest(String xmlContent, String soapAction) throws IOException, SOAPException {
		ByteArrayInputStream is = new ByteArrayInputStream(xmlContent.getBytes());
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage requestMsg = messageFactory.createMessage(generateHeaders(soapAction), is);
		requestMsg.saveChanges();
		return requestMsg;
	}
	
	private MimeHeaders generateHeaders(String soapAction) {
		MimeHeaders mimeHeaders = new MimeHeaders();
		mimeHeaders.addHeader("SOAPAction", soapAction);
		mimeHeaders.addHeader("Content-Type", "text/xml; charset=UTF-8");
		return mimeHeaders;
	}
	
	 protected String getSOAPResponseString(SOAPMessage reply) throws SOAPException, IOException {
	        ByteArrayOutputStream os = new ByteArrayOutputStream();
	        String strSOAPResponse = "";
	        reply.writeTo(os);
	        strSOAPResponse = new String(os.toByteArray());
	        return strSOAPResponse;
	    }

}
