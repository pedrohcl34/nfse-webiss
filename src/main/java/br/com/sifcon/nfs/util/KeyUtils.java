package br.com.sifcon.nfs.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.keyinfo.X509Data;

public class KeyUtils {

	public static PrivateKey loadPrivateKey(byte[] keyData, char[] password) throws IOException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, NoSuchProviderException, UnrecoverableKeyException {
		ByteArrayInputStream in = new ByteArrayInputStream(keyData);
		KeyStore store = KeyStore.getInstance("pkcs12", "SunJSSE");
		store.load(in, password);
		Enumeration<String> aliases = store.aliases();
		String alias = aliases.nextElement();
		return (PrivateKey) store.getKey(alias, password);
	}

	public static PublicKey loadPublicKey(byte[] keyData) throws CertificateException, FileNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(keyData);
		CertificateFactory f = CertificateFactory.getInstance("X.509");
		X509Certificate certificate = (X509Certificate) f.generateCertificate(in);
		return certificate.getPublicKey();
	}
	
	public static PublicKey generatePublicKey(PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		RSAPrivateCrtKey rsaPrivateKey = (RSAPrivateCrtKey) privateKey;
		RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(rsaPrivateKey.getModulus(), rsaPrivateKey.getPublicExponent());
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(publicKeySpec);
	}
	
	public static KeyInfo retrieveKeyInfo(KeyInfoFactory keyInfoFactory, PublicKey publicKey) throws KeyException {
		KeyValue keyValue = keyInfoFactory.newKeyValue(publicKey);
		return keyInfoFactory.newKeyInfo(Collections.singletonList(keyValue));
	}
	
	public static X509Certificate retrieveCertificate(byte[] keyData, char[] password) throws KeyStoreException, NoSuchProviderException, NoSuchAlgorithmException, CertificateException, IOException {
		ByteArrayInputStream in = new ByteArrayInputStream(keyData);
		KeyStore store = KeyStore.getInstance("pkcs12", "SunJSSE");
		store.load(in, password);
		Enumeration<String> aliases = store.aliases();
		String alias = aliases.nextElement();
		return (X509Certificate) store.getCertificate(alias);
		 
	}
	
	public static KeyInfo retrieveKeyInfoCertificate(X509Certificate certificate, KeyInfoFactory keyInfoFactory) {
		List<X509Certificate> x509Content = new ArrayList<>();  
        x509Content.add(certificate);  
        X509Data x509Data = keyInfoFactory.newX509Data(x509Content);  
        return keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));
	}
	
	public static byte[] getDataFromFile(String filePath) throws IOException {
		File file = new File(filePath);
		byte[] buffer = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
