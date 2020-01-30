package br.com.sifcon.nfs.services.response.type;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCInfNfse extends ComplexTypeBase {

	private Long numero;

	private String codigoVerificacao;

	private String dataEmissao;

	private Long nfseSubstituida;

	private String outrasInformacoes;

	private TCValoresNfse valoresNfse;

	private Double valorCredito;

	private TCTomador prestadorServico;

	private TCIdentificacaoOrgaoGerador orgaoGerador;

	private TCDeclaracaoPrestacaoServico declaracaoPrestacaoServico;

	private String id;

	public TCInfNfse(Element element, Boolean mapSubClasses) {
		Node nodeNumero = super.extractNodeByTag(element.getElementsByTagName("Numero"));
		Node nodeCodigoVerificacao = super.extractNodeByTag(element.getElementsByTagName("CodigoVerificacao"));
		Node nodeDataEmissao = super.extractNodeByTag(element.getElementsByTagName("DataEmissao"));
		Node nodeNfseSubstituida = super.extractNodeByTag(element.getElementsByTagName("NfseSubstituida"));
		Node nodeOutrasInformacoes = super.extractNodeByTag(element.getElementsByTagName("OutrasInformacoes"));
		Node nodeValorCredito = super.extractNodeByTag(element.getElementsByTagName("ValorCredito"));

		this.numero = nodeNumero != null ? Long.parseLong(nodeNumero.getTextContent()) : null;
		this.id = element.getAttribute("Id");
		this.codigoVerificacao = nodeCodigoVerificacao != null ? nodeCodigoVerificacao.getTextContent() : null;
		this.dataEmissao = nodeDataEmissao != null ? nodeDataEmissao.getTextContent() : null;
		this.nfseSubstituida = nodeNfseSubstituida != null ? Long.parseLong(nodeNfseSubstituida.getTextContent())
				: null;
		this.outrasInformacoes = nodeOutrasInformacoes != null ? nodeOutrasInformacoes.getTextContent() : null;
		this.valorCredito = nodeValorCredito != null ? Double.parseDouble(nodeValorCredito.getTextContent()) : null;
		if (mapSubClasses) {
			Node nodePrestadorServico = super.extractNodeByTag(element.getElementsByTagName("PrestadorServico"));
			Node nodeOrgaoGerador = super.extractNodeByTag(element.getElementsByTagName("OrgaoGerador"));
			Node nodeDeclaracaoPrestacaoServico = super.extractNodeByTag(
					element.getElementsByTagName("DeclaracaoPrestacaoServico"));
			Node nodeValoresNfse = super.extractNodeByTag(element.getElementsByTagName("ValoresNfse"));
			if (nodeValoresNfse != null)
				this.valoresNfse = new TCValoresNfse((Element) nodeValoresNfse);
			if (nodePrestadorServico != null)
				this.prestadorServico = new TCTomador((Element) nodePrestadorServico);
			if (nodeOrgaoGerador != null)
				this.orgaoGerador = new TCIdentificacaoOrgaoGerador((Element) nodeOrgaoGerador);
			if (nodeDeclaracaoPrestacaoServico != null)
				this.declaracaoPrestacaoServico = new TCDeclaracaoPrestacaoServico(
						(Element) nodeDeclaracaoPrestacaoServico);
		}
	}

}
