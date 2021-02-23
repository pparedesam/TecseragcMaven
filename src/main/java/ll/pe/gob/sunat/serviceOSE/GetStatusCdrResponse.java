
package ll.pe.gob.sunat.serviceOSE;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para getStatusCdrResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="getStatusCdrResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="statusCdr" type="{http://service.sunat.gob.pe}statusCdr"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getStatusCdrResponse", propOrder = {
    "statusCdr"
})
public class GetStatusCdrResponse {

    @XmlElement(required = true)
    protected StatusCdr statusCdr;

    /**
     * Obtiene el valor de la propiedad statusCdr.
     * 
     * @return
     *     possible object is
     *     {@link StatusCdr }
     *     
     */
    public StatusCdr getStatusCdr() {
        return statusCdr;
    }

    /**
     * Define el valor de la propiedad statusCdr.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusCdr }
     *     
     */
    public void setStatusCdr(StatusCdr value) {
        this.statusCdr = value;
    }

}
