package ll.comprobantes.electronicos.use;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.crypto.MarshalException;

import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.*;
import javax.xml.crypto.dsig.keyinfo.*;
import javax.xml.crypto.dsig.spec.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class FirmarDocumento {

    private static FirmarDocumento _Instancia;

    private FirmarDocumento() {
    }

    public static FirmarDocumento Instancia() {
        if (_Instancia == null) {
            _Instancia = new FirmarDocumento();
        }
        return _Instancia;
    }

    public static void firmar(String formatoArchivo, String fileNameXMLsf, String fileNameXMLcf,
            String ksTipe, String ksFile, String ksPass, String privateKsPass, String privateKsAlias) {

        try {

            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

            // Create a Reference to the enveloped document (in this case,
            // you are signing the whole document, so a URI of "" signifies
            // that, and also specify the SHA1 digest algorithm and
            // the ENVELOPED Transform.
            Reference ref;

            ref = fac.newReference("", fac.newDigestMethod(DigestMethod.SHA1, null),
                    Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
                    null, null);

            // Create the SignedInfo.
            SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,
                    (C14NMethodParameterSpec) null),
                    fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                    Collections.singletonList(ref));

            // Load the KeyStore and get the signing key and certificate.
            KeyStore ks = KeyStore.getInstance("jks");
            char[] pass = ksPass.toCharArray();

            // SERVICIOS EXCELENTES
            ks.load(new FileInputStream(ksFile), pass);

            KeyStore.PrivateKeyEntry keyEntry
                    = (KeyStore.PrivateKeyEntry) ks.getEntry(privateKsAlias, new KeyStore.PasswordProtection(privateKsPass.toCharArray()));

            X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

            // Create the KeyInfo containing the X509Data.
            KeyInfoFactory kif = fac.getKeyInfoFactory();
            List x509Content = new ArrayList();
            x509Content.add(cert.getSubjectX500Principal().getName());
            x509Content.add(cert);
            X509Data xd = kif.newX509Data(x509Content);

            KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

            // Instantiate the document to be signed.
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(fileNameXMLsf));

            Node content = doc.getElementsByTagName("ext:UBLExtensions").item(0);

            /// ext:UBLExtension
            Element UBLExtension = doc.createElement("ext:UBLExtension");
            content.appendChild(UBLExtension);

            /// NODO ext:ExtensionContent
            Element ExtensionContent = doc.createElement("ext:ExtensionContent");
            UBLExtension.appendChild(ExtensionContent);

            DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), ExtensionContent);

            // Create the XMLSignature, but don't sign it yet.
            XMLSignature signature = fac.newXMLSignature(si, ki, null, "SingFactura", null);

            //Node content2 = doc.getElementsByTagName("Signature").item(0);
            dsc.setDefaultNamespacePrefix("ds");

            // Marshal, generate, and sign the enveloped signature.
            signature.sign(dsc);
            // Create the DOMSignContext by specifying the signing informations: Private Key, Node to be signed, Where to insert the Signature.

            // Output the resulting document.
            OutputStream os = new FileOutputStream(fileNameXMLcf);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            doc.setXmlStandalone(true);
            trans.transform(new DOMSource(doc), new StreamResult(os));

        } catch (IOException | InvalidAlgorithmParameterException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException | CertificateException | MarshalException | XMLSignatureException | ParserConfigurationException | TransformerException | DOMException | SAXException e) {
            e.printStackTrace();
        }

    }

}
