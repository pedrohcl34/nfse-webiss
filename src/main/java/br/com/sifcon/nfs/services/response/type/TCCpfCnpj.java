package br.com.sifcon.nfs.services.response.type;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.nfs.types.complex.TipoPessoa;
import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCCpfCnpj extends ComplexTypeBase {

	private String cpf;

	private String cnpj;

	public TCCpfCnpj() {

	}

	public TCCpfCnpj(TipoPessoa tipoPessoa, String cpfCnpj) {
		if (tipoPessoa == TipoPessoa.PESSOA_FISICA)
			cpf = cpfCnpj;
		else
			cnpj = cpfCnpj;
	}

	public TCCpfCnpj(Element root) {
		Node nodeCpf = super.extractNodeByTag(root.getElementsByTagName("Cpf"));
		Node nodeCnpj = super.extractNodeByTag(root.getElementsByTagName("Cnpj"));

		this.cpf = nodeCpf != null ? nodeCpf.getTextContent() : null;
		this.cnpj = nodeCnpj != null ? nodeCnpj.getTextContent() : null;
	}

	public String generateElement() {
		return "<CpfCnpj>" + (cpf != null ? "<Cpf>" + cpf + "</Cpf>" : "")
				+ (cnpj != null ? "<Cnpj>" + cnpj + "</Cnpj>" : "") + "</CpfCnpj>";
	}

}
