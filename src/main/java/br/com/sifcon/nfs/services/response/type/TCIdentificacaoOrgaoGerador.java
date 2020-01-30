package br.com.sifcon.nfs.services.response.type;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCIdentificacaoOrgaoGerador extends ComplexTypeBase {

	private Integer codigoMunicipio;

	private String uf;

	public TCIdentificacaoOrgaoGerador() {

	}

	public TCIdentificacaoOrgaoGerador(Element root) {
		Node nodeCodigoMunicipio = super.extractNodeByTag(root.getElementsByTagName("CodigoMunicipio"));
		Node nodeUf = super.extractNodeByTag(root.getElementsByTagName("Uf"));

		this.codigoMunicipio = nodeCodigoMunicipio != null ? Integer.parseInt(nodeCodigoMunicipio.getTextContent())
				: null;
		this.uf = nodeUf != null ? nodeUf.getTextContent() : null;
	}
}
