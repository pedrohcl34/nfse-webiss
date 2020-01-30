package br.com.sifcon.soap.base;

public abstract class NfsBase {
	
	private StringBuilder envelope;
	
	protected static final String NAMESPACE_ENVELOPE = "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"";
	protected static final String NAMESPACE_ABRASF = "xmlns=\"http://nfse.abrasf.org.br\"";
	protected static final String NAMESPACE_NFSE = "xmlns:nfse=\"http://nfse.abrasf.org.br\"";
	protected static final String NAMESPACE_ABRASF_XSD = "xmlns=\"http://www.abrasf.org.br/nfse.xsd\"";
	protected static final String DADOS_MSG = "$MENSAGEM$";
	
	public NfsBase(String metodoAction, String versao) {
		envelope = new StringBuilder();
		envelope.append(beginEnvelope(metodoAction));
		envelope.append(generateCabecalho(versao));
		envelope.append(generateMensagem());
		envelope.append(endEnvelope(metodoAction));	
	}
	
	private String generateCabecalho(String versao) {
		return  "<nfseCabecMsg><![CDATA[<cabecalho "+NAMESPACE_ABRASF_XSD+" "+ "versao=\""+versao+"\">"
				+ "<versaoDados>"+ versao+"</versaoDados></cabecalho>]]></nfseCabecMsg>";
	}
	
	private String beginEnvelope(String metodoAction) {
		return "<soapenv:Envelope "+NAMESPACE_ENVELOPE+" "+ NAMESPACE_NFSE +"><soapenv:Body>"
				+ "<nfse:"+metodoAction+">";
	}
	
	private String generateMensagem() {
		return "<nfseDadosMsg><![CDATA["+DADOS_MSG+"]]></nfseDadosMsg>";
	}
	
	private String endEnvelope(String metodoAction) {
		return "</nfse:"+metodoAction+">" + "</soapenv:Body>"+"</soapenv:Envelope>";
	}
	
	protected String createSOAPRequest(String dados) {
		return envelope.toString().replace(DADOS_MSG, dados);
	}
	
	
	
	
	
}
