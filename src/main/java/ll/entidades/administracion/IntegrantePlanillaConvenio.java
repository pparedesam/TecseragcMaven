/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.administracion;

import ll.entidades.creditos.ProductoCrediticio;

/**
 *
 * @author DAVID
 */
public class IntegrantePlanillaConvenio {    

   
  
  private ApoderadoPlanillaConvenio objApoderadoPlanillaConvenio;
  private ProductoCrediticio objCrediticio;
  private String prestamo;
  private String aporte;
  private String fondoMortuorio;
  private Socio objSocio;
  
  public IntegrantePlanillaConvenio()
  {
      objApoderadoPlanillaConvenio = new ApoderadoPlanillaConvenio();
      objCrediticio = new ProductoCrediticio();
      objSocio = new Socio();
  }

    public ApoderadoPlanillaConvenio getObjApoderadoPlanillaConvenio() {
        return objApoderadoPlanillaConvenio;
    }

    public void setObjApoderadoPlanillaConvenio(ApoderadoPlanillaConvenio objApoderadoPlanillaConvenio) {
        this.objApoderadoPlanillaConvenio = objApoderadoPlanillaConvenio;
    }

    public ProductoCrediticio getObjCrediticio() {
        return objCrediticio;
    }

    public void setObjCrediticio(ProductoCrediticio objCrediticio) {
        this.objCrediticio = objCrediticio;
    }

    public String getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(String prestamo) {
        this.prestamo = prestamo;
    }

    public String getAporte() {
        return aporte;
    }

    public void setAporte(String aporte) {
        this.aporte = aporte;
    }

    public String getFondoMortuorio() {
        return fondoMortuorio;
    }

    public void setFondoMortuorio(String fondoMortuorio) {
        this.fondoMortuorio = fondoMortuorio;
    }

    public Socio getObjSocio() {
        return objSocio;
    }

    public void setObjSocio(Socio objSocio) {
        this.objSocio = objSocio;
    }

 
    
}
