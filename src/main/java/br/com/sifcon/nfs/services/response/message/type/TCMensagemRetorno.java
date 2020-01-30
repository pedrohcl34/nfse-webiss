package br.com.sifcon.nfs.services.response.message.type;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.nfs.exception.NfsErrorCode;
import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCMensagemRetorno extends ComplexTypeBase {

	private String codigo;

	private String mensagem;

	private String correcao;

	public NfsErrorCode getErrorCode() {
		return NfsErrorCode.valueOf(codigo);
	}

	public TCMensagemRetorno() {

	}

	public TCMensagemRetorno(Element root) {
		Node nodeCodigo = super.extractNodeByTag(root.getElementsByTagName("Codigo"));
		Node nodeMensagem = super.extractNodeByTag(root.getElementsByTagName("Mensagem"));
		Node nodeCorrecao = super.extractNodeByTag(root.getElementsByTagName("Correcao"));

		this.codigo = nodeCodigo != null ? nodeCodigo.getTextContent() : null;
		this.mensagem = nodeMensagem != null ? nodeMensagem.getTextContent() : null;
		this.correcao = nodeCorrecao != null ? nodeCorrecao.getTextContent() : null;
	}

}
