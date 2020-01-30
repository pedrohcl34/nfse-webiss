package br.com.sifcon.nfs.services.response.type;

import org.w3c.dom.Element;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCNfse {

	private String versao;
	
	private TCInfNfse infNfse;
	
	public TCNfse() {
		
	}

	public TCNfse(Element firstChild) {
		this.versao = firstChild.getAttribute("versao");
		this.infNfse = new TCInfNfse((Element) firstChild.getFirstChild(), true);	
	}
}
