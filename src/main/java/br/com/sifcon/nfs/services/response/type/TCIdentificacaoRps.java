package br.com.sifcon.nfs.services.response.type;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCIdentificacaoRps extends ComplexTypeBase{
	
	private Long numero;
	
	private String serie;
	
	private Integer tipo;
	
	public TCIdentificacaoRps() {
		
	}
	
	public TCIdentificacaoRps(Element root) {
		Node nodeNumero = super.extractNodeByTag(root.getElementsByTagName("Numero"));
		Node nodeSerie = super.extractNodeByTag(root.getElementsByTagName("Serie"));
		Node nodeTipo = super.extractNodeByTag(root.getElementsByTagName("Tipo"));
		
		this.numero = nodeNumero!=null?Long.parseLong(nodeNumero.getTextContent()):null;
		this.serie = nodeSerie!=null?nodeSerie.getTextContent():null;
		this.tipo = nodeTipo!=null?Integer.parseInt(nodeTipo.getTextContent()):null;			
	}
	
	public TCIdentificacaoRps(Long numero, String serie, Integer tipo) {
		super();
		this.numero = numero;
		this.serie = serie;
		this.tipo = tipo;
	}
	
	public String generateIdentificacaoRps() {
		return "<IdentificacaoRps><Numero>"+numero+"</Numero>"
				+"<Serie>"+serie+"</Serie>"+"<Tipo>"+tipo+"</Tipo></IdentificacaoRps>";
	}
	
	
}
