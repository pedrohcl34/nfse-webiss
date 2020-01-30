package br.com.sifcon.nfs.services.response.type;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCIdentificacaoNfse extends ComplexTypeBase {

	private Long numero;
	private TCCpfCnpj cpfCnpj;
	private Long inscricaoMunicipal;
	private Integer codigoMunicipio;

	public TCIdentificacaoNfse() {

	}

	public TCIdentificacaoNfse(Long numero, TCCpfCnpj cpfCnpj, String inscricaoMunicipal, Integer codigoMunicipio) {
		this.numero = numero;
		this.cpfCnpj = cpfCnpj;
		this.inscricaoMunicipal = Long.parseLong(inscricaoMunicipal);
		this.codigoMunicipio = codigoMunicipio;
	}

	public TCIdentificacaoNfse(Element root) {
		Node nodeNumero = super.extractNodeByTag(root.getElementsByTagName("Numero"));
		Node nodeCpfCnpj = super.extractNodeByTag(root.getElementsByTagName("CpfCnpj"));
		Node nodeInscricaoMunicipal = super.extractNodeByTag(root.getElementsByTagName("InscricaoMunicipal"));
		Node nodeCodigoMunicipio = super.extractNodeByTag(root.getElementsByTagName("CodigoMunicipio"));

		this.numero = nodeNumero != null ? Long.parseLong(nodeNumero.getTextContent()) : null;
		this.cpfCnpj = new TCCpfCnpj((Element) nodeCpfCnpj);
		this.inscricaoMunicipal = nodeInscricaoMunicipal != null
				? Long.parseLong(nodeInscricaoMunicipal.getTextContent())
				: null;
		this.codigoMunicipio = nodeCodigoMunicipio != null ? Integer.parseInt(nodeCodigoMunicipio.getTextContent())
				: null;
	}

	public String generateElement() {
		return "<IdentificacaoNfse>" + (numero != null ? "<Numero>" + numero + "</Numero>" : "")
				+ (cpfCnpj != null ? cpfCnpj.generateElement() : "")
				+ (inscricaoMunicipal != null ? "<InscricaoMunicipal>" + inscricaoMunicipal + "</InscricaoMunicipal>"
						: "")
				+ (codigoMunicipio != null ? "<CodigoMunicipio>" + codigoMunicipio + "</CodigoMunicipio>" : "")
				+ "</IdentificacaoNfse>";

	}
}
