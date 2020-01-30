package br.com.sifcon.nfs.sign;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.Collections;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.sifcon.nfs.exception.NfsSignException;
import br.com.sifcon.nfs.util.KeyUtils;
import br.com.sifcon.nfs.util.TransformXMLUtils;

public class Signer {

	private PrivateKey privateKey;
	private XMLSignatureFactory xmlSignatureFactory;
	private XMLSignature xmlSignature;
	private KeyInfo keyInfo;
	private X509Certificate certificate;

	@SuppressWarnings("static-access")
	public Signer(byte[] privateKeyData, String password) throws NfsSignException {

		try {
			privateKey = KeyUtils.loadPrivateKey(privateKeyData, password.toCharArray());
			certificate = KeyUtils.retrieveCertificate(privateKeyData, password.toCharArray());
		} catch (UnrecoverableKeyException e) {
			throw new NfsSignException("Não foi possível ler a chave", "Chave corrompida!", e);
		} catch (KeyStoreException e) {
			throw new NfsSignException("Não foi possível encontrar a KeyStore", "KeyStore não localizada!", e);
		} catch (NoSuchAlgorithmException e) {
			throw new NfsSignException("Não foi possível decriptar a chave", "Algoritmo não identificado!", e);
		} catch (CertificateException e) {
			throw new NfsSignException("Não foi possível validar a chave", "Certificado Inválido ou expitrado!", e);
		} catch (NoSuchProviderException e) {
			throw new NfsSignException("Não foi possível obter a chave", "Configuração não encontrada!", e);
		} catch (IOException e) {
			throw new NfsSignException("Não foi possível obter a chave", "Arquivo não encontrado", e);
		}

		xmlSignatureFactory = XMLSignatureFactory.getInstance("DOM");
		keyInfo = KeyUtils.retrieveKeyInfoCertificate(certificate, xmlSignatureFactory.getKeyInfoFactory());

	}
	
	private void configureReference(String idElemento) throws NfsSignException {
		try {
			Reference reference = xmlSignatureFactory.newReference(idElemento,
					xmlSignatureFactory.newDigestMethod(DigestMethod.SHA1, null),
					Collections.singletonList(
							xmlSignatureFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
					null, null);
			SignedInfo signedInfo = xmlSignatureFactory.newSignedInfo(
					xmlSignatureFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,
							(C14NMethodParameterSpec) null),
					xmlSignatureFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
					Collections.singletonList(reference));
			
			xmlSignature = xmlSignatureFactory.newXMLSignature(signedInfo, keyInfo);
		} catch (NoSuchAlgorithmException e) {
			throw new NfsSignException("Não foi possível decriptar a chave", "Algoritmo não identificado!", e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new NfsSignException("Não foi possível decriptar a chave", "Algoritmo não suportado!", e);
		}
	}

	public String signRequest(String xmlEnvelope, String idElementoAssinatura, String tagName, Boolean removeHeader) throws NfsSignException {
		
		Document document = null;
		try {
			document = TransformXMLUtils.fromXMLToDOM(xmlEnvelope);
		} catch (SAXException e) {
			throw new NfsSignException("Não foi possível converter Documento", "Documento XML inválido!", e);
		} catch (IOException e) {
			throw new NfsSignException("Não foi possível localizar o arquivo", "Arquivo XML inválido!", e);
		} catch (ParserConfigurationException e) {
			throw new NfsSignException("Não foi possível converter o Documento",
					"XML contém informações inválidas ou inesperadas!", e);
		}
		return signRequest(document, idElementoAssinatura, tagName, removeHeader);
	}
	
	public String signRequest(Document document, String idElementoAssinatura, String tagName, Boolean removeHeader) throws NfsSignException {
		NodeList elements = document.getElementsByTagName(tagName);
		org.w3c.dom.Element el = (org.w3c.dom.Element) elements.item(0);
		el.setIdAttribute("Id",true);
		DOMSignContext domSignContext = null;
			
			domSignContext = new DOMSignContext(privateKey, el.getParentNode());
		
		try {
			configureReference("#"+idElementoAssinatura);
			xmlSignature.sign(domSignContext);
		} catch (MarshalException e) {
			throw new NfsSignException("Não foi possível extrair XML", "XML mal escrito ou não identificado!", e);
		} catch (XMLSignatureException e) {
			throw new NfsSignException("Impossível assinar arquivo", "Configuração de assinador não detectada ou inválida!", e);
		}
		try {
			return TransformXMLUtils.fromDOMToXML(document, removeHeader);
		} catch (TransformerException e) {
			throw new NfsSignException("Ocorreu uma falha ao converter arquivo", "Não é possível transformar o Documento em texto", e);
		}
	}

}
