package br.com.sifcon.nfs.services.response.type;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCDeclaracaoPrestacaoServico extends ComplexTypeBase {

	private TCInfDeclaracaoPrestacaoServico infDeclaracaoPrestacaoServico;
	
	
	public TCDeclaracaoPrestacaoServico() {
		
	}

	public TCDeclaracaoPrestacaoServico(Element root) {
		Node nodeInfDeclaracaoPrestacaoServico = super.extractNodeByTag(root.getElementsByTagName("InfDeclaracaoPrestacaoServico"));
		this.infDeclaracaoPrestacaoServico = nodeInfDeclaracaoPrestacaoServico!=null?new TCInfDeclaracaoPrestacaoServico((Element)nodeInfDeclaracaoPrestacaoServico):null;
		
	}
}
