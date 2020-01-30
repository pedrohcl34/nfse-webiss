package br.com.sifcon.nfs.services.response.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCValoresDeclaracaoServico extends ComplexTypeBase {

	private Double valorServicos;
	
	private Double valorDeducoes;
	
	private Double valorPis;
	
	private Double valorCofins;
	
	private Double valorInss;
	
	private Double valorIr;
	
	private Double valorCsll;
	
	private Double outrasRetencoes;
	
	private Double valorIss;
	
	private Double aliquota;
	
	private Double descontoIncondicionado;
	
	private Double descontoCondicionado;
	
	public TCValoresDeclaracaoServico() {
		
	}

	public TCValoresDeclaracaoServico(Double valorServicos, Double aliquota, Double valorIss) {
		this.valorServicos = valorServicos;
		this.aliquota = aliquota;
		this.valorIss = valorIss;
	}

	public TCValoresDeclaracaoServico(Element root) {
		Node nodeValorServicos = super.extractNodeByTag(root.getElementsByTagName("ValorServicos"));
		Node nodeValorDeducoes = super.extractNodeByTag(root.getElementsByTagName("ValorDeducoes"));
		Node nodeValorPis = super.extractNodeByTag(root.getElementsByTagName("ValorPis"));
		Node nodeValorCofins = super.extractNodeByTag(root.getElementsByTagName("ValorCofins"));
		Node nodeValorInss = super.extractNodeByTag(root.getElementsByTagName("ValorInss"));
		Node nodeValorIr = super.extractNodeByTag(root.getElementsByTagName("ValorIr"));
		Node nodeValorCsll = super.extractNodeByTag(root.getElementsByTagName("ValorCsll"));
		Node nodeOutrasRetencoes = super.extractNodeByTag(root.getElementsByTagName("OutrasRetencoes"));
		Node nodeValorIss = super.extractNodeByTag(root.getElementsByTagName("ValorIss"));
		Node nodeAliquota = super.extractNodeByTag(root.getElementsByTagName("ValorIss"));
		Node nodeDescontoIncondicionado = super.extractNodeByTag(root.getElementsByTagName("DescontoIncondicionado"));
		Node nodeDescontoCondicionado = super.extractNodeByTag(root.getElementsByTagName("DescontoCondicionado"));
		
		this.valorServicos = nodeValorServicos!=null?Double.parseDouble(nodeValorServicos.getTextContent()):null;
		this.valorDeducoes = nodeValorDeducoes!=null?Double.parseDouble(nodeValorDeducoes.getTextContent()):null;
		this.valorPis = nodeValorPis!=null?Double.parseDouble(nodeValorPis.getTextContent()):null;
		this.valorCofins = nodeValorCofins!=null?Double.parseDouble(nodeValorCofins.getTextContent()):null;
		this.valorInss = nodeValorInss!=null?Double.parseDouble(nodeValorInss.getTextContent()):null;
		this.valorIr = nodeValorIr!=null?Double.parseDouble(nodeValorIr.getTextContent()):null;
		this.valorCsll = nodeValorCsll!=null?Double.parseDouble(nodeValorCsll.getTextContent()):null;
		this.outrasRetencoes = nodeOutrasRetencoes!=null?Double.parseDouble(nodeOutrasRetencoes.getTextContent()):null;
		this.valorIss = nodeValorIss!=null?Double.parseDouble(nodeValorIss.getTextContent()):null;
		this.aliquota = nodeAliquota!=null?Double.parseDouble(nodeAliquota.getTextContent()):null;
		this.descontoCondicionado = nodeDescontoCondicionado!=null?Double.parseDouble(nodeDescontoCondicionado.getTextContent()):null;
		this.descontoIncondicionado = nodeDescontoIncondicionado!=null?Double.parseDouble(nodeDescontoIncondicionado.getTextContent()):null;
		
	}

	public TCValoresDeclaracaoServico addValorDeducoes(Double valorDeducoes) {
		this.valorDeducoes = valorDeducoes;
		return this;
	}

	public TCValoresDeclaracaoServico addValorPis(Double valorPis) {
		this.valorPis = valorPis;
		return this;
	}

	public TCValoresDeclaracaoServico addValorCofins(Double valorCofins) {
		this.valorCofins = valorCofins;
		return this;
	}

	public TCValoresDeclaracaoServico addValorInss(Double valorInss) {
		this.valorInss = valorInss;
		return this;
	}

	public TCValoresDeclaracaoServico addValorIr(Double valorIr) {
		this.valorIr = valorIr;
		return this;
	}

	public TCValoresDeclaracaoServico addValorCsll(Double valorCsll) {
		this.valorCsll = valorCsll;
		return this;
	}

	public TCValoresDeclaracaoServico addOutrasRetencoes(Double outrasRetencoes) {
		this.outrasRetencoes = outrasRetencoes;
		return this;
	}

	public TCValoresDeclaracaoServico addDescontoIncondicionado(Double descontoIncondicionado) {
		this.descontoIncondicionado = descontoIncondicionado;
		return this;
	}

	public TCValoresDeclaracaoServico addDescontoCondicionado(Double descontoCondicionado) {
		this.descontoCondicionado = descontoCondicionado;
		return this;
	}

	public String generateServicos() {
		StringBuilder str = new StringBuilder();
		str.append("<Valores>");
		str.append("<ValorServicos>" + valorServicos + "</ValorServicos>");
		if (valorDeducoes != null)
			str.append("<ValorDeducoes>" + valorDeducoes + "</ValorDeducoes>");
		if (valorPis != null)
			str.append("<ValorPis>" + valorPis + "</ValorPis>");
		if (valorCofins != null)
			str.append("<ValorCofins>" + valorCofins + "</ValorCofins>");
		if (valorInss != null)
			str.append("<ValorInss>" + valorInss + "</ValorInss>");
		if (valorIr != null)
			str.append("<ValorIr>" + valorIr + "</ValorIr>");
		if (valorCsll != null)
			str.append("<ValorCsll>" + valorCsll + "</ValorCsll>");
		if (outrasRetencoes != null)
			str.append("<OutrasRetencoes>" + outrasRetencoes + "</OutrasRetencoes>");
		if (valorIss != null)
			str.append("<ValorIss>" + valorIss + "</ValorIss>");
		if (aliquota != null)
			str.append("<Aliquota>" + aliquota + "</Aliquota>");
		if (descontoIncondicionado != null)
			str.append("<DescontoIncondicionado>" + descontoIncondicionado + "</DescontoIncondicionado>");
		if (descontoCondicionado != null)
			str.append("<DescontoCondicionado>" + descontoCondicionado + "</DescontoCondicionado>");
		str.append("</Valores>");
		return str.toString();
	}

}
