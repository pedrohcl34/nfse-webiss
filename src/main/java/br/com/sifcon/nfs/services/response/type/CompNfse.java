package br.com.sifcon.nfs.services.response.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.w3c.dom.Element;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class CompNfse {

	@XmlElement(name = "Nfse", required = true)
	private TCNfse nfse;
	
	public CompNfse() {
		
	}
	
	public CompNfse(Element element) {
		nfse = new TCNfse((Element) element.getFirstChild());
	}
	
}
