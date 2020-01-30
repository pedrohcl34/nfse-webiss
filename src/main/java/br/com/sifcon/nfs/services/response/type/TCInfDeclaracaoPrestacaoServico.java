package br.com.sifcon.nfs.services.response.type;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.nfs.types.complex.TCIdentificacaoTomador;
import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCInfDeclaracaoPrestacaoServico extends ComplexTypeBase {

	private TCInfRps rps;

	private String competencia;

	private TCDadosServico servico;

	private TCIdentificacaoTomador prestador;

	private TCTomador tomador;

	private Integer regimeEspecialTributacao;

	private Integer optanteSimplesNacional;

	private Integer incentivoFiscal;

	private String id;

	public TCInfDeclaracaoPrestacaoServico() {

	}

	public TCInfDeclaracaoPrestacaoServico(Element root) {
		Node nodeRps = super.extractNodeByTag(root.getElementsByTagName("Rps"));
		Node nodeCompetencia = super.extractNodeByTag(root.getElementsByTagName("Competencia"));
		Node nodeServico = super.extractNodeByTag(root.getElementsByTagName("Servico"));
		Node nodePrestador = super.extractNodeByTag(root.getElementsByTagName("Prestador"));
		Node nodeTomador = super.extractNodeByTag(root.getElementsByTagName("Tomador"));
		Node nodeRegimeEspecialTributacao = super.extractNodeByTag(
				root.getElementsByTagName("RegimeEspecialTributacao"));
		Node nodeOptanteSimplesNacional = super.extractNodeByTag(root.getElementsByTagName("OptanteSimplesNacional"));
		Node nodeIncentivoFiscal = super.extractNodeByTag(root.getElementsByTagName("IncentivoFiscal"));
		this.id = root.getAttribute("Id");
		this.rps = nodeRps != null ? new TCInfRps((Element) nodeRps) : null;
		this.competencia = nodeCompetencia != null ? nodeCompetencia.getTextContent() : null;
		this.servico = nodeServico != null ? new TCDadosServico((Element) nodeServico) : null;
		this.prestador = nodePrestador != null ? new TCIdentificacaoTomador((Element) nodePrestador) : null;
		this.tomador = nodeTomador != null ? new TCTomador((Element) nodeTomador) : null;
		this.regimeEspecialTributacao = nodeRegimeEspecialTributacao != null
				? Integer.parseInt(nodeRegimeEspecialTributacao.getTextContent())
				: null;
		this.optanteSimplesNacional = nodeOptanteSimplesNacional != null
				? Integer.parseInt(nodeOptanteSimplesNacional.getTextContent())
				: null;
		this.incentivoFiscal = nodeIncentivoFiscal != null ? Integer.parseInt(nodeIncentivoFiscal.getTextContent())
				: null;
	}
}
