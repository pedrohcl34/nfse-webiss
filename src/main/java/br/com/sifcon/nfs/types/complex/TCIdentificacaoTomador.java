package br.com.sifcon.nfs.types.complex;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.nfs.services.response.type.TCCpfCnpj;
import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCIdentificacaoTomador extends ComplexTypeBase {

	private TCCpfCnpj cpfCnpj;
	
	private String inscricaoMunicipal;
	
	public TCIdentificacaoTomador(Element root) {
		Node nodeCpfCnpj = super.extractNodeByTag(root.getElementsByTagName("CpfCnpj"));
		Node nodeInscricaoMunicipal = super.extractNodeByTag(root.getElementsByTagName("InscricaoMunicipal"));
		
		this.cpfCnpj = nodeCpfCnpj!=null? new TCCpfCnpj((Element) nodeCpfCnpj):null;
		this.inscricaoMunicipal = nodeInscricaoMunicipal!=null ? nodeInscricaoMunicipal.getTextContent():null;
	}
}
