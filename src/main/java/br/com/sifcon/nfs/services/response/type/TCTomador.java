package br.com.sifcon.nfs.services.response.type;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.nfs.types.complex.TCIdentificacaoTomador;
import br.com.sifcon.nfs.types.complex.TCPrestador;
import br.com.sifcon.nfs.types.complex.TipoPessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCTomador extends TCPrestador {
	
	private TCIdentificacaoTomador identificacaoPrestador;
	
	private TCIdentificacaoTomador identificacaoTomador;
	
	private String razaoSocial; 
	
	private String nomeFantasia;
	
	private TCEndereco endereco;
	
	private TCContato contato;
	
	public TCTomador() {
		super(null, null, null);
	}
	public TCTomador(String razaoSocial, String cpfCnpj, String inscricaoMunicipal,TCContato contato,TipoPessoa tipo) {
		super(cpfCnpj, inscricaoMunicipal, tipo);
		this.razaoSocial = razaoSocial;
		this.contato = contato;
		
	}
	
	public TCTomador(Element root) {
		super(null, null, null);
		Node nodeRazaoSocial = super.extractNodeByTag(root.getElementsByTagName("RazaoSocial"));
		Node nodeIdentificacaoPrestador = super.extractNodeByTag(root.getElementsByTagName("IdentificacaoPrestador"));
		Node nodeIdentificacaoTomador = super.extractNodeByTag(root.getElementsByTagName("IdentificacaoTomador"));
		Node nodeNomeFantasia = super.extractNodeByTag(root.getElementsByTagName("NomeFantasia"));
		Node nodeEndereco = super.extractNodeByTag(root.getElementsByTagName("Endereco"));
		Node nodeContato = super.extractNodeByTag(root.getElementsByTagName("Contato"));
		
		if(nodeRazaoSocial!=null)
			this.razaoSocial = nodeRazaoSocial.getTextContent();
		if(nodeIdentificacaoPrestador!=null)
			this.identificacaoPrestador = new TCIdentificacaoTomador((Element) nodeIdentificacaoPrestador);
		if(nodeIdentificacaoTomador!=null)
			this.identificacaoTomador = new TCIdentificacaoTomador((Element) nodeIdentificacaoTomador);
		if(nodeNomeFantasia!=null)
			this.nomeFantasia = nodeNomeFantasia.getTextContent();
		if(nodeEndereco!=null)
			this.endereco = new TCEndereco((Element) nodeEndereco);
		if(nodeContato!=null)
			this.contato = new TCContato((Element) nodeContato);
		
	}
	public TCTomador addEndereco(TCEndereco endereco) {
		this.endereco = endereco;
		return this;
	}
	
	public String gerarTomador() {
		return "<Tomador><IdentificacaoTomador><CpfCnpj><"+tipo.getLabel()+">"+cpfCnpj+"</"+tipo.getLabel()+"></CpfCnpj>"
				+(inscricaoMunicipal!=null?("<InscricaoMunicipal>"+inscricaoMunicipal+"</InscricaoMunicipal>"):"")
				+ "</IdentificacaoTomador>"
				+"<RazaoSocial>" + razaoSocial+"</RazaoSocial>"
				+endereco.gerarEndereco()
				+contato.gerarContato()
				+ "</Tomador>";
	}
}
