package br.com.sifcon.nfs.services.response.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.sifcon.nfs.types.complex.TSExigibilidadeISS;
import br.com.sifcon.soap.base.ComplexTypeBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TCDadosServico extends ComplexTypeBase {

	private TCValoresDeclaracaoServico valores;
	
	private Integer issRetido;
	
	private Integer responsavelRetencao;
	
	private String itemListaServico;
	
	private Integer codigoCnae;
	
	private String codigoTributacaoMunicipio;
	
	private String discriminacao;
	
	private Integer codigoMunicipio;
	
	private String codigoPais;
	
	private TSExigibilidadeISS exigibilidadeISS;
	
	private Integer codigoExigibilidadeIss;
	
	private Integer municipioIncidencia;
	
	private String numeroProcesso;
	
	public TCDadosServico() {
		
	}
	
	public TCDadosServico(TCValoresDeclaracaoServico valores, Integer issRetido, String itemListaServico, String discriminacao, Integer codigoMunicipio, TSExigibilidadeISS exibilidadeISS) {
		this.valores = valores;
		this.issRetido = issRetido;
		this.itemListaServico = itemListaServico;
		this.discriminacao = discriminacao;
		this.codigoMunicipio = codigoMunicipio;
		this.exigibilidadeISS = exibilidadeISS;
	}
	
	public TCDadosServico(Element root) {
		Node nodeValores = super.extractNodeByTag(root.getElementsByTagName("Valores"));
		Node nodeIssRetido = super.extractNodeByTag(root.getElementsByTagName("IssRetido"));
		Node nodeResponsavelRetencao = super.extractNodeByTag(root.getElementsByTagName("ResponsavelRetencao"));
		Node nodeItemListaServico = super.extractNodeByTag(root.getElementsByTagName("ItemListaServico"));
		Node nodeCodigoCnae = super.extractNodeByTag(root.getElementsByTagName("CodigoCnae"));
		Node nodeCodigoTributacaoMunicipio = super.extractNodeByTag(root.getElementsByTagName("CodigoTributacaoMunicipio"));
		Node nodeDiscriminacao = super.extractNodeByTag(root.getElementsByTagName("Discriminacao"));
		Node nodeCodigoMunicipio = super.extractNodeByTag(root.getElementsByTagName("CodigoMunicipio"));
		Node nodeCodigoPais = super.extractNodeByTag(root.getElementsByTagName("CodigoPais"));
		Node nodeExigibilidadeIss = super.extractNodeByTag(root.getElementsByTagName("ExigibilidadeIss"));
		Node nodeMunicipioIncidencia = super.extractNodeByTag(root.getElementsByTagName("MunicipioIncidencia"));
		Node nodeNumeroProcesso = super.extractNodeByTag(root.getElementsByTagName("NumeroProcesso"));
		
		this.valores = nodeValores!=null? new TCValoresDeclaracaoServico((Element) nodeValores):null;
		this.issRetido = nodeIssRetido!=null?Integer.parseInt(nodeIssRetido.getTextContent()):null;
		this.responsavelRetencao = nodeResponsavelRetencao!=null?Integer.parseInt(nodeResponsavelRetencao.getTextContent()):null;
		this.itemListaServico = nodeItemListaServico!=null?nodeItemListaServico.getTextContent():null;
		this.codigoCnae = nodeCodigoCnae!=null?Integer.parseInt(nodeCodigoCnae.getTextContent()):null;
		this.codigoTributacaoMunicipio = nodeCodigoTributacaoMunicipio!=null?nodeCodigoTributacaoMunicipio.getTextContent():null;
		this.discriminacao = nodeDiscriminacao!=null?nodeDiscriminacao.getTextContent():null;
		this.codigoMunicipio = nodeCodigoMunicipio!=null?Integer.parseInt(nodeCodigoMunicipio.getTextContent()):null;
		this.codigoPais = nodeCodigoPais!=null?nodeCodigoPais.getTextContent():null;
		this.codigoExigibilidadeIss = nodeExigibilidadeIss!=null?Integer.parseInt(nodeExigibilidadeIss.getTextContent()):null;
		this.municipioIncidencia = nodeMunicipioIncidencia!=null?Integer.parseInt(nodeMunicipioIncidencia.getTextContent()):null;
		this.numeroProcesso = nodeNumeroProcesso!=null?nodeNumeroProcesso.getTextContent():null;
	}

	public TCDadosServico addResponsavelRetencao(Integer responsavelRetencao) {
		this.responsavelRetencao = responsavelRetencao;
		return this;
	}
	
	public TCDadosServico addCodigoCnae(Integer codigoCnae) {
		this.codigoCnae = codigoCnae;
		return this;
	}
	
	public TCDadosServico addCodigoTributacaoMunicipio(String codigoTributacaoMunicipio) {
		this.codigoTributacaoMunicipio = codigoTributacaoMunicipio;
		return this;
	}
	
	public TCDadosServico addCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
		return this;
	}
	
	public TCDadosServico addMunicipioIncidencia(Integer municipioIncidencia) {
		this.municipioIncidencia = municipioIncidencia;
		return this;
	}
	
	public TCDadosServico addNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
		return this;
	}
	
	public String gerarDadosServico() {
		StringBuilder str = new StringBuilder();
		str.append("<Servico>");
		str.append(valores.generateServicos());
		str.append("<IssRetido>"+issRetido+"</IssRetido>");
		if(responsavelRetencao!=null)str.append("<ResponsavelRetencao>"+responsavelRetencao+"</ResponsavelRetencao>");
		str.append("<ItemListaServico>" + itemListaServico +"</ItemListaServico>");
		if(codigoCnae!=null)str.append("<CodigoCnae>" + codigoCnae + "</CodigoCnae>");
		if(codigoTributacaoMunicipio!=null) str.append("<CodigoTributacaoMunicipio>"+ codigoTributacaoMunicipio+"</CodigoTributacaoMunicipio>");
		str.append("<Discriminacao>"+ discriminacao+"</Discriminacao>");
		str.append("<CodigoMunicipio>"+codigoMunicipio+"</CodigoMunicipio>");
		if(codigoPais!=null)str.append("<CodigoPais>"+ codigoPais+"</CodigoPais>");
		str.append("<ExigibilidadeISS>"+exigibilidadeISS.getCodigo()+"</ExigibilidadeISS>");
		if(municipioIncidencia!=null) str.append("<MunicipioIncidencia>"+municipioIncidencia+"</MunicipioIncidencia>");
		if(numeroProcesso!=null) str.append("<NumeroProcesso>"+numeroProcesso+"</NumeroProcesso>");
		str.append("</Servico>");
		return str.toString();
		
	}
}
