package br.com.sifcon.nfs.services.response.type;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.nfs.services.response.message.type.ListaMensagemAlertaRetorno;
import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaNfse  extends ComplexTypeBase{

	private CompNfse compNfse;
	
	private ListaMensagemAlertaRetorno listaMensagemAlertaRetorno;
	
	public ListaNfse() {
		
	}

	public ListaNfse(Element element) {
		Node nodeCompNfse = super.extractNodeByTag(element.getElementsByTagName("CompNfse"));
		Node nodeListaMensagem = super.extractNodeByTag(element.getElementsByTagName("ListaMensagemAlertaRetorno"));
		
		compNfse = nodeCompNfse!=null?new CompNfse((Element)nodeCompNfse):null;
		this.listaMensagemAlertaRetorno = nodeListaMensagem!=null?new ListaMensagemAlertaRetorno((Element)nodeListaMensagem):null;
	}
}
