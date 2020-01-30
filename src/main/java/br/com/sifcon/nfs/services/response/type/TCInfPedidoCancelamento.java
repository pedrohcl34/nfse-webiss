package br.com.sifcon.nfs.services.response.type;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCInfPedidoCancelamento extends ComplexTypeBase {

	private TCIdentificacaoNfse identificacaoNfse;
	private Integer codigoCancelamento;
	private String id;

	public TCInfPedidoCancelamento(Element root) {
		Node nodeIdentificacaoNfse = super.extractNodeByTag(root.getElementsByTagName("IdentificacaoNfse"));
		Node nodeCodigoCancelamento = super.extractNodeByTag(root.getElementsByTagName("codigoCancelamento"));
		this.identificacaoNfse = nodeIdentificacaoNfse != null
				? new TCIdentificacaoNfse((Element) nodeIdentificacaoNfse)
				: null;
		this.codigoCancelamento = nodeCodigoCancelamento != null
				? Integer.parseInt(nodeCodigoCancelamento.getTextContent())
				: null;
		this.id = root.getAttribute("Id");
	}

	public TCInfPedidoCancelamento(TCIdentificacaoNfse identificacaoNfse, Integer codigoCancelamento, String id) {
		this.identificacaoNfse = identificacaoNfse;
		this.codigoCancelamento = codigoCancelamento;
		this.id = id;
	}

	public String generateElement() {
		return "<InfPedidoCancelamento Id =\"" + id + "\">"
				+ (identificacaoNfse != null ? identificacaoNfse.generateElement() : "")
				+ (codigoCancelamento != null ? "<CodigoCancelamento>" + codigoCancelamento + "</CodigoCancelamento>"
						: "")
				+ "</InfPedidoCancelamento>";
	}

}
