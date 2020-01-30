package br.com.sifcon.nfs.services.response.type;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCContato extends ComplexTypeBase {
	
	private String email;
	
	private String telefone;
	
	public TCContato() {
		
	}
	public TCContato(String email, String telefone) {
		this.email = email;
		this.telefone = telefone;
	}

	public TCContato(Element root) {
		Node nodeEmail = super.extractNodeByTag(root.getElementsByTagName("Email"));
		Node nodeTelefone = super.extractNodeByTag(root.getElementsByTagName("Telefone"));
		
		this.email = nodeEmail!=null?nodeEmail.getTextContent():null;
		this.telefone = nodeTelefone!=null?nodeTelefone.getTextContent():null;
		
	}
	public String gerarContato() {
		return "<Contato><Telefone>"+telefone+"</Telefone>"
				+"<Email>"+email+"</Email>"
				+ "</Contato>";
	}

}
