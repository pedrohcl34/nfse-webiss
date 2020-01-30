package br.com.sifcon.nfs.services.response.type;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TCInfRps extends ComplexTypeBase {

	private TCIdentificacaoRps identificacaoRps;
	
	private String dataEmissao;
	
	private Integer status;
	
	private TCIdentificacaoRps rpsSubstituido;
	
	private String id;
	

	public TCInfRps() {
		
	}
	
	public TCInfRps(TCIdentificacaoRps identificacaoRps, String dataEmissao, Integer status, String id) {
		this.identificacaoRps = identificacaoRps;
		this.dataEmissao = dataEmissao;
		this.status = status;
		this.id = id;
	}

	public TCInfRps(Element root) {

		Node nodeIdentificacaoRps = super.extractNodeByTag(root.getElementsByTagName("IdentificacaoRps"));
		Node nodeDataEmissao = super.extractNodeByTag(root.getElementsByTagName("DataEmissao"));
		Node nodeStatus = super.extractNodeByTag(root.getElementsByTagName("Status"));
		Node nodeRpsSubstituido = super.extractNodeByTag(root.getElementsByTagName("RpsSubstituido"));
		this.id = root.getAttribute("Id");
		this.identificacaoRps = nodeIdentificacaoRps!=null?new TCIdentificacaoRps((Element)nodeIdentificacaoRps ):null;
		this.dataEmissao = nodeDataEmissao!=null?nodeDataEmissao.getTextContent():null;
		this.status = nodeStatus!=null?Integer.parseInt(nodeStatus.getTextContent()):null;
		this.rpsSubstituido = nodeRpsSubstituido!=null?new TCIdentificacaoRps((Element)nodeRpsSubstituido):null;
	}

	public String gerarRps() {
		return "<Rps>" + identificacaoRps.generateIdentificacaoRps() + "<DataEmissao>" + dataEmissao
				+ "</DataEmissao><Status>" + status + "</Status></Rps>";
	}
}
