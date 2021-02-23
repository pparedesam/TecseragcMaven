/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.administracion;

public class RepresentanteLegal {

 

    private PersonaJuridica objEmpresa;
    private PersonaNatural objRepresentanteLegal;
    private String FechaRegistro;
    private String Usuario;
    private Boolean Activo;
    private CargoRepresentante objCargoRepresentante;
    
    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }
    
    public CargoRepresentante getObjCargoRepresentante() {
        return objCargoRepresentante;
    }

    public void setObjCargoRepresentante(CargoRepresentante objCargoRepresentante) {
        this.objCargoRepresentante = objCargoRepresentante;
    }

    public RepresentanteLegal() {
        objEmpresa = new PersonaJuridica();
        objRepresentanteLegal = new PersonaNatural();
        objCargoRepresentante = new CargoRepresentante();
    }

    public PersonaJuridica getObjEmpresa() {
        return objEmpresa;
    }

    public void setObjEmpresa(PersonaJuridica objEmpresa) {
        this.objEmpresa = objEmpresa;
    }

    public PersonaNatural getObjRepresentanteLegal() {
        return objRepresentanteLegal;
    }

    public void setObjRepresentanteLegal(PersonaNatural objRepresentanteLegal) {
        this.objRepresentanteLegal = objRepresentanteLegal;
    }

    public String getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(String FechaRegistro) {
        this.FechaRegistro = FechaRegistro;
    }

    public Boolean getActivo() {
        return Activo;
    }

    public void setActivo(Boolean Activo) {
        this.Activo = Activo;
    }

}
