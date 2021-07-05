package br.com.sifcon.soap;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import org.xml.sax.SAXException;

import br.com.sifcon.nfs.exception.NfsSignException;
import br.com.sifcon.nfs.services.request.CancelarNfseEnvio;
import br.com.sifcon.nfs.services.request.ConsultarNfseRpsEnvio;
import br.com.sifcon.nfs.services.request.GerarNfseEnvio;
import br.com.sifcon.nfs.services.response.CancelarNfseResposta;
import br.com.sifcon.nfs.services.response.ConsultarNfseRpsResposta;
import br.com.sifcon.nfs.services.response.GerarNfseResposta;
import br.com.sifcon.nfs.services.response.type.TCContato;
import br.com.sifcon.nfs.services.response.type.TCCpfCnpj;
import br.com.sifcon.nfs.services.response.type.TCEndereco;
import br.com.sifcon.nfs.services.response.type.TCIdentificacaoNfse;
import br.com.sifcon.nfs.services.response.type.TCIdentificacaoRps;
import br.com.sifcon.nfs.services.response.type.TCInfPedidoCancelamento;
import br.com.sifcon.nfs.services.response.type.TCInfRps;
import br.com.sifcon.nfs.services.response.type.TCTomador;
import br.com.sifcon.nfs.services.response.type.TCValoresDeclaracaoServico;
import br.com.sifcon.nfs.services.response.type.TCDadosServico;
import br.com.sifcon.nfs.sign.Signer;
import br.com.sifcon.nfs.soap.request.SoapCancelarNfs;
import br.com.sifcon.nfs.soap.request.SoapConsultarNfs;
import br.com.sifcon.nfs.soap.request.SoapGerarNfs;
import br.com.sifcon.nfs.types.complex.TCPrestador;
import br.com.sifcon.nfs.types.complex.TSExigibilidadeISS;
import br.com.sifcon.nfs.types.complex.TSRegimeEspecialTributacao;
import br.com.sifcon.nfs.types.complex.TipoPessoa;
import br.com.sifcon.nfs.util.KeyUtils;

public class TestarTiposComplexos {

	public static void main(String args[]) {
		
		try {
			Signer signer = new Signer(KeyUtils.getDataFromFile("caminho do certificado"), "xxxxxx");
			CancelarNfseEnvio cancelarNfse = new CancelarNfseEnvio("2.02", new TCInfPedidoCancelamento(new TCIdentificacaoNfse(201900000000005L, new TCCpfCnpj(TipoPessoa.PESSOA_JURIDICA, "21167441000152"), "21167441000152", 9999999), 1, "1"), signer);
			SoapCancelarNfs soapCancelar = new SoapCancelarNfs();
			soapCancelar.configurarSoapConnection("https://homologacao.webiss.com.br/", "ws/nfse.asmx");
			CancelarNfseResposta resposta =  (CancelarNfseResposta) soapCancelar.onRequest(cancelarNfse,"http://nfse.abrasf.org.br/CancelarNfse");
			//.out.println(nfsConsultarEnvio.gerarXMLEnvio());
			System.out.println(resposta.getListaMensagemRetorno());
		} catch (NfsSignException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
