package ll.entidades.agentes;

import ll.entidades.administracion.Area;
import ll.entidades.administracion.Oficina;
import ll.entidades.administracion.Puesto;

/**
 *
 * @author paupar
 */
public class Menu {

    private int codigo;
    private String descripcion;
    private String url;
    private int dependiente;
    private int nivel;
    private Oficina objOficina;
    private Area objArea;
    private Puesto objPuesto;
    private int dependiente2;
    private int estado;
    private String descripcionN1;
    private String descripcionN2;
    private String icon;

    public int getDependiente2() {
        return dependiente2;
    }

    public void setDependiente2(int dependiente2) {
        this.dependiente2 = dependiente2;
    }

    public Menu() {

        objOficina = new Oficina();
        objArea = new Area();
        objPuesto = new Puesto();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getDependiente() {
        return dependiente;
    }

    public void setDependiente(int dependiente) {
        this.dependiente = dependiente;
    }

    public Oficina getObjOficina() {
        return objOficina;
    }

    public void setObjOficina(Oficina objOficina) {
        this.objOficina = objOficina;
    }

    public Area getObjArea() {
        return objArea;
    }

    public void setObjArea(Area objArea) {
        this.objArea = objArea;
    }

    public Puesto getObjPuesto() {
        return objPuesto;
    }

    public void setObjPuesto(Puesto objPuesto) {
        this.objPuesto = objPuesto;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDescripcionN1() {
        return descripcionN1;
    }

    public void setDescripcionN1(String descripcionN1) {
        this.descripcionN1 = descripcionN1;
    }

    public String getDescripcionN2() {
        return descripcionN2;
    }

    public void setDescripcionN2(String descripcionN2) {
        this.descripcionN2 = descripcionN2;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
