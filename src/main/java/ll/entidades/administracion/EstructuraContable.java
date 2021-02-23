package ll.entidades.administracion;

public class EstructuraContable {

    private Operacion objOperaciones;
    private Oficina objOficina;
    private Puesto objPuesto;
    private Area objArea;
    private String cuentaCargo;
    private String cuentaAbono;
    private String tipMoneda;
    private String activo;
    private Usuario objUsuario;

    public EstructuraContable() {

        objOperaciones = new Operacion();
        objOficina = new Oficina();
        objPuesto = new Puesto();
        objUsuario = new Usuario();
    }

    public Puesto getObjPuesto() {
        return objPuesto;
    }

    public void setObjPuesto(Puesto objPuesto) {
        this.objPuesto = objPuesto;
    }

    public Area getObjArea() {
        return objArea;
    }

    public void setObjArea(Area objArea) {
        this.objArea = objArea;
    }

    public Operacion getObjOperaciones() {
        return objOperaciones;
    }

    public void setObjOperaciones(Operacion objOperaciones) {
        this.objOperaciones = objOperaciones;
    }

    public Oficina getObjOficina() {
        return objOficina;
    }

    public void setObjOficina(Oficina objOficina) {
        this.objOficina = objOficina;
    }

    public String getCuentaCargo() {
        return cuentaCargo;
    }

    public void setCuentaCargo(String cuentaCargo) {
        this.cuentaCargo = cuentaCargo;
    }

    public String getCuentaAbono() {
        return cuentaAbono;
    }

    public void setCuentaAbono(String cuentaAbono) {
        this.cuentaAbono = cuentaAbono;
    }

    public String getTipMoneda() {
        return tipMoneda;
    }

    public void setTipMoneda(String tipMoneda) {
        this.tipMoneda = tipMoneda;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }

}
