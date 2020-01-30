package br.com.sifcon.nfs.services.response.type;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCEndereco extends ComplexTypeBase {
	
	private String endereco;
	
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	private Integer codigoMunicipio;
	
	private String uf;
	
	private Integer codigoPais;
	
	private String cep;
	
	public TCEndereco() {
		
	}
	
	public TCEndereco(Integer codigoPais, Integer codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
		this.codigoPais = codigoPais;
	}
	
	public TCEndereco(Element root) {
		Node nodeEndereco = super.extractNodeByTag(root.getElementsByTagName("Endereco"));
		Node nodeNumero = super.extractNodeByTag(root.getElementsByTagName("Numero"));
		Node nodeComplemento = super.extractNodeByTag(root.getElementsByTagName("Complemento"));
		Node nodeBairro = super.extractNodeByTag(root.getElementsByTagName("Bairro"));
		Node nodeCodigoMunicipio = super.extractNodeByTag(root.getElementsByTagName("CodigoMunicipio"));
		Node nodeUf = super.extractNodeByTag(root.getElementsByTagName("Uf"));
		Node nodeCodigoPais = super.extractNodeByTag(root.getElementsByTagName("CodigoPais"));
		Node nodeCep = super.extractNodeByTag(root.getElementsByTagName("Cep"));
		
		this.endereco = nodeEndereco!=null?nodeEndereco.getTextContent():null;
		this.numero = nodeNumero!=null?nodeNumero.getTextContent():null;
		this.complemento = nodeComplemento!=null?nodeComplemento.getTextContent():null;
		this.bairro = nodeBairro!=null?nodeBairro.getTextContent():null;
		this.codigoMunicipio = nodeCodigoMunicipio!=null? Integer.parseInt(nodeCodigoMunicipio.getTextContent()):null;
		this.uf = nodeUf!=null?nodeUf.getTextContent():null;
		this.codigoPais = nodeCodigoPais!=null?Integer.parseInt(nodeCodigoPais.getTextContent()):null;
		this.cep = nodeCep!=null?nodeCep.getTextContent():null;
		
	}

	public TCEndereco addEndereco(String endereco, String numero, String complemento,
			String bairro, String uf, String cep) {
		this.endereco = endereco.trim().isEmpty()? null:endereco;
		this.numero = numero.trim().isEmpty()?null: numero;
		this.bairro = bairro.trim().isEmpty()?null: bairro;
		this.uf = uf;
		this.cep = cep;
		if(cep!=null && (cep.contains("-") || cep.contains("."))) {
			this.cep = cep.replaceAll("-", "");
			this.cep = this.cep.replaceAll(".","");
		}
		this.complemento = complemento.trim().isEmpty()? null:complemento;
		return this;
	}
	
	public String gerarEndereco() {
		return "<Endereco>"+(endereco!=null?("<Endereco>"+endereco+"</Endereco>"):"")
				+(numero!=null?("<Numero>" + numero+"</Numero>"):"<Numero>S/N</Numero>")
				+(complemento!=null?("<Complemento>"+complemento+"</Complemento>"):"")
				+(bairro!=null?("<Bairro>"+bairro+"</Bairro>"):"")
				+"<CodigoMunicipio>"+codigoMunicipio+"</CodigoMunicipio>"
				+(uf!=null?("<Uf>"+uf+"</Uf>"):"")
				+"<CodigoPais>"+codigoPais+"</CodigoPais>"
				+(cep!=null? ("<Cep>"+cep+"</Cep>"):"")
				+"</Endereco>";
	}
}
