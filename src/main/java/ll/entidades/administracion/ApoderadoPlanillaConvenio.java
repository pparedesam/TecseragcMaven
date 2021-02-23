/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.administracion;

import java.util.ArrayList;
import java.util.List;
import ll.entidades.agentes.Maestra;
import ll.entidades.creditos.ProductoCrediticio;

/**
 *
 * @author DAVID
 */
public class ApoderadoPlanillaConvenio {
    
    private String idPersonaApoderado;    
    private String idCodigoPlanilla;
    private String idTipoPlanilla;
    private ProductoCrediticio objCrediticio;   
    private Socio objSocio;
    
    public ApoderadoPlanillaConvenio ()
    {
        objCrediticio = new ProductoCrediticio();      
        objSocio = new Socio();
    }   
    
    public List<Maestra> TipoPlanilla()
    {
        List<Maestra> obj= new ArrayList<Maestra>();

        obj.add(new Maestra(1,"Activo"));
        obj.add(new Maestra(2,"Cesante"));
        return obj;   
    }

    public String getIdPersonaApoderado() {
        return idPersonaApoderado;
    }

    public void setIdPersonaApoderado(String idPersonaApoderado) {
        this.idPersonaApoderado = idPersonaApoderado;
    }

    public String getIdCodigoPlanilla() {
        return idCodigoPlanilla;
    }

    public void setIdCodigoPlanilla(String idCodigoPlanilla) {
        this.idCodigoPlanilla = idCodigoPlanilla;
    }

    public String getIdTipoPlanilla() {
        return idTipoPlanilla;
    }

    public void setIdTipoPlanilla(String idTipoPlanilla) {
        this.idTipoPlanilla = idTipoPlanilla;
    }

    public ProductoCrediticio getObjCrediticio() {
        return objCrediticio;
    }

    public void setObjCrediticio(ProductoCrediticio objCrediticio) {
        this.objCrediticio = objCrediticio;
    }

    public Socio getObjSocio() {
        return objSocio;
    }

    public void setObjSocio(Socio objSocio) {
        this.objSocio = objSocio;
    }
    
}
