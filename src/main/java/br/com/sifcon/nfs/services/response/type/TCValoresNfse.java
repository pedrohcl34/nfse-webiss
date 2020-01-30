package br.com.sifcon.nfs.services.response.type;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCValoresNfse extends ComplexTypeBase {
	
	private Double baseCalculo;
	
	private Double aliquota;
	
	private Double valorIss;
	
	private Double valorLiquidoNfse;
	
	public  TCValoresNfse() {
		
	}

	public TCValoresNfse(Element root) {
		Node nodeBaseCalculo = super.extractNodeByTag(root.getElementsByTagName("BaseCalculo"));
		Node nodeAliquota = super.extractNodeByTag(root.getElementsByTagName("Aliquota"));
		Node nodeValorIss = super.extractNodeByTag(root.getElementsByTagName("ValorIss"));
		Node nodeValorLiquidoNfse = super.extractNodeByTag(root.getElementsByTagName("ValorLiquidoNfse"));
		
		this.baseCalculo = nodeBaseCalculo!=null?Double.parseDouble(nodeBaseCalculo.getTextContent()):null;
		this.aliquota = nodeAliquota!=null?Double.parseDouble(nodeAliquota.getTextContent()):null;
		this.valorIss = nodeValorIss!=null?Double.parseDouble(nodeValorIss.getTextContent()):null;
		this.valorLiquidoNfse = nodeValorLiquidoNfse!=null?Double.parseDouble(nodeValorLiquidoNfse.getTextContent()):null;

	}
}
