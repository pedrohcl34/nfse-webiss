package br.com.sifcon.nfs.services.response.message.type;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ListaMensagemAlertaRetorno {
	
	private List<TCMensagemRetorno> mensagemRetorno;
	
	public ListaMensagemAlertaRetorno() {
		
	}
	
	public TCMensagemRetorno getNextMessage() {
		TCMensagemRetorno mensagem = mensagemRetorno.get(0);
		if (mensagem.getCodigo().equals("L000") && mensagemRetorno.size()>1)
				return mensagemRetorno.get(1);
		return mensagem;
	}

	public ListaMensagemAlertaRetorno(Element element) {
		NodeList listMensagens = element.getElementsByTagName("MensagemRetorno");
		if(listMensagens!=null && listMensagens.getLength()>0) {
			this.mensagemRetorno = new ArrayList<>();
			for(int i =0; i <listMensagens.getLength();i++) {
				mensagemRetorno.add(new TCMensagemRetorno((Element)listMensagens.item(i)));
			}
		}
	}
}
