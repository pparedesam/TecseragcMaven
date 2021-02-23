package ll.entidades.administracion;

public class Operacion {

    private String idOperacion;
    private String nombreOperacion;
    private TipoOperacion objTipoOperacion;
    private String activo;
    private String mostrar;
    private String idUsuario;
    private String nroOrden;
    private String elemento;
    private Operacion objOpeExt;
    private Operacion objOpeAnt;
    private Operacion objOpeExtAnt;
    private int orden;

    public Operacion() {
        objTipoOperacion = new TipoOperacion();
        //objOpeExt = new Operacion();
        //objOpeExtAnt = new Operacion();
        //objOpeAnt = new Operacion();
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(String idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public TipoOperacion getObjTipoOperacion() {
        return objTipoOperacion;
    }

    public void setObjTipoOperacion(TipoOperacion objTipoOperacion) {
        this.objTipoOperacion = objTipoOperacion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getMostrar() {
        return mostrar;
    }

    public void setMostrar(String mostrar) {
        this.mostrar = mostrar;
    }

   

    public String getNroOrden() {
        return nroOrden;
    }

    public void setNroOrden(String nroOrden) {
        this.nroOrden = nroOrden;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public Operacion getObjOpeExt() {
        return objOpeExt;
    }

    public void setObjOpeExt(Operacion objOpeExt) {
        this.objOpeExt = objOpeExt;
    }

    public Operacion getObjOpeAnt() {
        return objOpeAnt;
    }

    public void setObjOpeAnt(Operacion objOpeAnt) {
        this.objOpeAnt = objOpeAnt;
    }

    public Operacion getObjOpeExtAnt() {
        return objOpeExtAnt;
    }

    public void setObjOpeExtAnt(Operacion objOpeExtAnt) {
        this.objOpeExtAnt = objOpeExtAnt;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

}
