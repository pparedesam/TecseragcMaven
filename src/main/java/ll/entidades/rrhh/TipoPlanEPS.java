/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.rrhh;

/**
 *
 * @author AndZuñ
 */
public class TipoPlanEPS {
    private int idEPS;
    private String descripcion;
    private String NroAportes;
    private double SeguroBase;
    private double MontoEmpleador;
    private TipoEPS objTipoEPS;

    public int getIdEPS() {
        return idEPS;
    }

    public void setIdEPS(int idEPS) {
        this.idEPS = idEPS;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNroAportes() {
        return NroAportes;
    }

    public void setNroAportes(String NroAportes) {
        this.NroAportes = NroAportes;
    }

    public double getSeguroBase() {
        return SeguroBase;
    }

    public void setSeguroBase(double SeguroBase) {
        this.SeguroBase = SeguroBase;
    }

    public double getMontoEmpleador() {
        return MontoEmpleador;
    }

    public void setMontoEmpleador(double MontoEmpleador) {
        this.MontoEmpleador = MontoEmpleador;
    }

    public TipoEPS getTipoEPS() {
        return objTipoEPS;
    }

    public void setTipoEPS(TipoEPS tipoEPS) {
        this.objTipoEPS = tipoEPS;
    }
    



}
